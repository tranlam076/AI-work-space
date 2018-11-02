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
	private boolean run = true;
	private boolean isDeny = false;

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
	int X = 1180;
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
	int startDiv1Y = divY * 3 - offset / 5;

	public BankersAlgorithm() {
		this.setTitle("Banker Algorithm");
		this.setSize(X, Y);
		this.setDefaultCloseOperation(3);
		this.addMouseListener(this);
		this.setVisible(true);
	}

	public void paint(Graphics graphic) {
		img = this.createImage(X, Y);
		gg = img.getGraphics();
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
					if (!isDeny)
						gg.setColor(Color.RED);
					gg.fillRect(offset + startReqX + i * (disEachResX + space),
							offset + valueY - requesting[i] * ResMaxY_1 / UImaxY, disEachResX,
							requesting[i] * ResMaxY_1 / UImaxY);
					gg.setColor(Color.WHITE);
					gg.setFont(new Font("TimesRoman", Font.PLAIN, 15));
					gg.drawString(requesting[i] + "",
							offset + startReqX + i * (disEachResX + space) + disEachResX / 2 - 2, offset + valueY - 2);
				}
				gg.setColor(Color.RED);
				gg.setFont(new Font("TimesRoman", Font.BOLD, 15));
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
			gg.drawString(available[i] + "", offset + startAvaiX + i * (disEachResX + space) + disEachResX / 2 - 2,
					offset + startDiv1Y - 2);
			gg.setColor(Color.BLUE);
		}

//		test 5
		if (run) {
			gg.setColor(Color.GREEN);
			gg.fillRect(offset + mapSizeX * size - 24 * size, offset + mapSizeY * size + size / 2, 8 * size, 2 * size);
			gg.setColor(Color.WHITE);
			gg.setFont(new Font("TimesRoman", Font.PLAIN, 15));
			gg.drawString("RUNNING", offset + mapSizeX * size - 24 * size + size / 2,
					offset + mapSizeY * size + 2 * size);
		}
		if (!run) {
			gg.setColor(Color.PINK);
			gg.fillRect(offset + mapSizeX * size - 24 * size, offset + mapSizeY * size + size / 2, 8 * size, 2 * size);
			gg.setColor(Color.WHITE);
			gg.setFont(new Font("TimesRoman", Font.PLAIN, 15));
			gg.drawString("PAUSED", offset + mapSizeX * size - 23 * size, offset + mapSizeY * size + 2 * size);
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
		int curProc = 0;
		int[] curRequesting = new int[resNum];
		Arrays.fill(curRequesting, 0);
		boolean isContinue = false;
		while (true) {
			if (!isSafe()) {
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
			while (true && run) {
				if (allDone()) {
					System.out.println("All processes are finished! Algorithm Done!");
					return;
				}
				if (curProc == 0 || process_array[curProc].isFinished) {
					curProc = rand.nextInt(process_array.length);
				}

				processTmp = process_array[curProc];
				changeAt = curProc;
				while (true && run) {
					if (processTmp.checkFinished()) {
						System.out.println("Process " + processTmp.id + " is finished!");
						Arrays.fill(requesting, 0);
						this.repaint();
						if (!run)
							continue;
						break;
					}
					requesting = curRequesting;
					if (!isContinue)
						requesting = createRandomArray(processTmp.need);
					isDeny = true;
					messageSub = "Process " + processTmp.id + " try to generate a request "
							+ Arrays.toString(requesting);
					try {
						Thread.sleep(delay);
						this.repaint();
						if (!run) {
							curRequesting = requesting;
							isContinue = true;
							continue;
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					if (!arrayCompareSmallerOrEqual(requesting, available)) {
						messageSub = "Process " + processTmp.id + " try to generate a request "
								+ Arrays.toString(requesting) + "... Exceeding available resources. Request is denied!";
						isDeny = false;
						try {
							Thread.sleep(delay);
							this.repaint();
							if (!run) {
								isContinue = false;
								continue;
							}
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
					if (isSafe()) {
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
						isDeny = false;
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
					isContinue = false;
				}
			}
		}
	}

	
	
	private boolean allDone() {
		for (int i = 0; i < process_array.length; i++) {
			if (process_array[i].isFinished == false) {
				return false;
			}
		}
		return true;
	}

	private synchronized boolean isSafe() {
		int[] work = new int[available.length];
		for (int i = 0; i < available.length; i++) {
			work[i] = available[i];
		}
		boolean[] finish = new boolean[process_array.length];
		Arrays.fill(finish, false);

		while (true) {
			int index = -1;
			for (int i = 0; i < finish.length; i++) {
				if (finish[i] == false && arrayCompareSmallerOrEqual(process_array[i].need, work)) {
					index = i;
					break;
				}
			}

			if (index == -1) {
				if (!isContainFalse(finish)) {
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

	private boolean arrayCompareSmallerOrEqual(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] > b[i]) {
				return false;
			}
		}
		return true;
	}

	private boolean isContainFalse(boolean[] input) {
		for (int i = 0; i < input.length; i++) {
			if (input[i] == false) {
				return true;
			}
		}
		return false;
	}

	private int[] createRandomArray(int[] input) {
		int count = 0;
		int[] res = new int[input.length];
		while (true) {
			count = 0;
			for (int i = 0; i < input.length; i++) {
				int cur = rand.nextInt(input[i] + 1);
				if (cur == 0) {
					count++;
				}
				res[i] = cur;
			}

			if (count == input.length) {
				continue;
			} else
				break;
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

		public boolean checkFinished() {
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
	public void mouseClicked(MouseEvent arg0) {
		int ix = arg0.getX();
		int iy = arg0.getY();
		int x = (ix - offset) / size;
		int y = (iy - offset) / size;
		System.out.println(ix + "-" + iy);
		System.out.println(x + "-" + y);
		System.out.println(mapSizeX + "-" + mapSizeY);
		if (x >= 86 && x <= 93 && y >= 62 && y <= 63) {
			run = !run;
			this.repaint();
		}
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
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
//		bankers_algorithm.run = false;
//		bankers_algorithm.init();
//		bankers_algorithm.go();
	}
}