package net.iubris.hermes_sample__roboguiced.controller;

import com.google.inject.Inject;

import android.content.Context;
import android.location.LocationManager;
import android.widget.Toast;

public class ConcreteSampleController implements SampleController {
	
	private Context context;
	protected LocationManager locationManager;
	
	@Inject
	public ConcreteSampleController(Context context, LocationManager locationManager) {
		this.context = context;
		this.locationManager = locationManager;
	}
	
	@Override
	public void doSomething() { 
		Toast.makeText(
				context,
			whoIAm()+
				locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER), 
			Toast.LENGTH_SHORT).show();
	}
	
	protected String whoIAm(){
		return "Hi, I'm "+this.getClass().getSimpleName()+"\nand\nyou are in:\n";
	}

}
