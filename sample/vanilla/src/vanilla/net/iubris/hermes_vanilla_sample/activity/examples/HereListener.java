package net.iubris.hermes_vanilla_sample.activity.examples;

import net.iubris.hermes.client.HermesClient;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes_vanilla_sample.controller.SampleController;
import android.view.View;
import android.view.View.OnClickListener;

final class HereListener implements OnClickListener {
	
	private final HermesClient<SampleController> hermesClient;
	
	HereListener(HermesClient<SampleController> hermesClient) {
		this.hermesClient = hermesClient;
	}

	@Override
	public void onClick(View arg0) {
		SampleController anExposer;
		try {
			anExposer = hermesClient.getController();
			anExposer.doSomething();
		} catch (ControllerUnavailableException e) {
			e.printStackTrace();
		}		
	}
}