/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * AbstractHermesSplashActivityDelegate.java is part of hermes.
 * 
 * hermes is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * hermes is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with hermes ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.hermes.activity.splash.delegate.base;

import net.iubris.hermes.activity.splash.delegate.HermesSplashDelegate;
import net.iubris.hermes.service.HermesService;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;

public abstract class AbstractHermesSplashActivityDelegate 
//<HA extends HermesClient<C,HS>, HS extends IHermesService<C>, C> 
<A extends Activity, HS extends Service & HermesService<C>, C>
implements HermesSplashDelegate<A,HS,C> {

	private final Class<A> activityClass;
	private final Class<HS> serviceClass;
	private final Activity context;

	//@Inject
	public AbstractHermesSplashActivityDelegate(Activity activity, 
			Class<A> activityClass, 
			Class<HS> serviceClass) {
		this.context = activity;
		this.activityClass = activityClass;
		this.serviceClass = serviceClass;
	}
	
	@Override
	public Class<HS> getServiceToStart() {
		return serviceClass;
	}

	@Override
	public Class<A> getActivityToStart() {
		return activityClass;
	}
		
	@Override
	public void startServiceInBackground() {		
		context.startService( new Intent(context, getServiceToStart()) );
	}
	
	@Override
	public void startMainActivity() {
		final Intent startIntent = new Intent(context,getActivityToStart());
		startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(startIntent);
	}
	
	@Override
	public void doOtherStuffInBackground() {}
}
