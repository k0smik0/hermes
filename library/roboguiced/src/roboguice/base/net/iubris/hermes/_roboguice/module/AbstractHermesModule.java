package net.iubris.hermes._roboguice.module;

import net.iubris.hermes.service.HermesService;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public abstract class AbstractHermesModule<HS extends HermesService<C>,C> extends AbstractModule {

	@Override
	protected void configure() {}
	/*
	@Override
	protected final void configure() {
		bindHermesServiceDescendantClass();
		ownConfigure();
	}*/

//	protected void ownConfigure() {}

	@Provides
	protected abstract Class<HS> providesHermesServiceDescendantClass();
}
