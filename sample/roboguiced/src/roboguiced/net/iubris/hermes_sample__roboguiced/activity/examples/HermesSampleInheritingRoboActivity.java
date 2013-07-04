/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesSampleRoboActivity.java is part of hermes.
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


import net.iubris.hermes.activity.HermesRoboActivity;
import net.iubris.hermes.connector.asynctask.HermesConnectingRoboAsyncTask;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes_sample__roboguiced.R;
import net.iubris.hermes_sample__roboguiced.controller.SampleController;
import net.iubris.hermes_sample__roboguiced.service.HermesSampleRoboService;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@ContentView(R.layout.sample_activities)
public class HermesSampleInheritingRoboActivity extends HermesRoboActivity<SampleController,HermesSampleRoboService> {
	
	@InjectView(R.id.here_button) private Button hereButton;
	@InjectView(R.id.text_view) TextView textView;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		hereButton.setOnClickListener(hereButtonListener);
		
		textView.setMovementMethod(new ScrollingMovementMethod());
	}
	
	private OnClickListener hereButtonListener = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
//			SampleController anExposer;
			
			new HermesConnectingRoboAsyncTask<HermesSampleRoboService, SampleController>(HermesSampleInheritingRoboActivity.this.getApplicationContext()) {
				@Override
				protected void useController(SampleController controller) {
					String doSomething = controller.doSomething();
					textView.setText(""+textView.getText()
							+doSomething
							+"\n\n");
				}
			}.execute();
			/*try {
				anExposer = getController();
				String something = anExposer.doSomething();
				textView.setText(""+textView.getText()
						+something
						+"\n\n");
//			} catch(NullPointerException npe) {
//				Ln.d("exposer npe!");
			} catch (ControllerUnavailableException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				onException(e);
			}*/
		}
	};
	
//	@Override
	protected void onException(ControllerUnavailableException arg0) {
		 Toast.makeText(this, "something heavy wrong: please repress button", Toast.LENGTH_SHORT).show();
	};
}
