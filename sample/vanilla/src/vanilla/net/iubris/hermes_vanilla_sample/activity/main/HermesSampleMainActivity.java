/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesSampleMainActivity.java is part of 'Hermes'.
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
package net.iubris.hermes_vanilla_sample.activity.main;

import net.iubris.hermes.activity.main.HermesMainActivity;
import net.iubris.hermes.connector.asynctask.HermesConnectingAsyncTask;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes_vanilla_sample.R;
import net.iubris.hermes_vanilla_sample.activity.examples.HermesSampleCompositingActivity;
import net.iubris.hermes_vanilla_sample.activity.examples.HermesSampleInheritingActivity;
import net.iubris.hermes_vanilla_sample.application.HermesSampleApplication;
import net.iubris.hermes_vanilla_sample.controller.SampleController;
import net.iubris.hermes_vanilla_sample.service.HermesSampleService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HermesSampleMainActivity 
extends HermesMainActivity<SampleController,HermesSampleService, HermesSampleApplication> 
{

	private Button hermesSampleActivityButtonInh;
	private Button hermesSampleActivityButtonComp;
	private Button hermesSampleActivityButtonLocal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_main_activity);
		
		hermesSampleActivityButtonInh = (Button)this.findViewById(R.id.sample_activity_button);		
		hermesSampleActivityButtonInh.setOnClickListener(hermesSampleActivityButtonListenerInh);
		
		hermesSampleActivityButtonComp = (Button)this.findViewById(R.id.sample_activity_compositing_button);
		hermesSampleActivityButtonComp.setOnClickListener(hermesSampleActivityButtonListenerComp);
		
		hermesSampleActivityButtonLocal = (Button)this.findViewById(R.id.sample_activity_local_button);
		hermesSampleActivityButtonLocal.setOnClickListener(hermesSampleActivityButtonListenerLocal);
	}	
	
	private OnClickListener hermesSampleActivityButtonListenerInh = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			startActivity( new Intent(HermesSampleMainActivity.this,HermesSampleInheritingActivity.class) );
		}
	};	
	private OnClickListener hermesSampleActivityButtonListenerComp = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			startActivity( new Intent(HermesSampleMainActivity.this,HermesSampleCompositingActivity.class) );
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
		
		new HermesConnectingAsyncTask<HermesSampleService, SampleController>(connector,this) {
			@Override
			protected void useController(SampleController controller) {
				controller.doSomething();
			}
		}.execute();
	};
	
	private void onException(ControllerUnavailableException e,String from) {
		Toast.makeText(this, "some error retrieving controller from: "+from, Toast.LENGTH_SHORT).show();
	}

}
