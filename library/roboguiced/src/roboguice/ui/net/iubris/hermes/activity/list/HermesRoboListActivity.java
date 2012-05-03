/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesRoboListActivity.java is part of hermes.
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
package net.iubris.hermes.activity.list;

import javax.inject.Inject;

import android.app.Service;

import net.iubris.hermes.Hermes;
import net.iubris.hermes.R;
import net.iubris.hermes.client.HermesClient;
import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes.service.IHermesService;
import net.iubris.hermes.ui.toast.utils.UIUtils;
import roboguice.activity.RoboListActivity;
import roboguice.util.Ln;

abstract public class HermesRoboListActivity<C,HS extends Service & IHermesService<C>> 
//extends RoboListActivity implements HermesClient<C,HS>/*, IHermesRoboActivity<HS,C>*/  {
extends RoboListActivity implements HermesClient<C>/*, IHermesRoboActivity<HS,C>*/  {

	//@Inject private HermesConnectorProvider<HS,C> connectorClient;
	@Inject private Connector<HS, C> connector;
	
	/*@Override
	public C getController() {		
//		return Hermes.getController(connectorClient.getConnector(), this);
		return Hermes.getController(this, connector);
	}*/
	/*
	@Override
	public HermesActivityListener<HS, C> getListener() {
		return listener;
	}*/
	
	@Override
	public C getController()  {	
		try {
Ln.d("getController");
			return Hermes.getController(this, connector);
		} catch (ControllerUnavailableException e) {
			onException(e);
		}
		return null;
	}

	@Override
	public void onException(ControllerUnavailableException e) {
		UIUtils.showShortToast(R.string.exception_controller_unavailable, this);
	}

}
