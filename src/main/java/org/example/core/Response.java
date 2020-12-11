package org.example.core;
//это ответ бота
import java.util.List;

public class Response {
    private String messageText;
    private List<Flat> flats;
    private boolean isSuccessful;

    public Response(String messageText){
        this.messageText = messageText;
    }

    public Response(String messageText, boolean isSuccessful){
        this.messageText = messageText;
        this.isSuccessful = isSuccessful;
    }

    public Response(String messageText, List<Flat> flats){
        this.flats = flats;
        this.messageText = messageText;
    }

    public Response(List<Flat> flats){
        this.flats = flats;
    }

    public String getMessageText(){
        return this.messageText;
    }

    public List<Flat> getFlats(){
        return this.flats;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

}
