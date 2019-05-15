package jc.com.webSocket;

public class Message {
	
	private String fromUserId;
	private String fromUserName;
	private String sendUserId;
	private String sendUserName;
	private String message;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Message(String fromUserId,String fromUserName,String sendUserId,String sendUserName,String message) {
		this.fromUserId = fromUserId;
		this.fromUserName = fromUserName;
		this.sendUserId = sendUserId;
		this.sendUserName = sendUserName;
		this.message = message;
	}
	
	public String getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}
	public String getSendUserName() {
		return sendUserName;
	}
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
