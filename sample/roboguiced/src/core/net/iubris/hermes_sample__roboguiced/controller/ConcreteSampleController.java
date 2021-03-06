/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * ConcreteSampleController.java is part of 'Hermes'.
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
package net.iubris.hermes_sample__roboguiced.controller;


import javax.inject.Inject;
import javax.inject.Singleton;

import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

@Singleton
public class ConcreteSampleController implements SampleController {
	
//	private Context context;
	protected LocationManager locationManager;
	
	@Inject
	public ConcreteSampleController(/*Context context,*/ LocationManager locationManager) {
//		this.context = context;
		this.locationManager = locationManager;
	}
	
	@Override
	public String doSomething() {
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
				
		/*Toast.makeText(
				context,
			s, 
			Toast.LENGTH_SHORT).show();*/
		return s;
	}
	
	protected String whoIAm(){
		return "Hi, I'm "+this.getClass().getSimpleName()+"\nand you are in:\n";
	}

}
