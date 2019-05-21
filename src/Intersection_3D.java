import java.util.ArrayList;
import java.util.List;

public class Intersection_3D extends Thread {
	
	private Lumiere E = new Lumiere("Est (T)");	
	private Lumiere O = new Lumiere("Ouest (T)");
	private Lumiere S = new Lumiere("Sud (T)");
	
	private List<String> commands = new ArrayList<String>();
	private boolean sync;

	Intersection_3D(List<String> list, boolean sync) { 
		this.commands = list;
		this.sync = sync;
	}
	// mettre le feu Est a vert
	//lancer les actions permise
	public void estV() throws InterruptedException {
		Thread.sleep(500);
		
		if(compareState("?RR")) {
			
			this.E.setFeuV();
			
			synchronized (Actions_3D.getEContinue()) {
				Actions_3D.getEContinue().notifyAll();
				if (this.sync){
					for (int k = 0; k < Actions_3D.getACtionCountByIndex(Actions_3D.getEContinue()); ++k){
						Intersection_Sync.estContinue_3D();
					}
				}
				Actions_3D.setACtionCountByIndex(Actions_3D.getEContinue(), 0);
			}
			synchronized (Actions_3D.getETourneGauche()) {
				Actions_3D.getETourneGauche().notifyAll();
			}
		}
		else if(compareState("?VR")) {
			this.E.setFeuV();
			this.estOuestnotifyAll();
		}
		else {
			System.out.print("Impossible de changer le feu de l'est à V!");
		}
		Thread.sleep(500);
	}
	// mettre le feu Ouest a vert
	//lancer les actions permise
	public void ouestV() throws InterruptedException {
		Thread.sleep(500);
		
		if(compareState("R?R")) {

			this.O.setFeuV();
			
			synchronized (Actions_3D.getOContinue()) {
				Actions_3D.getOContinue().notifyAll();
			}
			synchronized (Actions_3D.getOTourneDroite()) {
				Actions_3D.getOTourneDroite().notifyAll();
			}
		}
		else if(compareState("V?R")) {
			this.O.setFeuV();
			this.estOuestnotifyAll();
		}
		else {
			System.out.print("Impossible de changer le feu de l'est à Vert! (T)");
		}
		Thread.sleep(500);
	}
	// mettre le feu Sud a vert
	//lancer les actions permise
	public void sudV() throws InterruptedException {
		Thread.sleep(500);
		
		if(compareState("RR?")) {
			
			this.S.setFeuV();
			synchronized (Actions_3D.getSTourneDroite()) {
				Actions_3D.getSTourneDroite().notifyAll();
			}
			synchronized (Actions_3D.getSTourneGauche()) {
				Actions_3D.getSTourneGauche().notifyAll();
				if (this.sync){
					for (int k = 0; k < Actions_3D.getACtionCountByIndex(Actions_3D.getSTourneGauche()); ++k){
						Intersection_Sync.sudTourneGauche_3D();
					}
				}
				Actions_3D.setACtionCountByIndex(Actions_3D.getSTourneGauche(), 0);
			}
		}
		else {
			System.out.print("Impossible de changer le feu du sud à Vert! (T)");
		}
		Thread.sleep(500);
	}
	// s'assurer le bonne fonctionement quand le feu est et Ouest sont vert
	private void estOuestnotifyAll() {
		synchronized (Actions_3D.getEContinue()) {
			Actions_3D.getEContinue().notifyAll();
			if (this.sync){
				for (int k = 0; k < Actions_3D.getACtionCountByIndex(Actions_3D.getEContinue()); ++k){
					Intersection_Sync.estContinue_3D();
				}
			}
			Actions_3D.setACtionCountByIndex(Actions_3D.getEContinue(), 0);
		}
		synchronized (Actions_3D.getOContinue()) {
			Actions_3D.getOContinue().notifyAll();
		}
		synchronized (Actions_3D.getOTourneDroite()) {
			Actions_3D.getOTourneDroite().notifyAll();
		}
	}
	//lancer l'action de terverse si les quatre feu sont rouge
	public void feuTravrse() throws InterruptedException {
		Thread.sleep(500);
		if(compareState("RRR")) {
			
			synchronized (Actions_3D.getTraverse()) {
				Actions_3D.getTraverse().notifyAll();
			}
		}
		Thread.sleep(500);
	}
	//mettre le feu Est a Rouge
	public void estR() throws InterruptedException {
		Thread.sleep(500);
		this.E.setFeuR();
		this.feuTravrse();
		Thread.sleep(500);
	}
	//mettre le feu Ouest a Rouge
	public void ouestR() throws InterruptedException {
		Thread.sleep(500);
		this.O.setFeuR();
		this.feuTravrse();
		Thread.sleep(500);
	}
	//mettre le feu Sud a Rouge
	public void sudR() throws InterruptedException {
		Thread.sleep(500);
		this.S.setFeuR();
		this.feuTravrse();
		Thread.sleep(500);
	}
	// la présance d'un nouveau pietons
	public void nouveauxPietons() throws InterruptedException {
		new Pietons(Actions_3D.getTraverse()).start();
		this.feuTravrse();
		
	}
	// la création des nouvelles voitures
	public void nouvelsVoitures(Action action) throws InterruptedException {
		new Voiture(action).start();
		int l = Actions_3D.getACtionCountByIndex(action)+1;
		Actions_3D.setACtionCountByIndex(action, l);
		if (compareState(action.getState())) {
			Thread.sleep(500);
			synchronized (action) {
				action.notifyAll();
				if (sync){
					if (action == Actions_3D.getEContinue()) {
						for (int k = 0; k < Actions_3D.getACtionCountByIndex(action); ++k){
							Intersection_Sync.estContinue_3D();
						}
						Actions_3D.setACtionCountByIndex(action, 0);
					}else if (action == Actions_3D.getSTourneGauche()) {
						for (int k = 0; k < Actions_3D.getACtionCountByIndex(action); ++k){
							Intersection_Sync.sudTourneGauche_3D();
						}
						Actions_3D.setACtionCountByIndex(action, 0);
					}
				}
			}
			Thread.sleep(500);
		}
	}
	//comparer létat des feu actuel avec une etat entrer en paramatre
	public boolean compareState(String pstateRequired) {
		String stateRequired = pstateRequired;
		String stateActual = E.getCouleur() + O.getCouleur() + S.getCouleur();
		int index = 0;
		if (stateRequired.contains("?")){
			index = stateRequired.indexOf("?");
			stateRequired = stateRequired.replace("?", "");
			stateActual = E.getCouleur() + O.getCouleur() + S.getCouleur();
			stateActual = stateActual.substring(0, index) + stateActual.substring(index+1);
		}
		return stateRequired.equals(stateActual);
	}
	// excuter les different actions enterer par l'utilisateur
	public void exec(String command) throws InterruptedException {
		
        String action = command.replace(" ", "").toLowerCase();
        
        switch (action) {
            case "nouveauxpietons":
                this.nouveauxPietons();
                break;
            case "estcontinue":
                this.nouvelsVoitures(Actions_3D.getEContinue());
                break;
            case "esttournegauche":
                this.nouvelsVoitures(Actions_3D.getETourneGauche());
                break;
            case "ouestcontinue":
                this.nouvelsVoitures(Actions_3D.getOContinue());
                break;
            case "ouesttournedroite":
                this.nouvelsVoitures(Actions_3D.getOTourneDroite());
                break;
            case "sudtournedroite":
                this.nouvelsVoitures(Actions_3D.getSTourneDroite());
                break;
            case "sudtournegauche":
                this.nouvelsVoitures(Actions_3D.getSTourneGauche());
                break;
            case "estvert":
                this.estV();
                break;
            case "estrouge":
                this.estR();
                break;
            case "ouestvert":
                this.ouestV();
                break;
            case "ouestrouge":
                this.ouestR();
                break;
            case "sudvert":
                this.sudV();
                break;
            case "sudrouge":
                this.sudR();
                break;
            default:
            	System.out.println(command + " : ne peut etre executer, svp corrigez-là!");
        }
    }

    @Override
    public void run() {
	    System.out.println("la liste des commandes pour (T) est : " + commands);
	    System.out.println();
	    
        for (int i = 0; i < this.commands.size(); ++i) {
            try {
                this.exec(this.commands.get(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
