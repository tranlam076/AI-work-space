package Aircraft;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

public class AircraftPlay extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	boolean play = false;
	boolean isPlayFirst = false;
	boolean isStarted = false;
	int state = 0;
	int mapSize = 10;
	int size = 50;
	int offset = 100;
	private static Socket soc;
	private static DataInputStream dis;
	private static DataOutputStream dos;

	static ArrayList<Point> myAircraft = new ArrayList<>();
	static ArrayList<Point> enemyAttach = new ArrayList<>();
	static ArrayList<Point> endState = new ArrayList<>();
	static ArrayList<Point> attachAircrafts = new ArrayList<>();
	static ArrayList<Point> correctPoints = new ArrayList<>();

	public static void main(String[] args) {
		try {
			soc = new Socket("localhost", 2000);
			dos = new DataOutputStream(soc.getOutputStream());
			dis = new DataInputStream(soc.getInputStream());
			new AircraftPlay();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public AircraftPlay() throws IOException {
		this.setTitle("Ban May Bay");
		this.setSize(mapSize * size / 2 + offset * 2, (mapSize + 1) * size + offset * 2);
		this.setDefaultCloseOperation(3);
		this.addMouseListener(this);
		this.setVisible(true);
		while (true) {
			String recieve = dis.readUTF();
			if (recieve.length() > 0) {
				System.out.println(recieve);
				if (recieve.contains("reset")) {
					reset();
					System.out.println("reset");
				}
				if (recieve.contains("setup,")) {
					String getPosition[] = recieve.split(",");
					for (int i = 1; i < getPosition.length; i++) {
						endState.add(new Point(Integer.parseInt(getPosition[i].split("-")[0]),
								Integer.parseInt(getPosition[i].split("-")[1]) + mapSize / 2 + 1));
					}
					this.repaint();
					isStarted = false;
				}
				if (recieve.contains("play-now")) {
					play = true;
				}
				
				if (recieve.contains("reset")) {
					reset();
					System.out.println("reset");
				}

				if (recieve.contains("play-first")) {
					play = true;
					isPlayFirst = true;
					continue;
				}

				if (recieve.contains("play-second")) {
					play = false;
					continue;
				}

				if (recieve.contains("starting")) {
					isStarted = true;
					this.repaint();
					continue;
				}

				if (recieve.contains("first-win")) {
					state = 1;
					isStarted = false;
				}

				if (recieve.contains("second-win")) {
					state = 2;
					isStarted = false;
				}

				if (recieve.contains("enemyAttach,")) {
					String[] position = recieve.split(",");
					if (position[1].contains("correct")) {
						correctPoints.add(new Point(Integer.parseInt(position[2].split("-")[0]),
								Integer.parseInt(position[2].split("-")[1]) + mapSize/2 + 1));
					} else {
						enemyAttach.add(new Point(Integer.parseInt(position[1].split("-")[0]),
								Integer.parseInt(position[1].split("-")[1])));
					}
					this.repaint();
				}
			}
		}
	}

	public void paint(Graphics graphic) {
		graphic.setColor(Color.white);
		graphic.fillRect(0, 0, this.getWidth(), this.getHeight());
		graphic.setColor(Color.blue);
		graphic.fillRect(offset + (mapSize - 2) * size / 2, offset + (mapSize + 1) * size + 10, size, size - 30);
		graphic.setColor(Color.white);
		graphic.setFont(new Font("TimesRoman", Font.BOLD, 15));
		graphic.drawString("RESET", offset + (mapSize - 2) * size / 2, offset + (mapSize + 1) * size + size / 2);

		if (state != 0) {
			String msg = "";
			graphic.setColor(Color.orange);
			if (isPlayFirst && state == 1) {
				msg = "You win!";
			} else if (isPlayFirst && state == 2) {
				msg = "You lose!";
				graphic.setColor(Color.darkGray);
			}
			if (!isPlayFirst && state == 2) {
				msg = "You win!";
			} else if (!isPlayFirst && state == 1) {
				msg = "You lose!";
				graphic.setColor(Color.darkGray);
			}

			graphic.fillRect(offset , offset + (mapSize + 1) * size + 10, 2 * size,
					size - 30);
			graphic.setColor(Color.white);
			graphic.setFont(new Font("TimesRoman", Font.BOLD, 15));
			graphic.drawString(msg, offset , offset + (mapSize + 1) * size + size / 2);
		}

		graphic.setColor(Color.green);
		for (int i = 0; i <= mapSize / 2; i++) {
			graphic.drawLine(offset, offset + i * size, offset + mapSize * size / 2, offset + i * size);
			graphic.drawLine(offset + i * size, offset, offset + i * size, offset + mapSize * size / 2);
			graphic.drawLine(offset + i * size, offset + (mapSize / 2 + 1) * size, offset + i * size,
					offset + (mapSize + 1) * size);
		}
		for (int i = mapSize / 2; i <= mapSize + 1; i++) {
			graphic.drawLine(offset, offset + i * size, offset + mapSize * size / 2, offset + i * size);

		}
		for (int i = 0; i < myAircraft.size(); i++) {
			Color c = Color.darkGray;
			graphic.setColor(c);
			graphic.fillRect(offset + size * myAircraft.get(i).x + 1, offset + size * myAircraft.get(i).y + 1, size - 1,
					size - 1);
		}

		for (int i = 0; i < enemyAttach.size(); i++) {
			Color c = Color.red;
			graphic.setColor(c);
			graphic.fillRect(offset + size * enemyAttach.get(i).x + 1, offset + size * enemyAttach.get(i).y + 1,
					size - 1, size - 1);
		}

		for (int i = 0; i < attachAircrafts.size(); i++) {
			Color c = Color.yellow;
			graphic.setColor(c);
			graphic.fillRect(offset + size * attachAircrafts.get(i).x + 1, offset + size * attachAircrafts.get(i).y + 1,
					size - 1, size - 1);
		}
		for (int i = 0; i < correctPoints.size(); i++) {
			Color c = Color.pink;
			graphic.setColor(c);
			graphic.fillRect(offset + size * correctPoints.get(i).x + 1, offset + size * correctPoints.get(i).y + 1,
					size - 1, size - 1);
		}

		for (int i = 0; i < endState.size(); i++) {
			Color c = Color.green;
			graphic.setColor(c);
			graphic.fillRect(offset + size * endState.get(i).x + 1, offset + size * endState.get(i).y + 1, size - 1,
					size - 1);
		}

		graphic.setColor(Color.black);
		graphic.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		graphic.drawString("Setup position for your aircraft:", offset, offset - 15);
		graphic.drawString("Attach enemy:", offset, offset + mapSize * size / 2 + 35);

		String message = null;
		if (isPlayFirst)
			message = "Play first.....";
		if (!isPlayFirst)
			message = "Play second.....";
		if (!isStarted)
			message = "Wait for setting aircrafts...";
		graphic.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		graphic.drawString(message, offset + (mapSize / 2 - 2) * size + 10, offset + mapSize * size / 2 + 15);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		int ix = (x - offset) / size;
		int iy = (y - offset) / size;
		if (ix == mapSize / 2 - 1 && iy == mapSize + 1) {
			try {
//				reset();
				dos.writeUTF("reset");
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		if (x < offset || x >= offset + mapSize * size / 2)
			return;
		if (y < offset || y >= offset + (mapSize + 1) * size)
			return;

		if (y <= offset + (mapSize) * size / 2) {
			if (myAircraft.contains(new Point(ix, iy)))
				return;
			if (myAircraft.size() <= 7) {
				myAircraft.add(new Point(ix, iy));
			}
			if (myAircraft.size() > 7 && isStarted == false) {
				String setup = "setup,";
				for (int i = 0; i < myAircraft.size(); i++) {
					setup += myAircraft.get(i).x + "-" + myAircraft.get(i).y + ",";
				}
				try {
					dos.writeUTF(setup);
					dos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			this.repaint();
		} else if (y > offset + (mapSize + 2) * size / 2){
			if (attachAircrafts.contains(new Point(ix, iy)))
				return;
			String request = "";
			if (isPlayFirst) {
				request = "play,first," + ix + "-" + (iy - mapSize / 2 - 1);
			} else {
				request = "play,second," + ix + "-" + (iy - mapSize / 2 - 1);
			}
			if (play && isStarted) {
				try {
					dos.writeUTF(request);
					dos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				attachAircrafts.add(new Point(ix, iy));
				this.repaint();
				play = false;
			}
		}
		if (y >= offset + mapSize * size / 2 && y <= offset + (mapSize + 2) * size / 2)
			return;
	}

	private void reset() {
		myAircraft.clear();
		attachAircrafts.clear();
		endState.clear();
		enemyAttach.clear();
		correctPoints.clear();
		play = false;
		isStarted = false;
		state =0;
		this.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
