# Hermes

##mvc for android
<br/>
the idea is using (local) Service as persistent container for a custom controller, and then provide a component which make activities possible to communicate with service, just with a method call.

Why *Hermes* ? <br/>
Because the name: Hermes is communication god, in greak mythology.
So Hermes components make easy communication between activities and service.

Stop to bind, create connection, cast, and so on.
You retrieve controller istance, et voila.

Of course, there are still binder, connection, cast local service behind the scene,
but you don't have to care about all these. 
<br/><br/><br/>
In views (activities, buttons, views, or any class you consider "view" in meaning of mvc pattern) 
you can, alternatively:

* implement HermesClient and its methods, in order to access your controller

	or
	
* retrieve an instance of Connector, which provides the custom controller instance from service 
  
In "MainActivity" (the start ones, or launcher/dashboard) you have to use HermesMainEventHandler (retrieve an instance from HermesApplication or via roboguice - see example below) in order to manage service start/stop/binding/unbinding events 

<br/><br/>
Of course, honoring this mvc interpretation, you can consider Activity (or Fragment) as view container,<br/>
where you can inject subviews (as button, textarea, listview and so on) and populate their values calling your controller

about versions:

* the vanilla version needs just android libs (but you have to extends Application or inherit from HermesApplication)
* the roboguice version needs also roboguice, an amazing IoC container, based on google guice


consider to use last ones - IoC (Inversion of Control) is (actually) ultimate oop paradigma, and roboguice is an amazing system implementing it in android world, providing annotation for DI (dependency injection)

[and obviously I develop all my apps using this]
	

<br/><br/>I choised not provide really branch, but just 2 different folder, so: 

 * "common" folder contains common parts, 
 * then "vanilla" VS "roboguiced" folders contain different implementations, respectively honoring "pure android" way VS ioc/di (robo)guice way  

after you cloned repository, you have simply exclude uninteresting folders in your ide/ant/etc, in order to not compile version classes you don't want.

<br/><br/><br/>
###usage:

####1) your service must extends HermesService (or HermesRoboService, in roboguiced version): template parameters are YourService (itself) and YourController, so:
<pre>
public class MyService extends HermesRoboService<MyService,MyController> { // roboguiced version - or you can use HermesService
   @Inject MyController myController; // in vanilla version you have to instance this in OnCreate
   ...
}
</pre>

####2) main activity (the start one) is handler for start service when application start and stop it on OnDestroy.
##### main activity should be as "single_task" if there are other activities - so, service will live only during mainactivity lifecycle.
##### (really, it can be as always single_task also if it is unique activity in entire application) 
##### for listener and abstract class template (see examples below): default behaviour is just this one.
##### for "single_task" you can use FLAG_ACTIVITY_NEW_TASK - see also 
[Dashboard pattern](http://www.androiduipatterns.com/2011/02/ui-design-pattern-dashboard.html) 

<br/><br/>
So, different ways are possible:

*roboguiced way*
<pre>
//roboguiced way, using composite
public class MyMainRoboActivity extends RoboActivity { //roboguiced way
	@Inject private HermesMainRoboActivityListener&lt;MyService,MyController&gt; listener;
    
    // if u want stop service when user press back button in this main activity
    @Override
	public void onBackPressed() { 
		listener.dispatchOnBackPressed(); // stop service
		super.onBackPressed();
	}    
    ....your code...    
}
</pre>
OR
<pre>
//roboguiced way, using inheritance
// HermesMainRoboActivityListener is just a class with listener yet injected, and onBackPressed as above
public class MainActivity extends HermesMainRoboActivity&lt;MyService,MyController&gt; { //roboguiced way

    ....your code...
}
</pre>
<br/>
VS 
<br/><br/>
*vanilla way*
<br/>
<pre>
// vanilla way, using composite
public class MyMainActivity {

	private HermesMainEventHandler&lt;MyService,MyController&gt; eventHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);				
		eventHandler = ((MyApplication)getApplication()).getMainEventHandler();		
		eventHandler.dispatchOnCreate();
	}	
	@Override
	protected void onStart() {
		super.onStart();
		eventHandler.dispatchOnStart();	
	}		
	@Override
	public void onBackPressed() {
		eventHandler.dispatchOnDestroy();
		super.onBackPressed();
	}		
	@Override
	protected void onDestroy() {
		eventHandler.dispatchOnDestroy();
		super.onDestroy();
	}
	...your code...
}

	and extending (android) Application, you must have something as:
	
// vanilla way, using composite
public MyApplication extends Application implements HermesProvider &lt;MyService,MyController&gt; {
	private HermesCoreProviderApplicationDelegate&lt;MyService,MyController&gt; delegate;
    @Override
	public void onCreate() {	
		super.onCreate();		
		delegate = new HermesApplicationProviderDelegate&lt;MyService,MyController&gt;(this,providesHSClass());				
	}
	@Override
	public Connector&lt;MyService,MyController&gt; getConnector() {		
		return delegate.getConnector();
	}
	@Override
	public EventHandler&lt;MyService,MyController&gt; getEventHandler() {
		return delegate.getEventHandler();
	}
	@Override
	public Class&lt;MyService&gt; providesHSClass() {
		return MyService.class;    
	}
}

	or simply extends HermesApplication as:

// vanilla way, using inheritance
public class MyApplication extends HermesApplication&lt;MyService,MyController&gt; {
	// this is abstract in HermesApplication superclass
	@Override
	public Class&lt;MyService&gt; providesHSClass() {
		return MyService.class;    
	}
}
</pre>


####3) call controller in your activities/etc like above:

*roboguiced way*
<pre>
//roboguiced way, using composite
public MyActivity extends RoboActivity { // it can be Fragment, or Button, or any class you want
	@Inject Connector&lt;MyService,MyController&gt; connector;

	@Override
	protected void onResume() { // but it can be onCreate, or another you prefer
		MyController mc = connector.getController();
	    ..use controller...
	}
}
</pre>
OR
<pre>
public MyActivity extends HermesRoboActivity { // there are for Fragment too, or Button, ListActivity and so on...

	@Override
	protected void onResume() { // but it can be onCreate, or method you prefer
		MyController mc = getController(); // HermesRobo*Activity provides getController() method
	    ..use controller...
	}
}
</pre>
VS *vanilla way*
<pre>
// vanilla way, using composite
public class HermeSampleActivity extends Activity {}
public class MyActivity extends Activity {

	private Connector<MyService, MyController> connector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		connector = ((HermesSampleApplication)getApplication()).getConnector();		
	}	
	@Override
	protected void onResume() {		
		super.onResume();
		MyController hsc = hermesClientWrapper.getController();
		...use controller...
	}

OR

//vanilla way, using inheritance
public class MyActivity extends HermesActivity { // or Fragment, ListActivity, etc.
	@Override
	protected void onResume() { // but it can be onCreate, or method you prefer
		MyController mc = getController(); // Hermes*Activity provides getController() method
	    ..use controller...
	}
}
</pre>

#####Be careful with "getController()"! It is blocking! <br/>
#####You must use HermesConnectingAsyncTask or HermesConnectingRoboAsyncTask if you want retrieve controller within "onResume" (see examples below!)

<br/><br/><br/><br/>
some examples in source:

[sample for roboguiced branch](samples/roboguiced)

[sample for vanilla branch](samples/vanilla)

<br/><br/><br/><br/>




#### dependencies for roboguice branch:

  
[roboguice2](http://code.google.com/p/roboguice/wiki/Downloads), and guice-no\_aop and javax jars from guice, so:
	
 * [guice3 no_aop (original version)](http://code.google.com/p/google-guice/downloads/list)  
 * [guice3 no_aop (patched version - preferable](https://github.com/sonatype/sisu-guice)