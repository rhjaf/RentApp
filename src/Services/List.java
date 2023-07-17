package Services;

import DataModels.Booking;
import DataModels.UserDao;

import java.util.Scanner;
import java.util.UUID;

public class List {
//    String selected_user=""
    public void showChoice(int choice){
        if(choice==1){
            System.out.println("Enter user id:");
            Scanner scanner = new Scanner(System.in);
            System.out.println(new UserDao().getUsers());
            String s = scanner.nextLine();

        }
    }
}
