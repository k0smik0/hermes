package net.iubris.hermes.sample.roboguiced;

import net.iubris.hermes.activity.main.HermesMainRoboActivity;
import net.iubris.hermes.sample.R;
import net.iubris.hermes.sample.controller.HermesSampleController;
import net.iubris.hermes.sample.roboguiced.activity.HermesSampleRoboActivity;
import net.iubris.hermes.sample.roboguiced.service.HermesSampleRoboService;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HermeSampleMainRoboActivity extends HermesMainRoboActivity<HermesSampleController,HermesSampleRoboService>{

	@InjectView(R.id.sample_activity_button) private Button sampleActivityButton;		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_main_activity);
		
		sampleActivityButton.setOnClickListener(sampleRoboActivityButtonListener);	
	}	
		
	private OnClickListener sampleRoboActivityButtonListener = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {			
			startActivity( new Intent(HermeSampleMainRoboActivity.this,HermesSampleRoboActivity.class) );
		}
	};
	
	
}
