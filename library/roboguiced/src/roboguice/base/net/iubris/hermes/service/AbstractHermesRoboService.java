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

import net.iubris.hermes.service.binder.HermesServiceBinder;
import roboguice.service.RoboService;
import roboguice.util.Ln;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

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
	
	//start old way
	protected IBinder binder;

	@Override
	public final IBinder onBind(Intent intent) {
		doOnBind();
		return binder;
	}
	
	@Override
	public void onCreate() {
Ln.d(this.getClass().getSimpleName()+": start onCreate");
		super.onCreate();
Ln.d("HermesServiceListener: post super.onCreate");		
		
Ln.d(this.getClass().getSimpleName()+": binding");
		binder = new HermesServiceBinder<AbstractHermesRoboService<HS, C>,C>(this);
Ln.d(this.getClass().getSimpleName()+": bounded");
	}
	
	/*@Override
	public void onStart(Intent intent, int startId) {
Ln.d(this.getClass().getSimpleName()+": start onStart");
		super.onStart(intent, startId);

Ln.d("HermesServiceListener: post super.onStart");		
		
Ln.d(this.getClass().getSimpleName()+": binding");
		binder = new HermesServiceBinder<AbstractHermesRoboService<HS, C>,C>(this);
Ln.d(this.getClass().getSimpleName()+": bounded");
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
