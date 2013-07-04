/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HereListener.java is part of 'Hermes'.
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
package net.iubris.hermes_vanilla_sample.activity.examples;

import net.iubris.hermes.client.HermesClient;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes_vanilla_sample.controller.SampleController;
import android.view.View;
import android.view.View.OnClickListener;

final class HereListener implements OnClickListener {
	
	private final HermesClient<SampleController> hermesClient;
	
	HereListener(HermesClient<SampleController> hermesClient) {
		this.hermesClient = hermesClient;
	}

	@Override
	public void onClick(View arg0) {
		SampleController anExposer;
		try {
			anExposer = hermesClient.getController();
			anExposer.doSomething();
		} catch (ControllerUnavailableException e) {
			e.printStackTrace();
		}		
	}
}
