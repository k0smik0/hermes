/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesClientWrapper.java is part of hermes.
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
package net.iubris.hermes.client.delegate;

import net.iubris.hermes.Hermes;
import net.iubris.hermes.client.HermesClient;
import net.iubris.hermes.connector.Connector;
import net.iubris.hermes.connector.RoboConnector;
import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import net.iubris.hermes.service.HermesService;
import roboguice.util.Ln;
import android.app.Service;
import android.content.Context;
import android.widget.Toast;

import com.google.inject.Inject;

public class HermesClientDelegate
<HS extends Service & HermesService<C>, C> 
implements HermesClient<C> {

	private final RoboConnector<HS, C> connector;
	private final Context context;

	@Inject
	public HermesClientDelegate(Connector<HS, C> connector, Context context) {
		this.connector = connector;
		this.context = context;
	}
	
	@Override
	public C getController()  {	
		try {
Ln.d("getController");
			return Hermes.getController(context, connector);
		} catch (ControllerUnavailableException e) {
			onException(e);
		}
		return null;
	}

	
	/**
	 * Here you could show a toast as: <br/> "something heavy wrong:<br/>please re-press button"
	 * @param e
	 */
	protected void onException(ControllerUnavailableException e) {
		Toast.makeText(context, "something heavy wrong: please repress button", Toast.LENGTH_SHORT).show();
	}
}
