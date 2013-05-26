/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesRoboAsyncTaskSearchAndStartingActivity.java is part of hermes.
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
package net.iubris.hermes.utils.asynctask.start;

import java.util.concurrent.Executor;

import net.iubris.hermes.client.HermesClient;
import net.iubris.hermes.utils.asynctask.HermesRoboAsyncTask;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

public abstract class HermesRoboAsyncTaskStartingActivity
<Result,HC extends Activity & HermesClient<C>,A extends Activity, C>
extends HermesRoboAsyncTask<Result, HC, C> {

	private final Class<A> activityToStartClass;

	protected HermesRoboAsyncTaskStartingActivity(HC context, Class<A> activityToStartClass) {
		super(context);
		this.activityToStartClass = activityToStartClass;
	}
	
    protected HermesRoboAsyncTaskStartingActivity(HC context, Handler handler, Class<A> activityToStartClass) {
        super(context,handler);
        this.activityToStartClass = activityToStartClass;
    }

    protected HermesRoboAsyncTaskStartingActivity(HC context, Handler handler, Executor executor, Class<A> activityToStartClass) {
        super(context, handler, executor);
        this.activityToStartClass = activityToStartClass;
    }

    protected HermesRoboAsyncTaskStartingActivity(HC context, Executor executor, Class<A> activityToStartClass) {
        super(context,executor);
        this.activityToStartClass = activityToStartClass;
    }
	
	protected void startNextActivity() {
		context.startActivity( new Intent(context,activityToStartClass) );
	}
}
