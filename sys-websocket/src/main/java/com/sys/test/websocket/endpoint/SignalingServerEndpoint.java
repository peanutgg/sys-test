package com.sys.test.websocket.endpoint;

import com.sys.test.websocket.entity.WebSocketUser;
import lombok.Data;

import javax.websocket.server.ServerEndpoint;

/**
 * @author: 橘子
 * @date: 2022/6/10 14:08
 */
@ServerEndpoint("/SignalingServerEndpoint")
@Data
public class SignalingServerEndpoint {
//    private EntityManagerFactory emf;
//    private EntityManager em;
//    private EntityTransaction tx;
//    private TypedQuery<WebsocketUser> query;
    private WebSocketUser wsu;
}
