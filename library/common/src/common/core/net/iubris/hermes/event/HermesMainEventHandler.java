/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * EventHandler.java is part of hermes.
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
package net.iubris.hermes.event;

import javax.inject.Inject;

import net.iubris.hermes.connector.HermesEventHandlerInternalDelegate;
import net.iubris.hermes.service.HermesService;
import net.iubris.hermes.service.handler.ServiceHandler;
import android.app.Service;

public class HermesMainEventHandler<HS extends Service & HermesService<C>, C> {	
		
	private final ServiceHandler<HS,C> serviceHandler;
	private final HermesEventHandlerInternalDelegate<HS, C> eventHandlerInternalDelegate;
	
	@Inject
	public HermesMainEventHandler(HermesEventHandlerInternalDelegate<HS, C> eventHandlerInternalDelegate, ServiceHandler<HS,C> serviceHandler) {
		this.eventHandlerInternalDelegate = eventHandlerInternalDelegate;		
		this.serviceHandler = serviceHandler;		
	}
	
	public void dispatchOnCreate() {
//Log.d("HermesMainEventHandler:42","onCreate: starting service");
		serviceHandler.startService();
//Log.d("HermesMainEventHandler:44","onCreate: started service");
	}
	
	public void dispatchOnStart() {
//Log.d("HermesMainEventHandler:48","onStart: binding service");
		eventHandlerInternalDelegate.dispatchOnStart();
//Log.d("HermesMainEventHandler:50","onStart: post binding service");		
	}
	
	
	public void dispatchOnBackPressed() {
		eventHandlerInternalDelegate.dispatchOnBackPressed();
	}
	
	public void dispatchOnDestroy() {
//Log.d("MainEventHandler","onDestroy: stopping");
		serviceHandler.stopService();
		/*try {
		} catch (NullPointerException e) {
		}*/		
	}	
}
