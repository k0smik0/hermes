/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesRoboActivity.java is part of hermes.
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
package net.iubris.hermes.activity;


import javax.inject.Inject;

import net.iubris.hermes.client.HermesClient;
import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes.service.HermesService;
import roboguice.activity.RoboActivity;
import android.app.Service;
import android.os.Bundle;
import android.util.Log;

public class HermesRoboActivity<C, HS extends Service & HermesService<C>>
extends RoboActivity 
//implements HermesClient<C, HS>/*, IHermesRoboActivity<HS,C> */{
implements HermesClient<C>/*, IHermesRoboActivity<HS,C> */
{
//	@Inject private RoboConnector<HS, C> connector;
	@Inject private Connector<HS, C> connector;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		connector = connectorProvider.get();
Log.d(this.getClass().getSimpleName(), "connector: "+connector.getClass().getSimpleName()+" "+connector.hashCode());
//		eventHandler = eventHandlerProvider.get();
//Log.d(this.getClass().getSimpleName(),eventHandler.getClass().getSimpleName());
//		RoboGuice.getInjector(this).injectMembers(connector);
	}
	
	/*public Connector<HS, C> getConnector() {
		return connector;
	}*/
	
	@Override
	public C getController() throws ControllerUnavailableException {
		/*try {
Ln.d("getController");
//			return connector.getControllerExposerService().getController();
			return Hermes.getController(this, connector);
		} catch (ControllerUnavailableException e) {
			onException(e);
		}
		return null;*/
		/*try {
			return connector.getControllerExposerService().getController();
		} catch (ControllerUnavailableException e) {
//			e.printStackTrace();
			onException(e);
			throw new NullPointerException("no such controller");
		}*/
		return connector.getController();
	}

	/**
	 * Here you could show a toast as: <br/> "something heavy wrong:<br/>please re-press button"
	 * @param e
	 */
	/*@Override
	public void onException(ControllerUnavailableException e) {
		Toast.makeText(this, "something heavy wrong: please repress button", Toast.LENGTH_SHORT).show();
	}*/
}
