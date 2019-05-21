
class Pietons extends Thread {

	private Action traverse;
	
	public Pietons(Action traverse) {
		this.traverse = traverse;	
	}
	
	@Override
	public void run() {
		synchronized (this.traverse) {
			try {
				System.out.println(this.traverse.getMsgAction() + " (arrive) : Attendre le feu!");
				this.traverse.wait();
				System.out.println(this.traverse.getMsgAction() + " (effectué)");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
