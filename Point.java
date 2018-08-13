
import java.util.Random;
class Point {

	//Déclaration des champs (propres aux objets) & champs de classe (propres aux classes)
	protected double x,y; 					//Abcisse et Ordonné
	protected int Id=0;   					//Identifiant du point, négatif pour un centroid, positif pour un point ordinaire
	protected static int nombreP=0;			//Nombre d'objets créés de type Point, propre à la classe
	protected int ClusterId=0;					//Identifiant du Cluster (Groupe) auquel appartient le point

	//Constructeurs
	//1-Constructeur sans argument
	public Point(){							
		x=0.; y=0.; Id=0;
	}
	
	//2-Constructeur à arguments explicites
	public Point(double x, double y, int Id){
		this.x=x ; this.y=y ; this.Id=Id ;
		if (Id>0){
			Point.nombreP++; 				//énumération du nombre de points ordinaires (Id>0)
		}
	}
	public Point(double x, double y){
		this.x=x;
		this.y=y;
	}
		

	//3-Constructeur à argument de classe Point
	public Point(Point p){
		this.x=p.x;
		this.y=p.y;
		this.Id=p.Id;
	}
	
	//Méthodes ordinaires (non statiques)
	//1-Méthodes d'accès (get) et Méthodes d'Altération (set)
	public double getX() {	return(this.x);	}
	public double getY() {	return(this.y); }
	
	public void setY(double y) {  this.y = y; }
	public void setX(double x) {  this.x = x; }
	
	public void setCluster(int cl) {	 this.ClusterId=cl; }
	public int getCluster() { return(  this.ClusterId); }
	
	public void setId(int id) {  this.Id=id; }
	public int getId() {	 return(this.Id); }
	
	public int getNB(){
		return(nombreP);
	}
	
	//2-Méthodes d'affichage et méthodes de calcul (fonctions)
	public void affiche(){
		if (this.Id>0){
			System.out.println("les coordonnées du point "+this.getId()+" sont ("+x+","+y+")");      
		}
		if (this.Id<0){
			System.out.println("les coordonnées du centroid "+(-this.getId()) +"sont ("+x+","+y+")");
		}
	}
	
	public double distance(Point p){										//Calcule la distance entre le Point en argument implicite
		double carré=(this.x-p.x)*(this.x-p.x)+(this.y-p.y)*(this.y-p.y);	//et le point passé en argument explicite
		return(Math.sqrt(carré));
	}
	
	public double[] listDist(Point...points){				//Retourne un tableau unidimensionnel comportant la distance
		double distances[]=new double[points.length];		//entre l'Objet de type Point (argument implicite et les points 
		int i=0;											//entrées en arguments explicites (suites de Point ou bien tableau)
		for (Point p:points){
			distances[i]=this.distance(p);
			i++;
		}
		return(distances);
	}
	
	public boolean coincide(Point p){						//Retourne un boolean, vrai en cas de coincidence
		return((p.x==this.x) && (p.y==this.y));      
	}
	
	
	//Méthodes de classe (statiques)
	
	//Distance entre 2 points
	public static double distance(Point p1,Point p2){					
		double carré=(p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y);
		return(Math.sqrt(Math.abs(carré)));
	}
	//Echange de 2 points
	public static void echange(Point p1, Point p2){							
		double X=p1.x;
		double Y=p1.y;
		int ID=p1.Id;
		p1.x=p2.x;
		p1.y=p2.y;
		p1.Id=p2.Id;
		p2.x=X ; p2.y=Y ; p2.Id=ID ;
	}
	//Calcul du barycentre (de Type Point) des points appartenant au cluster (passé en argument) parmi les points (passés en argument)
	public static Point Mean(int Cluster, Point...points){
		double Xg=0,Yg=0;
		int n=0;
		for (Point p:points){
			if (Cluster==p.getCluster()){
				n++;
				Xg=Xg+p.getX();
				Yg=Yg+p.getY();
			}	
		}
		Point Centre=new Point(Xg/n,Yg/n, Cluster);
		return(Centre);
	}
	
	Point symetrie(){
		Point psy=new Point(this.y,this.x);
		return(psy);
	}
	
	Point copie(){
		Point pc=new Point(this.x,this.y);
		return(pc);
	}
	//Générateur aléatoire de points 
	public static Point[] genP(int Xmax, int Ymax, int NB){
		Random randX= new Random();
		Random randY= new Random();
		Random randSigne= new Random();
    		Point[] points=new Point[NB];
    		for (int i = 0; i < NB; ++i){
    			int signe=randSigne.nextInt(2);
    			if (signe==0) signe=-1;
    		    float randomx = signe*randX.nextFloat()*Xmax;
    		    signe=randSigne.nextInt(2);
    			if (signe==0) signe=-1;
    		    float randomy = signe*randY.nextFloat()*Ymax;
    		    points[i]=new Point(randomx,randomy,i+1);
    		    }
    		return(points);
    }
/*	//classe héritée
	class Pixel extends Point{
		private double lum;
		private String col;
		public Pixel(double x, double y, String col,double lum){
			super(x,y);
			this.col=col;
			this.lum=lum;
		}
		pub
	}*/
	}
	
	

