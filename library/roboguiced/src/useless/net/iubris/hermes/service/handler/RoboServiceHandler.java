package net.iubris.hermes.service.handler;

import javax.inject.Inject;

import net.iubris.hermes.service.HermesService;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;

public class RoboServiceHandler<HS extends Service & HermesService<C>, C> extends ServiceHandler<HS, C> {

	@Inject
	public RoboServiceHandler(Context context, Class<HS> hsServiceClass,
			ActivityManager activityManager) {
		super(context, hsServiceClass, activityManager);
	}

}
