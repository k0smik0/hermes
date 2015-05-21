/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * AbstractHermesRoboService.java is part of 'Hermes'.
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
package net.iubris.hermes.service;

import net.iubris.hermes.service.binder.ContainerServiceBinder;
import roboguice.service.RoboService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

abstract public class AbstractRoboContainerService<HS extends Service & ContainerService<C>, C> 
extends RoboService implements ContainerService<C> {

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
	
	//start old way
	protected IBinder binder;


	@Override
	public final IBinder onBind(Intent intent) {
		doOnBind();
		return binder;
	}
	
	@Override
	public void onCreate() {
//Log.d(this.getClass().getSimpleName()+"[AbstractHermesRoboService:62]","start onCreate");
		super.onCreate();
//Log.d(this.getClass().getSimpleName()+"[AbstractHermesRoboService:64]","post super.onCreate");

//Log.d(this.getClass().getSimpleName()+"[AbstractHermesRoboService:69]","binding");
		binder = new ContainerServiceBinder<AbstractRoboContainerService<HS, C>,C>(this);
//Log.d(this.getClass().getSimpleName()+"[AbstractHermesRoboService:71]","post binding");
	}
	
	/*@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
//Log.d(this.getClass().getSimpleName()+"[AbstractHermesRoboService:75]","start onStart");		
//Log.d(this.getClass().getSimpleName()+"[AbstractHermesRoboService:77]","finish onStart");
		return START_STICKY;
	}*/
	
	@Override
	public void onDestroy() {
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
	
}
