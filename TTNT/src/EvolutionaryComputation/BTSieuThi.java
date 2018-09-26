package EvolutionaryComputation;

import java.util.Arrays;
import java.util.Random;

public class BTSieuThi {
	public String[] FoodName = { "Tao", "Cherry", "Cam", "Quyt", "Duahau", "Chanh", "Kiwi", "Nho", "DuaLuoi", "DaoTien",
			"Chuoi", "DauTay", "Thom", "Le", "SuaBo", "CaChua", "CaRot", "CaTim", "HanhTay", "Toi", "KhoaiTay", "Nam",
			"BanhMy", "PhoMai", "Bia", "Ngheu", "Cua", "Ca", "Tom", "Ga", "Bo", "Trung" };

	public int[][] Food = { { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 1, 1, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 1, 1 }, { 0, 0, 1, 1, 1, 0, 0, 0, 0, 1 }, { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 1, 0, 0, 1 }, { 0, 0, 1, 1, 1, 0, 0, 1, 0, 1 }, { 0, 0, 1, 0, 0, 1, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 1, 0, 1, 0 }, { 0, 0, 1, 1, 0, 1, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 1, 0, 0, 0, 1 },
			{ 0, 0, 1, 1, 0, 0, 1, 0, 0, 1 }, { 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 0, 1, 1, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0, 1, 0, 1, 0, 1 }, { 0, 0, 1, 1, 0, 0, 1, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 1, 1, 0, 0, 0, 0 }, { 1, 0, 0, 1, 0, 0, 1, 0, 1, 1 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 1, 1, 0, 0, 1, 0 },
			{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 0 }, { 1, 1, 0, 0, 0, 0, 0, 0, 1, 0 }, { 1, 0, 0, 0, 1, 0, 1, 0, 0, 0 },
			{ 1, 0, 0, 0, 0, 1, 0, 1, 0, 0 }, { 1, 1, 0, 0, 0, 1, 0, 0, 0, 0 }, { 1, 1, 0, 0, 1, 1, 0, 1, 0, 0 } };
	public int[] FoodValue = { 31, 66, 55, 70, 34, 50, 106, 38, 62, 49, 55, 82, 33, 13, 118, 54, 83, 15, 56, 123, 32,
			40, 52, 63, 18, 74, 49, 62, 64, 55, 70, 100 };

	public static void main(String[] args) {
		new BTSieuThi();
	}

	public BTSieuThi() {
		KhoiTao();
		for (int i = 0; i < 100; i++) {
			DanhGia();
			LuaChon();
			Laighep();
			DotBien();
		}
		Print();
	}

	int N = 100;
	int[][] nghiem = new int[N][32];
	Random rand = new Random();

	private void KhoiTao() {
		for (int i = 0; i < N; i++) {
			nghiem[i] = new int[FoodName.length];
			for (int j = 0; j < nghiem[i].length; j++) {
				nghiem[i][j] = rand.nextInt(2);
			}
		}
	}

	int[] f = new int[10000];

	private void DanhGia() {
		for (int i = 0; i < N; i++) {
			int tien = 0;
			for (int j = 0; j < 32; j++) {
				tien += nghiem[i][j] * FoodValue[j];
			}
			int dinhduong = 0;
			for (int k = 0; k < 10; k++) {
				boolean cochat = false;
				for (int j = 0; j < 32; j++) {
					if (nghiem[i][j] == 1 && Food[i][j] == 1) {
						cochat = true;
						break;
					}
				}
				if (cochat) {
					dinhduong++;
					f[i] = 1 * tien + 50 * dinhduong;
				}
			}
		}
	}

	private void LuaChon() {
		int[] tmp = f.clone();
		Arrays.sort(tmp);
		int nguong = tmp[N * 80 / 100];
		for (int i = 0; i < N; i++) {
			if (f[i] > nguong) {
				nghiem[i] = nghiem[rand.nextInt(N)].clone();
			}
		}
	}

	private void Laighep() {
		for (int i = 0; i < N * 30.100; i++) {
			int cha = rand.nextInt(N);
			int me = rand.nextInt(N);
			for (int j = 0; i < 32; i++) {
				if (rand.nextBoolean()) {
					int tmp = nghiem[cha][j];
					nghiem[cha][j] = nghiem[me][j];
					nghiem[me][j] = tmp;
				}
			}
		}
	}

	private void DotBien() {
		int i = rand.nextInt(N);
		int j = rand.nextInt(32);
		nghiem[i][j] = 1 - nghiem[i][j];

	}

	private void Print() {
		int[] tmp = f.clone();
		Arrays.sort(tmp);
		int best = tmp[0];
		System.out.print(best + "|");
		for (int i = 0; i < N; i++) {
			if (f[i] == best) {
				for (int j = 0; j < nghiem[i].length; j++)
					if (nghiem[i][j] == 1) {
						System.out.print(FoodName[j] + ",");
					}
				System.out.println();
				break;
			}
		}
	}
}
