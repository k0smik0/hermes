/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesRoboServiceListener.java is part of hermes.
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
package net.iubris.hermes.service;

import com.google.inject.Inject;

import android.app.Service;
import android.os.IBinder;
import net.iubris.hermes.service.binder.HermesServiceBinder;
import roboguice.event.Observes;
import roboguice.inject.ContextSingleton;
import roboguice.service.event.OnCreateEvent;
import roboguice.service.event.OnDestroyEvent;
import roboguice.util.Ln;

@ContextSingleton
public class HermesRoboServiceListener<HS extends Service & IHermesService<C>,C> {		
	
	private IBinder binder;
	private HS hermesRoboService;
		
	@Inject
	public HermesRoboServiceListener(HS hermesRoboService) {
		this.hermesRoboService = hermesRoboService;		
	}
	
	/*
	public void init(HS hermesRoboService) {
Ln.d("initted");
		this.hermesRoboService = hermesRoboService;
	}*/
	
	public void doOnCreate(@Observes OnCreateEvent onCreateEvent) {		
		Ln.d("HermesServiceListener: onCreate");
		binder = new HermesServiceBinder<HS, C>(hermesRoboService);
		//hermesRoboService.setBinder(new HermesServiceBinder<HS, C>(hermesRoboService));
	}
	
	/*
	public void doOnStart(@Observes OnStartEvent onStartEvent) {
		if (binder == null) binder = new HermesServiceBinder<HS, C>(hermesRoboService);
			//hermesRoboService.setBinder(new HermesServiceBinder<HS, C>(hermesRoboService));
	}*/
	
	public void doOnDestroy(@Observes OnDestroyEvent onDestroyEvent) {
		binder = null;
		//hermesRoboService.setBinder(null);		
	}
	
	public IBinder dispatchOnBind() {
		return binder;
	}
}
