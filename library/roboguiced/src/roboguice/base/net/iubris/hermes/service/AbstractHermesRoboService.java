/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesRoboService.java is part of hermes.
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
package net.iubris.hermes.service;

import java.util.ArrayList;
import java.util.List;
import net.iubris.hermes.service.binder.HermesServiceBinder;
import roboguice.service.RoboService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

abstract public class AbstractHermesRoboService<HS extends Service & HermesService<C>, C> 
extends RoboService implements HermesService<C> {

	// new way - not working ;/
	/*
	@Inject private HermesRoboServiceListener<HS, C> hermesRoboServiceListener;
	
	@Override
	public IBinder onBind(Intent intent) {
		doOnBind();
		return hermesRoboServiceListener.dispatchOnBind();
	}
	*/
	// end new way
	
//	@Inject private HermesRoboServiceListener<HS, C> hermesRoboServiceListener;
	
	private List<Runnable> threadsToStart;
//	private List<Callable<Void>> threadsToStart;
	
	//start old way
	protected IBinder binder;

	@Override
	public final IBinder onBind(Intent intent) {
		doOnBind();
		return binder;
	}
	
	@Override
	public void onCreate() {
Log.d(this.getClass().getSimpleName()+"[AbstractHermesRoboService:62]","start onCreate");
		super.onCreate();
Log.d(this.getClass().getSimpleName()+"[AbstractHermesRoboService:64]","post super.onCreate");

		threadsToStart = new ArrayList<Runnable>(0);
//		threadsToStart = new ArrayList<Callable<Void>>(0);
		
Log.d(this.getClass().getSimpleName()+"[AbstractHermesRoboService:69]","binding");
		binder = new HermesServiceBinder<AbstractHermesRoboService<HS, C>,C>(this);
Log.d(this.getClass().getSimpleName()+"[AbstractHermesRoboService:71]","post binding");
	}
	
	@Override
	public final int onStartCommand(Intent intent, int flags, int startId) {
Log.d(this.getClass().getSimpleName()+"[AbstractHermesRoboService:75]","start onStart");		
		TaskOnStart.executeOnStart(threadsToStart);
Log.d(this.getClass().getSimpleName()+"[AbstractHermesRoboService:77]","finish onStart");
		return START_STICKY;
	}
	
	@Override
	public final void onDestroy() {
		binder = null;
	}
	// end old way
	
	
	@Override
	public final boolean onUnbind(Intent intent) {
		doOnUnBind();
		return super.onUnbind(intent);
	}
	
	@Override
	public void doOnBind() {};
	@Override
	public void doOnUnBind() {};
	
	/**
	 * use this method within "onCreate" in order to execute some blocking code<br/>
	 * every invocation add a new Runnable to internal List<Runnable>: <br/>
	 * then, within "onStartCommand", an Executor will executes all threads   
	 * @param runnable
	 */
	@Override
	public final void addToOnStartCommand(Runnable runnable) {
		threadsToStart.add(runnable);
	}
	/*public final void addToOnStartCommand(Callable<Void> callable) {
//		threadsToStart.add(callable);
	}*/
}
