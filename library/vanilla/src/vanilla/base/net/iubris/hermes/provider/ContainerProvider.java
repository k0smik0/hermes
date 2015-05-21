package net.iubris.hermes.provider;

import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.EventHandler;
import net.iubris.hermes.event.MainEventHandler;
import net.iubris.hermes.provider.exception.HermesProvidingException;
import net.iubris.hermes.service.ContainerService;
import net.iubris.hermes.service.handler.ServiceHandler;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Service;
import android.content.Context;

public class ContainerProvider<HS extends Service & ContainerService<C>, C>
//implements HermesProvider<HS,C> 
{
	
	private static ContainerProvider<?,?> instance;

	@SuppressWarnings("unchecked")
	public static <HS extends Service & ContainerService<C>, C> ContainerProvider<HS, C> getInstance(/*Application application, Class<HS> hermesServiceClass*/) throws HermesProvidingException {
		if (instance==null)
			throw new HermesProvidingException("you must invoke init, first");
//			instance = new HermesProviding<HS, C>(application,hermesServiceClass);
		return (ContainerProvider<HS, C>) instance;
	}
	
	/**
	 * Call init in your Application or in your main activity, within onCreate, as first statement! 
	 * @param application
	 * @param hermesServiceClass
	 */
	public static <HS extends Service & ContainerService<C>, C> void init(Application application, Class<HS> hermesServiceClass) {
		instance = new ContainerProvider<HS, C>(application, hermesServiceClass);
	}
	
	private final Connector<HS, C> connector;
	private final MainEventHandler<HS, C> mainEventHandler;
	private final EventHandler<HS, C> eventHandler;
	
	private ContainerProvider (Application application, Class<HS> hermesServiceClass) {
		connector = new Connector<HS, C>(application, hermesServiceClass );		
		
		eventHandler = new EventHandler<HS, C>(connector);
		
		final ServiceHandler<HS, C> serviceHandler = new ServiceHandler<HS, C>(application, hermesServiceClass, (ActivityManager) application.getSystemService(Context.ACTIVITY_SERVICE));
		mainEventHandler = new MainEventHandler<HS, C>(eventHandler,serviceHandler);
	}

//	@Override
	public Connector<HS, C> getConnector() {
		return connector;
	}

//	@Override
	public MainEventHandler<HS, C> getMainEventHandler() {
		return mainEventHandler;
	}
}
