/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * ExtendRoboAsyncTask.java is part of 'Hermes'.
 * 
 * 'Hermes' is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * 'Hermes' is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with 'Hermes'; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.ex.test;

import java.io.FileWriter;
import java.io.IOException;

import net.iubris.hermes.connector.exception.ControllerUnavailableException;
import android.content.Context;

public class ExtendRoboAsyncTask extends net.iubris.test.RoboAsyncTask<String> {
	
	DummyController dc = null;
	private String exception = "";

	protected ExtendRoboAsyncTask(Context context) {
		super(context);
	}

	public String getException() {
		return exception;
	}
	
	@Override
	public String call() throws ControllerUnavailableException {
		exception = "";
		System.out.println("call");
		//return getController().getMessage();
		throw new ControllerUnavailableException("no");
	}
	
	@Override
	protected void onSuccess(String t) {		
		System.out.println("success: "+t);
		//Ln.d("success: "+t);
	}
	
	//@Override
	public void onException(ControllerUnavailableException e) throws RuntimeException {
		FileWriter f;
		try {
			f = new FileWriter("/tmp/erat.txt");
			f.append("ERAT: ControllerUnavailableException: "+e);
			f.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//new JFrame(""+e);
	}
	
	@Override
	protected void onException(Exception e) throws RuntimeException {
		//System.out.println("...exception...");
		System.out.println("Exception "+e);
		/*try {
			throw e;
		} catch (ControllerUnavailableException cue) {
			//System.out.println("cue: "+cue);
			onException(e);
		} catch (NumberFormatException nfe) {
			System.out.println("nfe: "+nfe);
		} catch (Exception t) {
			t.printStackTrace();
			System.out.println("Exception "+e);
			super.onException(e);
		}*/
		//if (e instanceof ControllerUnavailableException) System.out.println("cue "+e);
		//else 
		//
	}

	//@Override
	public DummyController getController() throws ControllerUnavailableException {		
		//if (dc != null ) 	return dc;
		//return new DummyController();
		//System.out.println("throwing");
		throw new ControllerUnavailableException("no");
	}

}
