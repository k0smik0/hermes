package net.iubris.hermes.connector.asynctask;

import javax.inject.Inject;

import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes.service.HermesService;
import roboguice.util.RoboAsyncTask;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.widget.Toast;

public abstract class HermesConnectingRoboAsyncTask<HS extends Service & HermesService<C>,C> extends RoboAsyncTask<C> {
	@Inject private Connector<HS, C> connector;
	private final ProgressDialog dialog;
	
	protected HermesConnectingRoboAsyncTask(Context context) {
		super(context);
		dialog = new ProgressDialog(context);
	}
	@Override
	protected void onPreExecute() {
		buildDialog();
        dialog.show();
	}
	protected void buildDialog() {
		dialog.setMessage( buildDialogMessage() );
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
	}
	@Override
	final public C call() throws Exception {
		try {
			return connector.getController();
		} catch (ControllerUnavailableException e) {
			onControllerUnavailableException(e);
		}
		return null;
	}
	
	@Override
	final protected void onSuccess(C controller) throws Exception {
		useController(controller);
	};
	
	@Override
	final protected void onFinally() throws RuntimeException {
		if (dialog != null)
			dialog.dismiss();
		super.onFinally();
	}
	
	protected abstract void useController(C controller);
	
	protected String buildDialogMessage() {
		return "Please wait...";
	}
	
	protected void onControllerUnavailableException(ControllerUnavailableException e) {
		Toast.makeText(context, "some error retrieving controller", Toast.LENGTH_SHORT).show();
	}
}