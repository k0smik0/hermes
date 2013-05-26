package net.iubris.hermes._roboguice;

import javax.inject.Inject;
import javax.inject.Provider;

import android.app.Service;

import net.iubris.hermes.activity.main.event.MainEventHandler;
import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.RoboConnector;
import net.iubris.hermes.service.HermesService;
import net.iubris.hermes.service.handler.ServiceHandler;

public class MainEventHandlerProvider<HS extends Service & HermesService<C>, C> implements Provider<MainEventHandler<HS,C>> {

	private final ServiceHandler<HS, C> serviceHandler;
	private final Connector<HS, C> connector;
	
	@Inject
	public MainEventHandlerProvider(ServiceHandlerProvider<HS, C> serviceHandlerProvider, RoboConnector/*Provider*/<HS, C> connector/*Provider*/) {
		this.serviceHandler = serviceHandlerProvider.get();
		this.connector = connector/*Provider.get()*/;
	}

	@Override
	public MainEventHandler<HS,C> get() {
		return new MainEventHandler<HS, C>(connector, serviceHandler);
	}

}
