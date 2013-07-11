package net.iubris.hermes_sample__vanilla.controller;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

public class ConcreteSampleController implements SampleController {
	
	private final LocationManager locationManager;
	
	public ConcreteSampleController(LocationManager locationManager) {
		this.locationManager = locationManager;
	}

	@SuppressLint("InlinedApi")
	@Override
	public String doSomething() {			
		/*Toast.makeText(HermesSampleService.this, 
			"Hi, I'm anonymous SampleController"+"\nand\nyou are in:\n"+locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER), 
			Toast.LENGTH_SHORT).show();*/
		Location useLocation = null;
		Location lastGPSKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (lastGPSKnownLocation!=null) {
			Log.d("ConcreteController:47","use gps location");
			useLocation = lastGPSKnownLocation;
		} else {
			Location lastNetworkKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if (lastNetworkKnownLocation!=null) {
				Log.d("ConcreteController:52","use network location");
				useLocation = lastNetworkKnownLocation;
			} else {
				Location lastPassiveKnownLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
				Log.d("ConcreteController:56","try to use passive location");
				useLocation = lastPassiveKnownLocation;
			}
		}
		String s = whoIAm()+ ((useLocation!=null)? useLocation: "sorry, no location found");
		return s;
	}
	
	private String whoIAm(){
		return "Hi, I'm "+this.getClass().getSimpleName()+"\nand you are in:\n";
	}

}
