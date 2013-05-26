/*******************************************************************************
 * Copyleft 2011 Massimiliano Leone - k0smik0@logorroici.org .
 * 
 * HDActivity.java is part of hermes.
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
package net.iubris.hermes.sample.vanilla.activity.main;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import net.iubris.hermes.HermesApplication;
import net.iubris.hermes.activity.HermesActivity;
import net.iubris.hermes.sample.R;
import net.iubris.hermes.sample.controller.HermesSampleController;
import net.iubris.hermes.sample.vanilla.service.HermesSampleService;

public class HermeSampleActivity extends HermesActivity<HermesSampleController,HermesSampleService,HermesApplication<HermesSampleService, HermesSampleController>> {
	
	private Button hereButton;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_activity);
		
		hereButton = (Button)findViewById(R.id.here_button);		
		hereButton.setOnClickListener(hereButtonListener);		
	}
	
	private OnClickListener hereButtonListener = new OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			HermesSampleController anExposer;
			try {
				anExposer = getController();
				anExposer.doSomething();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			
		}
	};
}
