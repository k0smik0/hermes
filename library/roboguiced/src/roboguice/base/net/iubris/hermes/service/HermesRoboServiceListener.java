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

import net.iubris.hermes.service.binder.HermesServiceBinder;
import roboguice.event.Observes;
import roboguice.service.event.OnDestroyEvent;
import roboguice.service.event.OnStartEvent;
import roboguice.util.Ln;
import android.app.Service;
import android.os.IBinder;

import com.google.inject.Inject;

//@ContextSingleton
public class HermesRoboServiceListener<HS extends Service & HermesService<C>,C> {		
	
	private IBinder binder;
	private final HS hermesRoboService;
		
	@Inject
	public HermesRoboServiceListener(HS hermesRoboService) {
		this.hermesRoboService = hermesRoboService;		
	}
	
	/*
	public void init(HS hermesRoboService) {
Ln.d("initted");
		this.hermesRoboService = hermesRoboService;
	}*/
	
	/*public void doOnCreate(@Observes OnCreateEvent onCreateEvent) {		
Ln.d("HermesServiceListener: onCreate");
Ln.d("HermesServiceListener: hermesRoboService "+ hermesRoboService.hashCode());
Ln.d(this.getClass().getSimpleName()+": binding");
//		binder = new HermesServiceBinder<HS, C>(hermesRoboService);
Ln.d(this.getClass().getSimpleName()+": bounded");		
		//hermesRoboService.setBinder(new HermesServiceBinder<HS, C>(hermesRoboService));
	}*/
	
	public void doOnStart(@Observes OnStartEvent onStartEvent) {		
Ln.d("HermesServiceListener: onStart");
Ln.d("HermesServiceListener: hermesRoboService "+ hermesRoboService.hashCode());
Ln.d(this.getClass().getSimpleName()+": binding");
				binder = new HermesServiceBinder<HS, C>(hermesRoboService);
Ln.d(this.getClass().getSimpleName()+": bounded");		
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
