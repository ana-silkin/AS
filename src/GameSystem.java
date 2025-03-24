import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The {@code GameSystem} class represents the main game system.
 * It follows the Singleton design pattern to ensure only one instance exists.
 * This class is responsible for managing the game state, players, and elements.
 *
 * <p>Usage example:
 * <pre>
 *     GameSystem gameSystem = GameSystem.getInstance();
 *     gameSystem.startGame();
 * </pre>
 *
 * @author Ana Silkin
 * @version 1.0
 */
public class GameSystem {

    // Game variables
    public static File in;
    private static GameSystem instance;
    private int turnIndex;
    private List<Plumber> teamPlumbers;
    private List<Saboteur> teamSaboteurs;
    private int scorePlumbers;
    private int scoreSaboteurs;
    private List<Element> mapElements;
    private Player CurrentPlayer;
    private Timer globalTimer;
    private Timer turnTimer;
    private double maxGameLength;
    private int maxScore;

    /**
     * Private constructor to prevent instantiation from other classes.
     * Initializes game variables and sets up empty lists for teams and map elements.
     */
    private GameSystem() {
        java.lang.System.out.println("GameSystem Created");
        this.scorePlumbers = 0;
        this.scoreSaboteurs = 0;
        teamPlumbers = new ArrayList<>();
        teamSaboteurs = new ArrayList<>();
        mapElements = new ArrayList<>();
    }

    /**
     * Places an element on the map at a specific position.
     *
     * @param e the element to place
     * @param p the position at which to place the element
     */
    public void placeElementOnMap(Element e, Position p) {
        Main.Print("placeElementOnMap(Element e, Position p)");
        this.mapElements.add(e);
        p.SetElement(e);
        e.setPosition(p);
    }

    /**
     * Gets the singleton instance of the {@code GameSystem}.
     * If the instance does not exist, it creates a new one.
     *
     * @return the singleton instance of {@code GameSystem}
     */
    public static GameSystem getInstance() {
        if (instance == null) {
            instance = new GameSystem();
        }
        return instance;
    }

    /**
     * Retrieves the global timer for the game.
     *
     * @return the global timer
     */
    public Timer getGlobalTimer() {
        return this.globalTimer;
    }

    /**
     * Generates the teams (Plumbers and Saboteurs).
     * Initializes the {@code CurrentPlayer} to the first player in the Saboteurs team.
     */
    private void generateTeams() {
        java.lang.System.out.println("Teams Generated");
        teamSaboteurs.add(new Saboteur(0));
        teamSaboteurs.add(new Saboteur(1));
        teamPlumbers.add(new Plumber(2));
        teamPlumbers.add(new Plumber(3));
        this.CurrentPlayer = teamSaboteurs.get(0);
    }

    /**
     * Starts the game by generating the map, teams, and setting up timers.
     */
    public void startGame() {
        java.lang.System.out.println("--------------Start Game Started--------------");
        generateMap();
        generateTeams();
        Main.Print("Creating Global Timer");
        globalTimer = new Timer();
        globalTimer.setTime(600);
        Main.Print("Creating Turn Timer");
        turnTimer = new Timer();
        turnTimer.setTime(60);
        globalTimer.startCountDown();
        turnTimer.startCountDown();
        java.lang.System.out.println("--------------Start Game Ended--------------");
    }

    /**
     * Ends the game by calculating the score and determining the winner.
     */
    public void endGame() {
        java.lang.System.out.println("--------------End Game Started--------------");
        this.calculateScore();
        Main.Print("Determine winner");
        java.lang.System.out.println("--------------End Game Ended--------------");
    }

    /**
     * Simulates the breaking of an element in the game.
     * Randomly determines if a pump element should break during a turn.
     */
    public void breakElement() {
        Main.Print("System.breakElement()");
        Random rnd = new Random();
        for (var i : this.mapElements) {
            if (i.getElementType() == 1) { // Check if it's a pump
                boolean ans = Main.getAnswer();
                if (ans) {
                    i.setIsBroken(true);
                }
            }
        }
    }

    /**
     * Changes the turn by advancing to the next player, updating the turn index,
     * and checking for the game end condition.
     */
    public void changeTurn() {
        Main.Print("GameSystem.changeTurn()");
        this.turnIndex++;
        this.breakElement();
        this.CurrentPlayer = this.nextPlayer();
        this.checkTime();
        this.turnTimer.setTime(60);
        turnTimer.startCountDown();
        this.calculateScore();
        Main.Print("Did any of the teams reach maximum score? 0. No 1. Yes");
        boolean result = Main.getAnswer();
        if (result) {
            this.endGame();
        } else {
            Main.Print("No one won. :(");
        }
    }

    /**
     * Gets the next player in turn.
     *
     * @return the next player
     */
    public Player nextPlayer() {
        Main.Print("Next Player");
        for (var i : this.teamSaboteurs) {
            if (i.playerID == (CurrentPlayer.playerID + 1) % 4) {
                return i;
            }
        }
        for (var i : this.teamPlumbers) {
            if (i.playerID == (CurrentPlayer.playerID + 1) % 4) {
                return i;
            }
        }
        return null;
    }

    /**
     * Generates the game map from an XML file and populates the map elements.
     * Reads element types such as Pipe, Pump, Cistern, and Spring from the XML.
     */
    public void generateMap() {
        java.lang.System.out.println("Generation of the map started");
        try {
            File xmlFile = new File("map.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Elements").item(0).getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    String row = element.getAttribute("row");
                    String column = element.getAttribute("column");
                    Position position = new Position(Integer.parseInt(row), Integer.parseInt(column));

                    // Handle different element types based on the tag name
                    switch (element.getTagName()) {
                        case "Pipe":
                            Pipe pipe = new Pipe(position,
                                    Boolean.parseBoolean(element.getAttribute("isBroken")),
                                    Boolean.parseBoolean(element.getAttribute("hasWater")), null, null);
                            this.mapElements.add(pipe);
                            position.SetElement(pipe);
                            break;
                        case "Pump":
                            Pump pump = new Pump(position);
                            this.mapElements.add(pump);
                            position.SetElement(pump);
                            break;
                        case "Cistern":
                            Cistern cistern = new Cistern(position);
                            this.mapElements.add(cistern);
                            position.SetElement(cistern);
                            break;
                        case "Spring":
                            Spring spring = new Spring(position);
                            this.mapElements.add(spring);
                            position.SetElement(spring);
                            break;
                        default:
                            break;
                    }
                }
            }
            System.out.println("Map generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.lang.System.out.println("Generation of the map ended.");
    }

    /**
     * Finds and returns an element on the map at a specific position.
     *
     * @param x the row of the position
     * @param y the column of the position
     * @return the element at the specified position, or null if no element is found
     */
    public Element FindElementByPosition(int x, int y) {
        for (var i : mapElements) {
            if (i.getPosition().getColumn() == y && i.getPosition().getRow() == x) {
                return i;
            }
        }
        return null;
    }

    /**
     * Calculates the current score of the game.
     */
    public void calculateScore() {
        Main.Print("Calculate Score.");
    }

    /**
     * Retrieves the list of Plumbers on the team.
     *
     * @return the list of Plumbers
     */
    public List<Plumber> getTeamPlumbers() {
        return teamPlumbers;
    }

    /**
     * Retrieves the list of Saboteurs on the team.
     *
     * @return the list of Saboteurs
     */
    public List<Saboteur> getTeamSaboteurs() {
        return teamSaboteurs;
    }

    /**
     * Checks the time and ends the game if the global timer reaches zero.
     */
    public void checkTime() {
        Main.Print("GameSystem.checkTime()");
        if (globalTimer.getTime() == 0) {
            this.endGame();
        }
    }

    /**
     * Checks and returns the remaining time for the current turn.
     *
     * @return the remaining time for the current turn
     */
    public double checkTurnTime() {
        return turnTimer.getTime();
    }

    /**
     * Retrieves the list of map elements.
     *
     * @return the list of map elements
     */
    public List<Element> getMapElements() {
        return mapElements;
    }
}
