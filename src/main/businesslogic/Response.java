package main.businesslogic;
//это ответ бота
import java.util.List;

public class Response {
    private String messageText;
    private List<Flat> flats;

    public Response(String messageText){
        this.messageText = messageText;
    }

    public Response(String messageText, List<Flat> flats){
        this.flats = flats;
        this.messageText = messageText;
    }

    public String getMessageText(){
        return this.messageText;
    }

    public List<Flat> getFlats(){
        return this.flats;
    }

}
