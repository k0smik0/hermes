package net.iubris.hermes.connector;

import javax.inject.Inject;

import net.iubris.hermes.service.HermesService;
import android.app.Service;

public class RoboHermesEventHandlerInternalDelegate<HS extends Service & HermesService<C>, C> extends HermesEventHandlerInternalDelegate<HS, C> {

	@Inject
	public RoboHermesEventHandlerInternalDelegate(RoboConnector<HS, C> connector) {
		super(connector);
	}
}
