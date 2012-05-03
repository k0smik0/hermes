/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesMainRoboActivityListener.java is part of hermes.
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
package net.iubris.hermes.activity.main.listener;

import net.iubris.hermes.activity.main.listener.event.EventHandler;
import net.iubris.hermes.service.IHermesService;
import roboguice.activity.event.OnCreateEvent;
import roboguice.activity.event.OnDestroyEvent;
import roboguice.activity.event.OnResumeEvent;
import roboguice.event.Observes;
import roboguice.util.Ln;
import android.app.Service;

import com.google.inject.Inject;

//@ContextSingleton
public class HermesMainRoboActivityListener<HS extends Service & IHermesService<C>, C> 
//extends HermesRoboActivityListener<HS, C> 
{
	
	/*
	private final Context context;
	private final Class<HS> hsServiceClass;
	private final Intent serviceIntent;	
	private final Connector<HS,C> connector;	 
	private final ActivityManager activityManager;
	*/
	
	private final EventHandler<HS,C> eventHandler;
		
	@Inject
	public HermesMainRoboActivityListener(/*Connector<HS,C> connector, Context context, Class<HS> hsServiceClass, ActivityManager activityManager, */EventHandler<HS,C> eventHandler) {
Ln.d("constructor");
		/*this.connector = connector;
		this.context = context;
		this.hsServiceClass = hsServiceClass;
		this.serviceIntent = (new Intent()).setClass(context, hsServiceClass);
		//this.serviceIntent.setClass(context,hsServiceClass);
		this.activityManager = activityManager;*/
		this.eventHandler = eventHandler;
	}

	public void doOnCreate(@Observes OnCreateEvent onCreateEvent) {
		/*if (!isServiceRunning()) {
Ln.d("create: start service");
			context.startService(serviceIntent);
		}*/
		eventHandler.dispatchOnCreate();
	}
	
	public void doOnResume(@Observes OnResumeEvent onResumeEvent) {
		/*if (!connector.isBound() ) {
Ln.d("resume: binding");
			connector.doBindService();
		}*/
		eventHandler.dispatchOnResume();
	}
	
	public void doOnDestroy(@Observes OnDestroyEvent onDestroyEvent) {
		/*
Ln.d("destroying");
Ln.d("destroy: unbind");
		try {
			connector.doUnbindService();
		} catch (NullPointerException e) {
Ln.d("unbinding: npe");
			e.printStackTrace();
		}
Ln.d("destroy: stop service");
		try {///
			context.stopService(serviceIntent);
		} catch (NullPointerException e) {
Ln.d("stopping: npe");
			e.printStackTrace();
		}
		//stopService();
		*/
		doOnDestroy();
	}

	public void dispatchOnBackPressed() {
		doOnDestroy();
	}
	
	private void doOnDestroy() {
		eventHandler.dispatchOnDestroy();
	}

	/*public void stopService() {
		context.stopService(serviceIntent);
	}*/
	
	/*
	private boolean isServiceRunning() {	    
	    for (RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
	        if (hsServiceClass.getName().equals(service.service.getClassName())) {
Ln.d(hsServiceClass + " found is running");	        	
	            return true;
	        }	        
	    }
	    return false;
	}
	*/
	
	
	/*public void doOnPause(@Observes OnPauseEvent onPauseEvent) {
Ln.d("pause");
//				connector.doUnbindService();
	}*/	
}	
