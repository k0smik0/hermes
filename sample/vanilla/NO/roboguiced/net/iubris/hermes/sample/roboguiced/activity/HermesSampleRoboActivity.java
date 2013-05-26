/*******************************************************************************
 * Copyleft 2011 Massimiliano Leone - k0smik0@logorroici.org .
 * 
 * HDRoboActivity.java is part of hermes.
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
package net.iubris.hermes.sample.roboguiced.activity;


import net.iubris.hermes.activity.HermesRoboActivity;
import net.iubris.hermes.sample.R;
import net.iubris.hermes.sample.controller.HermesSampleController;
import net.iubris.hermes.sample.roboguiced.service.HermesSampleRoboService;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HermesSampleRoboActivity extends HermesRoboActivity<HermesSampleController,HermesSampleRoboService> {
	
	@InjectView(R.id.here_button) private Button hereButton;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_main_activity);
		
		hereButton.setOnClickListener(hereButtonListener);
	}
	
	private OnClickListener hereButtonListener = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			HermesSampleController anExposer;
			anExposer = getController();
			anExposer.doSomething();			
		}
	};
}
