/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesRoboAsyncTask.java is part of hermes.
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
package net.iubris.hermes.utils.asynctask;


import java.util.concurrent.Executor;

import android.app.Activity;
import android.os.Handler;
import net.iubris.hermes.client.HermesClient;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import roboguice.RoboGuice;

public abstract class HermesRoboAsyncTask<ResultT, 
//HA extends HermesClient/*<C, HS>,
//C, 
//HS extends IHermesService<C>*/>
HC extends Activity & HermesClient<C>, C>
//extends HermesAsyncTask<Result,HC,C> {
extends HermesAsyncTask<ResultT,HC,C> implements HermesCallable<ResultT> {

	protected HermesRoboAsyncTask(HC context) {		
		super(context);
        RoboGuice.getInjector(context).injectMembers(this);
	}
	
    protected HermesRoboAsyncTask(HC context, Handler handler) {
        super(context,handler);
        RoboGuice.getInjector(context).injectMembers(this);
    }

    protected HermesRoboAsyncTask(HC context, Handler handler, Executor executor) {
        super(context, handler, executor);
        RoboGuice.getInjector(context).injectMembers(this);
    }

    protected HermesRoboAsyncTask(HC context, Executor executor) {
        super(context,executor);
        RoboGuice.getInjector(context).injectMembers(this);      
    }
	
	/*@Override
	public abstract Result call() throws ControllerUnvailableException;*/
	
	protected  void onException(ControllerUnavailableException e) {		
		//UIUtils.showShortToast(R.string.exception_controller_unavailable,e,context);
	}
	
	/**
	 * invoke "cancel(false)" as default
	 */
	/*
	protected void onActivityDestroy(@Observes OnDestroyEvent ignored ) {
		//future();
		Ln.d("Killing background thread %s", this);
		cancel(false);
	}*/
}
