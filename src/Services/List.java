package Services;

import DataModels.UserArrayDataAccessService;

import java.util.Scanner;

public class List {
//    String selected_user=""
    public void showChoice(int choice){
        if(choice==1){
            System.out.println("Enter user id:");
            Scanner scanner = new Scanner(System.in);
            System.out.println(new UserArrayDataAccessService().getUsers());
            String s = scanner.nextLine();

        }
    }
}
