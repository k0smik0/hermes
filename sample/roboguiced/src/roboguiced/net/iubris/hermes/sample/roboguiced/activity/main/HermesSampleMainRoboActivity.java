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
package net.iubris.hermes.sample.roboguiced.activity.main;

import roboguice.inject.InjectView;

import net.iubris.hermes.activity.main.HermesMainRoboActivity;
import net.iubris.hermes.sample.controller.SampleController;
import net.iubris.hermes.sample.roboguiced.activity.HermesSampleCompositingRoboActivity;
import net.iubris.hermes.sample.roboguiced.activity.HermesSampleRoboActivity;
import net.iubris.hermes.sample.roboguiced.service.HermesSampleRoboService;
import net.iubris.hermes.sample.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HermesSampleMainRoboActivity extends HermesMainRoboActivity<SampleController,HermesSampleRoboService> {

	@InjectView(R.id.sample_activity_button) private Button hermesSampleActivityButton;
	@InjectView(R.id.sample_activity_compositing_button) private Button hermesSampleActivityButton2;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_main_activity);
	
		hermesSampleActivityButton.setOnClickListener(hermesSampleActivityButtonListener);
		hermesSampleActivityButton2.setOnClickListener(hermesSampleActivityButtonListener2);
	}	
	
	private OnClickListener hermesSampleActivityButtonListener = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			startActivity( new Intent(HermesSampleMainRoboActivity.this,HermesSampleRoboActivity.class) );
		}
	};
	private OnClickListener hermesSampleActivityButtonListener2 = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			startActivity( new Intent(HermesSampleMainRoboActivity.this,HermesSampleCompositingRoboActivity.class) );
		}
	};	
}
