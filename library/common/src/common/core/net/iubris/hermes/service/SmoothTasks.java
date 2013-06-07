package net.iubris.hermes.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.util.Log;

public class SmoothTasks {
	
	static public void execute(List<Runnable> threadsToStart) {
//		ExecutorService executorService = Executors.newFixedThreadPool(threadsToStart.size());
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		int threadPoolNumber = availableProcessors/*+1*/;
Log.d("SmoothTasks:15","processors: "+availableProcessors+" - using "+threadPoolNumber);
		ExecutorService executorService = Executors.newFixedThreadPool(threadPoolNumber);
Log.d("SmoothTasks:17","current thread is: "+Thread.currentThread().getName());

		for (Runnable runnable: threadsToStart) {
			executorService.execute(runnable);
		}
		/*try {
			executorService.invokeAll(threadsToStart);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		executorService.shutdown();
	}

}
