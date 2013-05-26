package net.iubris.hermes.sample.roboguiced.activity.main;

import roboguice.inject.InjectView;

import net.iubris.hermes.activity.main.HermesMainRoboActivity;
import net.iubris.hermes.sample.controller.HermesSampleController;
import net.iubris.hermes.sample.roboguiced.activity.HermesSampleCompositingRoboActivity;
import net.iubris.hermes.sample.roboguiced.activity.HermesSampleRoboActivity;
import net.iubris.hermes.sample.roboguiced.service.HermesSampleRoboService;
import net.iubris.hermes.sample.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HermesSampleMainRoboActivity extends HermesMainRoboActivity<HermesSampleController,HermesSampleRoboService> {

	@InjectView(R.id.sample_activity_button) private Button hermesSampleActivityButton;
	@InjectView(R.id.sample_activity_button) private Button hermesSampleActivityButton2;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_main_activity);
	
		hermesSampleActivityButton.setOnClickListener(hermesSampleActivityButtonListener);
		hermesSampleActivityButton2.setOnClickListener(hermesSampleActivityButtonListener2);
	}	
	
	private OnClickListener hermesSampleActivityButtonListener = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			startActivity( new Intent(HermesSampleMainRoboActivity.this,HermesSampleRoboActivity.class) );
		}
	};
	private OnClickListener hermesSampleActivityButtonListener2 = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			startActivity( new Intent(HermesSampleMainRoboActivity.this,HermesSampleCompositingRoboActivity.class) );
		}
	};	
}
