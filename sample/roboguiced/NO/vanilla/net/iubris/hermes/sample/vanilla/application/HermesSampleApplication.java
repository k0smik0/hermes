package net.iubris.hermes.sample.vanilla.application;

import net.iubris.hermes.HermesApplication;
import net.iubris.hermes.sample.controller.HermesSampleController;
import net.iubris.hermes.sample.vanilla.service.HermesSampleService;

public class HermesSampleApplication extends HermesApplication<HermesSampleService, HermesSampleController> {
	@Override
	public Class<HermesSampleService> providesHSClass() {
		return HermesSampleService.class;
	}
}
