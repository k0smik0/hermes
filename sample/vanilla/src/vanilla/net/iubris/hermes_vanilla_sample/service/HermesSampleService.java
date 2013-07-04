/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesSampleService.java is part of 'Hermes'.
 * 
 * 'Hermes' is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * 'Hermes' is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with 'Hermes'; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.hermes_vanilla_sample.service;

import net.iubris.hermes.service.AbstractHermesService;
import net.iubris.hermes_vanilla_sample.controller.SampleController;
import android.location.LocationManager;
import android.widget.Toast;

public class HermesSampleService extends AbstractHermesService<HermesSampleService,SampleController> {
	
	LocationManager locationManager;
	
	@Override
	public void onCreate() {		
		super.onCreate();
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
	}

	// of course, if business logic need you istance service in onCreate, you can 
	private final SampleController exposer = new SampleController() {
		@Override
		public void doSomething() {			
			Toast.makeText(HermesSampleService.this, 
				"Hi, I'm anonymous SampleController"+"\nand\nyou are in:\n"+locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER), 
				Toast.LENGTH_SHORT).show();			
		}
	};	
	
	@Override
	public SampleController getController() {
		return exposer;
	}

}
