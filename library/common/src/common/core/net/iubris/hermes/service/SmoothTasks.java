/*******************************************************************************
 * Copyleft 2013 Massimiliano Leone - massimiliano.leone@iubris.net .
 * 
 * SmoothTasks.java is part of 'Hermes'.
 * 
 * 'Hermes' is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * 'Hermes' is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with 'Hermes' ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
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
