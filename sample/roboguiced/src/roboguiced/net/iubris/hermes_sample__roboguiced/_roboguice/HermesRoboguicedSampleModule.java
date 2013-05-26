package net.iubris.hermes_sample__roboguiced._roboguice;

import net.iubris.hermes._roboguice.module.AbstractHermesModule;
import net.iubris.hermes_sample__roboguiced.controller.ConcreteSampleController;
import net.iubris.hermes_sample__roboguiced.controller.SampleController;
import net.iubris.hermes_sample__roboguiced.service.HermesSampleRoboService;

public class HermesRoboguicedSampleModule extends AbstractHermesModule<HermesSampleRoboService,SampleController> {

	@Override
	protected void configure() {
		bind(SampleController.class).to(ConcreteSampleController.class);
	}

	@Override
	protected Class<HermesSampleRoboService> providesHermesServiceDescendantClass() {
		return HermesSampleRoboService.class;
	}

}
