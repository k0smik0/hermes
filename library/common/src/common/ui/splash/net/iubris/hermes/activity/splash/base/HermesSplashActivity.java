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

import net.iubris.hermes.service.HermesService;
import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * heavy inspired to RoboSplashActivity (from RoboGuice)
 * @author Massimiliano Leone - k0smik0
 *
 * @param <HS>
 * @param <C>
 */
abstract public class HermesSplashActivity
//<HA extends HermesClient<C, HS>, HS extends IHermesService<C>,C>
</*A extends Activity,*/ HS extends Service & HermesService<C>,C>
extends Activity {
	
	public static String ACTION_POST_SPLASH = "action_post_splash";
	
//	private HermesSplashDelegate<A,HS,C> splashActivityDelegate;
	
	/**
	 * milliseconds
	 */
	protected int minDisplayMs = 1000;

	
	
	/**
	 * ispired by robosplashactivity
	 */
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(getLayoutResID());
		super.onCreate(savedInstanceState);
		
		
		// old
		/*
		splashActivityDelegate = getDelegate();
		
Log.d("HermesSplashActivity:47",""+splashActivityDelegate);
		
//		new SplashThread<A,HS,C>(this, splashActivityDelegate, wait).start();
		
		splashActivityDelegate.startService();
		splashActivityDelegate.doOtherStuff();
		splashActivityDelegate.startMainActivity();
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finish();
		*/
		
		
//		setContentView(getLayoutResID());
		
		final Application app = getApplication();
		
		final long start = System.currentTimeMillis();
		
		
		
		new Thread(){
			public void run() {
				doOtherStuffinBackground(app);
				final long duration = System.currentTimeMillis() - start;
Log.d("HermesSplashActivity:92","duration: "+duration);
				if (duration < minDisplayMs) {
Log.d("HermesSplashActivity:94","sleeping still: "+(minDisplayMs-duration));					
					try {
						Thread.sleep(minDisplayMs - duration);
					} catch (InterruptedException e) {
						Thread.interrupted();
Log.d("HermesSplashActivity:100","interrupted");
					}
				}
				
				startHermesService(); // we execute after sleep
				
				startMainActivity();
				finish();				
			};
		}.start();

		
	}
	/*@Override
	protected void onResume() {
		super.onResume();
		
		final Application app = getApplication();
		startHermesService();
		final long start = System.currentTimeMillis();
		new Thread(){
			public void run() {
				doOtherStuffinBackground(app);
//				pause();
				final long duration = System.currentTimeMillis() - start;
Log.d("HermesSplashActivity:92","duration: "+duration);
				if (duration < minDisplayMs) {
Log.d("HermesSplashActivity:94","sleeping still: "+(minDisplayMs-duration));					
					try {
						Thread.sleep(minDisplayMs - duration);
					} catch (InterruptedException e) {
						Thread.interrupted();
Log.d("HermesSplashActivity:100","interrupted");
					}
				}
			};
		}.run();

		startMainActivity();
		finish();
	}*/
	
	/*private void pause() {
		final long duration = System.currentTimeMillis() - start;
		if (duration < minDisplayMs) {
			try {
				Thread.sleep(minDisplayMs - duration);
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
		}
	}*/
	
//	protected abstract HermesSplashDelegate<A,HS,C> getDelegate();
	
	
	
	public void startHermesService() {		
		startService( new Intent(this, getServiceToStart()) );
	}
	
	public void startMainActivity() {
		final Intent startIntent = new Intent(this,getActivityToStart());
		startIntent.setAction(HermesSplashActivity.ACTION_POST_SPLASH);
		startIntent.setFlags(getLauncherMode());
		startActivity(startIntent);
	}
	
	public void doOtherStuffinBackground(Application app) {}
	
	protected abstract int getLayoutResID();
	protected abstract int getLauncherMode();
	protected abstract Class<HS> getServiceToStart();
	protected abstract Class<? extends Activity> getActivityToStart();
	
	
	
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
