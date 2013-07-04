/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesMainRoboActivityListener.java is part of 'Hermes'.
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
package net.iubris.hermes.event.listener;

import net.iubris.hermes.event.HermesMainEventHandler;
import net.iubris.hermes.service.HermesService;
import roboguice.activity.event.OnCreateEvent;
import roboguice.activity.event.OnDestroyEvent;
import roboguice.activity.event.OnStartEvent;
import roboguice.event.Observes;
import android.app.Service;

import com.google.inject.Inject;

//@ContextSingleton
public class HermesMainRoboActivityListener<HS extends Service & HermesService<C>, C> 
//extends HermesRoboActivityListener<HS, C> 
{
	
	private final HermesMainEventHandler<HS,C> mainEventHandler;
		
	@Inject
	public HermesMainRoboActivityListener(HermesMainEventHandler<HS,C> mainEventHandler) {
		this.mainEventHandler = mainEventHandler;
	}

	public void dispatchOnCreate(@Observes OnCreateEvent onCreateEvent) {
//Log.d("HermesMainRoboActivityListener:46","dispatchOnCreate");
		mainEventHandler.dispatchOnCreate();
	}
	
	public void dispatchOnStart(@Observes OnStartEvent onStartEvent) {
//Log.d("HermesMainRoboActivityListener:51","dispatchOnStart:");
		mainEventHandler.dispatchOnStart();
	}
	
	public void dispatchOnDestroy(@Observes OnDestroyEvent onDestroyEvent) {
		doOnDestroy();
	}

	public void dispatchOnBackPressed() {
		doOnDestroy();
	}
	
	private void doOnDestroy() {
		mainEventHandler.dispatchOnDestroy();
	}
	
}	
