package net.iubris.hermes.connector;

import net.iubris.hermes.service.HermesService;
import android.app.Application;
import android.app.Service;

public class ConnectorProvider<HS extends Service & HermesService<C>,C> {

	private static final ConnectorProvider<HS,C> instance = new ConnectorProvider<HS, C>();
	private Connector<HS,C> connector;
	
//	private static Connector<HS, C> connector;

	public static ConnectorProvider<HS,C> getInstance() {
		/*if (instance==null)
			instance = new ConnectorProvider();*/
		return instance;
	}	
	private ConnectorProvider(){};
	
	

	public Connector<HS, C> getConnector(Application applicationContext, Class<HS> serviceClass) {
		if (connector==null)
			connector = new Connector<HS, C>(applicationContext, serviceClass);
		return connector;
	}
}
