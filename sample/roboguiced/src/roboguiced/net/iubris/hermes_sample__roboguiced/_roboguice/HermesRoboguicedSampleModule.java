package net.iubris.hermes_sample__roboguiced._roboguice;

import net.iubris.hermes_sample__roboguiced.controller.ConcreteSampleController;
import net.iubris.hermes_sample__roboguiced.controller.SampleController;
import net.iubris.hermes_sample__roboguiced.service.HermesSampleRoboService;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class HermesRoboguicedSampleModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(SampleController.class).to(ConcreteSampleController.class);
	}
	
	@Provides
	public Class<HermesSampleRoboService> providesHermesSampleRoboService() {
		return HermesSampleRoboService.class;
	}

}
