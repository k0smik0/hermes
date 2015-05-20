/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesMainEventHandler.java is part of 'Hermes'.
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
package net.iubris.hermes.event;

import javax.inject.Inject;

import net.iubris.hermes.connector.HermesEventHandler;
import net.iubris.hermes.service.HermesService;
import net.iubris.hermes.service.handler.ServiceHandler;
import android.app.Service;

public class HermesMainEventHandler<HS extends Service & HermesService<C>, C> {	
		
	private final ServiceHandler<HS,C> serviceHandler;
	private final HermesEventHandler<HS, C> eventHandler;
	
	@Inject
	public HermesMainEventHandler(HermesEventHandler<HS, C> eventHandler, ServiceHandler<HS,C> serviceHandler) {
		this.eventHandler = eventHandler;		
		this.serviceHandler = serviceHandler;
	}
	
	public void dispatchOnCreate() {
//Log.d("HermesMainEventHandler:42","onCreate: starting service");
		serviceHandler.startService();
//Log.d("HermesMainEventHandler:44","onCreate: started service");
	}
	
	public void dispatchOnStart() {
//Log.d("HermesMainEventHandler:48","onStart: binding service");
		eventHandler.dispatchOnStart();
//Log.d("HermesMainEventHandler:50","onStart: post binding service");		
	}
	
	
	public void dispatchOnBackPressed() {
		eventHandler.dispatchOnBackPressed();
	}
	
	public void dispatchOnDestroy() {
//Log.d("MainEventHandler","onDestroy: stopping");
		serviceHandler.stopService();
		/*try {
		} catch (NullPointerException e) {
		}*/		
	}	
}
