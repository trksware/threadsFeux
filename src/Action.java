
public class Action{
	
	private String msgAction;
	private String state = "";
	
	public Action(String msgAction, String state) {
		this.msgAction = msgAction;
		this.state = state;
	}

	public String getMsgAction() {
		return msgAction;
	}

	public String getState() {
		return state;
	}
}
