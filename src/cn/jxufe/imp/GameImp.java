package cn.jxufe.imp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import cn.jxufe.service.GameService;
import cn.jxufe.websocket.FarmActionHandler;
@Service
public class GameImp implements GameService {
	@Autowired
	FarmActionHandler farmActionHandler;
	
	Timer timer = new Timer();
	@Override
	public void ActionStart() {
		timer.schedule(
				new TimerTask() {
						@Override
						 public void run() {
								talkToAll();
							}
						}, 0,60000);
	}
	
	private void talkToAll(){
		farmActionHandler.sendMessageToUsers(new TextMessage("现在服务器时间是："+ new Date()));
	}

	
}