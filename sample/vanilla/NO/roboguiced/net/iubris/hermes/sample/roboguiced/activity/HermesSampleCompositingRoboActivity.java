package net.iubris.hermes.sample.roboguiced.activity;

import javax.inject.Inject;

import net.iubris.hermes.client.wrapper.HermesClientWrapper;
import net.iubris.hermes.sample.R;
import net.iubris.hermes.sample.controller.HermesSampleController;
import net.iubris.hermes.sample.vanilla.service.HermesSampleService;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HermesSampleCompositingRoboActivity extends RoboActivity {

	@Inject HermesClientWrapper<HermesSampleService, HermesSampleController> hermesClientWrapper;
	
	@InjectView(R.id.here_button) private Button hereButton;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		
		hereButton.setOnClickListener(hereButtonListener);
	}
	
	private OnClickListener hereButtonListener = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			HermesSampleController anExposer;
			anExposer = hermesClientWrapper.getController();
			anExposer.doSomething();			
		}
	};
}
