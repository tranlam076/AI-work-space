package banker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import javax.swing.JFrame;

public class BankersAlgorithm extends JFrame implements MouseListener {

	private Process[] process_array;
	private Process processTmp;
	private boolean turn = true;
	private boolean deny = false;

	private int[] available;
	private int[] availableMax;
	Random rand = new Random();
	private int[] requesting;
	int delay = 1000;
	private int changeAt = 0;
	private static final long serialVersionUID = 1L;

//	UI
	Image img;
	Graphics gg;
	int X = 1020;
	int Y = 700;
	int size = 10;
	int offset = 40;
	int mapSizeX = (X - offset * 2) / size;
	int mapSizeY = (Y - offset * 2) / size;
	String message = "";
	String messageSub = "";

//	create pannel
	int divX = 171; // 46
	int divY = 209; // 75

//	test----
	int procNum = 1;
	int startProcPannelX = 1;
	int disPannelX = divX / 2 + divX * 3;
	int startProcPannelY = divY / 4 + 1;
	int disPannelY = mapSizeY * size - divY / 4 - 2;

//	test 2
	int space = 3;
	int resNum = 1;
	int startResAllocX = space + 1 + divX / 2;
	int startResMaxX = startResAllocX + divX;
	int startResNeedX = startResMaxX + divX;

//	test 3
	int UImaxY = 1;

//	test 4
	int startReqX = startResNeedX + divX;
	int startAvaiX = startReqX + divX;
	int startDiv1Y = divY*3 - offset/5;

	public BankersAlgorithm() {
		this.setTitle("Banker Algorithm");
		this.setSize(X, Y);
		this.setDefaultCloseOperation(3);
		this.addMouseListener(this);
		this.setVisible(true);
		img = this.createImage(X, Y);
		gg = img.getGraphics();
	}

	public void paint(Graphics graphic) {
		int disEachProcY = disPannelY / procNum;
		int disEachResX = (divX - (resNum + 1) * space) / resNum;
		int ResMaxY_1 = disEachProcY - 5;
		int ResMaxY_2 = disEachProcY - 5;
		gg.setColor(Color.white);
		gg.fillRect(0, 0, this.getWidth(), this.getHeight());
		gg.setColor(Color.black);
		gg.drawRect(offset, offset, mapSizeX * size, mapSizeY * size);

//		draw x
		gg.drawLine(offset, offset + divY / 4, offset + mapSizeX * size, offset + divY / 4);
//		draw y
		gg.drawLine(offset + divX / 2, offset, offset + divX / 2, offset + mapSizeY * size);
		for (int i = divX / 2 + divX; i < mapSizeX * size; i += divX) {
			gg.drawLine(offset + i, offset, offset + i, offset + mapSizeY * size);
		}
		gg.setColor(Color.BLACK);

		gg.setFont(new Font("TimesRoman", Font.BOLD, 15));
		gg.drawString(messageSub, offset + offset / 5, Y - 20);

		gg.setFont(new Font("TimesRoman", Font.BOLD, 15));
		gg.drawString("Process", offset + offset / 5, offset + divY / 4 - offset / 5);

		gg.setFont(new Font("TimesRoman", Font.BOLD, 15));
		gg.drawString("Allocation", offset + offset / 5 + divX / 2, offset + divY / 4 - offset / 5);

		gg.setFont(new Font("TimesRoman", Font.BOLD, 15));
		gg.drawString("Max", offset + offset / 5 + divX / 2 + divX, offset + divY / 4 - offset / 5);

		gg.setFont(new Font("TimesRoman", Font.BOLD, 15));
		gg.drawString("Need", offset + offset / 5 + divX / 2 + divX * 2, offset + divY / 4 - offset / 5);

		gg.setFont(new Font("TimesRoman", Font.BOLD, 15));
		gg.drawString("Attemping request", offset + offset / 5 + divX / 2 + divX * 3, offset + divY / 4 - offset / 5);

		gg.setFont(new Font("TimesRoman", Font.BOLD, 15));
		gg.drawString("Available", offset + offset / 5 + divX / 2 + divX * 4, offset + divY / 4 - offset / 5);

//		test
//		gg.setColor(Color.WHITE);
//		gg.fillRect(offset + startProcPannelX, offset + startProcPannelY, disPannelX - 2, disPannelY + 1);

		gg.setColor(Color.GREEN);
		for (int i = startProcPannelY + disEachProcY + 1; i <= startProcPannelY + disPannelY; i += disEachProcY) {
			gg.drawLine(offset + startProcPannelX, offset + i, offset + startProcPannelX * 2 + disPannelX - 3,
					offset + i);
		}

//		test 2 
		gg.setColor(Color.BLUE);
		for (int j = 0; j < procNum; j++) {
			int valueY = startProcPannelY + 1 + disEachProcY + j * disEachProcY;
			gg.setFont(new Font("TimesRoman", Font.BOLD, 15));
			if (changeAt == j) {
				for (int i = 0; i < resNum; i++) {
					gg.setColor(Color.ORANGE);
					if (!deny) gg.setColor(Color.RED);
					gg.fillRect(offset + startReqX + i * (disEachResX + space),
							offset + valueY - requesting[i] * ResMaxY_1 / UImaxY, disEachResX,
							requesting[i] * ResMaxY_1 / UImaxY);
					gg.setColor(Color.WHITE);
					gg.setFont(new Font("TimesRoman", Font.PLAIN, 15));
					gg.drawString(requesting[i] + "",
							offset + startReqX + i * (disEachResX + space) + disEachResX / 2 - 2, offset + valueY - 2);
				}
				gg.setColor(Color.RED);
				gg.drawString("P" + j, offset + offset / 5, valueY + offset * 4 / 5);
				gg.setColor(Color.BLUE);
			} else {
				gg.drawString("P" + j, offset + offset / 5, valueY + offset * 4 / 5);
			}

			for (int i = 0; i < resNum; i++) {
				gg.fillRect(offset + startResAllocX + i * (disEachResX + space),
						offset + valueY - process_array[j].allocation[i] * ResMaxY_1 / UImaxY, disEachResX,
						process_array[j].allocation[i] * ResMaxY_1 / UImaxY);
				gg.fillRect(offset + startResMaxX + i * (disEachResX + space),
						offset + valueY - process_array[j].max[i] * ResMaxY_1 / UImaxY, disEachResX,
						process_array[j].max[i] * ResMaxY_1 / UImaxY);
				gg.fillRect(offset + startResNeedX + i * (disEachResX + space),
						offset + valueY - process_array[j].need[i] * ResMaxY_1 / UImaxY, disEachResX,
						process_array[j].need[i] * ResMaxY_1 / UImaxY);
				if (changeAt == j && process_array[j].max[i] > process_array[j].need[i]) {
					int maxChangeY = requesting[i];
					gg.setColor(Color.PINK);
					gg.fillRect(offset + startResMaxX + i * (disEachResX + space),
							offset + valueY - process_array[j].max[i] * ResMaxY_1 / UImaxY, disEachResX,
							maxChangeY * ResMaxY_1 / UImaxY);
					gg.setColor(Color.BLUE);
				}
				gg.setColor(Color.WHITE);
				gg.setFont(new Font("TimesRoman", Font.PLAIN, 15));
				gg.drawString(process_array[j].max[i] + "",
						offset + startResMaxX + i * (disEachResX + space) + disEachResX / 2 - 2, offset + valueY - 2);
				gg.setFont(new Font("TimesRoman", Font.PLAIN, 15));
				gg.drawString(process_array[j].allocation[i] + "",
						offset + startResAllocX + i * (disEachResX + space) + disEachResX / 2 - 2, offset + valueY - 2);
				gg.setFont(new Font("TimesRoman", Font.PLAIN, 15));
				gg.drawString(process_array[j].need[i] + "",
						offset + startResNeedX + i * (disEachResX + space) + disEachResX / 2 - 2, offset + valueY - 2);
				gg.setColor(Color.BLUE);
			}
		}

		for (int i = 0; i < resNum; i++) {
			gg.fillRect(offset + startAvaiX + i * (disEachResX + space),
					offset + startDiv1Y - availableMax[i] * ResMaxY_2 / UImaxY, disEachResX,
					availableMax[i] * ResMaxY_2 / UImaxY);
			gg.setColor(Color.RED);
			gg.fillRect(offset + startAvaiX + i * (disEachResX + space),
					offset + startDiv1Y - (availableMax[i] - available[i]) * ResMaxY_2 / UImaxY, disEachResX,
					(availableMax[i] - available[i]) * ResMaxY_2 / UImaxY);
			gg.setColor(Color.WHITE);
			gg.setFont(new Font("TimesRoman", Font.PLAIN, 15));
			gg.drawString(available[i] + "",
					offset + startAvaiX + i * (disEachResX + space) + disEachResX / 2 - 2,
					offset + startDiv1Y - 2);
			gg.setColor(Color.BLUE);
		}
		graphic.drawImage(img, 0, 0, null);
	}

	private void init() {

		Data data = new Data();
		Properties prop = data.readData();
		int UImaxYtmp = UImaxY;

		int process_number = Integer.parseInt(prop.getProperty("num-process"));
		process_array = new Process[process_number];
		procNum = process_number;

		int resource_number = Integer.parseInt(prop.getProperty("num-sources"));
		available = new int[resource_number];
		availableMax = new int[resource_number];
		requesting = new int[resource_number];
		Arrays.fill(requesting, 0);
		resNum = resource_number;

		String vector = prop.getProperty("available");
		String[] vector_array = vector.split(" ");

		for (int i = 0; i < resource_number; i++) {
			available[i] = Integer.parseInt(vector_array[i]);
			availableMax[i] = Integer.parseInt(vector_array[i]);
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
				UImaxYtmp = cur.max[j] = Integer.parseInt(max[j]);
				if (UImaxY < UImaxYtmp)
					UImaxY = UImaxYtmp;
			}

			for (int j = 0; j < max.length; j++) {
				cur.need[j] = cur.max[j] - cur.allocation[j];
			}

			process_array[i] = cur;
			process_array[i] = cur;
		}
		for (int i = 0; i < process_array.length; i++) {
			for (int j = 0; j < resNum; j++) {
				availableMax[j] += process_array[i].allocation[j];
			}
		}
		this.repaint();
	}

	private void go() {

		if (!is_safe()) {
			messageSub = "Input System is not Safe! Please try again!";
			System.out.println("Input System is not Safe! Please try again!");
			this.repaint();
			try {
				Thread.sleep(delay);
				this.repaint();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		try {
			Thread.sleep(delay);
			this.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (true && turn) {
			if (all_done()) {
				System.out.println("All processes are finished! Algorithm Done!");
				return;
			}
			int checkTime = 0;
			int i = rand.nextInt(process_array.length);
			if (process_array[i].isFinished) {
				continue;
			}
			processTmp = process_array[i];
			changeAt = i;
			while (true && checkTime < 4 && turn) {
				checkTime++;
				if (processTmp.check_finished()) {
					System.out.println("Process " + processTmp.id + " is finished!");
					Arrays.fill(requesting, 0);
					this.repaint();
					break;
				}

				requesting = get_random_array(processTmp.need);
				deny = true;
				messageSub = "Process " + processTmp.id + " try to generate a request " + Arrays.toString(requesting);
				try {
					Thread.sleep(delay);
					this.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (!array_compare_smaller_or_equal(requesting, available)) {
					messageSub = "Process " + processTmp.id + " try to generate a request "
							+ Arrays.toString(requesting) + "... Exceeding available resources. Request is denied!";
					deny = false;
					try {
						Thread.sleep(delay);
						this.repaint();
					} catch (Exception e) {
						e.printStackTrace();
					}
					continue;
				}
				for (int j = 0; j < requesting.length; j++) {
					available[j] = available[j] - requesting[j];
					processTmp.allocation[j] = processTmp.allocation[j] + requesting[j];
					processTmp.need[j] = processTmp.need[j] - requesting[j];
				}
				try {
					Thread.sleep(delay);
					this.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (is_safe()) {
					messageSub = "Process " + processTmp.id + " try to generate a request "
							+ Arrays.toString(requesting) + "... Safe state. Make request!";
					for (int k = 0; k < requesting.length; k++) {
						available[k] = available[k] + processTmp.allocation[k];
						processTmp.allocation[k] = 0;
						processTmp.max[k] = processTmp.need[k];
					}

					try {
						Thread.sleep(delay);
						this.repaint();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					messageSub = "Process " + processTmp.id + " try to generate a request "
							+ Arrays.toString(requesting) + "... Unsafe state. Request is denied!";
					deny = false;
					for (int l = 0; l < requesting.length; l++) {
						available[l] = available[l] + requesting[l];
						processTmp.allocation[l] = processTmp.allocation[l] - requesting[l];
						processTmp.need[l] = processTmp.need[l] + requesting[l];
					}
					try {
						Thread.sleep(delay);
						this.repaint();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
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

	private int[] get_random_array(int[] input) {
		int[] res = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			int cur = rand.nextInt(input[i] + 1);
			res[i] = cur;
		}
		return res;
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

	public static void main(String[] args) {
		BankersAlgorithm bankers_algorithm = new BankersAlgorithm();
		bankers_algorithm.init();
		bankers_algorithm.go();
	}
}