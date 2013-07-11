/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesSampleCompositingRoboActivity.java is part of 'Hermes'.
 * 
 * 'Hermes' is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * 'Hermes' is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with 'Hermes'; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.hermes_sample__roboguiced.activity.examples;

import javax.inject.Inject;

import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.asynctask.HermesConnectingRoboAsyncTask;
import net.iubris.hermes_sample__roboguiced.R;
import net.iubris.hermes_sample__roboguiced.controller.SampleController;
import net.iubris.hermes_sample__roboguiced.service.HermesSampleRoboService;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

@ContentView(R.layout.sample_activities)
public class HermesSampleCompositingRoboActivity extends RoboActivity {

	@Inject Connector<HermesSampleRoboService, SampleController> connector;
	
//	@InjectView(R.id.here_button) private Button hereButton;
	
	@InjectView(R.id.text_view) TextView textView;

	private HermesConnectingRoboAsyncTask<HermesSampleRoboService,SampleController> hermesConnectingRoboAsyncTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//Debug.startMethodTracing(Environment.getExternalStorageDirectory().getPath()+"/traces/hermes_sample_roboguiced__using_connector");
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.sample_activities);
//		hereButton.setOnClickListener(hereButtonListener);
		
		textView.setMovementMethod(new ScrollingMovementMethod());
		
		hermesConnectingRoboAsyncTask = new HermesConnectingRoboAsyncTask<HermesSampleRoboService, SampleController>(/*HermesSampleCompositingRoboActivity.this.*/getApplicationContext()) {
//			protected void onPreExecute() throws Exception {};
			@Override
			protected void useController(SampleController controller) {
				String doSomething = controller.doSomething();
				textView.setText(""+textView.getText()
						+doSomething
						+"\n\n");
//Debug.stopMethodTracing();
			}			
		};
	}
	
//	@Override
//	protected void onResume() {
//		super.onResume();
////Debug.stopMethodTracing();
//	}
	
	public void onClickHere(View arg0) {
		/*SampleController anExposer;
		try {
			anExposer = connector.getController();
			String something = anExposer.doSomething();
			
			textView.setText(textView.getText()
					+something
					+"\n\n");				
			
		} catch (ControllerUnavailableException e) {
			Toast.makeText(HermesSampleCompositingRoboActivity.this, "something heavy wrong: please repress button", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}*/
		
		hermesConnectingRoboAsyncTask.execute();
	}
}
