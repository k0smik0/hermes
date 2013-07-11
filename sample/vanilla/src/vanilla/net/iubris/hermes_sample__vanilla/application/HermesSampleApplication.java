/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesSampleApplication.java is part of 'Hermes'.
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
package net.iubris.hermes_sample__vanilla.application;

import net.iubris.hermes.provider.HermesProvider;
import net.iubris.hermes_sample__vanilla.service.HermesSampleService;
import android.app.Application;
import android.os.Debug;
import android.os.Environment;

//public class HermesSampleApplication extends AbstractHermesApplication<HermesSampleService, SampleController> {
public class HermesSampleApplication extends Application {

	/*@Override
	public Class<HermesSampleService> providesHSClass() {
		return HermesSampleService.class;
	}*/
	
	@Override
	public void onCreate() {
		super.onCreate();
Debug.startMethodTracing(Environment.getExternalStorageDirectory().getPath()+"/traces/hermes_sample_vanilla__startup");
		HermesProvider.init(this, HermesSampleService.class);
	}
}
