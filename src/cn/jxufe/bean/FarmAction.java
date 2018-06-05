package cn.jxufe.bean;

import java.util.List;

public class FarmAction {
	/**
	 * 该用户各个土地的信息
	 */
	private List<Message> messageList;
	public List<Message> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}
}
