/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesSampleMainRoboActivity.java is part of 'Hermes'.
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
package net.iubris.hermes_sample__roboguiced.activity.main;

import javax.inject.Inject;

import net.iubris.hermes.activity.main.HermesMainRoboActivity;
import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.asynctask.HermesConnectingRoboAsyncTask;
import net.iubris.hermes_sample__roboguiced.R;
import net.iubris.hermes_sample__roboguiced.activity.examples.HermesSampleCompositingRoboActivity;
import net.iubris.hermes_sample__roboguiced.activity.examples.HermesSampleInheritingRoboActivity;
import net.iubris.hermes_sample__roboguiced.controller.SampleController;
import net.iubris.hermes_sample__roboguiced.service.HermesSampleRoboService;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//@ContentView(R.layout.sample_main_activity)
public class HermesSampleMainRoboActivity
extends HermesMainRoboActivity<SampleController,HermesSampleRoboService>
//implements HermesClient<SampleController> 
{

//	@InjectView(R.id.sample_activity_inheriting_button) private Button hermesSampleActivityButtonInh;
//	@InjectView(R.id.sample_activity_compositing_button) private Button hermesSampleActivityButtonComp;
//	@InjectView(R.id.sample_activity_local_button) private Button hermesSampleActivityButtonLocal;
	@InjectView(R.id.text_view) TextView textView;
	
	@Inject Connector<HermesSampleRoboService, SampleController> connector;
	private HermesConnectingRoboAsyncTask<HermesSampleRoboService,SampleController> hermesConnectingRoboAsyncTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//Debug.startMethodTracing(Environment.getExternalStorageDirectory().getPath()+"/traces/hermes_sample_roboguiced__startup");
		setContentView(R.layout.sample_main_activity);
	
		textView.setMovementMethod(new ScrollingMovementMethod());
		
		hermesConnectingRoboAsyncTask = new HermesConnectingRoboAsyncTask<HermesSampleRoboService, SampleController>(this.getApplicationContext()) {
			@Override
			protected void useController(SampleController controller) {
				printResult( controller.doSomething() );
			}
			@Override
			protected void onException(Exception e) throws RuntimeException {
				showException(e, this.getClass().getSimpleName());
			}
		};
	}	
	
	@Override
	protected void onResume() {
		super.onResume();
//		hermesConnectingRoboAsyncTask.execute();
//Debug.stopMethodTracing();
	}
	
	public void onClickInheriting(View arg0) {
		startActivity( new Intent(HermesSampleMainRoboActivity.this,HermesSampleInheritingRoboActivity.class) );
	}
	public void onClickCompositing(View arg0) {
		startActivity( new Intent(HermesSampleMainRoboActivity.this,HermesSampleCompositingRoboActivity.class) );
	}
	public void onClickLocal(View arg0) {
		hermesConnectingRoboAsyncTask.execute();
		/*try {
			String doSomething = connector.getController().doSomething();
			printResult(doSomething);				
		} catch (ControllerUnavailableException e) {
			onException(e, "local button");
		}*/
	}
	
	private void printResult(String s) {
		textView.setText(textView.getText()
				+s
				+"\n\n");
	}
	
	
	
	
//	@Override
//	protected void onResume() {
//		super.onResume();
		/*try {
			getController().doSomething();
		} catch (ControllerUnavailableException e) {
			onException(e, "onResume, blocking");
		}*/
		
		/*new AbstractConnectAsyncTask<HermesSampleRoboService, SampleController>(getConnector(),this) {
			@Override
			protected void useController(SampleController controller) {
				controller.doSomething();
			}
		}.execute();*/
//	};
	
	private void showException(Exception e, String from) {
		Toast.makeText(this, "some error retrieving controller from: "+from, Toast.LENGTH_SHORT).show();
	}
	
}
