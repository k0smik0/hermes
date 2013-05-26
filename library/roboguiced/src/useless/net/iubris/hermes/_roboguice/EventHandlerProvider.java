package net.iubris.hermes._roboguice;

import javax.inject.Inject;
import javax.inject.Provider;

import net.iubris.hermes.activity.main.event.EventHandler;
import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.RoboConnector;
import net.iubris.hermes.service.HermesService;
import android.app.Service;

public class EventHandlerProvider<HS extends Service & HermesService<C>, C> implements Provider<EventHandler<HS,C>> {
	
	private final Connector<HS, C> connector;
	
	@Inject
	public EventHandlerProvider(RoboConnector/*Provider*/<HS, C> connector/*Provider*/) {
		this.connector = connector/*Provider.get()*/;
	}
	
	@Override
	public EventHandler<HS,C> get() {
		return new EventHandler<HS, C>(connector);
	}

}
