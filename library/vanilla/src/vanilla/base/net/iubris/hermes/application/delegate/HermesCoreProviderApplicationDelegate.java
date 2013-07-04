/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesCoreProviderApplicationDelegate.java is part of 'Hermes'.
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
package net.iubris.hermes.application.delegate;

import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.HermesEventHandlerInternalDelegate;
import net.iubris.hermes.event.HermesMainEventHandler;
import net.iubris.hermes.providers.HermesProvider;
import net.iubris.hermes.service.HermesService;
import net.iubris.hermes.service.handler.ServiceHandler;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Service;
import android.content.Context;

public class HermesCoreProviderApplicationDelegate<HS extends Service & HermesService<C>,C> implements HermesProvider<HS, C> {

	private Connector<HS, C> connector;
//	private HermesEventHandler<HS, C> eventHandler;
	private HermesMainEventHandler<HS, C> mainEventHandler;
	private HermesEventHandlerInternalDelegate<HS, C> internalDelegate;

	
	public HermesCoreProviderApplicationDelegate(Application applicationContext, Class<HS> hermesServiceClass) {
		connector = new Connector<HS, C>(applicationContext, hermesServiceClass );		
				
//		eventHandler = new HermesEventHandler<HS, C>(connector);
		internalDelegate = new HermesEventHandlerInternalDelegate<HS, C>(connector);

		final ServiceHandler<HS, C> serviceHandler = new ServiceHandler<HS, C>(applicationContext, hermesServiceClass, (ActivityManager) applicationContext.getSystemService(Context.ACTIVITY_SERVICE));
		mainEventHandler = new HermesMainEventHandler<HS, C>(internalDelegate,serviceHandler);
	}
		
	@Override
	public Connector<HS, C> getConnector() {
		return connector;
	}

	@Override
	public HermesMainEventHandler<HS, C> getMainEventHandler() {
		return mainEventHandler;
	}
}
