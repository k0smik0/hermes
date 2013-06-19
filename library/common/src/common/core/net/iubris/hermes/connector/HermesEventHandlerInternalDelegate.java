package net.iubris.hermes.connector;

import javax.inject.Inject;

import net.iubris.hermes.service.HermesService;
import android.app.Service;

public class HermesEventHandlerInternalDelegate<HS extends Service & HermesService<C>, C> {
	
	protected final Connector<HS,C> connector;
	
	@Inject
	public HermesEventHandlerInternalDelegate(Connector<HS, C> connector) {
		this.connector = connector;
	}

	public void dispatchOnStart(){
		if (!connector.isServiceBound() ) {
//Log.d("HermesEventHandlerInternalDelegate:20","dispatchOnStart: "+connector.getContext()+" binding");
			connector.doBindService();
		}
	}
	
	public void dispatchOnBackPressed() {
//Log.d("HermesEventHandlerInternalDelegate:26","dispatchOnBackPressed: "+connector.getContext()+" unbinding");
		connector.doUnbindService();
	}
	
}
