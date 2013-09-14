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
package net.iubris.hermes_sample__vanilla.activity.main;

import net.iubris.hermes.activity.main.HermesMainActivity;
import net.iubris.hermes.connector.asynctask.HermesConnectingAsyncTask;
import net.iubris.hermes_sample__vanilla.R;
import net.iubris.hermes_sample__vanilla.activity.examples.HermesSampleCompositingActivity;
import net.iubris.hermes_sample__vanilla.activity.examples.HermesSampleInheritingActivity;
import net.iubris.hermes_sample__vanilla.controller.SampleController;
import net.iubris.hermes_sample__vanilla.service.HermesSampleService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HermesSampleMainActivity 
extends HermesMainActivity<SampleController,HermesSampleService/*, HermesSampleApplication*/> 
{

//	private Button hermesSampleActivityButtonInh;
//	private Button hermesSampleActivityButtonComp;
//	private Button hermesSampleActivityButtonLocal;
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_main_activity);
		
		textView = (TextView) findViewById(R.id.text_view);
		
//		hermesSampleActivityButtonInh = (Button)this.findViewById(R.id.sample_activity_button);		
//		hermesSampleActivityButtonInh.setOnClickListener(hermesSampleActivityButtonListenerInh);
//		
//		hermesSampleActivityButtonComp = (Button)this.findViewById(R.id.sample_activity_compositing_button);
//		hermesSampleActivityButtonComp.setOnClickListener(hermesSampleActivityButtonListenerComp);
//		
//		hermesSampleActivityButtonLocal = (Button)this.findViewById(R.id.sample_activity_local_button);
//		hermesSampleActivityButtonLocal.setOnClickListener(hermesSampleActivityButtonListenerLocal);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//Debug.stopMethodTracing();
//		hermesConnectingAsyncTask.execute();
	};
	
	public void onClickInheriting(View arg0) {
		startActivity( new Intent(HermesSampleMainActivity.this,HermesSampleInheritingActivity.class) );
	}
	public void onClickCompositing(View arg0) {
		startActivity( new Intent(HermesSampleMainActivity.this,HermesSampleCompositingActivity.class) );
	}
	public void onClickHere(View arg0) {
		/*try {
			getController().doSomething();
		} catch (ControllerUnavailableException e) {
			onException(e, "local button");
		}*/
		
		new HermesConnectingAsyncTask<HermesSampleService, SampleController>(connector,this) {
			@Override
			protected void useController(SampleController controller) {
				String doSomething = controller.doSomething();
				textView.setText(""+textView.getText()
						+doSomething
						+"\n\n");
			}
		}.execute();
	}
	
	
	
//	private void onException(ControllerUnavailableException e,String from) {
//		Toast.makeText(this, "some error retrieving controller from: "+from, Toast.LENGTH_SHORT).show();
//	}

}
