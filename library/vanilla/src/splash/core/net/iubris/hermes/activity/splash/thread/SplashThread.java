/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * SplashThread.java is part of hermes.
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
package net.iubris.hermes.activity.splash.thread;

import net.iubris.hermes.activity.splash.delegate.IHermesSplashDelegate;
import net.iubris.hermes.service.IHermesService;
import android.app.Activity;
import android.app.Service;

public class SplashThread<A extends Activity,HS extends Service & IHermesService<C>,C> extends Thread {
	private final Activity activity;
	private final  IHermesSplashDelegate<A,HS,C> iHermesSplashDelegate;
	private final int minWait;
	
	public SplashThread(Activity activity, IHermesSplashDelegate<A,HS,C> iHermesSplashDelegate, int minWait) {
		this.activity = activity;
		this.iHermesSplashDelegate = iHermesSplashDelegate;
		this.minWait = minWait;
	}
	
	@Override
	public void run() {
		iHermesSplashDelegate.startServiceInBackground();
		iHermesSplashDelegate.doOtherStuffInBackground();
		try {
			try {
				Thread.sleep(minWait);	    
			} catch (InterruptedException e) {  
        	   Thread.interrupted();
			}
			iHermesSplashDelegate.startMainActivity();
			activity.finish();
       } finally {}
	}
}
