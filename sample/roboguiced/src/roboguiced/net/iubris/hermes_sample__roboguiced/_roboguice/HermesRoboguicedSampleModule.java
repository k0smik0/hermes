/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesRoboguicedSampleModule.java is part of 'Hermes'.
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
package net.iubris.hermes_sample__roboguiced._roboguice;

import net.iubris.hermes._roboguice.module.AbstractHermesModule;
import net.iubris.hermes_sample__roboguiced.controller.ConcreteSampleController;
import net.iubris.hermes_sample__roboguiced.controller.SampleController;
import net.iubris.hermes_sample__roboguiced.service.HermesSampleRoboService;

public class HermesRoboguicedSampleModule extends AbstractHermesModule<HermesSampleRoboService,SampleController> {

	@Override
	protected void configure() {
		bind(SampleController.class).to(ConcreteSampleController.class);
	}

	@Override
	protected Class<HermesSampleRoboService> providesHermesServiceDescendantClass() {
		return HermesSampleRoboService.class;
	}

}
