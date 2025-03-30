package com.example.nienluannganh.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerWebsocket {
	@MessageMapping("/hello") 
    @SendTo("/topic/greetings") 
    public String sendMessage(String message) {
        return "Hello, " + message + "!";
    }
}
