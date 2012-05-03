package net.iubris.hermes.sample.vanilla.activity.splash;

import net.iubris.hermes.activity.splash.HermesSplashActivity;
import net.iubris.hermes.activity.splash.delegate.IHermesSplashDelegate;
import net.iubris.hermes.activity.splash.delegate.base.AbstractHermesSplashActivityDelegate;
import net.iubris.hermes.sample.controller.SampleController;
import net.iubris.hermes.sample.vanilla.activity.main.HermesSampleMainActivity;
import net.iubris.hermes.sample.vanilla.service.HermesSampleService;


public class HermeSampleSplashActivity 
extends HermesSplashActivity<HermesSampleMainActivity,HermesSampleService,SampleController> {

	@Override
	protected IHermesSplashDelegate<HermesSampleMainActivity, HermesSampleService, SampleController> getDelegate() {
		return	new AbstractHermesSplashActivityDelegate<HermesSampleMainActivity, HermesSampleService, SampleController>(this,HermesSampleMainActivity.class,HermesSampleService.class) {
			@Override
			public int getLayoutResID() {
				return 0;
			}
			@Override
			public void doOtherStuffInBackground() {}
		};
	}	
}
