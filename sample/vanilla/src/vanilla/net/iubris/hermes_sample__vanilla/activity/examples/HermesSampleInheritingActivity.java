/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesSampleInheritingActivity.java is part of 'Hermes'.
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

import net.iubris.hermes.activity.HermesActivity;
import net.iubris.hermes.connector.asynctask.HermesConnectingAsyncTask;
import net.iubris.hermes_sample__vanilla.R;
import net.iubris.hermes_sample__vanilla.controller.SampleController;
import net.iubris.hermes_sample__vanilla.service.HermesSampleService;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HermesSampleInheritingActivity extends HermesActivity<SampleController,HermesSampleService/*,HermesSampleApplication*/> {
	
	private TextView textView;

//	private Button hereButton;
//	private OnClickListener hereButtonListener = new HereListener(this);
	
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_activities);
		
//		hereButton = (Button)findViewById(R.id.here_button);	
		textView = (TextView) findViewById(R.id.text_view);
	}
	
	public void onClickHere(View v) {
		/*SampleController controller;
		try {
			controller = 
					//HermesSampleInheritingActivity.this.
					getController();
			String doSomething = controller.doSomething();
			textView.setText(""+textView.getText()
					+doSomething
					+"\n\n");
		} catch (ControllerUnavailableException e) {
			onException(e,"here button");
		}
		*/
		
		new HermesConnectingAsyncTask<HermesSampleService, SampleController>(connector, /*HermesSampleCompositingRoboActivity.this.*/getApplicationContext()) {
			
			@Override
			protected void onPreExecute() {
//Debug.startMethodTracing(Environment.getExternalStorageDirectory().getPath()+"/traces/hermes_sample_roboguiced__using_connector");				
			};
			
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
	
//	private void onException(ControllerUnavailableException e,String from) {
//		Toast.makeText(this, "some error retrieving controller from: "+from, Toast.LENGTH_SHORT).show();
//	}
}
