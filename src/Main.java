public class Main {

    public static int totalCustomersServed = 0;
    public static int totalCustomersPassed = 0;
    public static int totalWaitTime = 0;

    public static double idleMinuites = 0;
    public static double aveWaitTime = 0;

    public static int longestWaitTime = 0;

    public static int washer = 0;

    public static void main(String[] args) {

        int numCustomers = 4;
        int lengthShift = 20;
        int sizeOfLine = 8;

        simulation(numCustomers, lengthShift);
        System.out.println(totalCustomersServed);

    }

    public static void simulation(int numCustomers, int lengthShift) {

        WaitQueue<Car> WashLine = new WaitQueue<>();

        populateWithFirstArrivals(WashLine, numCustomers);

        for (int time = 0; time < lengthShift; time++) {

            updateWasher();

            //PART 1: Check if another car has arrive.

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
                    totalCustomersServed += 1;
                }

            }

        }

    }

    public static void populateWithFirstArrivals(WaitQueue<Car> inputQueue, int numCustomers) {
        for (int i = 0; i < numCustomers; i++) {
            Car tempCar = new Car(0, 2); //TODO add randomness of wait time
            inputQueue.enqueue(tempCar);
        }
    }

    public static void updateWasher() {
        if (washer > 0) {
            washer--;
        } else {
            idleMinuites++;
        }
    }

    public static boolean washerIsFull() {
        return (washer > 0);

    }

    public static void getStats(Car customer) {
        totalWaitTime += customer.getwTime();
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

    public static void calcStats() {
        //TODO Calculate statistics
    }
}
