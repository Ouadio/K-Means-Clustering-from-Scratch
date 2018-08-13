
public class MatriceManip {
	public static int minCol(double[][] mat, int col){
		int i=0,j=1;
		int indice=0;
		bloc :
		while(i<(mat.length-1)){
			while(j<mat.length && mat[i][col]<mat[j][col]){
				if (j+1==mat.length) break bloc;
					else j++;
			}
			i=j;
			indice=j;
			j++;
		}
		return(indice);
	}

}
