import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Parser {
    private List<String> commands_intersection_3D;
    private List<String> commands_intersection_4D;
    private int chosenIntersection;

    public Parser() throws FileNotFoundException, InterruptedException {
        this.chooseIntersection();
        this.loadFile();
        this.startSimulation();
    }
  // lancer la simulation
    public void startSimulation() throws InterruptedException {

        if (this.chosenIntersection == 1) {
        	System.out.println();
            Intersection_3D Intersection_3D = new Intersection_3D(this.commands_intersection_3D, false);
            Intersection_3D.start();
            
            while(Intersection_3D.isAlive()) {
            	Thread.sleep(500);
            	if(Intersection_3D.isAlive()) {continue;}
            	Intersection_3D.join();
            	System.out.println();
            	System.out.println("Terminé!");
            	System.out.println("Au revoir!");
            	System.exit(0);
            }
        } else if (this.chosenIntersection == 2) {
        	System.out.println();
            Intersection_4D Intersection_4D = new Intersection_4D(  this.commands_intersection_4D, false);
            Intersection_4D.start();
            
            while(Intersection_4D.isAlive()) {
            	Thread.sleep(500);
            	if(Intersection_4D.isAlive()) {continue;}
            	Intersection_4D.join();
            	System.out.println();
            	System.out.println("Terminé!");
            	System.out.println("Au revoir!");
            	System.exit(0);
            }
        } else {
        	System.out.println();
            Intersection_3D Intersection_3D = new Intersection_3D(this.commands_intersection_3D, true);
            Intersection_3D.start();
            Intersection_4D Intersection_4D = new Intersection_4D(  this.commands_intersection_4D, true);
            Intersection_4D.start();
            
            while(Intersection_3D.isAlive() || Intersection_4D.isAlive()) {
            	Thread.sleep(2000);
            	if(Intersection_3D.isAlive() || Intersection_4D.isAlive()) {continue;}
            	Thread.sleep(2000);
            	Intersection_4D.join();
            	System.out.println();
            	System.out.println("Terminé!");
            	System.out.println("Au revoir!");
            	System.exit(0);
            }

        }
    }
// telecharger le fichier a executer
    public void loadFile() throws FileNotFoundException {
        if (chosenIntersection == 1) {
            File file = new File("..//Intersection_3D.txt");
            this.commands_intersection_3D = this.parser(file);

        } else if (chosenIntersection == 2) {
            File file = new File("..//Intersection_4D.txt");
            this.commands_intersection_4D = this.parser(file);
        } else {
            File file = new File("..//Intersection_3D.txt");
            this.commands_intersection_3D = this.parser(file);
            File file2 = new File("..//Intersection_4D.txt");
            this.commands_intersection_4D = this.parser(file2);
        }


    }


    public List<String> parser(File file) throws FileNotFoundException {
        List<String> listDesTtraces = new ArrayList<>();
        @SuppressWarnings("resource")
		Scanner entrer = new Scanner(file);
        while (entrer.hasNext()) {
            String line = entrer.nextLine().trim();
            listDesTtraces.add(line);
        }
        return listDesTtraces;


    }
// choisir l'intersection
    public void chooseIntersection() {
        //String intersecions [] = {"Intersection_3D", "Intersection_3D", "Intersection_Sync" };

    	String inputS;
    	int input;
    	
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenue a l application de simulation des intersections : ");
        System.out.println();
        System.out.println("Vous devez choisir l intersection a simuler ");
        System.out.println("1-Intersection en (T)");
        System.out.println("2-Intersection ordinaire (+)");
        System.out.println("3-Synchronisation des deux intersections (T) et (+)");
        System.out.print("Entrer votre choix : ");
        inputS = sc.nextLine();
        while (!inputS.equals("1") && !inputS.equals("2") && !inputS.equals("3")) {
        	System.out.println();
            System.out.println("le numero entre est invalide svp de faire un bon choix");
            System.out.println("1-Intersection en (T)");
            System.out.println("2-Intersection ordinaire (+)");
            System.out.println("3-Synchronisation des deux intersections (T) et (+)");
            System.out.print("Entrer votre choix : ");
            inputS = sc.nextLine();
        }
        input = Integer.parseInt(inputS);
        this.chosenIntersection = input;
    }
}

