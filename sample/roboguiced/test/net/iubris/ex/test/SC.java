/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * SC.java is part of 'Hermes'.
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

import roboguice.util.SafeAsyncTask;

public class SC extends SafeAsyncTask<String> {
	
	@Override
	public void execute() {
		System.out.println("executing");
		super.execute();
	}
	
	@Override
	public String call() throws NumberFormatException {
		//System.out.println("call and throwing");
		//if ("aa".equals("ee")) return "ii";
		throw new NumberFormatException("nfe throwed");
	}
	
	/*@Override
	protected void onException(Exception e) throws RuntimeException {		
		super.onException(e);
	}*/
	
	
	
	
	@Override
	protected void onException(Exception e) {
		System.out.println(e);
		try {
			throw e.getCause();
		} catch (NumberFormatException nfe) {
			System.out.println("nfe: "+nfe);
		} catch (Throwable t) {
			t.printStackTrace();
		}		
		System.out.println("E "+e);
		//super.onException(e);
	}
	
	@Override
	protected void onSuccess(String t) throws Exception {		
		System.out.println(t);
	}
	
	@Override
	protected void onThrowable(Throwable t) throws RuntimeException {		
		System.out.println("onThrowable"+t);
		super.onThrowable(t);
	}

}
