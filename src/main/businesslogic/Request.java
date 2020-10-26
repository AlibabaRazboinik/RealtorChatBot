package main.businesslogic;

import java.util.List;

//информация от пользователя (нам неважно откуда она пришла telegram, console)
public class Request {
    private String messageText;
    private Integer userId;

    public Request(String messageText, Integer userId) {
        this.messageText = messageText;
        this.userId = userId;
    }

    public String getMessageText() {
        return this.messageText;
    }

    public Integer getUserId() {
        return this.userId;
    }


}
