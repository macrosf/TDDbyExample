package com.stackoverflow.q2275443;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestExecutor {
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<String> future = executor.submit(new Task());
		try{
			System.out.println("started...");
			System.out.println(future.get(3, TimeUnit.SECONDS));
			System.out.println("finished");
		}catch (TimeoutException e){
			future.cancel(true);
			System.out.println("Terminated!");
		}
		executor.shutdownNow();
	}
}

class Task implements Callable<String>{

	public String call() throws Exception {
		Thread.sleep(4000);
		return "Ready";
	}
	
}