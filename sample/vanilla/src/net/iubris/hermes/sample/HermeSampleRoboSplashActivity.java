/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermeSampleRoboSplashActivity.java is part of 'Hermes'.
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
package net.iubris.hermes.sample;

import net.iubris.hermes.activity.splash.HermesSplashActivity;
import net.iubris.hermes.activity.splash.delegate.AbstractHermesSplashActivityDelegate;
import net.iubris.hermes.activity.splash.delegate.IHermesSplashActivityDelegate;
import net.iubris.hermes.sample.controller.SampleController;
import net.iubris.hermes.sample.roboguiced.HermeSampleMainRoboActivity;
import net.iubris.hermes.sample.roboguiced.service.SampleRoboService;

public class HermeSampleRoboSplashActivity extends HermesSplashActivity<HermeSampleMainRoboActivity,SampleRoboService,SampleController> {

	@Override
	protected IHermesSplashActivityDelegate<HermeSampleMainRoboActivity, SampleRoboService, SampleController> getDelegate() {
		//AbstractHermesSplashActivityDelegate<HDMainRoboActivity, HDRoboService, AnExposer> abstractHermesRoboSplashActivityDelegate =
		return	new AbstractHermesSplashActivityDelegate<HermeSampleMainRoboActivity, SampleRoboService, SampleController>() {
			@Override
			public int getLayoutResID() {
				return 0;
			}
		};
	}	
}
