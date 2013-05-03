/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesSampleCompositingRoboActivity.java is part of hermes.
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
package net.iubris.hermes_sample__roboguiced.activity.examples;

import javax.inject.Inject;

import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes_sample__roboguiced.R;
import net.iubris.hermes_sample__roboguiced.controller.SampleController;
import net.iubris.hermes_sample__roboguiced.service.HermesSampleRoboService;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HermesSampleCompositingRoboActivity extends RoboActivity {

//	@Inject RoboConnector<HermesSampleRoboService, SampleController> connector;
	@Inject Connector<HermesSampleRoboService, SampleController> connector;
	
//	@Inject HermesRoboActivityListener<HermesSampleRoboService, SampleController> hermesRoboActivityListener;
	
//	private RoboHermesClientDelegate<HermesSampleRoboService, SampleController> hermesClientDelegate;
	
	@InjectView(R.id.here_button) private Button hereButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_activities);
		hereButton.setOnClickListener(hereButtonListener);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
//		hermesRoboActivityListener.dispatchOnBackPressed();
	}
	
	/*@Override
	protected void onResume() {
		super.onResume();
		hereButton.setOnClickListener(hereButtonListener);
	}*/
	
	private OnClickListener hereButtonListener = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			SampleController anExposer;
			try {
				anExposer = connector.getController();
				anExposer.doSomething();
			} catch (ControllerUnavailableException e) {
				Toast.makeText(HermesSampleCompositingRoboActivity.this, "something heavy wrong: please repress button", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
		}
	};
}
