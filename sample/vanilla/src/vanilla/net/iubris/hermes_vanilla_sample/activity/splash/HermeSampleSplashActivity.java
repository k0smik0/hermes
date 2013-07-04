/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermeSampleSplashActivity.java is part of 'Hermes'.
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
package net.iubris.hermes_vanilla_sample.activity.splash;

import net.iubris.hermes_vanilla_sample.activity.main.HermesSampleMainActivity;
import net.iubris.hermes_vanilla_sample.controller.SampleController;
import net.iubris.hermes_vanilla_sample.service.HermesSampleService;

/*
 * in order to use this class, you need hermes-common-splash.jar from library/common<br/>
 * then, declare this as main launcher activity in AndroidManifest.xml (anyway: HermesSampleMainActivity must be "singleTask") 
 */
public class HermeSampleSplashActivity 
extends AbstractHermesSplashActivity<HermesSampleMainActivity,HermesSampleService,SampleController> {

	@Override
	protected HermesSplashDelegate<HermesSampleMainActivity, HermesSampleService, SampleController> getDelegate() {
		return	new AbstractHermesSplashActivityDelegate<HermesSampleMainActivity, HermesSampleService, SampleController>(this,HermesSampleMainActivity.class,HermesSampleService.class) {
			@Override
			public int getLayoutResID() {
				return 0;
			}
		};
	}	
}
