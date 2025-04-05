package org.example.entitees.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Ticker;
import org.example.entitees.Train;
import org.example.entitees.User;
import org.example.entitees.ticket;
import org.example.util.UserServerUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.util.spi.ToolProvider.findFirst;

public class userBookingService{
    private User user;
    private List<User> userList;
    private ObjectMapper objectMapper=new ObjectMapper();
    private static final String USERS_PATH ="app/src/main/java/ticket/booking/localDb/users.json";
    public userBookingService(User user1) throws IOException {
        this.user=user1;
        loadUsers();
    }
   public userBookingService() throws IOException{
      loadUsers();
   }
   public List<User> loadUsers() throws IOException{
        File users = new File(USERS_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
   }
    public Boolean loginUser(){
        Optional<User> foundUser= userList.stream().filter(user1 -> {
            return user1.getName().equalsIgnoreCase(user.getName()) && UserServerUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
            return foundUser.isPresent();
        }
        public Boolean signUp(User user1) {
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }
        catch (IOException ex){
            return Boolean.FALSE;
        }
    }
    private void saveUserListToFile() throws IOException{
        File usersFile=new File(USERS_PATH);
        objectMapper.writeValue(usersFile,userList);
    }
    public void fetchBooking(){
        user.printTickets();

    }
    public Boolean cancelBooking(String ticketId){
//        Scanner s = new Scanner(System.in);
//        System.out.println("Enter the ticket id to cancel");
//       ticketId=s.next();
       if (ticketId==null || ticketId.isEmpty()){
           System.out.println("Ticket ID cannot be null or empty");
           return Boolean.FALSE;
       }
       String finalTicketId1=ticketId;
       boolean removed= user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId1) );
       if (removed){
           System.out.println("Ticked with ID"+ ticketId+"has been cancelled.");
           return Boolean.TRUE;
       }
       else {
           System.out.println("No ticket found with ID"+ticketId);
           return Boolean.FALSE;
       }

    }
    public List<Train> getTrains(String source , String  destination){
        try {
            TrainServices trainServices= new TrainServices();
            return trainServices.searchTrains(source,destination);
        }
        catch (IOException ex){
            return new ArrayList<>();
        }
    }
    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }
    public Boolean bookTrainSeat(Train train,int row, int seat){
        try{
            TrainServices trainServices= new TrainServices();
            List<List<Integer>> seats = train.getSeats();
            if (row>=0 && row< seats.size() && seat>=0 && seat<seats.get(row).size()){
                if (seats.get(row).get(seat)==0){
                    seats.get(row).set(seat,1);
                    train.setSeats(seats);
                    trainServices.addTrain(train);
                    return true;//Booking done
                }
                else {
                    return false;//seat is already booked
                }
            }
            else{
                return false;//Invalid row or seat index
            }
        }
        catch (IOException ex){
            return Boolean.FALSE;
        }
    }
}
