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
