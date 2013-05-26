package net.iubris.ex.test;

import java.util.concurrent.Callable;

public abstract class AbstractClient<V> implements Callable<V>{
	
	public void execute() {
		try {
			success(call());
		} catch (Exception e) {
			onException(e);
		}
	}	

	protected void onException(Exception e) {
		System.out.println("AC: exception");
	}
	
	protected void success(V v) throws Exception {
		System.out.println("AC: success");
	}
}
