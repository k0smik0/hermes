/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesSampleCompositingActivity.java is part of hermes.
 * 
 * hermes is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * hermes is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with hermes ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.hermes_vanilla_sample.activity.examples;

import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes_vanilla_sample.R;
import net.iubris.hermes_vanilla_sample.application.HermesSampleApplication;
import net.iubris.hermes_vanilla_sample.controller.SampleController;
import net.iubris.hermes_vanilla_sample.service.HermesSampleService;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HermesSampleCompositingActivity extends Activity {

	//vanilla branch version, be careful with classpath
	Connector<HermesSampleService, SampleController> connector;
//	HermesClientDelegate<HermesSampleService,SampleController> hermesClientDelegate;
	
	private Button hereButton;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_activities);
		connector = ((HermesSampleApplication)getApplication()).getConnector();
		
		hereButton = (Button)findViewById(R.id.here_button);		
		hereButton.setOnClickListener(hereButtonListener);
	}
	
	private OnClickListener hereButtonListener = new OnClickListener() {		
		@Override
		public void onClick(View v) {
			SampleController anExposer;
			try {
				anExposer = connector.getController();
				anExposer.doSomething();
			} catch (ControllerUnavailableException e) {
				e.printStackTrace();
				onException(e, "");
			}
		}
	};
	
	private void onException(ControllerUnavailableException e,String from) {
		Toast.makeText(this, "some error retrieving controller from: "+from, Toast.LENGTH_SHORT).show();
	}
}
