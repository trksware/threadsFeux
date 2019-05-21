import java.util.ArrayList;
import java.util.List;

public class Intersection_4D extends Thread{
	
	private Lumiere E = new Lumiere("Est (+)");	
	private Lumiere O = new Lumiere("Ouest (+)");
	private Lumiere S = new Lumiere("Sud (+)");
	private Lumiere N = new Lumiere("Nord (+)");
	
	private List<String> commands = new ArrayList<String>();
	private boolean sync;
	
	Intersection_4D(List<String> list, boolean sync) {
		this.commands = list;
		this.sync = sync;
	}
	// mettre le feu Est a vert
	//lancer les actions permise
	public void estV() throws InterruptedException {
		Thread.sleep(500);
		
		if(compareState("?RRR")) {
			
			this.E.setFeuV();
			
			synchronized (Actions_4D.getEContinue()) {
				Actions_4D.getEContinue().notifyAll();
			}
			synchronized (Actions_4D.getETourneDroite()) {
				Actions_4D.getETourneDroite().notifyAll();
			}
			synchronized (Actions_4D.getETourneGauche()) {
				Actions_4D.getETourneGauche().notifyAll();
			}
		}
		else if(compareState("?VRR")) {
			this.E.setFeuV();
			this.estOuestnotifyAll();
		}
		else {
			System.out.print("Impossible de changer le feu de l'est à Vert! (+)");
		}
		Thread.sleep(500);
	}
	//mettre le feu ouest a vert
	//lancer les actions permise
	public void ouestV() throws InterruptedException {
		Thread.sleep(500);
		
		if(compareState("R?RR")) {

			this.O.setFeuV();
			
			synchronized (Actions_4D.getOContinue()) {
				Actions_4D.getOContinue().notifyAll();

				if (this.sync){
					for (int k = 0; k < Actions_4D.getACtionCountByIndex(Actions_4D.getOContinue()); ++k){
						Intersection_Sync.ouestContinue_4D();
					}
				}
				Actions_4D.setACtionCountByIndex(Actions_4D.getOContinue(), 0);
			}
			synchronized (Actions_4D.getOTourneDroite()) {
				Actions_4D.getOTourneDroite().notifyAll();
			}
			synchronized (Actions_4D.getOTourneGauche()) {
				Actions_4D.getOTourneGauche().notifyAll();
			}
		}
		else if(compareState("V?RR")) {
			this.O.setFeuV();
			this.estOuestnotifyAll();
		}
		else {
			System.out.print("Impossible de changer le feu de l'est à Vert! (+)");
		}
		Thread.sleep(500);
	}
	//mettre le feu Sud a vert
	//lancer les actions permise
	public void sudV() throws InterruptedException {
		Thread.sleep(500);
		
		if(compareState("RR?R")) {
			
			this.S.setFeuV();
			synchronized (Actions_4D.getSContinue()) {
				Actions_4D.getSContinue().notifyAll();
			}
			synchronized (Actions_4D.getSTourneDroite()) {
				Actions_4D.getSTourneDroite().notifyAll();
				if (this.sync){
					for (int k = 0; k < Actions_4D.getACtionCountByIndex(Actions_4D.getSTourneDroite()); ++k){
						Intersection_Sync.sudTourneDroite_4D();
					}
				}
				Actions_4D.setACtionCountByIndex(Actions_4D.getSTourneDroite(), 0);
			}
			synchronized (Actions_4D.getSTourneGauche()) {
				Actions_4D.getSTourneGauche().notifyAll();
			}
		}
		else if(compareState("RR?V")) {
			this.S.setFeuV();
			this.sudNordnotifyAll();
		}
		else {
			System.out.print("Impossible de changer le feu du sud à Vert! (+)");
		}
		Thread.sleep(500);
	}

	//mettre le feu Nord a vert
	//lancer les actions permise
	public void nordV() throws InterruptedException {
		
		Thread.sleep(500);
		
		if(compareState("RRR?")) {
			
			this.N.setFeuV();
			
			synchronized (Actions_4D.getNContinue()) {
				Actions_4D.getNContinue().notifyAll();
			}
			synchronized (Actions_4D.getNTourneDroite()) {
				Actions_4D.getNTourneDroite().notifyAll();
			}
			synchronized (Actions_4D.getNTourneGauche()) {
				Actions_4D.getNTourneGauche().notifyAll();
				if (this.sync){
					for (int k = 0; k < Actions_4D.getACtionCountByIndex(Actions_4D.getNTourneGauche()); ++k){
						Intersection_Sync.nordTourneGauche_4D();
					}
				}
				Actions_4D.setACtionCountByIndex(Actions_4D.getNTourneGauche(), 0);
			}
		}
		else if(compareState("RRV?")) {
			this.N.setFeuV();
			this.sudNordnotifyAll();
		}
		else {
			System.out.print("Impossible de changer le feu du nord à Vert! (+)");
		}
		Thread.sleep(500);
	}
	// s'assurer le bonne fonctionement quand le feu est et Ouest sont vert
	private void estOuestnotifyAll() {
		synchronized (Actions_4D.getEContinue()) {
			Actions_4D.getEContinue().notifyAll();
		}
		synchronized (Actions_4D.getETourneDroite()) {
			Actions_4D.getETourneDroite().notifyAll();
		}
		synchronized (Actions_4D.getOContinue()) {
			Actions_4D.getOContinue().notifyAll();
			if (this.sync){
				for (int k = 0; k < Actions_4D.getACtionCountByIndex(Actions_4D.getOContinue()); ++k){
					Intersection_Sync.ouestContinue_4D();
				}
			}
			Actions_4D.setACtionCountByIndex(Actions_4D.getOContinue(), 0);
		}
		synchronized (Actions_4D.getOTourneDroite()) {
			Actions_4D.getOTourneDroite().notifyAll();
		}
	}
	// s'assurer le bonne fonctionement quand le feu Sud et Nord sont vert
	private void sudNordnotifyAll() {
		synchronized (Actions_4D.getSContinue()) {
			Actions_4D.getSContinue().notifyAll();
		}
		synchronized (Actions_4D.getSTourneDroite()) {
			Actions_4D.getSTourneDroite().notifyAll();
			if (this.sync){
				for (int k = 0; k < Actions_4D.getACtionCountByIndex(Actions_4D.getSTourneDroite()); ++k){
					Intersection_Sync.sudTourneDroite_4D();
				}
			}
			Actions_4D.setACtionCountByIndex(Actions_4D.getSTourneDroite(), 0);
		}
		synchronized (Actions_4D.getNContinue()) {
			Actions_4D.getNContinue().notifyAll();
		}
		synchronized (Actions_4D.getNTourneDroite()) {
			Actions_4D.getNTourneDroite().notifyAll();
		}
	}
	//
	//lancer l'action de terverse si les quatre feu sont rouge
	public void feuTravrse() throws InterruptedException {
		Thread.sleep(500);
		if(compareState("RRRR")) {
			
			synchronized (Actions_4D.getTraverse()) {
				Actions_4D.getTraverse().notifyAll();
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
	//mettre le feu Nord a Rouge
	public void nordR() throws InterruptedException {
		Thread.sleep(500);
		this.N.setFeuR();
		this.feuTravrse();
		Thread.sleep(500);
	}
   // la présance d'un nouveau pietons
	public void nouveauxPietons() throws InterruptedException {
		new Pietons(Actions_4D.getTraverse()).start();
		this.feuTravrse();
		
	}
   // la création des nouvelles voitures
	public void nouvelsVoitures(Action action) throws InterruptedException {
		new Voiture(action).start();
		int l = Actions_4D.getACtionCountByIndex(action)+1;
		Actions_4D.setACtionCountByIndex(action, l);
		if (compareState(action.getState())) {
			Thread.sleep(500);
			synchronized (action) {
				action.notifyAll();
								if (sync){
					if (action == Actions_4D.getOContinue()) {
						for (int k = 0; k < Actions_4D.getACtionCountByIndex(action); ++k){
							Intersection_Sync.ouestContinue_4D();
						}
						Actions_4D.setACtionCountByIndex(action, 0);
					}else if (action == Actions_4D.getSTourneDroite()) {
						for (int k = 0; k < Actions_4D.getACtionCountByIndex(action); ++k){
							Intersection_Sync.sudTourneDroite_4D();
						}
						Actions_4D.setACtionCountByIndex(action, 0);
					}else if (action == Actions_4D.getNTourneGauche()) {
						for (int k = 0; k < Actions_4D.getACtionCountByIndex(action); ++k){
							Intersection_Sync.nordTourneGauche_4D();
						}
						Actions_4D.setACtionCountByIndex(action, 0);
					}
				}
			}
			Thread.sleep(500);
		}
	}
	//comparer létat des feu actuel avec une etat entrer en paramatre
	public boolean compareState(String pstateRequired) {
		String stateRequired = pstateRequired;
		String stateActual = E.getCouleur() + O.getCouleur() + S.getCouleur() + N.getCouleur();
		int index = 0;
		if (stateRequired.contains("?")){
			index = stateRequired.indexOf("?");
			stateRequired = stateRequired.replace("?", "");
			stateActual = E.getCouleur() + O.getCouleur() + S.getCouleur() + N.getCouleur();
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
				this.nouvelsVoitures(Actions_4D.getEContinue());
				break;
			case "esttournedroite":
				this.nouvelsVoitures(Actions_4D.getETourneDroite());
				break;
			case "esttournegauche":
				this.nouvelsVoitures(Actions_4D.getETourneGauche());
				break;
			case "ouestcontinue":
				this.nouvelsVoitures(Actions_4D.getOContinue());
				break;
			case "ouesttournedroite":
				this.nouvelsVoitures(Actions_4D.getOTourneDroite());
				break;
			case "ouesttournegauche":
				this.nouvelsVoitures(Actions_4D.getOTourneGauche());
				break;
			case "sudcontinue":
				this.nouvelsVoitures(Actions_4D.getSContinue());
				break;
			case "sudtournedroite":
				this.nouvelsVoitures(Actions_4D.getSTourneDroite());
				break;
			case "sudtournegauche":
				this.nouvelsVoitures(Actions_4D.getSTourneGauche());
				break;
			case "nordcontinue":
				this.nouvelsVoitures(Actions_4D.getNContinue());
				break;
			case "nordtournedroite":
				this.nouvelsVoitures(Actions_4D.getNTourneDroite());
				break;
			case "nordtournegauche":
				this.nouvelsVoitures( Actions_4D.getNTourneGauche());
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
			case "nordvert":
				this.nordV();
				break;
			case "nordrouge":
				this.nordR();
				break;
			default:
            	System.out.println(command + " : ne peut etre executer, svp corrigez-là!");
		}
		
	}
	
	@Override
	public void run() {
		System.out.println("la liste des commandes pour (+) est : " + commands);
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
