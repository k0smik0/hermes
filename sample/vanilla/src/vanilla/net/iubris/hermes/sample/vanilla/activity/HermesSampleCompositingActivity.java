/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesSampleCompositingActivity.java is part of hermes.
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
package net.iubris.hermes.sample.vanilla.activity;

import net.iubris.hermes.client.wrapper.HermesClientWrapper;
import net.iubris.hermes.sample.controller.SampleController;
import net.iubris.hermes.sample.vanilla.application.HermesSampleApplication;
import net.iubris.hermes.sample.vanilla.service.HermesSampleService;
import android.app.Activity;
import android.os.Bundle;

public class HermesSampleCompositingActivity extends Activity {

	//vanilla branch version, be careful with classpath
	HermesClientWrapper<HermesSampleApplication,HermesSampleService,SampleController> hermesClientWrapper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		hermesClientWrapper = new HermesClientWrapper<HermesSampleApplication,HermesSampleService, SampleController>((HermesSampleApplication)getApplication(), HermesSampleService.class);
	}		
}
