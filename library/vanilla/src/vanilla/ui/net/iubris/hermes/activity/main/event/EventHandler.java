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
package net.iubris.hermes.activity.main.event;

import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.service.IHermesService;
import net.iubris.hermes.service.handler.ServiceHandler;
import android.app.Service;

public class EventHandler<HS extends Service & IHermesService<C>, C> {	
		
	private final Connector<HS,C> connector;	
	private final ServiceHandler<HS,C> serviceHandler;		
	
	public EventHandler(Connector<HS,C> connector, ServiceHandler<HS,C> serviceHandler) {
		this.connector = connector;
		this.serviceHandler = serviceHandler;		
	}
	
	public void dispatchOnCreate() {
		serviceHandler.startService();
	}
	
	public void dispatchOnResume() {
		if (!connector.isBound() ) {
			connector.doBindService();
		}
	}	

	public void dispatchOnDestroy() {
//Log.d("EventHandler","destroying");
//Log.d("EventHandler","destroy: unbind");
		try {
			connector.doUnbindService();
		} catch (NullPointerException e) {
//Log.d("EventHandler","unbinding: npe");
			e.printStackTrace();
		}
//Log.d("EventHandler","destroy: stop service");
		try {			
			serviceHandler.stopService();
		} catch (NullPointerException e) {
//Log.d("EventHandler","stopping: npe");
			e.printStackTrace();
		}		
	}	
}
