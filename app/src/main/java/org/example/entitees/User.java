package org.example.entitees;

import java.util.List;

public class User{
    private String name;
    private String password;
    private String hashedPassword;
    private List<ticket> ticketsBooked;
    private String userId;

    public User(String name, String password, String hashedPassword, List<ticket> ticketsBooked, String userId) {
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.ticketsBooked = ticketsBooked;
        this.userId = userId;
    }

    public User() {
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public List<ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public void printTickets() {
        for(int i = 0; i < ticketsBooked.size(); i++) {
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }
    public String getUserId () {
        return userId;
    }
    public void setName (String name){
        this.name = name;
    }
    public void setHashedPassword (String hashedPassword){
        this.hashedPassword = hashedPassword;
    }
    public void setTicketsBooked(List<ticket> ticketsBooked){
        this.ticketsBooked=ticketsBooked;
    }
}
