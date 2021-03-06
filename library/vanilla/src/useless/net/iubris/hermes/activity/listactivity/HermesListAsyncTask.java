/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * HermesListAsyncTask.java is part of 'Hermes'.
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
package net.iubris.hermes.activity.listactivity;

import net.iubris.hermes.client.HermesClient;
import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

public abstract class HermesListAsyncTask<HA extends Activity & HermesClient<C>, 
	AA extends ArrayAdapter<Value>, Value, Params, Result, C> 
extends AsyncTask<Params, Value, Result>{
			
	protected final HA activity;
	protected final AA adapter;
	//protected final ProgressDialog progressDialog;

	public HermesListAsyncTask(HA activity, AA adapter/*, ProgressDialog progressDialog*/) {
		this.activity = activity;
		this.adapter = adapter;
		//this.progressDialog = new ProgressDialog( (Context)activity );
		//this.progressDialog = progressDialog;
	}
	/*
	@Override
	protected void onPreExecute() {
		progressDialog.show();
		adapter.clear();
		//adapter.notifyDataSetChanged();
	}	
	
	@Override
	protected void onPostExecute(Result result) {
		adapter.notifyDataSetChanged();
		progressDialog.dismiss();
	}*/
}
