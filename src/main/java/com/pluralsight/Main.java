package com.pluralsight;

import java.util.Scanner;

public class Main {

    private static Vehicle[] inventory = new Vehicle[20];
    private static int vehicleCount = 0; // Move this here


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        inventory[0] = new Vehicle(101121, "Ford Explorer", "Red", 45000, 13500);
        inventory[1] = new Vehicle(101122, "Toyota camry", "Blue", 60000, 11000);
        inventory[2] = new Vehicle(101123, "Chevrolet Malibu","Black",50000,9700);
        inventory[3] = new Vehicle(101124, "Honda Civic","White",70000,7500);
        inventory[4] = new Vehicle(101125, "Subaru Outback","Green",55000,14500);
        inventory[5] = new Vehicle(101126, "Jeep Wrangler","Yellow",30000,16000);
        inventory[6] = new Vehicle(101126, "BMW M8 Competition","Black",5000,110000);
        inventory[7] = new Vehicle(101126, "Tesla Model S Plaid","Pearl White",1000,99000);

        vehicleCount = 8;


        int command;
        do { // this starts the code infinitely until stopped.
            System.out.println("What do you want to do?");
            System.out.println(" 1 - List all vehicles");
            System.out.println(" 2 - Search by make/model");
            System.out.println(" 3 - Search by price range");
            System.out.println(" 4 - Search by color");
            System.out.println(" 5 - Add a vehicle");
            System.out.println(" 6 - Quit");
            System.out.print("Enter your command: ");
            command = scanner.nextInt();


            switch(command) { // the switch allows us to go into what the user chooses, like a vending machine, choose case 1 you get chips, case 2 you get a drink.
                case 1: // this means, if user picks 1, then do the following code.
                    listAllVehicles();
                    break; // this stops the code and exit's the switch that the computer is in at the moment. if there was no case then th next case would also run and so on until all of the cases are done running.
                case 2:
                    findVehiclesByMakeModel(scanner);
                    break;
                case 3:
                    findVehiclesByPriceRange(scanner);
                    break;
                case 4:
                    findVehiclesByColor(scanner);
                    break;
                case 5:
                    addAVehicle(scanner);
                    break;
                case 6:
                    System.out.println("\n ----- You have exited the Vehicle Inventory applocation -----");
                    return; // this exits the loop and ends it
            }
        } while (true); // loop continues until case 6 is chosen to exit the code.

    }


    private static void listAllVehicles(){
        System.out.println("\n ----- LISITNG VEHICLES -----");
        for(int i = 0; i < vehicleCount; i++) {
            Vehicle v = inventory[i]; { // skips empty slots
                System.out.println("ID: " + v.getVehicleId()
                        + ", Make/Model: " + v.getMakeModel()
                        + ", Color: " + v.getColor()
                        + ", Odometer: " + v.getOdometerReading()
                        + ", Price: $" + v.getPrice());
            }
        }
        System.out.println("-----------------------------\n");

    }


    private static void findVehiclesByMakeModel(Scanner scanner){
        scanner.nextLine();
        System.out.print("Enter Make/Model to search: ");
        String searchMakeModel = scanner.nextLine(); // whatever user types, put it in the box called searchMakeModel

        System.out.println("\n ----- SEARCH RESULTS -----");
        boolean foundCarsFromSearch = false; // After deep search & errors I found that this creates something like a light switch, that starts in the 'Off' position.
                                             // We need this for the later if statements, we'll check: "Did we find ANY cars?" If the flag is still OFF, we'll tell the user "Sorry, no cars found!"

        for (int i =0; i < vehicleCount; i++) {

            if (inventory[i].getMakeModel().toLowerCase().contains(searchMakeModel)) { // check inventory 1by1 from 0, if make model entered matches, and to ignore user inputs caps locks
                System.out.println("ID: " + inventory[i].getVehicleId() // if found a matched vehicle print out all its info.
                        + ", Make/Model: " + inventory[i].getMakeModel()
                        + ", Color: " + inventory[i].getColor()
                        + ", Odometer: " + inventory[i].getOdometerReading()
                        + ", Price: $" + inventory[i].getPrice());
                foundCarsFromSearch = true;
            }
        }
        if (!foundCarsFromSearch) {
            System.out.println("Sorry, no vehicles found.");
        }
        System.out.println("-----------------------------\n");
    }


    private static void findVehiclesByPriceRange(Scanner scanner){
        scanner.nextLine();

        System.out.print("Please enter below the Minimum and maximum price range.\n");
        System.out.print("Enter minimum price: ");
        float minPrice = scanner.nextFloat();

        System.out.print("Enter maximum price: ");
        float maxPrice = scanner.nextFloat();

        System.out.println("\n ----- SEARCH RESULTS -----");

        boolean found = false;
        for(int i = 0; i < vehicleCount; i++){
            if(inventory[i].getPrice() >= minPrice && inventory[i].getPrice() <= maxPrice) {
                System.out.println("ID: " + inventory[i].getVehicleId() // if found a matched vehicle print out all its info.
                        + ", Make/Model: " + inventory[i].getMakeModel()
                        + ", Color: " + inventory[i].getColor()
                        + ", Odometer: " + inventory[i].getOdometerReading()
                        + ", Price: $" + inventory[i].getPrice());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Sorry, no vehicles found in that price range.");
        }
        System.out.println("-----------------------------\n");
    }


    private static void findVehiclesByColor(Scanner scanner){

        scanner.nextLine();
        System.out.print("Enter Color to search: ");
        String searchColor = scanner.nextLine();
        System.out.println("\n ----- SEARCH RESULTS -----");

        boolean found = false;

        for (int i =0; i < vehicleCount; i++) {
            if(inventory[i].getColor().equalsIgnoreCase(searchColor)){
                System.out.println("ID: " + inventory[i].getVehicleId() // if found a matched vehicle print out all its info.
                        + ", Make/Model: " + inventory[i].getMakeModel()
                        + ", Color: " + inventory[i].getColor()
                        + ", Odometer: " + inventory[i].getOdometerReading()
                        + ", Price: $" + inventory[i].getPrice());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Sorry, no vehicles found.");
        }
        System.out.println("-----------------------------\n");
    }


    private static void addAVehicle(Scanner scanner){
        if (vehicleCount >= 20) {
            System.out.println("Inventory is full!");
            return; // return means end the loop here and go back.
        }

        scanner.nextLine();
        System.out.print("Enter Vehicle ID: \n");
        long id = scanner.nextLong(); // Creating a new variable for this private static, to save the values of user inputs for new vehicles, so that when called upon, they can print out as new values.

        scanner.nextLine();
        System.out.print("Enter Make/Model: \n");
        String makeModel = scanner.nextLine();

        System.out.print("Enter Color: \n");
        String color = scanner.nextLine();

        System.out.print("Enter Odometer reading: \n");
        int odometer = scanner.nextInt();

        scanner.nextLine();
        System.out.print("Enter the Price: $\n");
        float price = scanner.nextFloat();

        inventory[vehicleCount] = new Vehicle(id, makeModel, color, odometer, price);
        // the right side is basically creating a new vehicle using all of the info the user just gave us in that specific order.
        // The left side is saying to put that new user info into the next empty parking spot in that is determined by our vehicleCount counter that knows how many vehicles we have.
        vehicleCount++; // this increases the vehicle count by 1 since we just added the new vehicle.
        System.out.println("Vehicle added successfully!\n");
    }



}