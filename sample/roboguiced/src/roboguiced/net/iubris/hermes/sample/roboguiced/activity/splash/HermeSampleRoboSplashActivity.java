/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermeSampleRoboSplashActivity.java is part of hermes.
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
package net.iubris.hermes.sample.roboguiced.activity.splash;

import net.iubris.hermes.activity.splash.HermesSplashActivity;
import net.iubris.hermes.activity.splash.delegate.IHermesSplashDelegate;
import net.iubris.hermes.activity.splash.delegate.base.AbstractHermesSplashActivityDelegate;

import net.iubris.hermes.sample.controller.SampleController;
import net.iubris.hermes.sample.roboguiced.service.HermesSampleRoboService;

import net.iubris.hermes.sample.roboguiced.activity.main.HermesSampleMainRoboActivity;


public class HermeSampleRoboSplashActivity 
extends HermesSplashActivity<HermesSampleMainRoboActivity,HermesSampleRoboService,SampleController> {

	@Override
	protected IHermesSplashDelegate<HermesSampleMainRoboActivity, HermesSampleRoboService, SampleController> getDelegate() {
		return	new AbstractHermesSplashActivityDelegate<HermesSampleMainRoboActivity, HermesSampleRoboService, SampleController>(this,HermesSampleMainRoboActivity.class,HermesSampleRoboService.class) {
			@Override
			public int getLayoutResID() {
				return 0;
			}

			@Override
			public void doOtherStuffInBackground() {}
		};
	}	
}
