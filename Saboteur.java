public class Saboteur extends Player {
    public Saboteur(int playerID) {
        super(playerID, new Position(0,0));
    }

    /**
     * Allows the Saboteur to puncture a pipe if they are in the correct position.
     * The pipe must not already be broken.
     *
     * @param pipe The pipe to puncture
     */
    public void puncturePipe(Pipe pipe) {
        Main.Print("puncturePipe(Pipe pipe)");
        System.out.println("Checking if the saboteur is on the pipe... 0. No 1. Yes");
        boolean result1 = Main.getAnswer();
//        if(pipe.getPosition().getColumn()==super.playerPosition.getColumn() && pipe.elementPosition.getRow() == playerPosition.getRow()){
        if(result1){
            System.out.println("Positions match. Can puncture the pipe.");
            System.out.println("Checking if the pipe is already broken... 0. No 1. Yes");
            boolean result = Main.getAnswer();
            if(!result) {
                System.out.println("Puncture attempt successful.");
                pipe.setIsBroken(true);
            } else {
                System.out.println("Puncture attempt unsuccessful.");
            }

        }
        else {
            System.out.println("Can't puncture. Positions don't match.");
        }

    }
}