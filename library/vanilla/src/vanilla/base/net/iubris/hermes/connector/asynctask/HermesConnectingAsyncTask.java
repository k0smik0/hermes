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
	final protected C doInBackground(Void... params) {
		try {
			return connector.getController();
		} catch (ControllerUnavailableException e) {
			onException(e);
		}
		return null;
	}
	@Override
	protected void onPostExecute(C controller) {
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