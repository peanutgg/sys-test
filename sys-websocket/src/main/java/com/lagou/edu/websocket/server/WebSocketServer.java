package com.sys.test.websocket.server;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/websocket/{openid}")
@Component
@Slf4j
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。把它设计成线程安全的。
    private static AtomicInteger onlineCount=new AtomicInteger();

    //用来存放每个客户端对应的WebSocketServer对象
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    //发送消息
    public void sendMessage(Session session, String message) throws IOException {
        if(session != null){
            synchronized (session) {
                log.info("发送数据:{}", message);
                session.getBasicRemote().sendText(message.toString());
            }
        }
    }

    //给指定用户发送信息
    public void sendInfo(String openid, JSONObject message){
        Session session = sessionPools.get(openid);
        log.info("给[{}], session:{}", openid, session);
        try {
            sendMessage(session, message.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //给指定用户发送信息
    public void sendInfo(String openid, String message){
        Session session = sessionPools.get(openid);
        log.info("给[{}], session:{}", openid, session);
        try {
            sendMessage(session, message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "openid") String openid){
        sessionPools.put(openid, session);
        addOnlineCount();
        log.info("[{}]加入webSocket，当前人数为:{}，session:{}", openid, onlineCount,session);
        try {
            sendMessage(session, "欢迎[" + openid + "]加入连接！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("人员信息:{}", sessionPools);
    }

    //关闭连接时调用
    @OnClose
    public void onClose(@PathParam(value = "openid") String openid){
        sessionPools.remove(openid);
        subOnlineCount();
        log.info("[{}]断开webSocket连接，当前人数为:{}", openid, onlineCount);
    }

    /**
     * 收到客户端信息
     */
    @OnMessage
    public void onMessage(String message) throws IOException{
        log.info("客户端[{}]已收到", message);
        message = "客户端：" + message + ",已收到";
        for (Session session: sessionPools.values()) {
            try {
                sendMessage(session, message);
            } catch(Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable){
        log.error("发生错误:{}", session);
        throwable.printStackTrace();
    }


    /**
     * 人数加1
     */
    public static void addOnlineCount(){
        onlineCount.incrementAndGet();
    }

    /**
     * 人数减1
     */
    public static void subOnlineCount() {
        onlineCount.decrementAndGet();
    }


}

