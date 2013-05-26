/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesConnectorAsyncTask.java is part of hermes.
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
/**
 * old version, SafeAsyncTask based
 */
package net.iubris.hermes.connector.asynctask;


import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class HermesConnectorAsyncTask<C> extends AsyncTask<Void, Void, C> {
	
	private final Context context;
	private final Connector<?, C> connector;
	private ProgressDialog dialog;
	
	public HermesConnectorAsyncTask(Connector<?, C> connector, Context context){
		this.connector = connector;
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		if (!connector.isBound()) {
			dialog = new ProgressDialog(context);
	        //dialog.setTitle( "Calculating...");
	        dialog.setMessage("Please wait...");
	        dialog.setIndeterminate(true);
	        dialog.show();
		}
//Ln.d("is bound");
	}
	@Override
	protected void onPostExecute(C result) {
		if (dialog != null)
			dialog.dismiss();		
	};

	@Override
	protected C doInBackground(Void... params) {
		try {
			return connector.getControllerExposerService().getController();
		} catch (ControllerUnavailableException e) {
//			e.printStackTrace();
		}
		return null;		
	}
	
}
