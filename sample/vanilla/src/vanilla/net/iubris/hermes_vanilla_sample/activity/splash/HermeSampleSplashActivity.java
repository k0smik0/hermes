package net.iubris.hermes_vanilla_sample.activity.splash;

import net.iubris.hermes_vanilla_sample.activity.main.HermesSampleMainActivity;
import net.iubris.hermes_vanilla_sample.controller.SampleController;
import net.iubris.hermes_vanilla_sample.service.HermesSampleService;

/*
 * in order to use this class, you need hermes-common-splash.jar from library/common<br/>
 * then, declare this as main launcher activity in AndroidManifest.xml (anyway: HermesSampleMainActivity must be "singleTask") 
 */
public class HermeSampleSplashActivity 
extends AbstractHermesSplashActivity<HermesSampleMainActivity,HermesSampleService,SampleController> {

	@Override
	protected HermesSplashDelegate<HermesSampleMainActivity, HermesSampleService, SampleController> getDelegate() {
		return	new AbstractHermesSplashActivityDelegate<HermesSampleMainActivity, HermesSampleService, SampleController>(this,HermesSampleMainActivity.class,HermesSampleService.class) {
			@Override
			public int getLayoutResID() {
				return 0;
			}
		};
	}	
}
