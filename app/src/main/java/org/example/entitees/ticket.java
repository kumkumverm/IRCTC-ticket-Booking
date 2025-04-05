package org.example.entitees;

import javax.xml.crypto.Data;

public class ticket {
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private Data dateOfTravel;
    private Train train;
    public String getTicketInfo(){
        return String.format("Ticket ID: %s belongs to User %s from %s to %s on %s",ticketId,userId,source,destination);
    }
    public String getTicketId(){
        return ticketId;
    }
    public void setTicketId(String ticketId){
        this.ticketId=ticketId;
    }
    public String getSource(){
        return source;
    }
    public void setSource(String source){
        this.source=source;
    }
    public String getUserId(){
        return userId;
    }
    public String setUserId(){
        return this.userId=userId;
    }
    public String getDestination(){
        return destination;
    }
}
