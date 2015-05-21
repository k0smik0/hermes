/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesRoboActivity.java is part of 'Hermes'.
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


import javax.inject.Inject;

import net.iubris.hermes.client.ContainerServiceClient;
import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.exception.ActorUnavailableException;
import net.iubris.hermes.service.ContainerService;
import roboguice.activity.RoboActivity;
import android.app.Service;
import android.os.Bundle;
import android.util.Log;

public class HermesRoboActivity<C, HS extends Service & ContainerService<C>>
extends RoboActivity 
//implements HermesClient<C, HS>/*, IHermesRoboActivity<HS,C> */{
implements ContainerServiceClient<C>/*, IHermesRoboActivity<HS,C> */
{
//	@Inject private RoboConnector<HS, C> connector;
	@Inject private Connector<HS, C> connector;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		connector = connectorProvider.get();
Log.d(this.getClass().getSimpleName()+"[HermesRoboActivity:47]", "connector: "+connector.getClass().getSimpleName()+" "+connector.hashCode());
//		eventHandler = eventHandlerProvider.get();
//Log.d(this.getClass().getSimpleName(),eventHandler.getClass().getSimpleName());
//		RoboGuice.getInjector(this).injectMembers(connector);
	}
	
	/*public Connector<HS, C> getConnector() {
		return connector;
	}*/
	
	@Override
	public C getActor() throws ActorUnavailableException {
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
