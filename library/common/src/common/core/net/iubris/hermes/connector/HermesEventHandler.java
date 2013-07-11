/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesEventHandlerInternalDelegate.java is part of 'Hermes'.
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
package net.iubris.hermes.connector;

import javax.inject.Inject;

import net.iubris.hermes.service.HermesService;
import android.app.Service;

public class HermesEventHandler<HS extends Service & HermesService<C>, C> {
	
	protected final Connector<HS,C> connector;
	
	@Inject
	public HermesEventHandler(Connector<HS, C> connector) {
		this.connector = connector;
	}

	public void dispatchOnStart(){
		if (!connector.isServiceBound() ) {
//Log.d("HermesEventHandlerInternalDelegate:20","dispatchOnStart: "+connector.getContext()+" binding");
			connector.doBindService();
		}
	}
	
	public void dispatchOnBackPressed() {
//Log.d("HermesEventHandlerInternalDelegate:26","dispatchOnBackPressed: "+connector.getContext()+" unbinding");
		try {
			connector.doUnbindService();
		} catch (IllegalArgumentException e) {}
	}
	
}
