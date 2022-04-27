package com.sys.test.websocket.server;
/*

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnError;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/client/{userName}")
@Component
@Slf4j
public class WebSocketServer2 {

    */
/**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     *//*

    private static int onlineCount = 0;
    */
/**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     *//*

    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    */
/**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     *//*

    private Session session;
    */
/**
     * 接收userId
     *//*

    private String userName = "";

    */
/**
     * @Description: 连接建立成功调用的方法，成功建立之后，将用户的userName 存储到redis
     * @params: [session, userId]
     * @return: void
     * @Author: wangxianlin
     * @Date: 2020/5/9 9:13 PM
     *//*

    @OnOpen
    public void onOpen(Session session, @PathParam("userName") String userName) {
        this.session = session;
        this.userName = userName;
        webSocketMap.put(userName, this);
        addOnlineCount();
        log.info("用户连接:" + userName + ",当前在线人数为:" + getOnlineCount());
    }

    */
/**
     * @Description: 连接关闭调用的方法
     * @params: []
     * @return: void
     * @Author: wangxianlin
     * @Date: 2020/5/9 9:13 PM
     *//*

    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userName)) {
            webSocketMap.remove(userName);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:" + userName + ",当前在线人数为:" + getOnlineCount());
    }


    */
/**
     * @Description: 收到客户端消息后调用的方法, 调用API接口 发送消息到
     * @params: [message, session]
     * @return: void
     * @Author: wangxianlin
     * @Date: 2020/5/9 9:13 PM
     *//*

    @OnMessage
    public void onMessage(String message, @PathParam("userName") String userName) {
        log.info("用户消息:" + userName + ",报文:" + message);
        if (StringUtils.isNotBlank(message)) {
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("sender", this.userName);
                String receiver = jsonObject.getString("receiver");
                //传送给对应toUserId用户的websocket
                if (StringUtils.isNotBlank(receiver) && webSocketMap.containsKey(receiver)) {
                    webSocketMap.get(receiver).session.getBasicRemote().sendText(jsonObject.toJSONString());
                } else {
                    log.error("用户:" + receiver + "不在该服务器上");
                    //否则不在这个服务器上，发送到mysql或者redis
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    */
/**
     * 发布websocket消息
     * 消息格式： { "sender": "u2","receiver": "u1","msg": "hello world","createTime":"2021-10-12 11:12:11"}
     *
     * @param dto
     * @return
     *//*

    public static void sendWebsocketMessage(ChatMsg dto) {
        if (dto != null) {
            if (StringUtils.isNotBlank(dto.getReceiver()) && webSocketMap.containsKey(dto.getReceiver())) {
                String json = JSON.toJSONString(dto);
                try {
                    webSocketMap.get(dto.getReceiver()).session.getBasicRemote().sendText(json);
                } catch (IOException e) {
                    log.error("消息发送异常：{}", e.toString());
                }
            } else {
                log.error("用户:" + dto.getReceiver() + ",不在线！");
            }
        }
    }

    */
/**
     * @param session
     * @param error
     *//*

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userName + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    */
/**
     * @Description: 获取在线人数
     * @params: []
     * @return: int
     * @Author: wangxianlin
     * @Date: 2020/5/9 9:09 PM
     *//*

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    */
/**
     * @Description: 在线人数+1
     * @params: []
     * @return: void
     * @Author: wangxianlin
     * @Date: 2020/5/9 9:09 PM
     *//*

    public static synchronized void addOnlineCount() {
        WebSocketServer2.onlineCount++;
    }

    */
/**
     * @Description: 在线人数-1
     * @params: []
     * @return: void
     * @Author: wangxianlin
     * @Date: 2020/5/9 9:09 PM
     *//*

    public static synchronized void subOnlineCount() {
        WebSocketServer2.onlineCount--;
    }
}
*/
