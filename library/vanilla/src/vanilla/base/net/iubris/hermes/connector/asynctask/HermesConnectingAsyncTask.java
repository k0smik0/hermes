/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesConnectingAsyncTask.java is part of 'Hermes'.
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

import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes.service.HermesService;
import android.app.Service;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public abstract class HermesConnectingAsyncTask<HS extends Service & HermesService<C>,C> extends AsyncTask<Void, Void, C> {
	private final Connector<HS, C> connector;
	private final Context context;
//	private ProgressDialog dialog;
	
	protected HermesConnectingAsyncTask(Connector<HS, C> connector, Context context) {
		this.connector = connector;
		this.context = context;
	}
	@Override
	protected void onPreExecute() {
		/*dialog = new ProgressDialog(context);
//		dialog.setTitle( "Calculating...");
		dialog.setMessage( buildDialogMessage() );
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
		dialog.show();*/
	}
	@Override
	protected final C doInBackground(Void... params) {
		try {
			return connector.getController();
		} catch (ControllerUnavailableException e) {
			onException(e);
		}
		return null;
	}
	@Override
	protected final void onPostExecute(C controller) {
//		if (dialog != null)
//			dialog.dismiss();
		useController(controller);
	}
	
	protected abstract void useController(C controller);
	
	/*protected String buildDialogMessage() {
		return "Please wait...";
	}*/
	
	protected void onException(ControllerUnavailableException e) {
		Toast.makeText(context, "some error retrieving controller", Toast.LENGTH_SHORT).show();
	}
}
