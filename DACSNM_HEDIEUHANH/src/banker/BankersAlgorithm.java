package banker;
/**
 * @author Yimu Yang
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.swing.JFrame;

public class BankersAlgorithm extends JFrame implements Runnable, MouseListener {

	private Process[] process_array;
	private int[] available;
	private List<Prameter> listPrams = new ArrayList<>();

	private Position getValues(ArrayList<Prameter> listParams, String name) {
		for (Prameter param : listParams) {
			if (param.getName().equals(name)) {
				return param.getPsotion();
			}
		}
		return null;
	}

//	UI
	private static final long serialVersionUID = 1L;
	Image img;
//	Graphics gg;
	int X = 1000;
	int Y = 700;
	int size = 10;
	int offset = 40;
	int mapSizeX = (X - offset * 2) / size;
	int mapSizeY = (Y - offset * 2) / size;

//	create pannel
	int divX = 170; // 46
	int divY = 209; // 75

//	test----
	int procNum = 5;
	int startProcPannelX = 1;
	int disPannelX = divX / 2 + divX * 3;
	int startProcPannelY = divY / 4 + 1;
	int disPannelY = mapSizeY * size - divY / 4;
	int disEachProcY = disPannelY / procNum;
//	test 2
	int space = 3;
	int resNum = 3;
	int startResAllocX = space + 1 + divX / 2;
	int startResMaxX = startResAllocX + divX ;
	int startResNeedX = startResMaxX + divX ;
	int disEachResX = (divX - (resNum + 1) * space) / resNum;

	public BankersAlgorithm() {
		this.setTitle("Banker Algorithm");
		this.setSize(X, Y);
		this.setDefaultCloseOperation(3);
		this.addMouseListener(this);
		this.setVisible(true);
//		img = this.createImage(1000, 700);
//		gg=img.getGraphics();
	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.drawRect(offset, offset, mapSizeX * size, mapSizeY * size);
//		for (int i = 0; i <= mapSizeY; i++) {
//			g.drawLine(offset, offset + i * size, offset + mapSizeX * size , offset + i * size);
//		}
//		for (int i = 0; i <= mapSizeX; i++) {
//			g.drawLine(offset + i * size, offset, offset + i * size, offset + mapSizeY * size );
//		}

//		draw x
		g.drawLine(offset, offset + divY / 4, offset + mapSizeX * size, offset + divY / 4);
//		draw y
		g.drawLine(offset + divX / 2, offset, offset + divX / 2, offset + mapSizeY * size);
		for (int i = divX / 2 + divX; i < mapSizeX * size; i += divX) {
			g.drawLine(offset + i, offset, offset + i, offset + mapSizeY * size);
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.BOLD, 15));
		g.drawString("Process", offset + offset / 5, offset + divY / 4 - offset / 5);

		g.setFont(new Font("TimesRoman", Font.BOLD, 15));
		g.drawString("Allocation", offset + offset / 5 + divX / 2, offset + divY / 4 - offset / 5);

		g.setFont(new Font("TimesRoman", Font.BOLD, 15));
		g.drawString("Max", offset + offset / 5 + divX / 2 + divX, offset + divY / 4 - offset / 5);

		g.setFont(new Font("TimesRoman", Font.BOLD, 15));
		g.drawString("Need", offset + offset / 5 + divX / 2 + divX * 2, offset + divY / 4 - offset / 5);

		g.setFont(new Font("TimesRoman", Font.BOLD, 15));
		g.drawString("Available", offset + offset / 5 + divX / 2 + divX * 3, offset + divY / 4 - offset / 5);

		g.setFont(new Font("TimesRoman", Font.BOLD, 15));
		g.drawString("Execution", offset + offset / 5 + divX / 2 + divX * 4, offset + divY / 4 - offset / 5);

//		test
		g.setColor(Color.WHITE);
		g.fillRect(offset + startProcPannelX, offset + startProcPannelY, disPannelX - 2, disPannelY - 2);

		g.setColor(Color.GREEN);
		for (int i = startProcPannelY + disEachProcY + 1; i <= startProcPannelY + disPannelY; i += disEachProcY) {
			g.drawLine(offset + startProcPannelX, offset + i, offset + startProcPannelX * 2 + disPannelX - 3,
					offset + i);
		}

//		test 2 
		g.setColor(Color.BLUE);
		for (int j = 0; j < procNum; j ++) {
			int valueY = startProcPannelY + disEachProcY + 1 + j*disEachProcY;
			g.setFont(new Font("TimesRoman", Font.BOLD, 15));
			g.drawString("P" + j, offset + offset / 5 , valueY - offset / 5);
			for (int i = 0; i < resNum; i++) {
				g.fillRect(offset + startResAllocX + i * (disEachResX + space), offset + valueY - 20,
				disEachResX, 20);
				
				g.fillRect(offset + startResMaxX + i * (disEachResX + space), offset + valueY - 20,
						disEachResX, 20);
				
				g.fillRect(offset + startResNeedX + i * (disEachResX + space), offset + valueY - 20,
						disEachResX, 20);
			}
		}

//		g.drawImage(img, 0, 0, null);
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
			available[i] = Integer.parseInt(vector_array[i]);
		}

		for (int i = 0; i < process_number; i++) {
			Process cur = new Process(resource_number);
			cur.id = i;

			String[] input = (prop.getProperty("p" + i)).split("-");
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

			process_array[i] = cur;
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
		for (int i = 0; i < available.length; i++) {
			work[i] = available[i];
		}
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

				if (!array_compare_smaller_or_equal(request, available)) {
					System.out.println("Process " + process.id + " try to generate a request "
							+ Arrays.toString(request) + " Exceeding available resources. Request is denied!");
					continue;
				}

				// Pre-allocating resources.
				for (int i = 0; i < request.length; i++) {
					available[i] = available[i] - request[i];
					process.allocation[i] = process.allocation[i] + request[i];
					process.need[i] = process.need[i] - request[i];
				}

				if (is_safe()) {
					System.out.println("Process " + process.id + " try to generate a request "
							+ Arrays.toString(request) + " Safe state. Make request!");
					// Release resource after finishing.
					for (int i = 0; i < request.length; i++) {
						available[i] = available[i] + process.allocation[i];
						process.allocation[i] = 0;
					}
				} else {
					System.out.println("Process " + process.id + " try to generate a request "
							+ Arrays.toString(request) + " Unsafe state. Request is denied!");
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
		BankersAlgorithm bankers_algorithm = new BankersAlgorithm();
		bankers_algorithm.init();
		bankers_algorithm.go();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void run() {

	}
}