package net.iubris.hermes.client.delegate;

import javax.inject.Inject;

import net.iubris.hermes.client.delegate.HermesClientDelegate;
import net.iubris.hermes.connector.RoboConnector;
import net.iubris.hermes.service.HermesService;
import android.app.Service;

public class RoboHermesClientDelegate<HS extends Service & HermesService<C>, C> extends HermesClientDelegate<HS, C> {

	@Inject
	public RoboHermesClientDelegate(RoboConnector<HS, C> connector/*, Context context*/) {
		super(connector/*, context*/);
	}
}
