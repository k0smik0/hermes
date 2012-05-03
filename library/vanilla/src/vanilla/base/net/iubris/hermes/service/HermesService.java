/*******************************************************************************
 * Copyleft 2012 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesService.java is part of hermes.
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
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

abstract public class HermesService
//<C> extends Service implements IHermesService<C> {
<HS extends Service & IHermesService<C>, C> 
extends Service implements IHermesService<C> {

	protected IBinder binder;
	
	@Override
	public IBinder onBind(Intent intent) {
		doOnBind();
		return binder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		doOnUnBind();
		return super.onUnbind(intent);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		binder = new HermesServiceBinder<HermesService<HS,C>,C>(this);
	}
	
	@Override
	public void onDestroy() {
		binder = null;
	}
	
	@Override
	public void doOnBind() {}	
	@Override
	public void doOnUnBind() {}
}
