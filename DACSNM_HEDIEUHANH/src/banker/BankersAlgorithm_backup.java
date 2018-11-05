package banker;

import java.util.*;

public class BankersAlgorithm_backup {
 
	private Process[] processArray;
	private int[] available;
	public BankersAlgorithm_backup() {
		super();
	}
	
	private void init() {
		
		Data data = new Data();
		Properties prop = data.readData();
		
		int process_number = Integer.parseInt(prop.getProperty("num-process"));
		processArray = new Process[process_number];
		
		int resource_number = Integer.parseInt(prop.getProperty("num-sources"));
		available = new int[resource_number];
		
		String vector = prop.getProperty("available");
		String[] vector_array = vector.split(" ");
		
		for (int i = 0; i < resource_number; i++) {
			available[i] =  Integer.parseInt(vector_array[i]);
		}
				
		for (int i = 0; i < process_number; i++) {
			Process cur = new Process(resource_number);
			cur.id = i;
			processArray[i] = cur;
			String[] input = (prop.getProperty("p"+i)).split("-");
			String[] available = input[0].split(" ");
			
			for (int j = 0; j < available.length; j++) {
				cur.allocation[j] = Integer.parseInt(available[j]);
			}
			
			String[] max = input[1].split(" ");
			
			for (int j = 0; j < max.length; j++) {
				cur.max[j] = Integer.parseInt(max[j]);
			}			
			
			for (int j = 0; j < max.length; j++) {
				cur.need[j] = cur.max[j] - cur.allocation[j];
			}			
		}
	}
	
	private void go() {	
		if (!isSafe()) {
			System.out.println("Input System is not Safe! Please try again!");
			return;
		}
				
		for (int i = 0; i < processArray.length; i++) {
			Thread t = new Thread(new Request(processArray[i]));
			t.start();
		}
		
		while (true) {						
			if (allDone()) {
				System.out.println("All processes are finished! Algorithm Done!");
				return;
			}
		}
	}
	
	private boolean allDone() {
		for (int i = 0; i < processArray.length; i++) {
			if (processArray[i].isFinished == false) {
				return false;
			}
		}
		return true;
	}	
	
	private synchronized boolean isSafe() {
		int[] work = new int[available.length];
		System.arraycopy(available, 0, work, 0, available.length);
		boolean[] finish = new boolean[processArray.length];		
		Arrays.fill(finish, false);
		
		while (true) {
			int index = -1;
			for (int i = 0; i < finish.length; i++) {
				if (finish[i] == false && arrayCompareSmallerOrEqual(processArray[i].need, work)) {
					index = i;
					break;
				}
			}
			
			if (index == -1) {
				if (!containFalse(finish)) {
					return true;
				} else {
					return false;
				}								
			} else {
				finish[index] = true;
				for (int i = 0; i < work.length; i++) {
					work[i] = work[i] + processArray[index].allocation[i];
				}
			}			
		}		
	}
	
	private boolean arrayCompareSmallerOrEqual(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] > b[i]) {
				return false;
			}
		}
		return true;
	}
	
	private boolean containFalse(boolean[] input) {
		for (int i = 0; i < input.length; i++) {
			if (input[i] == false) {
				return true;
			}
		}
		return false;
	}

	private class Request implements Runnable {
		
		private Process process;
		
		public Request(Process process) {
			this.process = process;
		}
		
		@Override
		public synchronized void run() {
			while (true) {	
				if (process.isFinished()) {
					System.out.println("Process " + process.id + " is finished!");
					return;
				}
				
				int[] request = getRandomArray(process.need);
				
				if (!arrayCompareSmallerOrEqual(request, process.need)) {
					System.out.println("Process " + process.id + " try to generate a request " + Arrays.toString(request) 
					+ " Exceeding its maximum claim. Request is denied!");
					continue;
				}
				
				if (!arrayCompareSmallerOrEqual(request, available)) {
					System.out.println("Process " + process.id + " try to generate a request " + Arrays.toString(request) 
					+ " Exceeding available resources. Request is denied!");
					continue;
				}
				
				for (int i = 0; i < request.length; i++) {
					available[i] = available[i] - request[i];
					process.allocation[i] = process.allocation[i] + request[i];
					process.need[i] = process.need[i] - request[i];
				}
								
				if (isSafe()) {
					System.out.println("Process " + process.id + " try to generate a request " + Arrays.toString(request) 
					+ " Safe state. Make request!");
					for (int i = 0; i < request.length; i++) {
						available[i] = available[i] + process.allocation[i];
						process.allocation[i] = 0;						
					}
				} else {
					System.out.println("Process " + process.id + " try to generate a request " + Arrays.toString(request) 
					+ " Unsafe state. Request is denied!");
					for (int i = 0; i < request.length; i++) {
						available[i] = available[i] + request[i];
						process.allocation[i] = process.allocation[i] - request[i];
						process.need[i] = process.need[i] + request[i];
					}
				}
			}
		}
	
		private int[] getRandomArray(int[] input) {
			int[] res = new int[input.length];
			Random r = new Random();
			for (int i = 0; i < input.length; i++) {
				int cur = r.nextInt(input[i] + 1);
				res[i] = cur;
			}
			return res;
		}		
	}

	private class Process {
		private int id;
		private int[] max;
		private int[] allocation;
		private int[] need;
		private boolean isFinished;
		
		public Process(int totalResource) {
			id = -1;
			max = new int[totalResource];
			allocation = new int[totalResource];
			need = new int[totalResource];
			isFinished = false;
		}
		
		public boolean isFinished() {
			for (int i = 0; i < need.length; i++) {
				if (need[i] > 0) {
					isFinished = false;
					return false;
				}
			}
			isFinished = true;
			return true;
		}		
	}
		
	public static void main(String[] args) {
		BankersAlgorithm_backup bankers_algorithm = new BankersAlgorithm_backup();
		bankers_algorithm.init();
		bankers_algorithm.go();
	}	
}