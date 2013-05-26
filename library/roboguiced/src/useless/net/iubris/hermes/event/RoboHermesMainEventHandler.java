package net.iubris.hermes.event;

import javax.inject.Inject;

import roboguice.activity.event.OnCreateEvent;
import roboguice.activity.event.OnDestroyEvent;
import roboguice.event.Observes;

import net.iubris.hermes.connector.RoboHermesEventHandlerInternalDelegate;
import net.iubris.hermes.service.HermesService;
import net.iubris.hermes.service.handler.RoboServiceHandler;
import android.app.Service;
import android.util.Log;

public class RoboHermesMainEventHandler<HS extends Service & HermesService<C>, C> extends HermesMainEventHandler<HS, C> {
	@Inject
	public RoboHermesMainEventHandler(RoboHermesEventHandlerInternalDelegate<HS, C> eventHandlerInternalDelegate, RoboServiceHandler<HS, C> serviceHandler) {
		super(eventHandlerInternalDelegate, serviceHandler);
	}
	
	public void dispatchOnCreate(@Observes OnCreateEvent onCreateEvent) {
Log.d("HermesMainRoboActivityListener","doOnCreate: start service");
		dispatchOnCreate();
	}
	
	/*public void doOnResume(@Observes OnResumeEvent onResumeEvent) {
		dispatchOnResume();
	}*/
	
	public void dispatchOnDestroy(@Observes OnDestroyEvent onDestroyEvent) {
		doOnDestroy();
	}

	public void dispatchOnBackPressed() {
		doOnDestroy();
	}
	
	private void doOnDestroy() {
		dispatchOnDestroy();
	}
}
