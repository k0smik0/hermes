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

import net.iubris.hermes.activity.main.event.EventHandler;
import net.iubris.hermes.application.wrapper.HermesApplicationWrapper;
import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.service.IHermesService;
import android.app.Application;
import android.app.Service;

public abstract class HermesApplication<HS extends Service & IHermesService<C>,C> 
extends Application 
implements IHermesApplication<HS, C> {

	private HermesApplicationWrapper<HS,C> hermesApplicationWrapper;
	
	@Override
	public void onCreate() {		
		super.onCreate();		
		hermesApplicationWrapper = new HermesApplicationWrapper<HS,C>(this,providesHSClass());				
	}
	@Override
	public Connector<HS,C> getConnector() {		
		return hermesApplicationWrapper.getConnector();
	}
	@Override
	public EventHandler<HS,C> getEventHandler() {
		return hermesApplicationWrapper.getEventHandler();
	}
	@Override
	public abstract Class<HS> providesHSClass();
}
