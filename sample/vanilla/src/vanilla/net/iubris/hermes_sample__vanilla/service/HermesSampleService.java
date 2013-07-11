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
package net.iubris.hermes_sample__vanilla.service;

import net.iubris.hermes.service.AbstractHermesService;
import net.iubris.hermes_sample__vanilla.controller.ConcreteSampleController;
import net.iubris.hermes_sample__vanilla.controller.SampleController;
import android.location.LocationManager;
import android.util.Log;

public class HermesSampleService extends AbstractHermesService<HermesSampleService,SampleController> {
	
	LocationManager locationManager;
	private ConcreteSampleController concreteSampleController;
	
	@Override
	public void onCreate() {		
		super.onCreate();
		concreteSampleController = new ConcreteSampleController((LocationManager) getSystemService(LOCATION_SERVICE));
	}

	/*
	private final SampleController exposer = new SampleController() {
		@SuppressLint("InlinedApi")
		@Override
		public String doSomething() {			
//			Toast.makeText(HermesSampleService.this, 
//				"Hi, I'm anonymous SampleController"+"\nand\nyou are in:\n"+locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER), 
//				Toast.LENGTH_SHORT).show();
			Location useLocation = null;
			Location lastGPSKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (lastGPSKnownLocation!=null) {
				Log.d("ConcreteController:47","using gps location");
				useLocation = lastGPSKnownLocation;
			} else {
				Location lastNetworkKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				if (lastNetworkKnownLocation!=null) {
					Log.d("ConcreteController:52","using network location");
					useLocation = lastNetworkKnownLocation;
				} else {
					Location lastPassiveKnownLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
					Log.d("ConcreteController:56","using passive location");
					useLocation = lastPassiveKnownLocation;
				}
			}
			String s = whoIAm()+ ((useLocation!=null)? useLocation: "sorry, no location found");
			return s;
		}
		
		private String whoIAm(){
			return "Hi, I'm "+this.getClass().getSimpleName()+"\nand you are in:\n";
		}
	};*/	
	
	@Override
	public SampleController getController() {
		return concreteSampleController;
	}
	
	@Override
	public void doOnBind() {
		Log.d(getClass().getSimpleName()+":36","onBind");
	}

}
