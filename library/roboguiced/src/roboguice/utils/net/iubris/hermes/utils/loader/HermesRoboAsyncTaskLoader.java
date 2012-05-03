/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesRoboAsyncTaskLoader.java is part of hermes.
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
package net.iubris.hermes.utils.loader;

import com.google.inject.Inject;

import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes.service.IHermesService;
import android.app.Service;
import android.content.Context;
import roboguice.content.RoboAsyncTaskLoader;

public abstract class HermesRoboAsyncTaskLoader<Result, HS extends Service & IHermesService<C>, C> extends RoboAsyncTaskLoader<Result> {

	@Inject private Connector<HS, C> connector;
	
	public HermesRoboAsyncTaskLoader(Context context) {
		super(context);
	}
	
	public C getController() throws ControllerUnavailableException {
		return connector.getControllerExposerService().getController();
	}

}
