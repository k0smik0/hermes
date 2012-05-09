/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesButtonListener.java is part of hermes.
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
package net.iubris.hermes.view.listener.button;

import android.view.View.OnClickListener;
import net.iubris.hermes.client.HermesClient;

abstract public class HermesButtonListener
//<HA extends IHermesActivity<C, HS>, C, HS extends IHermesService<C>>
<HC extends HermesClient<C>, C> implements OnClickListener {

	protected final HC hermesClient;

	public HermesButtonListener(HC hermesClient) {
		this.hermesClient = hermesClient;
	}
}
