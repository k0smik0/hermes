/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesSampleMainRoboActivity.java is part of hermes.
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
package net.iubris.hermes_sample__roboguiced.activity.main;

import net.iubris.hermes.activity.main.HermesMainRoboActivity;
import net.iubris.hermes.connector.asynctask.HermesConnectingRoboAsyncTask;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes_sample__roboguiced.R;
import net.iubris.hermes_sample__roboguiced.activity.examples.HermesSampleCompositingRoboActivity;
import net.iubris.hermes_sample__roboguiced.activity.examples.HermesSampleInheritingRoboActivity;
import net.iubris.hermes_sample__roboguiced.controller.SampleController;
import net.iubris.hermes_sample__roboguiced.service.HermesSampleRoboService;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HermesSampleMainRoboActivity extends HermesMainRoboActivity<SampleController,HermesSampleRoboService> {

	@InjectView(R.id.sample_activity_inheriting_button) private Button hermesSampleActivityButtonInh;
	@InjectView(R.id.sample_activity_compositing_button) private Button hermesSampleActivityButtonComp;
	@InjectView(R.id.sample_activity_local_button) private Button hermesSampleActivityButtonLocal;
	
//	@Inject private SampleController localSampleController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_main_activity);
	
		hermesSampleActivityButtonInh.setOnClickListener(hermesSampleActivityButtonListenerInh);
		hermesSampleActivityButtonComp.setOnClickListener(hermesSampleActivityButtonListenerComp);
		
		hermesSampleActivityButtonLocal.setOnClickListener(hermesSampleActivityButtonListenerLocal);
	}	
	
	private OnClickListener hermesSampleActivityButtonListenerInh = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			startActivity( new Intent(HermesSampleMainRoboActivity.this,HermesSampleInheritingRoboActivity.class) );
		}
	};
	private OnClickListener hermesSampleActivityButtonListenerComp = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			startActivity( new Intent(HermesSampleMainRoboActivity.this,HermesSampleCompositingRoboActivity.class) );
		}
	};
	private OnClickListener hermesSampleActivityButtonListenerLocal = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			try {
				getController().doSomething();
			} catch (ControllerUnavailableException e) {
				onException(e, "local button");
			}
		}
	};
	
	@Override
	protected void onResume() {
		super.onResume();
		
		new HermesConnectingRoboAsyncTask<HermesSampleRoboService, SampleController>(this) {
			@Override
			protected void useController(SampleController controller) {
				controller.doSomething();
			}
		}.execute();
		
		
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
	};
	
	private void onException(ControllerUnavailableException e,String from) {
		Toast.makeText(this, "some error retrieving controller from: "+from, Toast.LENGTH_SHORT).show();
	}
	
}
