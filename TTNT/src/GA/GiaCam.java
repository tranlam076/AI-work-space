package GA;

import java.util.Random;
import java.util.Arrays;

public class GiaCam {
	public int max = 10000;
// tao mot mang FoodName chua tên các loai thuc an
	public int M = 5; // So loai gia cam
	public int N = 3; // So loai thit gia cam
	public String[] TenGiaCam = { "Ga", "Vit", "Ngan", "Ngong", "Co" };
	public float[] KhoiLuong = { 31, 66, 55 }; // Khoi luong tung loai thit
	public float[][] ChiTiet = { { 5.6f, 7.2f, 4.3f, 200000 }, { 3.2f, 5.6f, 1.5f, 3000000 },
			{ 1.2f, 5.2f, 2.9f, 3000000 }, { 5.2f, 4.6f, 1.7f, 3000000 }, { 1.8f, 2.4f, 5.7f, 500000 } };

// hàm main
	public static void main(String[] args) {
		new GiaCam();
	}

	Random rand = new Random();
//N là so cá the (ta tao 100 cá the)
	int Nx = 100;
//tao nghiem là mang 2 chieu chua các các the, moi cá the chua các chatdinh duong
	int[][] nghiem = new int[Nx][];
//Moi cá the có mot do thích nghi riêng, nên tao 1 mang 'dokhongthichnghi' gom N phan te, de chua do thích nghi cua N cá the
	int[] dokhongthichnghi = new int[Nx];

	public GiaCam() {
		KhoiTao();
		for (int i = 0; i < 1000; i++) { // Lap 1000 lan de danh gia
			System.out.print(i + "|");
			DanhGia();
			Print();
			LuaChon();
			LaiGhep();
			DotBien();
		}
	}

	private void Print() {
		int[] temp = dokhongthichnghi.clone();
		Arrays.sort(temp);
		int best = temp[0];
		System.out.print(best + "|");
		for (int i = 0; i < N; i++) {
			if (dokhongthichnghi[i] == best) {
				for (int j = 0; j < nghiem[i].length; j++)
					if (nghiem[i][j] == 1) {
						System.out.print(TenGiaCam[j] + ",");
					}
				System.out.println();
				break;
			}
		}
	}

	private void DotBien() {
		int index = rand.nextInt(Nx);
		int bit = rand.nextInt(N);
		// dot bien
		nghiem[index][bit] = 1 - nghiem[index][bit];
	}

	private void LaiGhep() {
		for (int i = 0; i < 20; i++) {
			int cha = rand.nextInt(Nx);
			int me = rand.nextInt(Nx);
			for (int j = 0; j < nghiem[cha].length; j++)
				if (rand.nextInt(2) == 1) {
					int temp = nghiem[cha][j];
					nghiem[cha][j] = nghiem[me][j];
					nghiem[me][j] = temp;
				}
		}
	}

	private void LuaChon() {
		int[] temp = dokhongthichnghi.clone();
		Arrays.sort(temp);
		int nguong = temp[Nx * 80 / 100];
		for (int i = 0; i < Nx; i++) {
			if (dokhongthichnghi[i] > nguong) {
				nghiem[i] = nghiem[rand.nextInt(Nx)].clone();
			}
		}
	}

	private void DanhGia() {
		for (int i = 0; i < Nx; i++) {
			dokhongthichnghi[i] = 0;
			// Tien
			for (int k = 0; k < N; k++) {
				for (int j = 0; j < nghiem[i].length; j++)
					dokhongthichnghi[i] += nghiem[i][j] * ChiTiet[k][j];
			}
			// Khoi luong
			float[] KL = new float[N];
			for (int k = 0; k < N; k++)
				KL[k] = 0;
			for (int k = 0; k < N; k++) {
				for (int j = 0; j < nghiem[i].length; j++)
					if (nghiem[i][j] == 1)
						KL[k] += ChiTiet[k][j];
				if (KL[k] < KhoiLuong[k])
					dokhongthichnghi[i] += 30;
			}
		}
	}

	private void KhoiTao() {
		for (int i = 0; i < Nx; i++) {
			nghiem[i] = new int[M];
			for (int j = 0; j < nghiem[i].length; j++) {
				nghiem[i][j] = rand.nextInt(2);
			}
		}
	}
}