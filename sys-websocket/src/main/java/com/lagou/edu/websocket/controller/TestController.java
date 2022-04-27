package com.sys.test.websocket.controller;

import com.sys.test.websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    @Autowired
    WebSocketServer socketServer;

    @PostMapping("/webMessage/send")
    public String sendMsg(@RequestParam("message") String message, @RequestParam("openid") String openid) {
        socketServer.sendInfo(openid, message);
        return "SUCCESS";
    }
}
