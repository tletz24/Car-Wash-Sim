/*
Ethan's Car Wash

Authors: Trenton Letz and Riley Kalb

This program simulates a car wash's shift, given a few statistics about the car wash itself.
Once it simulates a shift with the given parameters it then gives the statistics about the shift;
The program then outputs the total customers served, total customers passed, longest wait time;
average wait time, and the total time the washer that was idle. We also added a method that
randomly selects a car type and brand. The type is dependent on the time that the car will take
to be serviced (Coupe = 2, Sedan = 3, Truck = 4, SUV = 5), but the brand is randomly selected.
 */

import java.text.DecimalFormat;
import java.util.Random;

public class Main {

    //Here we declare and initialize all of the statistics that we wil be working with
    private static int      totalCustomersPassed = 0;
    private static int               idleMinutes = 0;
    private static int           longestWaitTime = 0;
    private static int                    washer = 0;
    private static double        totalWaitTime = 0.0;
    private static double          aveWaitTime = 0.0;
    private static double totalCustomersServed = 0.0;

    private static Random         rnd = new Random();
    private static String[]  carTypes = {"Coupe","Sedan","Truck","SUV"};
    private static String[] carBrands = {"Ford", "Chevy", "BMW", "Audi", "Lincoln", "Honda", "Subaru"};

    public static void main(String[] args) {

        int openingCustomers = rnd.nextInt(7);
        int lengthShift      = 20;
        int sizeOfLine       = 8;

        runSimulation(openingCustomers, lengthShift, sizeOfLine);

    }

    private static void runSimulation(int numCustomers, int lengthShift, int sizeOfQueue) {

        WaitQueue<Car> WashLine = new WaitQueue<>();

        populateWithFirstArrivals(WashLine, numCustomers);

        System.out.println("");
        System.out.println("Simulation Updates:");
        System.out.println("--------------------------------------");

        for (int time = 0; time < lengthShift; time++) {

            updateWasher();

            //Here we check and see if a new customer has arrived based on a 1 in 5 probability.
            if (rnd.nextInt(4) == 0) {

                if(WashLine.size() < sizeOfQueue) {
                    addNewCustomer(time, WashLine);
                } else {
                    totalCustomersPassed++;
                    System.out.println("A customer has passed your line");
                }
            }

            //Here we deal with entering a car into the wash, or not.
            if (WashLine.hasItems()) {

                if (time >= WashLine.top().getaTime() && !washerIsFull()) {
                    System.out.println("Customer going into washer at time: " + time + "...");
                    washer = WashLine.top().getsTime();
                    getStats(WashLine.top());
                    WashLine.dequeue();
                    totalCustomersServed += 1.0;
                }
            }
            updateWaitTimes(WashLine);
        }
        printStats();
    }

    //This function is called to populate the Queue with the customers that were waiting in line
    //before the car wash shift has started.
    private static void populateWithFirstArrivals(WaitQueue<Car> inputQueue, int numCustomers) {

        System.out.println("");
        System.out.print("Initially waiting in line was: ");

        for (int i = 0; i < numCustomers; i++) {

            Car tempCar = new Car(0, (rnd.nextInt(4) + 1));
            inputQueue.enqueue(tempCar);

            if(i == (numCustomers - 1)) {
                System.out.print(generateBrand(tempCar.getsTime()) + "  ");
            } else {
                System.out.print(generateBrand(tempCar.getsTime()) + ", ");
            }
        }
        System.out.println("");
    }

    //Here we update the washer at the start of every cycle.
    private static void updateWasher() {

        if (washer > 0) {
            washer--;
        } else {
            idleMinutes++;
        }

    }

    //Checks if there a car in the washer.
    private static boolean washerIsFull() {
        return (washer > 0);
    }

    //Here we get the stats from each car as it goes through the washer.
    private static void getStats(Car customer) {
        totalWaitTime += customer.getwTime();

        if (customer.getwTime() > longestWaitTime) {
            longestWaitTime = customer.getwTime();
        }
    }

    private static void printStats() {

        DecimalFormat df = new DecimalFormat("##.00");
        DecimalFormat sf = new DecimalFormat("0");
        String M         = " minute(s)";

        if (totalCustomersServed > 0.0) {
            aveWaitTime = (totalWaitTime / totalCustomersServed);
        }

        System.out.println("");
        System.out.println("This Shifts Statistics:");
        System.out.println("--------------------------------------");
        System.out.println("The total customers served: "         + sf.format(totalCustomersServed));
        System.out.println("The total customers passed: "         + totalCustomersPassed);
        System.out.println("The longest wait time: "              + longestWaitTime + M);
        System.out.println("The average wait time: "              + df.format(aveWaitTime) + M);
        System.out.println("The total time the washer was idle: " + idleMinutes + M);
        System.out.println("--------------------------------------");

    }

    private static void addNewCustomer(int time, WaitQueue inputQueue) {
        Car tempCar = new Car(time, rnd.nextInt(4) + 1);
        inputQueue.enqueue(tempCar);
        System.out.println("A " + generateBrand(tempCar.getsTime()) + " has entered the Line");
    }

    //Adds 1 to the wait times of every car in the Queue
    public static void updateWaitTimes(WaitQueue<Car> inputQueue) {
        if(inputQueue.hasItems()) {
            for (int i = 0; i < inputQueue.size(); i++) {
                inputQueue.top().incrementWait();
                Car temp = inputQueue.dequeue();
                inputQueue.enqueue(temp);
            }
        }
    }

    private static String generateBrand(int s) {
        return carBrands[rnd.nextInt(6)] + " " + carTypes[s - 1];
    }


}
