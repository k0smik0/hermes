/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesActivity.java is part of 'Hermes'.
 * 
 * 'Hermes' is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * 'Hermes' is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with 'Hermes' ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.hermes.activity;

import net.iubris.hermes.client.HermesClient;
import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes.provider.HermesProvider;
import net.iubris.hermes.provider.exception.HermesProvidingException;
import net.iubris.hermes.service.HermesService;
import android.app.Activity;
import android.app.Service;
import android.os.Bundle;

abstract public class HermesActivity<C,HS extends Service & HermesService<C>/*,HP extends Application & HermesProvider<HS, C>*/> 
extends Activity implements HermesClient<C> {
	
	protected Connector<HS, C> connector;
//	protected HermesMainEventHandler<HS,C> mainEventHandler;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		@SuppressWarnings("unchecked")
//		HP hermesProvider = (HP)getApplication();
		try {
			connector = (Connector<HS, C>) HermesProvider.getInstance().getConnector();
		} catch (HermesProvidingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public C getController()  throws ControllerUnavailableException {
		return connector.getController();
	}

	/**
	 * Here you could show a toast as: <br/> "something heavy wrong:<br/>please re-press button"
	 * @param e
	 */
//	protected void onException(ControllerUnavailableException e) {}
}
