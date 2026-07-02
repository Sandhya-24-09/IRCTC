package ticket.booking.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class UserBookingService{

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<User> userList;

    private User user;

    private final String USER_FILE_PATH = "app/src/main/java/ticket/booking/localDb/users.json";

    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUserListFromFile();
    }

    public UserBookingService() throws IOException {
        loadUserListFromFile();
    }

    private void loadUserListFromFile() throws IOException {
        userList = objectMapper.readValue(new File(USER_FILE_PATH), new TypeReference<List<User>>() {});
    }

    public Boolean loginUser() {

        Optional<User> foundUser = userList.stream()
                .filter(user1 ->
                        user1.getName().equals(user.getName()) &&
                                UserServiceUtil.checkPassword(user.getPassword(),
                                        user1.getHashedPassword()))
                .findFirst();

        if (foundUser.isPresent()) {
            user = foundUser.get();     // ⭐ VERY IMPORTANT
            return true;
        }

        return false;
    }

    public Boolean signUp(User user1) {
        boolean exists = userList.stream()
                .anyMatch(u -> u.getName().equals(user1.getName()));

        if (exists) {
            System.out.println("Username already exists!");
            return false;
        }

        try {
            userList.add(user1);
            saveUserListToFile();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USER_FILE_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBookings() {

        if (user == null) {
            System.out.println("Please login first.");
            return;
        }

        Optional<User> userFetched = userList.stream()
                .filter(user1 ->
                        user1.getName().equals(user.getName()) &&
                                UserServiceUtil.checkPassword(user.getPassword(),
                                        user1.getHashedPassword()))
                .findFirst();

        if (userFetched.isPresent()) {

            if (userFetched.get().getTicketsBooked().isEmpty()) {
                System.out.println("No bookings found.");
            } else {
                userFetched.get().printTickets();
            }

        } else {
            System.out.println("Invalid Login.");
        }
    }

    // todo: Complete this function
    public Boolean cancelBooking(String ticketId) {

        if (user == null) {
            System.out.println("Please login first.");
            return false;
        }

        Ticket ticketToCancel = null;

        for (Ticket ticket : user.getTicketsBooked()) {
            if (ticket.getTicketId().equals(ticketId)) {
                ticketToCancel = ticket;
                break;
            }
        }

        if (ticketToCancel == null) {
            System.out.println("Ticket not found.");
            return false;
        }

        try {

            TrainService trainService = new TrainService();

            Train train = ticketToCancel.getTrain();

            List<List<Integer>> seats = train.getSeats();

            seats.get(ticketToCancel.getRow())
                    .set(ticketToCancel.getSeat(), 0);

            train.setSeats(seats);

            trainService.updateTrain(train);

            user.getTicketsBooked().remove(ticketToCancel);

            saveUserListToFile();

            System.out.println("Booking cancelled successfully.");

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Train> getTrains(String source, String destination){
        try{
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        }catch(IOException ex){
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat, String source, String destination) {
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);

                    train.setSeats(seats);
                    trainService.addTrain(train);
                    Ticket ticket = new Ticket();

                    ticket.setTicketId(UUID.randomUUID().toString());
                    ticket.setUserId(user.getUserId());
                    ticket.setRow(row);
                    ticket.setSeat(seat);
                    ticket.setSource(source);
                    ticket.setDestination(destination);



// You can change this later if you ask for travel date from the user
                    ticket.setDateOfTravel("2026-07-01");

                    ticket.setTrain(train);

                    user.getTicketsBooked().add(ticket);

                    saveUserListToFile();
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }
}