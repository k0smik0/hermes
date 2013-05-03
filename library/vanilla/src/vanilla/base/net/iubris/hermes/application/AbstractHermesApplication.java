/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesApplication.java is part of hermes.
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
package net.iubris.hermes.application;

import net.iubris.hermes.application.delegate.HermesCoreProviderApplicationDelegate;
import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.event.HermesMainEventHandler;
import net.iubris.hermes.providers.HermesProvider;
import net.iubris.hermes.service.HermesService;
import android.app.Application;
import android.app.Service;

public abstract class AbstractHermesApplication<HS extends Service & HermesService<C>,C> 
extends Application 
implements HermesProvider<HS, C> {

	private HermesCoreProviderApplicationDelegate<HS,C> hermesCoreProviderDelegate;
	
	@Override
	public void onCreate() {		
		super.onCreate();		
		hermesCoreProviderDelegate = new HermesCoreProviderApplicationDelegate<HS,C>(this, providesHSClass() );				
	}
	@Override
	public Connector<HS,C> getConnector() {		
		return hermesCoreProviderDelegate.getConnector();
	}
	@Override
	public HermesMainEventHandler<HS, C> getMainEventHandler() {
		return hermesCoreProviderDelegate.getMainEventHandler();
	}
	
	protected abstract Class<HS> providesHSClass();
}
