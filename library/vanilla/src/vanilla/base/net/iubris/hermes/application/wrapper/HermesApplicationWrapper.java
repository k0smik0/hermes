/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesApplicationWrapper.java is part of hermes.
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
package net.iubris.hermes.application.wrapper;

import net.iubris.hermes.HermesCoreProvider;
import net.iubris.hermes.activity.main.event.EventHandler;
import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.service.IHermesService;
import net.iubris.hermes.service.handler.ServiceHandler;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Service;
import android.content.Context;

public class HermesApplicationWrapper<HS extends Service & IHermesService<C>,C>  
implements HermesCoreProvider<HS, C> {

	private Connector<HS, C> connector;
	private EventHandler<HS, C> eventHandler;

	public HermesApplicationWrapper(Application application, Class<HS> hermeServiceClass) {
		connector = new Connector<HS, C>(application, hermeServiceClass );		
		ServiceHandler<HS, C> serviceHandler = new ServiceHandler<HS, C>(application, hermeServiceClass, (ActivityManager) application.getSystemService(Context.ACTIVITY_SERVICE));		
		eventHandler = new EventHandler<HS, C>(this.connector, serviceHandler);	
	}
	
	@Override
	public Connector<HS, C> getConnector() {
		return connector;
	}

	@Override
	public EventHandler<HS, C> getEventHandler() {
		return eventHandler;
	}
}
