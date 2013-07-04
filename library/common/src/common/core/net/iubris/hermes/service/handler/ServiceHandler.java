/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * ServiceHandler.java is part of 'Hermes'.
 * 
 * 'Hermes' is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * 'Hermes' is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with 'Hermes' ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.hermes.service.handler;

import javax.inject.Inject;

import net.iubris.hermes.service.HermesService;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

public class ServiceHandler<HS extends Service & HermesService<C>, C> {
	
	private final Context context;
	private final Class<HS> hsServiceClass;
	private final Intent serviceIntent;	
	private final ActivityManager activityManager;

	@Inject
	public ServiceHandler(Context context, Class<HS> hsServiceClass, ActivityManager activityManager) {		
		this.context = context;
		this.hsServiceClass = hsServiceClass;
		this.serviceIntent = (new Intent()).setClass(context, hsServiceClass);
		this.activityManager = activityManager;
	}

	public void startService() {		
		if (!isServiceRunning()) {
//Log.d("ServiceHandler:49","start service");
			context.startService(serviceIntent);
		}
	}
	
	public void stopService() {
//Log.d("ServiceHandler:55","stop service");
		context.stopService(serviceIntent);
	}
	
	private boolean isServiceRunning() {
		for (RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
			if (hsServiceClass.getName().equals(service.service.getClassName())) {
//Log.d("ServiceHandler",hsServiceClass + " found is running");
				return true;
			}
		}
		return false;
	}
}
