/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * ExtTest.java is part of 'Hermes'.
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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import roboguice.activity.RoboActivity;
import android.content.Context;
import android.test.ActivityTestCase;

import com.xtremelabs.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class ExtTest extends ActivityTestCase {

	//private RoboInjector injector;
	protected Context context = new RoboActivity();
	ExtendRoboAsyncTask extendRoboAsyncTask;
	
	
	//Async async;
	//AsyncCompletion asyncCompletion;
	
	@Before	
	public void setUp() {		
		System.out.println("setUp");
		extendRoboAsyncTask = new ExtendRoboAsyncTask(context);
		
		/*asyncCompletion = new AsyncCompletion() {			
			@Override
			public void run(State arg0) throws Exception {
				System.out.println("here");
				throw new ControllerUnavailableException("nono");				
			}
			@Override
			public void onException(Throwable arg0) {
				System.out.println("E: "+arg0);
				super.onException(arg0);
			}
			
			public void onException(ControllerUnavailableException e) {
				System.out.println("CUE: "+e);
			}
			
		};*/
	}
	
	@Test
	public void test() {
		System.out.println("test");/*
		try {
			extendRoboAsyncTask.future().get();
		} catch (InterruptedException e) {
			System.out.println("ie: "+e);
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println("ee: "+e);
			e.printStackTrace();
		}
		*/
		//new ExtClass().execute();
		extendRoboAsyncTask.execute();
		/*try {
			extendRoboAsyncTask.call();
		} catch (ControllerUnavailableException e) {
			System.out.println("main:");
			extendRoboAsyncTask.onException(e);			
		}*/
		//AsyncRunner as = new DefaultRunner(1, "TEST_RUNNER");
		//as.start(asyncCompletion);
				
		//new SC().execute();
		/*try {
			new SC().future().get();
		} catch (InterruptedException e) {			
			e.printStackTrace();
			System.out.println("ie");
		} catch (ExecutionException e) {
			System.out.println("ee");
			e.printStackTrace();
		}*/
		//extendRoboAsyncTask.execute();
	}
}
