/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * Connector.java is part of hermes.
 * 
 * hermes is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * hermes is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with hermes ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.hermes.connector;


import java.util.concurrent.CountDownLatch;

import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes.service.IHermesService;
import net.iubris.hermes.service.binder.HermesServiceBinder;
import net.iubris.hermes.service.exposer.ControllerExposer;
import roboguice.util.Ln;
import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.google.inject.Inject;
import com.google.inject.Singleton;

//@ContextSingleton
@Singleton
public class Connector<HS extends Service & IHermesService<C>, C> /*implements ObserverConnector*/ {

	//private static final long CONNECTION_TIMEOUT = 3 * 1000;
	private final Context context;
	private final Class<HS> serviceClass;
	private HermesServiceConnection serviceConnection = new HermesServiceConnection();
	
	private CountDownLatch countDownLatch = null;
	private HS serviceBound = null;
	
	@Inject
	public Connector(/*Context context,*/
		    Application application,
			Class<HS> serviceClass/*, 
			HermesServiceConnection<HS,C> serviceConnection*/) {

		this.context = application;
Ln.d("context caller: "+context);		
		this.serviceClass = serviceClass;
		//this.serviceConnection = serviceConnection;
	}
	
	public void doBindService() {
Ln.d("binding");
		countDownLatch = new CountDownLatch(1);
Ln.d("countdown = "+countDownLatch.getCount());
		final Intent intent = new Intent();
		intent.setClass(context, serviceClass);
		/*serviceIsBound =*/ context.bindService( intent, 
				serviceConnection, /*Context.BIND_AUTO_CREATE);*/ 0);
		//serviceConnection.register(this);
	}
	
	public void doUnbindService() {
Ln.d("unbinding");				
		context.unbindService(serviceConnection);		
//Ln.d("unbounded");
	}
	
	public boolean isBound() {
		return serviceBound != null;
	}	
	
	public ControllerExposer<C> getControllerExposerService() throws ControllerUnavailableException {		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return serviceBound;
        //return serviceConnection.getService();
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
			Connector.this.serviceBound = ((HermesServiceBinder<HS,C>)service).getService(); // cast
			Connector.this.countDownLatch.countDown();			
Ln.d("connected");	
Ln.d("countdown = "+countDownLatch.getCount());
		}
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Connector.this.serviceBound = null;
Ln.d("disconnected: serviceBound null");			
			Connector.this.countDownLatch = null;
Ln.d("disconnected: countDownLatch null");
Ln.d("disconnected");
		}		
	}
}
