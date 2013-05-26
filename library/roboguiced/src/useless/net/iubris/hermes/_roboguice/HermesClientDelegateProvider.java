package net.iubris.hermes._roboguice;

import javax.inject.Inject;
import javax.inject.Provider;

import net.iubris.hermes.client.delegate.HermesClientDelegate;
import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.RoboConnector;
import net.iubris.hermes.service.HermesService;
import android.app.Service;
import android.content.Context;

public class HermesClientDelegateProvider<HS extends Service & HermesService<C>, C> implements Provider<HermesClientDelegate<HS,C>> {

	private final Context context;
	private final Connector<HS, C> connector;

	@Inject 
	public HermesClientDelegateProvider(Context context, RoboConnector/*Provider*/<HS, C> connector/*Provider*/) {
		this.context = context;
		this.connector = connector/*Provider.get()*/;
	}

	@Override
	public HermesClientDelegate<HS, C> get() {
		return new HermesClientDelegate<HS,C>(connector,context);
	}
}
