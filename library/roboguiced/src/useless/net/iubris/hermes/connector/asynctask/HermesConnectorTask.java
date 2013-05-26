/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesConnectorTask.java is part of hermes.
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
package net.iubris.hermes.connector.asynctask;

import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import roboguice.util.Ln;
import roboguice.util.SafeAsyncTask;
import android.app.ProgressDialog;
import android.content.Context;

public class HermesConnectorTask<C> extends SafeAsyncTask<C> {

	private final Connector<?, C> connector;
	private final ProgressDialog dialog;
	
	public HermesConnectorTask(Context context,Connector<?, C> connector){
		this.connector = connector;
		this.dialog = new ProgressDialog(context);	
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
	}
	
	@Override
	protected void onPreExecute() throws Exception {
		if (!connector.isBound()) {	        
	        dialog.show();
		}
Ln.d("is bound");	
	}
	
	protected void onSuccess(C t) throws Exception {
		if (dialog != null)
			dialog.dismiss();
	};
	
	@Override
	public C call() throws ControllerUnavailableException {		
		return connector.getControllerExposerService().getController();		
	}
		
	/**
	 * Calls onException(e) as default, and there calling onThrowable(e) so Log.d.<br/>
	 * So, in UI, you could show a toast as: <br/> "something heavy wrong:<br/>please re-press button" 
	 * @param e
	 */
	protected void onException(ControllerUnavailableException e) {		
		onException(e);
	}

}
