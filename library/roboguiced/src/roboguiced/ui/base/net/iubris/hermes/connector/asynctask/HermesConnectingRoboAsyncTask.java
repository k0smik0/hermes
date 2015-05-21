/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesConnectingRoboAsyncTask.java is part of 'Hermes'.
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
package net.iubris.hermes.connector.asynctask;

import javax.inject.Inject;

import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.exception.ActorUnavailableException;
import net.iubris.hermes.service.ContainerService;
import roboguice.util.RoboAsyncTask;
import android.app.Service;
import android.content.Context;

public abstract class HermesConnectingRoboAsyncTask<HS extends Service & ContainerService<C>,C> extends RoboAsyncTask<C> {
	@Inject private Connector<HS, C> connector;
//	private final ProgressDialog dialog;
	
	protected HermesConnectingRoboAsyncTask(Context context) {
		super(context);
//		dialog = new ProgressDialog(context);
	}
	/*@Override
	protected void onPreExecute() {
		buildDialog();
        dialog.show();
	}
	protected void buildDialog() {
		dialog.setMessage( buildDialogMessage() );
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
	}*/
	@Override
	final public C call() throws Exception {
		try {
			return connector.getController();
		} catch (ActorUnavailableException e) {
			onControllerUnavailableException(e);
		}
		return null;
	}
	
	@Override
	final protected void onSuccess(C controller) throws Exception {
		useController(controller);
	};
	
	/*@Override
	final protected void onFinally() throws RuntimeException {
		if (dialog != null)
			dialog.dismiss();
		super.onFinally();
	}*/
	
	protected abstract void useController(C controller);
	
	/*protected String buildDialogMessage() {
		return "Please wait...";
	}*/
	
	protected void onControllerUnavailableException(ActorUnavailableException e) {
//		Toast.makeText(context, "some error retrieving controller", Toast.LENGTH_SHORT).show();
	}
}
