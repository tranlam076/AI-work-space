package nguoibanhang;


import java.util.Arrays;
import java.util.Random;

public class NguoiBanHang {
	int m = 10;
	int dis[][] = new int [m][m];
	int n=100;
	int [][] nghiem = new int [n][m];
	int[] f = new int[n];
	Random rand = new Random();
	
	
	public static void main(String[] args) {
		new NguoiBanHang();
	}

	public NguoiBanHang() {
		
		for(int i=0;i<m;i++){
			for(int j=0;j<m;j++){
				dis[i][j]=dis[j][i] = rand.nextInt(1000000);
			}
		}
		KhoiTao();
//		int [] con= child(nghiem[98], nghiem[99], 3);
//		for(int i=0;i<m;i++){
//			System.out.println(con[i]+",");
//			
//		}
//		
		print();
		
		
		for (int i = 0; i < 100; i++) {
			DanhGia();
			print();
//			LuaChon();
			LaiGhep();
			DotBien();
		}
	}

	private void KhoiTao() {
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				nghiem[i][j]=j;
			}
			for(int j=0;j<m*10;j++){
				int x= rand.nextInt(m);
				int y = rand.nextInt(m);
				int tmp = nghiem[i][x];
				nghiem[i][x] = nghiem[i][y];
				nghiem[i][y]=tmp;
			}
		}
	}
	
	private void DanhGia() {
		for(int i=0;i<n;i++){
			int d=0;
			for(int j=0;j<m;j++){
				d+=dis[nghiem[i][j-1]][nghiem[i][j]];
			}
			f[i]=d;
		}
	}
	
	private void LuaChon() {
	
	}

	private void LaiGhep() {
		for (int i = 0; i < n * 30 / 100; i++) {
			int cha = rand.nextInt(n);
			int me = rand.nextInt(n);
			int id = rand.nextInt(m-1)+1;
			int [] con1= child(nghiem[cha], nghiem[me],id);
			int [] con2= child(nghiem[me], nghiem[cha], id);
			nghiem[cha]=con1;
			nghiem[me]=con2;

		}
	}	

	private int[] child(int[] a, int[] b, int id) {
		// TODO Auto-generated method stub
		int [] checktrung= new int[a.length];
		int [] ans= new int[a.length];
		for(int i=0;i<id;i++){
			ans[i]=a[i];
			checktrung[a[i]]=i;
		}
		for(int i=0;i<a.length;i++){
			if(checktrung[b[i]] ==0){
				ans[i]=b[i];
				checktrung[b[i]] =1;
			}
			else{
				ans[i] = -1;
			}
		}
		int j=0;
		for(int i=0;i<a.length;i++){
			if(ans[i]==-1){
				
				while(checktrung[j]==1) j++;
				ans[i]=j;
				j++;
			}
		}
		return ans;
	}
	private void DotBien() {
		int i=rand.nextInt(n);
		int x=rand.nextInt(n);
		int y=rand.nextInt(n);		
	}
	
	private void print() {
		int[] cl = f.clone();
		Arrays.sort(cl);
		int best = cl[0];
		for(int i=0;i<n;i++){
//			if(f[i]){
				if(f[i] == best){
				for(int j=0;j<m;j++){
					System.out.println(nghiem[i][j]+" \n");
				}
				System.out.println("");
			}
			break;
		}
	}

}
