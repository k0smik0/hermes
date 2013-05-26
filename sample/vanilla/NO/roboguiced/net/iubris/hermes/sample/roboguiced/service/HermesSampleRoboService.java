/*******************************************************************************
 * Copyleft 2011 Massimiliano Leone - k0smik0@logorroici.org .
 * 
 * HDRoboService.java is part of hermes.
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
package net.iubris.hermes.sample.roboguiced.service;

import roboguice.util.Ln;

import com.google.inject.Inject;

import android.location.LocationManager;
import android.widget.Toast;
import net.iubris.hermes.sample.controller.HermesSampleController;
import net.iubris.hermes.service.HermesRoboService;
 

public class HermesSampleRoboService extends HermesRoboService<HermesSampleRoboService,HermesSampleController> {
	
	@Inject LocationManager locationManager; // sample injection
	
	@Override
	public void onCreate() {
		super.onCreate();
		Ln.d("onCreate");
	}
		
	private HermesSampleController controller = new HermesSampleController() {
		@Override
		public void doSomething() { 
			Toast.makeText(HermesSampleRoboService.this, 
				"Hi, I'm "+this.getClass().getSimpleName()+"\nand\nyou are in:\n"+locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER), 
				Toast.LENGTH_SHORT).show();
		}
	};	

	@Override
	public HermesSampleController getController() {
		return controller;		
	}
	
} 
