import java.util.Random;

public class Main {

    //Here we declare all of the statistics that we wil be working with
    public static double totalCustomersServed = 0;
    public static int totalCustomersPassed = 0;
    public static double totalWaitTime = 50;
    public static int idleMinutes = 0;
    public static int longestWaitTime = 0;
    public static int washer = 0;

    public static double aveWaitTime = 0.0;

    public static Random rnd = new Random();

    public static void main(String[] args) {

        int openingCustomers = rnd.nextInt(8);
        int lengthShift = 20;
        int sizeOfLine = 8;

        simulation(openingCustomers, lengthShift, sizeOfLine);


    }

    public static void simulation(int numCustomers, int lengthShift, int sizeOfQueue) {

        WaitQueue<Car> WashLine = new WaitQueue<>();

        populateWithFirstArrivals(WashLine, numCustomers);

        System.out.println("");

        for (int time = 0; time < lengthShift; time++) {

            updateWasher();

            if(rnd.nextInt(4) == 0) {
                addNewCustomer(time, WashLine);
            }

            if (true) {
                if (true) {

                } else {
                    System.out.println("A customer has passed");
                    totalCustomersPassed++;
                }
            }

            //PART 2: Deal with what we have.
            if (WashLine.hasItems()) {

                if (time >= WashLine.top().getaTime() && !washerIsFull()) {
                    System.out.println("Customer going into washer at time: " + time + " ...");
                    washer = WashLine.top().getsTime();
                    getStats(WashLine.top());
                    WashLine.dequeue();
                    totalCustomersServed += 1.0;
                }
            }
        }

        printStats();

    }

    public static void populateWithFirstArrivals(WaitQueue<Car> inputQueue, int numCustomers) {
        for (int i = 0; i <= numCustomers; i++) {
            Car tempCar = new Car(0, (rnd.nextInt(3) + 2));
            inputQueue.enqueue(tempCar);
        }
    }

    public static void updateWasher() {
        if (washer > 0) {
            washer--;
        } else {
            idleMinutes++;
        }
    }

    public static boolean washerIsFull() {
        return (washer > 0);

    }

    public static void getStats(Car customer) {
        totalWaitTime += customer.getwTime();
        if (customer.getwTime() > longestWaitTime) {
            longestWaitTime = customer.getwTime();
        }
    }

    public static void tester() {
        Car tempCar = new Car(0, 2);

        WaitQueue<Car> tempList = new WaitQueue<>();

        System.out.println(tempCar.getaTime());

        tempCar.incrementWait();

        System.out.println(tempCar.getwTime());

        tempList.enqueue(tempCar);

        tempList.top().getwTime();
    }

    public static void printStats() {

        //TODO format the wait time to be with 2 decimal places.
        if(totalCustomersServed > 0.0) {
            aveWaitTime = (totalWaitTime / totalCustomersServed);
        }

        System.out.println("");
        System.out.println("This Shifts Statistics:");
        System.out.println("--------------------------------------");
        System.out.println("The total customers served: "         + totalCustomersServed);
        System.out.println("The total customers passed: "         + totalCustomersPassed);
        System.out.println("The longest wait time: "              + longestWaitTime);
        System.out.println("The average wait time: "              + aveWaitTime);
        System.out.println("The total time the washer was idle: " + idleMinutes);
        System.out.println("--------------------------------------");

    }

    public static void addNewCustomer(int time, WaitQueue inputQueue) {
        Car tempCar = new Car(time, rnd.nextInt(5));
        inputQueue.enqueue(tempCar);
    }
}
