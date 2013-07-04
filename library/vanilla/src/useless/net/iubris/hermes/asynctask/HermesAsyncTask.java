/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesAsyncTask.java is part of 'Hermes'.
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
package net.iubris.hermes.asynctask;

import java.util.concurrent.Executor;

import repackaged.roboguice.util.SafeAsyncTask;


import net.iubris.hermes.asynctask.HermesCallable;
import net.iubris.hermes.client.HermesClient;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import android.app.Activity;
import android.os.Handler;

public abstract class HermesAsyncTask<ResultT, HC extends Activity & HermesClient<C>,C> extends SafeAsyncTask<ResultT> implements HermesCallable<ResultT> {

	protected final HC context;

	public HermesAsyncTask(HC context) {
		this.context = context;		
	}
    protected HermesAsyncTask(HC context, Handler handler) {
        super(handler);
        this.context = context;      
    }
    protected HermesAsyncTask(HC context, Handler handler, Executor executor) {
        super(handler, executor);
        this.context = context;      
    }
    protected HermesAsyncTask(HC context, Executor executor) {
        super(executor);
        this.context = context;      
    }
	
	protected  void onException(ControllerUnavailableException e) {
		onException(e);		
	}
	
	public HC getContext() {
		return context;
	}	
}
