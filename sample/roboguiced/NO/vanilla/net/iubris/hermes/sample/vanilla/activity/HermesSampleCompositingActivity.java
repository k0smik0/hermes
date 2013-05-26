package net.iubris.hermes.sample.vanilla.activity;

import net.iubris.hermes.client.wrapper.HermesClientWrapper;
import net.iubris.hermes.sample.controller.HermesSampleController;
import net.iubris.hermes.sample.vanilla.application.HermesSampleApplication;
import net.iubris.hermes.sample.vanilla.service.HermesSampleService;
import android.app.Activity;
import android.os.Bundle;

public class HermesSampleCompositingActivity extends Activity {

	//vanilla branch version, be careful with classpath
	HermesClientWrapper<HermesSampleApplication,HermesSampleService,HermesSampleController> hermesClientWrapper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		hermesClientWrapper = new HermesClientWrapper<HermesSampleApplication,HermesSampleService, HermesSampleController>((HermesSampleApplication)getApplication(), HermesSampleService.class);
	}		
}
