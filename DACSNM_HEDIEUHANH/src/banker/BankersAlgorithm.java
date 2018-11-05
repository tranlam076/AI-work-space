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

	private Process[] processArray;
	private Process processTmp;
	private boolean run = false;

	private int[] available;
	private int[] availableMax;
	Random rand = new Random();
	private int[] request;
	int delay = 1000;
	private int changeAt = 0;
	private static final long serialVersionUID = 1L;

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

	int divX = 171;
	int divY = 209;

	int procNum = 1;
	int startProcPannelX = 1;
	int disPannelX = divX / 2 + divX * 3;
	int startProcPannelY = divY / 4 + 1;
	int disPannelY = mapSizeY * size - divY / 4 - 2;

	int space = 3;
	int resNum = 1;
	int startResAllocX = space + 1 + divX / 2;
	int startResMaxX = startResAllocX + divX;
	int startResNeedX = startResMaxX + divX;

	int UImaxY = 1;

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
		gg.drawLine(offset, offset + divY / 4, offset + mapSizeX * size, offset + divY / 4);
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

		gg.setColor(Color.GREEN);
		for (int i = startProcPannelY + disEachProcY + 1; i <= startProcPannelY + disPannelY; i += disEachProcY) {
			gg.drawLine(offset + startProcPannelX, offset + i, offset + startProcPannelX * 2 + disPannelX - 3,
					offset + i);
		}

		gg.setColor(Color.BLUE);
		for (int j = 0; j < procNum; j++) {
			int valueY = startProcPannelY + 1 + disEachProcY + j * disEachProcY;
			gg.setFont(new Font("TimesRoman", Font.BOLD, 15));
			if (changeAt == j) {
				for (int i = 0; i < resNum; i++) {
					gg.setColor(Color.ORANGE);
					gg.fillRect(offset + startReqX + i * (disEachResX + space),
							offset + valueY - request[i] * ResMaxY_1 / UImaxY, disEachResX,
							request[i] * ResMaxY_1 / UImaxY);
					gg.setColor(Color.WHITE);
					gg.setFont(new Font("TimesRoman", Font.PLAIN, 15));
					gg.drawString(request[i] + "",
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
						offset + valueY - processArray[j].allocation[i] * ResMaxY_1 / UImaxY, disEachResX,
						processArray[j].allocation[i] * ResMaxY_1 / UImaxY);
				gg.fillRect(offset + startResMaxX + i * (disEachResX + space),
						offset + valueY - processArray[j].max[i] * ResMaxY_1 / UImaxY, disEachResX,
						processArray[j].max[i] * ResMaxY_1 / UImaxY);
				gg.fillRect(offset + startResNeedX + i * (disEachResX + space),
						offset + valueY - processArray[j].need[i] * ResMaxY_1 / UImaxY, disEachResX,
						processArray[j].need[i] * ResMaxY_1 / UImaxY);
				if (changeAt == j && processArray[j].max[i] > processArray[j].need[i]) {
					int requestChange = request[i];
					gg.setColor(Color.PINK);
					gg.fillRect(offset + startResAllocX + i * (disEachResX + space),
							offset + valueY - (requestChange + processArray[j].allocation[i])* ResMaxY_1 / UImaxY, disEachResX,
							requestChange * ResMaxY_1 / UImaxY);
					gg.setColor(Color.BLUE);
				}
				gg.setColor(Color.WHITE);
				gg.setFont(new Font("TimesRoman", Font.PLAIN, 15));
				gg.drawString(processArray[j].max[i] + "",
						offset + startResMaxX + i * (disEachResX + space) + disEachResX / 2 - 2, offset + valueY - 2);
				gg.setFont(new Font("TimesRoman", Font.PLAIN, 15));
				gg.drawString(processArray[j].allocation[i] + "",
						offset + startResAllocX + i * (disEachResX + space) + disEachResX / 2 - 2, offset + valueY - 2);
				gg.setFont(new Font("TimesRoman", Font.PLAIN, 15));
				gg.drawString(processArray[j].need[i] + "",
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
		processArray = new Process[process_number];
		procNum = process_number;

		int resource_number = Integer.parseInt(prop.getProperty("num-sources"));
		available = new int[resource_number];
		availableMax = new int[resource_number];
		request = new int[resource_number];
		Arrays.fill(request, 0);
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

			processArray[i] = cur;
			processArray[i] = cur;
		}
		for (int i = 0; i < processArray.length; i++) {
			for (int j = 0; j < resNum; j++) {
				availableMax[j] += processArray[i].allocation[j];
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
					messageSub = "All processes are finished! Algorithm Done!";
					this.repaint();
					return;
				}
				if (curProc == 0 || processArray[curProc].isFinished()) {
					for (int i = 0; i < procNum; i++) {
						if (arrayCompareSmallerOrEqual(processArray[i].need, available)
								&& !processArray[i].isFinished()) {
							curProc = i;
						}
					}
				}

				processTmp = processArray[curProc];
				changeAt = curProc;
				while (true && run) {
					if (processTmp.isFinished()) {
						messageSub = "Process " + processTmp.id + " is finished!";
						try {
							for (int j = 0; j < request.length; j++) {
								available[j] = available[j] + processTmp.allocation[j];
								processTmp.allocation[j] = 0;
							}
							this.repaint();
							Thread.sleep(delay);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (!run)
							continue;
						break;
					}
					request = curRequesting;
					if (!isContinue)
						request = createRandomArray(processTmp.need);
					messageSub = "Process " + processTmp.id + " try to generate a request "
							+ Arrays.toString(request) + "...";
					try {
						this.repaint();
						Thread.sleep(delay);
						if (!run) {
							curRequesting = request;
							isContinue = true;
							continue;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					for (int j = 0; j < request.length; j++) {
						processTmp.allocation[j] = processTmp.allocation[j] + request[j];
						processTmp.need[j] = processTmp.need[j] - request[j];
					}

					messageSub = "Process " + processTmp.id + " try to generate a request "
							+ Arrays.toString(request) + "... Safe state. Make request!";
					for (int j = 0; j < request.length; j++) {
						available[j] = available[j] - request[j];
						curRequesting = request;
						Arrays.fill(request, 0);
					}
					try {
						this.repaint();
						Thread.sleep(delay);
						if (!run) {
							isContinue = true;
							continue;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					isContinue = false;
				}
			}
		}
	}

	private boolean allDone() {
		for (int i = 0; i < processArray.length; i++) {
			if (processArray[i].isFinished() == false) {
				return false;
			}
		}
		init();
		this.repaint();
		run = false;
		go();
		return true;
	}

	private synchronized boolean isSafe() {
		int[] work = new int[available.length];
		for (int i = 0; i < available.length; i++) {
			work[i] = available[i];
		}
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
				if (!isContainFalse(finish)) {
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

		public Process(int total_resource) {
			id = -1;
			max = new int[total_resource];
			allocation = new int[total_resource];
			need = new int[total_resource];
		}

		public boolean isFinished() {
			for (int i = 0; i < need.length; i++) {
				if (need[i] > 0) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int ix = arg0.getX();
		int iy = arg0.getY();
		int x = (ix - offset) / size;
		int y = (iy - offset) / size;
		if (x >= 70 && x <= 77 && y >= 62 && y <= 63) {
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
		BankersAlgorithm bankersAlgorithm = new BankersAlgorithm();
		bankersAlgorithm.init();
		bankersAlgorithm.go();
	}
}