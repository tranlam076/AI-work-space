package chutrinh;

import java.util.Arrays;
import java.util.Random;

public class ChuTrinh {
	int m = 100; // so thanh pho
	int dis[][] = new int[m][m];
	int n = 100; // so nghiem
	int[][] nghiem = new int[n][m];
	int[] f = new int[n];
	Random rand = new Random();

	public static void main(String[] args) {
		new ChuTrinh();
	}

	public ChuTrinh() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				dis[i][j] = dis[j][i] = rand.nextInt(10000);
			}
		}
		KhoiTao();
		print();
		for (int i = 0; i < 100; i++) {
			DanhGia();
			print();
			LuaChon();
			LaiGhep();
			DotBien();
		}
	}

	private void KhoiTao() {
		for (int i = 0; i < n; i++) {
			nghiem[i] = ChinhHop(m);
		}
	}

	private int[] ChinhHop(int M) {
		int a[] = new int[M];
		for (int i = 0; i < M; i++) {
			a[i] = i;
		}
		for (int i = 0; i < m; i++) {
			int x = rand.nextInt(m);
			int y = rand.nextInt(m);
			int tmp = a[x];
			a[x] = a[y];
			a[y] = tmp;
		}
		return a;
	}

	private void DanhGia() {
		for (int i = 0; i < n; i++) {
			int d = 0;
			for (int j = 1; j < m; j++) {
				d += dis[nghiem[i][j]][nghiem[i][(j+1)%m]];
			}
			f[i] = d;
		}
	}

	private void LuaChon() {
		int[] tmp = f.clone();
		Arrays.sort(tmp);
		int nguong = tmp[n * 80 / 100];
		for (int i = 0; i < n; i++)
			if (f[i] > nguong) {
				nghiem[i] = nghiem[rand.nextInt(n)].clone();
			}
	}

	private void LaiGhep() {
		for (int i = 0; i < n * 30 / 100; i++) {
			int cha = rand.nextInt(n);
			int me = rand.nextInt(n);
			int vitri = rand.nextInt(m - 1) + 1;
			int[] con1 = LG(cha, me, vitri);
			int[] con2 = LG(me, cha, vitri);
			nghiem[cha] = con1;
			nghiem[me] = con2;
		}
	}

	private int[] LG(int cha, int me, int vitri) {
		int[] trung = new int[n];
		int[] con = new int[n];
		for (int i = 0; i < vitri; i++) {
			con[i] = nghiem[cha][i];
			nghiem[cha][i] = i;
		}
		for (int i = 0; i < m; i++) {
			if (trung[nghiem[me][i]] == 0) {
				con[i] = nghiem[me][i];
				trung[nghiem[me][i]] = 1;
			} else {
				con[i] = -1;
			}
		}
		int j = 0;
		for (int i = vitri; i < m; i++) {
			if (con[i] == -1) {
				while (trung[j] == 1)
					j++;
				con[i] = j;
//				trung[j] = 1;
				j++;
			}
		}
		return con;
	}

	private void DotBien() {
		int i=rand.nextInt(n);
		int x=rand.nextInt(n);
		int y=rand.nextInt(n);
		int tmp=nghiem[i][x];
//		nghiem[]
//		for (int i = 0; i < 100; i++) {
//
//		}
	}

	private void print() {
		int[] cl = f.clone();
		Arrays.sort(cl);
		int best = cl[0];
		for (int i = 0; i < n; i++) {
			if (f[i] == best) {
				for (int j = 0; j < m; j++) {
					System.out.println(nghiem[i][j] + best);
				}
				System.out.println("");
			}
			break;
		}
	}

}
