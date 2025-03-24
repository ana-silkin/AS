import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;


public class Main{

    static List<Plumber> teamPlumbers;
    static List<Saboteur> teamSaboteurs;
    static Player curretPlayer;
    static GameSystem gamesystem;
    static  Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
      gamesystem = GameSystem.getInstance();
       gamesystem.startGame();
        teamPlumbers = gamesystem.getTeamPlumbers();
        teamSaboteurs= gamesystem.getTeamSaboteurs();
       Action();
    }

    public static void Action(){
        boolean running = true;

        while (running) {
            Main.Print("\n=== Game Menu ===");
            System.out.println("1. Puncture Pipe (Saboteur)");//done
            System.out.println("2. Pick Up New Pipe/Pump (Plumber)");//done
            System.out.println("3. Insert New Pipe/Pump (Plumber)");
            System.out.println("4. Insert Pipe Into Pump (Plumber)");
            System.out.println("5. Fix Pipe/Pump (Plumber)");//done
            System.out.println("6. Change Pump Direction");//done
            System.out.println("7. Move");//done
            System.out.println("8. Time Ran Out");//done
            System.out.println("9. Turn Change");//done
            System.out.println("-1. Exit");//done

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Saboteur: Puncturing a Pipe...");
                    chooseSaboteur();
                    break;
                case 2:
                    System.out.println("Plumber: Picking Up a New Pipe/Pump...");
                    choosePlumber().pickingUpPipeOrPump();
                    break;
                case 3:
                    System.out.println("Plumber: Inserting a New Pipe/Pump...");
                    choosePlumber().placeElement(Destination());
                    break;
                case 4:
                    System.out.println("Plumber: Inserting Pipe Into a Pump...");
                    choosePlumber().insertPipeIntoPump((Pump)gamesystem.getMapElements().get(3));
                    break;
                case 5:
                    System.out.println("Plumber: Fixing a Pump/Pipe...");
                    choosePlumberToFix();
                    break;
                case 6:
                    System.out.println("Player: Changing Pump Direction...");
                    choosePlayer();
                    curretPlayer.changeWaterFlowDirection((Pump)gamesystem.getMapElements().get(3),ChoosePipe(),ChoosePipe());
                    break;
                case 7:
                    System.out.println("Player: Moving...");
                    choosePlayer();
                    curretPlayer.move(Destination().elementPosition);
                    break;
                case 8:
                    System.out.println("Time Ran Out...");
                    gamesystem.getGlobalTimer().setTime(0);
                    gamesystem.checkTime();
                    break;
                case 9:
                    System.out.println("Turn Change...");
                    gamesystem.changeTurn();
                    break;
                case -1:
                    System.out.println("Exiting the Menu...");
                    scanner.close();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }


    }


    public static Plumber choosePlumber (){
        System.out.println("Choose the player:\n1. Plumber1\n2. Plumber2");
        int index=scanner.nextInt();
        switch (index){
            case 1:
                return teamPlumbers.get(0);

            case 2:
                return teamPlumbers.get(1);

            default:
                System.out.println("Incorrect value. Proceeding with previous player.");
                return teamPlumbers.get(0);

        }
    }
    public static void choosePlumberToFix(){
        System.out.println("Choose the player:\n1. Plumber1\n2. Plumber2");
        int index=scanner.nextInt();
        switch (index){
            case 1:
                teamPlumbers.get(0).fixElement(Destination());
                break;
            case 2:
                teamPlumbers.get(1).fixElement(Destination());
                break;
            default:
                System.out.println("Incorrect value. Proceeding with previous player.");
                teamPlumbers.get(0).fixElement(Destination());
                break;
        }
    }
    public static void choosePlayer(){
        System.out.println("Choose the player:\n1. Plumber1\n2. Plumber2\n3. Saboteur1\n4. Saboteur2");

        int index=scanner.nextInt();
        switch (index){
            case 1:
                curretPlayer=teamPlumbers.get(0);
                break;
            case 2:
                curretPlayer=teamPlumbers.get(1);
                break;
            case 3:
                curretPlayer=teamSaboteurs.get(0);
                break;
            case 4:
                curretPlayer=teamSaboteurs.get(1);
                break;
            default:
                System.out.println("Incorrect value. Proceeding with previous player");
                break;
        }

    }
    public static void chooseSaboteur(){
        System.out.println("Choose the player:\n1. Saboteur1\n2. Saboteur2");

        int index=scanner.nextInt();
        switch (index){
            case 1:
                teamSaboteurs.get(0).puncturePipe(ChoosePipe());
                break;
            case 2:
                teamSaboteurs.get(1).puncturePipe(ChoosePipe());
                break;
            default:
                System.out.println("Incorrect value. Proceeding with previous player");
                teamSaboteurs.get(0).puncturePipe(ChoosePipe());
                break;
        }

    }

    public static Element Destination(){
        System.out.println("Choose destination ...");
        System.out.println("1. Pipe1\n2. Pipe2\n3. Pipe3\n4. Pump\n5. Pipe4\n6. Cistern\n7. Spring");
        int index=scanner.nextInt();
        return gamesystem.getMapElements().get(index-1);
    }

    public static Pipe ChoosePipe(){
        System.out.println("Choose pipe ...");
        System.out.println("1. Pipe1\n2. Pipe2\n3. Pipe3\n4. Pipe4");
        int index=scanner.nextInt();
        if(index<4) {
            return (Pipe)gamesystem.getMapElements().get(index - 1);
        }
        else {
            return (Pipe)gamesystem.getMapElements().get(4);
        }
    }
    public static Pipe chooseNewInputPipe(){
        Main.Print("Choose new input pipe ...");
        int index=scanner.nextInt();
        if(index<4) {
            return (Pipe)gamesystem.getMapElements().get(index - 1);
        }
        else {
            return (Pipe)gamesystem.getMapElements().get(4);
        }
    }

    public static Pipe chooseNewOutputPipe(){
        Main.Print("Choose new output pipe ...");
        int index=scanner.nextInt();
        if(index<4) {
            return (Pipe)gamesystem.getMapElements().get(index - 1);
        }
        else {
            return (Pipe)gamesystem.getMapElements().get(4);
        }
    }

    public static void Print(String message){
        System.out.println(message);

    }

    public static boolean getAnswer(){
        int ans=scanner.nextInt();
        switch (ans)
        {
            case 0:
                return false;
            case 1:
                return true;
            default:
                return false;
        }
    }
}