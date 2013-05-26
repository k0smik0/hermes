package net.iubris.hermes._roboguice;

import javax.inject.Inject;
import javax.inject.Provider;

import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.service.HermesService;
import roboguice.inject.ContextSingleton;
import android.app.Service;
import android.content.Context;

public class ConnectorProvider<HS extends Service & HermesService<C>, C> implements Provider<Connector<HS, C>> {

	private final Context context;
	private final Class<HS> serviceClass;
	
	@Inject
	protected ConnectorProvider(Context context, Class<HS> serviceClass) {
		this.context = context;
		this.serviceClass = serviceClass;
	}

	@Override @ContextSingleton
	public Connector<HS, C> get() {
		return new Connector<HS, C>(context, serviceClass);
	}
}
