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
