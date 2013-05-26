package net.iubris.hermes._roboguice;

import javax.inject.Inject;
import javax.inject.Provider;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;

import net.iubris.hermes.service.HermesService;
import net.iubris.hermes.service.handler.ServiceHandler;

public class ServiceHandlerProvider<HS extends Service & HermesService<C>, C> implements Provider<ServiceHandler<HS,C>> {

	private Context context;
	private Class<HS> hsServiceClass;
	private ActivityManager activityManager;
	
	@Inject
	public ServiceHandlerProvider(Context context, Class<HS> hsServiceClass, ActivityManager activityManager) {
		this.context = context;
		this.hsServiceClass = hsServiceClass;
		this.activityManager = activityManager;
	}
	
	@Override
	public ServiceHandler<HS,C> get() {
		return new ServiceHandler<HS, C>(context, hsServiceClass, activityManager);
	}



}
