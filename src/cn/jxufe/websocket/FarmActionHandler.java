package cn.jxufe.websocket;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import cn.jxufe.entity.User;

public class FarmActionHandler extends TextWebSocketHandler {
	private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketHandler.class);

	private static final ArrayList<WebSocketSession> users =

			new ArrayList<WebSocketSession>();
	private User farmUser;

	@Override

	protected void handleTextMessage(WebSocketSession session, TextMessage message)

			throws Exception {
	}

	@Override
	/* 发布webSocket会话时，在会话管理列表中注册该webSocket会话 */
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.add(session);
		farmUser = (User) session.getHandshakeAttributes().get("user");
		String username = farmUser.getUsername();
		LOGGER.info("用户 " + username + " 成功建立连接");
	}

	@Override
	/* 发生webSocket会话主动关闭事件时，清理会话管理列表 */

	public void afterConnectionClosed(WebSocketSession session, CloseStatus status)

			throws Exception {
		farmUser = (User) session.getHandshakeAttributes().get("user");
		String username = farmUser.getUsername();
		LOGGER.info("用户 " + username + " 连接关闭。状态: " + status);
		users.remove(session);
	}

	@Override
	/* 发生传输错误时关闭该用户的webSocket会话，并清理会话管理列表 */

	public void handleTransportError(WebSocketSession session, Throwable exception)

			throws Exception {
		farmUser = (User) session.getHandshakeAttributes().get("user");
		String username = farmUser.getUsername();
		if (session.isOpen()) {
			session.close();
		}
		LOGGER.debug("用户: " + username + " 连接由于传输错误被关闭......");
		users.remove(session);
	}

	/* 向全部用户发消息 */
	public void sendMessageToUsers(TextMessage message) {
		for (WebSocketSession user : users) {
			try {
				if (user.isOpen()) {
					user.sendMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/* 向单一用户发消息 */
	public void sendMessageToUser(String userName, TextMessage message) {
		for (WebSocketSession user : users) {
			farmUser = (User) user.getHandshakeAttributes().get("user");
			if (farmUser == null)
				continue;
			if (farmUser.getUsername().equals(userName)) {
				try {
					if (user.isOpen()) {
						user.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}