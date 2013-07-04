package net.iubris.hermes_sample__roboguiced.controller;


import javax.inject.Inject;
import javax.inject.Singleton;

import android.location.Location;
import android.location.LocationManager;

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
		Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		String s = whoIAm()+ ((lastKnownLocation!=null)? lastKnownLocation: "sorry, no gps location found");
				
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
