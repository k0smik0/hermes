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
