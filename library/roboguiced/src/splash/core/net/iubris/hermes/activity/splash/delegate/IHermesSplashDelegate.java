/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * IHermesSplashDelegate.java is part of hermes.
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
package net.iubris.hermes.activity.splash.delegate;

import net.iubris.hermes.activity.splash.HermesSplash;
import net.iubris.hermes.service.IHermesService;
import android.app.Activity;
import android.app.Service;

public interface IHermesSplashDelegate
//<HA extends HermesClient<C,HS>, HS extends IHermesService<C>, C> {
<A extends Activity, HS extends Service & IHermesService<C>, C> 
extends HermesSplash {
	public Class<HS> getServiceToStart();
	public Class<A> getActivityToStart();
	public int getLayoutResID();
}
