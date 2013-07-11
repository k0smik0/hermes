package net.iubris.hermes.provider;

import android.app.ActivityManager;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.HermesEventHandler;
import net.iubris.hermes.event.HermesMainEventHandler;
import net.iubris.hermes.provider.exception.HermesProvidingException;
import net.iubris.hermes.service.HermesService;
import net.iubris.hermes.service.handler.ServiceHandler;

public class HermesProvider<HS extends Service & HermesService<C>, C>
//implements HermesProvider<HS,C> 
{
	
	private static HermesProvider<?,?> instance;

	@SuppressWarnings("unchecked")
	public static <HS extends Service & HermesService<C>, C> HermesProvider<HS, C> getInstance(/*Application application, Class<HS> hermesServiceClass*/) throws HermesProvidingException {
		if (instance==null)
			throw new HermesProvidingException("you must invoke init, first");
//			instance = new HermesProviding<HS, C>(application,hermesServiceClass);
		return (HermesProvider<HS, C>) instance;
	}
	
	/**
	 * Call init in your Application or in your main activity, within onCreate, as first statement! 
	 * @param application
	 * @param hermesServiceClass
	 */
	public static <HS extends Service & HermesService<C>, C> void init(Application application, Class<HS> hermesServiceClass) {
		instance = new HermesProvider<HS, C>(application, hermesServiceClass);
	}
	
	private final Connector<HS, C> connector;
	private final HermesMainEventHandler<HS, C> mainEventHandler;
	private final HermesEventHandler<HS, C> eventHandler;
	
	private HermesProvider (Application application, Class<HS> hermesServiceClass) {
		connector = new Connector<HS, C>(application, hermesServiceClass );		
		
		eventHandler = new HermesEventHandler<HS, C>(connector);
		
		final ServiceHandler<HS, C> serviceHandler = new ServiceHandler<HS, C>(application, hermesServiceClass, (ActivityManager) application.getSystemService(Context.ACTIVITY_SERVICE));
		mainEventHandler = new HermesMainEventHandler<HS, C>(eventHandler,serviceHandler);
	}

//	@Override
	public Connector<HS, C> getConnector() {
		return connector;
	}

//	@Override
	public HermesMainEventHandler<HS, C> getMainEventHandler() {
		return mainEventHandler;
	}
}
