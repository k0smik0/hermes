/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesSampleRoboService.java is part of hermes.
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
package net.iubris.hermes_sample__roboguiced.service;

import java.util.concurrent.Callable;

import net.iubris.hermes.service.AbstractHermesRoboService;
import net.iubris.hermes_sample__roboguiced.controller.SampleController;
import roboguice.util.Ln;
import android.util.Log;

import com.google.inject.Inject;
 

public class HermesSampleRoboService extends AbstractHermesRoboService<HermesSampleRoboService,SampleController> {
	
//	@Inject LocationManager locationManager; // sample injection
	@Inject SampleController controller;
	
	@Override
	public void onCreate() {
		super.onCreate();
Ln.d("onCreate");
/*
		addToOnStartCommand( new Runnable() {
			@Override
			public void run() {
				Log.d(this.getClass().getSimpleName()+" at "+"HermesSampleRoboService@"+HermesSampleRoboService.this.hashCode()+":42", Thread.currentThread().getName());
				Log.d(this.getClass().getSimpleName()+" at "+"HermesSampleRoboService@"+HermesSampleRoboService.this.hashCode()+":43", "first executed");
			}
		});
		addToOnStartCommand( new Runnable() {
			@Override
			public void run() {
				Log.d(this.getClass().getSimpleName()+" at "+"HermesSampleRoboService@"+HermesSampleRoboService.this.hashCode()+":49",Thread.currentThread().getName());
				Log.d(this.getClass().getSimpleName()+" at "+"HermesSampleRoboService@"+HermesSampleRoboService.this.hashCode()+":50", "second executed");
			}
		});
		addToOnStartCommand( new Runnable() {
			@Override
			public void run() {
				Log.d(this.getClass().getSimpleName()+" at "+"HermesSampleRoboService@"+HermesSampleRoboService.this.hashCode()+":56",Thread.currentThread().getName());
				Log.d(this.getClass().getSimpleName()+" at "+"HermesSampleRoboService@"+HermesSampleRoboService.this.hashCode()+":57", "third executed");
			}
		});
		*/
addToOnStartCommand( new Callable<Void>() {
	@Override
	public Void call() {
		Log.d(this.getClass().getSimpleName()+" at "+"HermesSampleRoboService@"+HermesSampleRoboService.this.hashCode()+":42", "current thread is: "+Thread.currentThread().getName());
		Log.d(this.getClass().getSimpleName()+" at "+"HermesSampleRoboService@"+HermesSampleRoboService.this.hashCode()+":43", "first executed");
		return null;
	}
});
addToOnStartCommand( new Callable<Void>() {
	@Override
	public Void call() {
		Log.d(this.getClass().getSimpleName()+" at "+"HermesSampleRoboService@"+HermesSampleRoboService.this.hashCode()+":49","current thread is: "+Thread.currentThread().getName());
		Log.d(this.getClass().getSimpleName()+" at "+"HermesSampleRoboService@"+HermesSampleRoboService.this.hashCode()+":50", "second executed");
		return null;
	}
});
addToOnStartCommand( new Callable<Void>() {
	@Override
	public Void call() {
		Log.d(this.getClass().getSimpleName()+" at "+"HermesSampleRoboService@"+HermesSampleRoboService.this.hashCode()+":56","current thread is: "+Thread.currentThread().getName());
		Log.d(this.getClass().getSimpleName()+" at "+"HermesSampleRoboService@"+HermesSampleRoboService.this.hashCode()+":57", "third executed");
		return null;
	}
});
Ln.d("post onCreate");
	}
	
	
		
	/*
	private SampleController controller = new SampleController() {
		@Override
		public void doSomething() { 
			Toast.makeText(HermesSampleRoboService.this, 
				"Hi, I'm "+this.getClass().getSimpleName()+"\nand\nyou are in:\n"+locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER), 
				Toast.LENGTH_SHORT).show();
		}
	};
	*/

	@Override
	public SampleController getController() {
		return controller;		
	}
	
} 
