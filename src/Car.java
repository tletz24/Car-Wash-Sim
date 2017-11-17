public class Car {

    private int aTime;
    private int sTime;
    private int wTime;

    public Car(int aTime, int sTime) {
        this.aTime = aTime;
        this.sTime = sTime;
    }

    public int getaTime() {
        return aTime;
    }

    public void setaTime(int aTime) {
        this.aTime = aTime;
    }

    public int getsTime() {
        return sTime;
    }

    public void setsTime(int sTime) {
        this.sTime = sTime;
    }

    public int getwTime() {
        return wTime;
    }

    public void setwTime(int wTime) {
        this.wTime = wTime;
    }

    public void incrementWait() {
        this.wTime++;
    }

}