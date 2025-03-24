/**
 * Timer for wrap Timer functions
 */
 public class Timer {
    /**
     * class for purpose of handling the global game time for determining the game end and turn time limit
     */
    private double time_left;

    /**
     * initilize timer
     */
    public Timer() {
       Main.Print("Created Timer");
       time_left = 0;
    }

    /**
     * @return time_left
     */
    public double getTime() {
       Main.Print("Timer.getTime()");
       return time_left; }

    /**
     * setTime
     * @param time time to be set as time_left
     */
    public void setTime(double time) {
       this.time_left=time;
       Main.Print("Timer.setTime()");
    }

    public void startCountDown(){
      Main.Print("Start countdown...");
    }
}