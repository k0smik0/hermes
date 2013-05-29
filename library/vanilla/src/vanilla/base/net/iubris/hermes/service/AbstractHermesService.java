/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesService.java is part of hermes.
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
import net.iubris.hermes.service.binder.HermesServiceBinder;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

abstract public class AbstractHermesService<HS extends Service & HermesService<C>, C> 
extends Service implements HermesService<C> {

	protected IBinder binder;
	private ArrayList<Runnable> tasksToStart;
	private ArrayList<Runnable> tasksToStop;
	
	@Override
	public final IBinder onBind(Intent intent) {
		doOnBind();
		return binder;
	}
	
	@Override
	public final boolean onUnbind(Intent intent) {
		doOnUnBind();
		return super.onUnbind(intent);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		binder = new HermesServiceBinder<AbstractHermesService<HS,C>,C>(this);
		tasksToStart = new ArrayList<Runnable>(0);
		tasksToStop = new ArrayList<Runnable>(0);
	}
	
	@Override
	public final int onStartCommand(Intent intent, int flags, int startId) {
		SmoothTasks.execute(tasksToStart);
		return START_STICKY;
	}
	
	@Override
	public final void onDestroy() {
		SmoothTasks.execute(tasksToStop);
		binder = null;
	}
	
	@Override
	public void doOnBind() {}	
	@Override
	public void doOnUnBind() {}
	
	/**
	 * use this method within "onCreate" in order to execute some blocking code<br/>
	 * every invocation add a new Runnable to internal List<Runnable>: <br/>
	 * then, within "onStartCommand", an Executor will executes all threads   
	 * @param runnable
	 */
	@Override
	public final void addToOnStartCommand(Runnable runnable) {
		tasksToStart.add(runnable);
	}
	@Override
	public final void addToOnDestroy(Runnable runnable) {
		tasksToStop.add(runnable);
	}
}
