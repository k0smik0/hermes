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

import net.iubris.hermes.asynctask.HermesCallable;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import roboguice.util.RoboAsyncTask;
import android.content.Context;
import android.os.Handler;

public abstract class HermesRoboAsyncTask
//<ResultT, HC extends Activity & HermesClient<C>, C>
<ResultT/*,C, HS extends Service & IHermesService<C>*/>
extends RoboAsyncTask<ResultT> implements /*HermesClient<C>,*/ HermesCallable<ResultT> {

	//@Inject Connector<HS, C> connector;
	
	protected HermesRoboAsyncTask(Context context) {	
		super(context);
	}	
    protected HermesRoboAsyncTask(Context context, Handler handler) {
        super(context,handler);
    }
    protected HermesRoboAsyncTask(Context context, Handler handler, Executor executor) {
        super(context, handler, executor);
    }
    protected HermesRoboAsyncTask(Context context, Executor executor) {
        super(context,executor);     
    }			
	protected  void onException(ControllerUnavailableException e) {
		onException(e);
		//UIUtils.showShortToast(R.string.exception_controller_unavailable,e,context);
	}	
	/*
	@Override
	public C getController() {		
		try {
			return connector.getControllerExposerService().getController();
		} catch (ControllerUnavailableException e) {
			onException(e);
			e.printStackTrace();
		}
		return null;
	}*/
}
