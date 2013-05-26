package net.iubris.ex.test;

import net.iubris.hermes.connector.exception.ControllerUnavailableException;

public class ExtClass extends AbstractClient<String> {

	
	@Override
	public String call() throws ControllerUnavailableException {
		throw new ControllerUnavailableException("no");
		//return null;
	}
	
	protected void onException(ControllerUnavailableException e) {
		System.out.println("EC: cue exception");
		super.onException(e);
	}

}
