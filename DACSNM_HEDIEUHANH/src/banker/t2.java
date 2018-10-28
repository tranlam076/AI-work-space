package banker;
/**
 * @author Yimu Yang
 */

import java.util.*;

public class t2 {
    /**
     * instance variable - array of input processes.
     */
	private Process[] process_array;
	
    /**
     * instance variable - vector of available sources.
     */
	private int[] available;
	
	/**
     * BankerAlgorithm class constructor.
     */
	public t2() {
		System.out.println("Please give number of the processes:");
		Scanner sc = new Scanner(System.in);
		int process_number = Integer.parseInt(sc.nextLine());
		process_array = new Process[process_number];
		
		System.out.println("Please give number of total available resources:");
		int resource_number = Integer.parseInt(sc.nextLine());
		available = new int[resource_number];
		
		System.out.println("Please give available vector:");
		String vector = sc.nextLine();
		String[] vector_array = vector.split(" ");
		
		for (int i = 0; i < resource_number; i++) {
			available[i] =  Integer.parseInt(vector_array[i]);
		}
				
		for (int i = 0; i < process_number; i++) {
			Process cur = new Process(resource_number);
			cur.id = i;
			process_array[i] = cur;
			System.out.println("Please give allocation vector for process " + i + ":");
			String input = sc.nextLine();
			String[] input_array = input.split(" ");
			
			for (int j = 0; j < input_array.length; j++) {
				cur.allocation[j] = Integer.parseInt(input_array[j]);
			}
			
			System.out.println("Please give max vector for process " + i + ":");
			String input_2 = sc.nextLine();
			String[] input_array_2 = input_2.split(" ");
			
			for (int j = 0; j < input_array_2.length; j++) {
				cur.max[j] = Integer.parseInt(input_array_2[j]);
			}			
			
			for (int j = 0; j < input_array_2.length; j++) {
				cur.need[j] = cur.max[j] - cur.allocation[j];
			}			
		}
		sc.close();
	}
	
	/**
     * go() method to create threads for each process.
     */
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
	
	/**
     * check if all process are finished.
     */
	private boolean all_done() {
		for (int i = 0; i < process_array.length; i++) {
			if (process_array[i].isFinished == false) {
				return false;
			}
		}
		return true;
	}	
	
	/**
     * safe algorithm to check if the current system is safe.
     */
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
	
	/**
     * method to compare two integer array.
     */
	private boolean array_compare_smaller_or_equal(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] > b[i]) {
				return false;
			}
		}
		return true;
	}
	
	/**
     * method to check if a boolean array contains a false instance.
     */
	private boolean contain_false(boolean[] input) {
		for (int i = 0; i < input.length; i++) {
			if (input[i] == false) {
				return true;
			}
		}
		return false;
	}
	
	/**
     * private nested thread class.
     */	
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
		
		/**
	     * method to generate a random smaller array based on the input array.
	     */
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
	
	/**
     * private Process class.
     */
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
	
	/**
     * main entrance.
     */		
	public static void main(String[] args) {
		t2 bankers_algorithm = new t2();
		bankers_algorithm.go();
	}	
}