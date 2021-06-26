package com.ust.person.exception;

import java.util.Date;

public class ErrorDetails {
	private Date timeStamp;
	private String messages;
	private String details;
	public ErrorDetails() {
		
	}
	public ErrorDetails(Date timeStamp, String messages, String details) {
		super();
		this.timeStamp = timeStamp;
		this.messages = messages;
		this.details = details;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "ErrorDetails [timeStamp=" + timeStamp + ", messages=" + messages + ", details=" + details + "]";
	}
	
	
	

}
