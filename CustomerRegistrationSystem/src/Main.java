import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Queue;
public class Main{
    private static boolean running = true;
    public static void showMenu(){
        System.out.println("1. Register Customer");
        System.out.println("2. Cancel Customer");
        System.out.println("3. List Of Customer");
        System.out.println("0. Exit");
    }

    public static void main(String [] args){


        HashMap<Integer,MenuAction> actions = new HashMap<>();
        Scanner inline =  new Scanner(System.in);
        Queue<Customer> customers = new LinkedList<>();

        actions.put(1,(scanner,customer) -> {
            int halt =1;
            System.out.println("Please enter your name: ");
            String name = scanner.nextLine().toUpperCase();

            System.out.println("Please enter address: ");
            String address = scanner.nextLine().toUpperCase();

            System.out.println("Enter Phone Number: ");
            String phoneNumber = scanner.nextLine();


                System.out.println("Create a password with (8-12 characters) :");
                do{
                String password = scanner.nextLine();
                String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])\\S{8,12}$";
                boolean valid = password.matches(regex);

                if (valid) {
                    customer.add(Customer.create(name, address, phoneNumber, password));
                    halt = 0;
                } else {
                    System.out.println("Please enter a correct password format");
                }
            }while(halt != 0);
        });

        actions.put(2,(scanner,customer) ->{

            Customer toDelete = null;

            System.out.println("Enter User Id To delete");
            int userID = scanner.nextInt();
            for(Customer c : customer){
                if(c.userID() == userID){
                    toDelete = c;
                    break;
                }
            }
            if(toDelete!= null){
                customer.remove(toDelete);
                System.out.println("Customer deleted: " + toDelete);
            } else {
                System.out.println("No customer found with ID " + userID);
            }
        });

        actions.put(3,(scanner,customer) -> {

            if(customer.isEmpty()){
                System.out.println("There is no registered customer yet..");
            }
            else {
                System.out.println("""
                        ________________________________________________________________
                        Employee Records (Displayed Name with corresponding department)\
                        \nList Of Employers
                        ________________________________________________________________""");
                customer.forEach(e -> System.out.println("| Name -> | "+e.name().toUpperCase()+" | Address -> | "+e.address().toUpperCase()+" | Contact Into -> | "+e.contactInfo()));
                System.out.println("________________________________________________________________");
                }
            }
        );

        actions.put(0,(scanner,customer) -> {
            System.out.println("Exiting program,Thank you...");
            System.exit(0);
            running = false;
        });

        while(running){

            showMenu();
            if(!inline.hasNextInt()){
                System.out.println("Invalid input , Please enter a number...");
                inline.nextLine();
                continue;
            }

            System.out.println("Enter you choice");
            int choice = inline.nextInt();

            if(choice == 3){
                actions.get(3).execute(inline,customers);
            }
            else{
                actions.getOrDefault(choice,(scanner,customer) -> System.out.println("Invalid Choice...")).execute(inline,customers);
            }
        }


    }
}