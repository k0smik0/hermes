/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * Connector.java is part of 'Hermes'.
 * 
 * 'Hermes' is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * 'Hermes' is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with 'Hermes' ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.hermes.connector;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes.service.HermesService;
import net.iubris.hermes.service.binder.HermesServiceBinder;
import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

@Singleton
public class Connector<HS extends Service & HermesService<C>, C> {

	private final Context context;
	private final Class<HS> serviceClass;
	private final HermesServiceConnection serviceConnection = new HermesServiceConnection();
	
	private CountDownLatch countDownLatch = null;
	private static final int CONNECTION_TIMEOUT = 4; // 4 second -> use seconds as timeunit!
	private HS controllerExposerService = null;
	private boolean binding = false;
	
	@Inject
	public Connector(Application applicationContext, Class<HS> serviceClass) {
		this.context = applicationContext;
//Log.d("Connector:55",""+this);		
//Log.d("Connector:56","context is: "+applicationContext.getClass().getSimpleName()+context);
//Log.d("Connector",context caller: "+context);
		this.serviceClass = serviceClass;
		//this.serviceConnection = serviceConnection;
	}
	
	public Context getContext() {
		return context;
	}
	
	protected boolean doBindService() {
		binding  = true;
//Log.d("Connector:67", context+" binding");
		countDownLatch = new CountDownLatch(1);
//Log.d("Connector:69","countdown = "+countDownLatch.getCount());
		final Intent intent = new Intent();
		intent.setClass(context, serviceClass);
		/*serviceIsBound =*/ 
		return context.bindService( intent, serviceConnection, /*Context.BIND_AUTO_CREATE);*/ 0);
//		serviceConnection.register(this);		
	}
	
	protected void doUnbindService() {
//Log.d("Connector:79",context+" unbinding?");
		if (isServiceBound()) {
//Log.d("Connector:81","yes is bounded with "+context+" -> unbinding");
			context.unbindService(serviceConnection);
//Log.d("Connector:83","unbounded from "+context);
		}
	}
	
	public boolean isServiceBound() {
		return controllerExposerService != null;
	}
	
	public C getController() throws ControllerUnavailableException {
		if (!isServiceBound()) {
//Log.d("Connector:94","getting service but it is not bounded! binding...");
			if (!binding)
				doBindService();
//			else  
//Log.d("Connector:98","it's already binding...");
		}
		try {
			boolean await = countDownLatch.await(CONNECTION_TIMEOUT,TimeUnit.SECONDS);
			if (!await)
				throw new ControllerUnavailableException("too long await - no such controller available");
		} catch (InterruptedException e) {
//			e.printStackTrace();
			throw new ControllerUnavailableException(e);
		}
//Log.d("Connector:108","countdown = "+countDownLatch.getCount());
		C controller = controllerExposerService.getController();
//Log.d("Connector:110","returning "+controller.getClass().getSimpleName()+"@"+controller.hashCode());
		return controller;
//		return serviceConnection.getService();
	}

	/*
	@Override
	public void doOnConnected() {
		countDownLatch.countDown();
	}

	@Override
	public void doOnDisconnected() {
	}*/
	
	/*
	private boolean checkServiceIsRunning() {		
	    for (RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
Ln.d(service);	    	
	        if ("net.iubris.genziana.service.GenzianaService".equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}*/
	
	private class HermesServiceConnection implements ServiceConnection {		
		@SuppressWarnings("unchecked")
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Connector.this.controllerExposerService = ((HermesServiceBinder<HS,C>)service).getService(); // cast
//Log.d("HermesServiceConnection:140",context+" connected to "+service);
			binding = false;
			Connector.this.countDownLatch.countDown();		
		}
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Connector.this.controllerExposerService = null;
//Log.d("HermesServiceConnection:142",name.getShortClassName()+"disconnected - serviceBound null");
			Connector.this.countDownLatch = null;			
//Log.d("HermesServiceConnection:144",name.getShortClassName()+"disconnected - countDownLatch null");
//Log.d("HermesServiceConnection:145","disconnected");
		}		
	}

}
