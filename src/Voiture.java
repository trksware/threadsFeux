
class Voiture extends Thread {
	
	private Action action;
	
	public Voiture(Action action) {
		this.action = action;
	}
	
	@Override
	public void run() {
		synchronized (this.action) {
			try {
				System.out.println(this.action.getMsgAction() + " (arrive) : Attendre le feu!");
				this.action.wait();
				System.out.println(this.action.getMsgAction() + " (effectué)");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
