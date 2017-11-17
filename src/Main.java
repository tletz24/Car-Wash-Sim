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

    }

    public static void simulation(int numCustomers, int lengthShift) {

        //Here we are declaring all of the statistics we would like to analyze


        int time;

        WaitQueue<Car> WashLine = new WaitQueue<>();

        populateWithFirstArrivals(WashLine, numCustomers);

        for (time = 0; time < lengthShift; time++) {

            updateWasher();

            //PART 1: Check if another car has arrive.

            //Check and see if customer has arrived
            //Enter customer into the queue if the queue is not full

            //PART 2: Deal with what we have.

            if(time == WashLine.top().getaTime()) {
                System.out.print("Customer going into washer...");
                washer = WashLine.top().getsTime();
                getStats(WashLine.top());
                WashLine.dequeue();
            }
            //if time euqals time arrived
				/*
					1.) Get the stats from the customer
					2.) Put the customer in the wash
					3.) Remove customer from the queue
				*/

        }

    }

    public static void populateWithFirstArrivals(WaitQueue<Car> inputQueue, int numCustomers) {
        for(int i = 0; i < numCustomers; i++) {
            Car tempCar = new Car(0, 0); //TODO
            inputQueue.enqueue(tempCar);
        }
    }

    public static void updateWasher() {
        if(washer > 0) {
            washer--;
        }
    }

    public static boolean checkWasherStatus() {
        return washer > 0;

    }

    public static void getStats(Car customer) {
        totalWaitTime += customer.getwTime();
        totalCustomersServed++;
    }
}
