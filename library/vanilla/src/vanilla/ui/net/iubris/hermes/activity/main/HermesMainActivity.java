/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesMainActivity.java is part of hermes.
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

import net.iubris.hermes.activity.HermesActivity;
import net.iubris.hermes.activity.main.event.EventHandler;
import net.iubris.hermes.application.HermesApplication;
import net.iubris.hermes.client.HermesClient;
import net.iubris.hermes.service.IHermesService;
import android.app.Service;
import android.os.Bundle;

abstract public class HermesMainActivity<C,HS  extends Service & IHermesService<C>, HA extends HermesApplication<HS, C>>
extends HermesActivity<C,HS,HA>
implements HermesClient<C>,HermesMain
{

	private EventHandler<HS,C> eventHandler;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);				
		eventHandler = ((HA)getApplication()).getEventHandler();		
		eventHandler.dispatchOnCreate();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		eventHandler.dispatchOnResume();
	}
		
	@Override
	public void onBackPressed() {
		eventHandler.dispatchOnDestroy();
		super.onBackPressed();
	}
		
	@Override
	protected void onDestroy() {
		eventHandler.dispatchOnDestroy();
		super.onDestroy();
	}
}
