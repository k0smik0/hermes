/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesMainRoboActivity.java is part of hermes.
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
package net.iubris.hermes.activity.main;

import net.iubris.hermes.client.main.HermesMain;
import net.iubris.hermes.event.listener.HermesMainRoboActivityListener;
import net.iubris.hermes.service.HermesService;
import roboguice.activity.RoboActivity;
import android.app.Service;
import android.util.Log;

import com.google.inject.Inject;

public class HermesMainRoboActivity<C,HS extends Service & HermesService<C>> 
//extends HermesRoboActivity<C, HS> implements HermesMain
extends RoboActivity implements HermesMain
//implements HermesClient<C,HS>/*, IHermesRoboActivity<HS, C>*/{
//implements HermesClient<C>/*, IHermesRoboActivity<HS, C>*/ 
{
		
	@Inject protected HermesMainRoboActivityListener<HS, C> listener;
	
	@Override
	public void onBackPressed() {
		doOnBackPressed();
		super.onBackPressed();
	}
	
	@Override
	public void doOnBackPressed() {
Log.d(this.getClass().getSimpleName()+"[HermesMainRoboActivity:47]","back pressed - dispatching");
		listener.dispatchOnBackPressed();
	}
	
	
	/*protected HermesMainRoboActivityListener<HS, C> getListener() {
		return listener;
	}*/
	
	
	/*
	@Override
	protected void onDestroy() {
		serviceListener.stopService();
		super.onDestroy();
	}*/

	/*@Override
	public C getController() throws ControllerUnvailableException {
		return connectorClient.getConnector().getControllerExposerService().getController();
	}*/
	/*
	@Override
	public HermesActivityListener<HS, C> getListener() {
		return second_listener;
	}*/
}
