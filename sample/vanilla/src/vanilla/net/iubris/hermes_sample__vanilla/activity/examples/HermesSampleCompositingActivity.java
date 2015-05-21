/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesSampleCompositingActivity.java is part of 'Hermes'.
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
package net.iubris.hermes_sample__vanilla.activity.examples;

import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.asynctask.HermesConnectingAsyncTask;
import net.iubris.hermes.provider.ContainerProvider;
import net.iubris.hermes.provider.exception.HermesProvidingException;
import net.iubris.hermes_sample__vanilla.R;
import net.iubris.hermes_sample__vanilla.controller.SampleController;
import net.iubris.hermes_sample__vanilla.service.HermesSampleService;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HermesSampleCompositingActivity extends Activity {

	//vanilla branch version, be careful with classpath
	Connector<HermesSampleService, SampleController> connector;
//	HermesClientDelegate<HermesSampleService,SampleController> hermesClientDelegate;
	TextView textView;
	
//	private Button hereButton;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//Debug.startMethodTracing(Environment.getExternalStorageDirectory().getPath()+"/traces/hermes_sample_vanilla__using_connector");		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_activities);
//		connector = ((HermesSampleApplication)getApplication()).getConnector();
		try {
			ContainerProvider<HermesSampleService, SampleController> hermesProvider = ContainerProvider.getInstance();
			connector = hermesProvider.getConnector();
		} catch (HermesProvidingException e) {
			e.printStackTrace();
		}
		textView = (TextView) findViewById(R.id.text_view);
		
//		hereButton = (Button)findViewById(R.id.here_button);		
//		hereButton.setOnClickListener(hereButtonListener);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		hermesConnectingAsyncTask.execute();
	}
	
//	private OnClickListener hereButtonListener = new OnClickListener() {		
//		@Override
		public void onClickHere(View v) {
			/*SampleController anExposer;
			try {
				anExposer = connector.getController();
				anExposer.doSomething();
			} catch (ControllerUnavailableException e) {
				e.printStackTrace();
				onException(e, "");
			}*/
			
			new HermesConnectingAsyncTask<HermesSampleService, SampleController>(connector, /*HermesSampleCompositingRoboActivity.this.*/getApplicationContext()) {
				@Override
				protected void onPreExecute() {};
				
				@Override
				protected void useController(SampleController controller) {
					String doSomething = controller.doSomething();
					textView.setText(""+textView.getText()
							+doSomething
							+"\n\n");
	//Debug.stopMethodTracing();
				}
				
			}.execute();			
		}
//	};
	
//	private void onException(ControllerUnavailableException e,String from) {
//		Toast.makeText(this, "some error retrieving controller from: "+from, Toast.LENGTH_SHORT).show();
//	}
}
