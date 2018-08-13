
public class kMeans {
	public static void main(String[] stringss){
		//Entrée des points à utiliser
		double x,y;
		System.out.println("Enter the number of points you want to use");
		int NB=Clavier.lireInt();
		Point[] points=new Point[NB];
		System.out.println("Do you want to manually set your points' coordinates Y/N?");
		char réponse=Clavier.lireChar();
		if (réponse=='Y'){
			int j=1;
			for (int i=0;i<NB;i++){
				System.out.println("enter X"+(i+1));
				x=Clavier.lireDouble();
				System.out.println("enter Y"+(i+1));
				y=Clavier.lireDouble();
				points[i]=new Point(x,y,j);
				j++;
			}
		} else if (réponse=='N') {
			System.out.println("The points are generated randomly in an interval of [-Xmax : +Xmax] & [-Ymax : +Ymax]");
			System.out.println("Enter the max boundary for axis 'x' Xmax > 0 : ");
			int Xmax=Clavier.lireInt();
			System.out.println("Enter the max boundary for axis 'y' Ymax > 0 : ");
			int Ymax=Clavier.lireInt();
			points=Point.genP(Xmax, Ymax, NB);
		}
		
		System.out.println("Below your randomly generated points coordinates ");
		for (int i=0;i<NB;i++){
			points[i].affiche();
		}
		
		//Choix du nombre de clusters à considérer
		System.out.println("Enter the number of clusters k : ");
		int K=Clavier.lireInt();

		//Choix du nombre d'itérations
		System.out.println("Enter the maximum number of iterations");
		int iterations=Clavier.lireInt();
		
		//Entrée des coordonnées des centroids
		Point[] centroids=new Point[K];
		int f=0;
		System.out.println("***** Entering Centroids Initial Coordinates *****");
		for (int i=0;i<K;i++){
			System.out.println("Enter Cx"+(i+1));
			x=Clavier.lireDouble();
			System.out.println("Enter Cy"+(i+1));
			y=Clavier.lireDouble();
			f=f-1;
			centroids[i]=new Point(x,y,f);
		}
		
		//Affichage des Id des centroids
	    System.out.println("Your centroids Ids (ranging from 1 to the number of centroids k)");
		for (int k=0;k<K;k++){
			System.out.println("Id[C"+(k+1)+"]= "+centroids[k].getId());
		}
		
		//Itérations
		double[][] Matrice=new double[K][];
		int j=0;
		int déplacés=K;
		while (j<iterations && déplacés!=0){
			for (int i=0;i<K;i++){
				Matrice[i]=(centroids[i]).listDist(points);
			}
			
			//Attribution des numéro de clusters aux points
			for (int i=0;i<points.length;i++){
				int ClusterId=centroids[MatriceManip.minCol(Matrice,i)].getId();
				points[i].setCluster(ClusterId);
			}
			
			//Calcul des nouveaux centres des clusters et correction des anciens clusters par ces nouveaux
			for (int i=0;i<K;i++){
				if (centroids[i].getX()==(Point.Mean(centroids[i].getId(), points)).getX() && centroids[i].getY()==(Point.Mean(centroids[i].getId(), points)).getY()){
					déplacés=déplacés-1;
				}
				centroids[i].setX((Point.Mean(centroids[i].getId(), points)).getX());
				centroids[i].setY((Point.Mean(centroids[i].getId(), points)).getY());
			}
			
			//Affichage des coordonnées des centroids après (j+1)ème itération
			System.out.println("After "+(j+1)+" iteration");
			for (int i=0;i<K;i++){
				centroids[i].affiche();
			}
			//Affichage des clusters auxquels les points appartiennent après (j+1)ème itération
			System.out.println("Clusters after "+(j+1)+" iterations");
			for (int k=0;k<NB;k++){
				System.out.println("Cluster[P"+(k+1)+"]= "+points[k].getCluster());
			}
			if (déplacés==0){
				System.out.println("You have reached AN equilibrium after "+ j + " iterations");
			}
			j++;
		}
		if (j == iterations & déplacés!=0) {
			System.out.println("You have reached the max iterations");
		}
	}
}
