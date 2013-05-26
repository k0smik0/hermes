package net.iubris.hermes.connector;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.iubris.hermes.service.HermesService;
import android.app.Application;
import android.app.Service;

//@ContextSingleton
@Singleton
public class RoboConnector<HS extends Service & HermesService<C>, C> extends Connector<HS, C> {

	@Inject
	public RoboConnector(/*Context context*/Application context, Class<HS> serviceClass) {
		super(context, serviceClass);
	}
}
