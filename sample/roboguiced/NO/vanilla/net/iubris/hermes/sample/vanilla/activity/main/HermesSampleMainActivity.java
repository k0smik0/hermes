package net.iubris.hermes.sample.vanilla.activity.main;

import net.iubris.hermes.HermesApplication;
import net.iubris.hermes.activity.main.HermesMainActivity;
import net.iubris.hermes.sample.R;
import net.iubris.hermes.sample.controller.HermesSampleController;
import net.iubris.hermes.sample.vanilla.service.HermesSampleService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HermesSampleMainActivity extends HermesMainActivity<HermesSampleController,HermesSampleService, HermesApplication<HermesSampleService, HermesSampleController>> {

	private Button hermesSampleActivityButton;
//	private Button hdRoboActivityButton;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_main_activity);
		
		hermesSampleActivityButton = (Button)this.findViewById(R.id.sample_activity_button);		
		hermesSampleActivityButton.setOnClickListener(hermesSampleActivityButtonListener);		
	}	
	
	private OnClickListener hermesSampleActivityButtonListener = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			startActivity( new Intent(HermesSampleMainActivity.this,HermeSampleActivity.class) );
		}
	};	
}
