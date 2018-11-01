package banker;

import java.util.*;

public class BankersAlgorithm_backup {
 
	private Process[] process_array;
	private int[] available;
	public BankersAlgorithm_backup() {
		super();
	}
	
	private void init() {
		
		Data data = new Data();
		Properties prop = data.readData();
		
		int process_number = Integer.parseInt(prop.getProperty("num-process"));
		process_array = new Process[process_number];
		
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
			process_array[i] = cur;
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
		if (!is_safe()) {
			System.out.println("Input System is not Safe! Please try again!");
			return;
		}
				
		for (int i = 0; i < process_array.length; i++) {
			Thread t = new Thread(new Request(process_array[i]));
			t.start();
		}
		
		while (true) {						
			if (all_done()) {
				System.out.println("All processes are finished! Algorithm Done!");
				return;
			}
		}
	}
	
	private boolean all_done() {
		for (int i = 0; i < process_array.length; i++) {
			if (process_array[i].isFinished == false) {
				return false;
			}
		}
		return true;
	}	
	
	private synchronized boolean is_safe() {
		int[] work = new int[available.length];
		System.arraycopy(available, 0, work, 0, available.length);
		boolean[] finish = new boolean[process_array.length];		
		Arrays.fill(finish, false);
		
		while (true) {
			int index = -1;
			for (int i = 0; i < finish.length; i++) {
				if (finish[i] == false && array_compare_smaller_or_equal(process_array[i].need, work)) {
					index = i;
					break;
				}
			}
			
			if (index == -1) {
				if (!contain_false(finish)) {
					return true;
				} else {
					return false;
				}								
			} else {
				finish[index] = true;
				for (int i = 0; i < work.length; i++) {
					work[i] = work[i] + process_array[index].allocation[i];
				}
			}			
		}		
	}
	
	private boolean array_compare_smaller_or_equal(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] > b[i]) {
				return false;
			}
		}
		return true;
	}
	
	private boolean contain_false(boolean[] input) {
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
				if (process.check_finished()) {
					System.out.println("Process " + process.id + " is finished!");
					return;
				}
				
				int[] request = get_random_array(process.need);
				
				if (!array_compare_smaller_or_equal(request, process.need)) {
					System.out.println("Process " + process.id + " try to generate a request " + Arrays.toString(request) 
					+ " Exceeding its maximum claim. Request is denied!");
					continue;
				}
				
				if (!array_compare_smaller_or_equal(request, available)) {
					System.out.println("Process " + process.id + " try to generate a request " + Arrays.toString(request) 
					+ " Exceeding available resources. Request is denied!");
					continue;
				}
				
				// Pre-allocating resources.
				for (int i = 0; i < request.length; i++) {
					available[i] = available[i] - request[i];
					process.allocation[i] = process.allocation[i] + request[i];
					process.need[i] = process.need[i] - request[i];
				}
								
				if (is_safe()) {
					System.out.println("Process " + process.id + " try to generate a request " + Arrays.toString(request) 
					+ " Safe state. Make request!");
					// Release resource after finishing.
					for (int i = 0; i < request.length; i++) {
						available[i] = available[i] + process.allocation[i];
						process.allocation[i] = 0;						
					}
				} else {
					System.out.println("Process " + process.id + " try to generate a request " + Arrays.toString(request) 
					+ " Unsafe state. Request is denied!");
					// Retrieving pre-allocated resources.
					for (int i = 0; i < request.length; i++) {
						available[i] = available[i] + request[i];
						process.allocation[i] = process.allocation[i] - request[i];
						process.need[i] = process.need[i] + request[i];
					}
				}
			}
		}
	
		private int[] get_random_array(int[] input) {
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
		
		public Process(int total_resource) {
			id = -1;
			max = new int[total_resource];
			allocation = new int[total_resource];
			need = new int[total_resource];
			isFinished = false;
		}
		
		public boolean check_finished() {
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