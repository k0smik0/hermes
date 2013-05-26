package net.iubris.hermes.event;

import javax.inject.Inject;

import net.iubris.hermes.connector.RoboConnector;
import net.iubris.hermes.service.HermesService;
import android.app.Service;

public class RoboHermesEventHandler<HS extends Service & HermesService<C>, C> extends HermesEventHandler<HS, C> {
	@Inject
	public RoboHermesEventHandler(RoboConnector<HS, C> connector) {
		super(connector);
	}
}
