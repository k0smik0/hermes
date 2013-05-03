/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesSplashActivity.java is part of hermes.
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
package net.iubris.hermes.activity.splash.base;

import net.iubris.hermes.activity.splash.delegate.HermesSplashDelegate;
import net.iubris.hermes.activity.splash.thread.SplashThread;
import net.iubris.hermes.service.HermesService;
import android.app.Activity;
import android.app.Service;
import android.os.Bundle;

abstract public class AbstractHermesSplashActivity
//<HA extends HermesClient<C, HS>, HS extends IHermesService<C>,C>
<A extends Activity, HS extends Service & HermesService<C>,C>
extends Activity {
	
	private HermesSplashDelegate<A,HS,C> splashActivityDelegate;
	
	/**
	 * milliseconds
	 */
	protected int wait = 1500;
	
	/**
	 * ispired by robosplashactivity
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		splashActivityDelegate = getDelegate();		
		
		new SplashThread<A,HS,C>(this, splashActivityDelegate, wait).start();
	}
	
	protected abstract HermesSplashDelegate<A,HS,C> getDelegate();
	
	/**
	 * from RoboSplashActivity
	 * @param app
	 */	
	/*
	@Override
	public void doOtherStuffInBackground() {
		//startServiceInBackground();
	}
	
	public void startServiceInBackground() {		
		startService( new Intent(this, splashActivityDelegate.getServiceToStart()) );
	}
	
	public void startMainActivity() {
		final Intent startIntent = new Intent(this,splashActivityDelegate.getActivityToStart());
		startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startIntent);
	}*/
}
