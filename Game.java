import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Diego Antonio
 * @version 2018.03.13
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> anteriorRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        anteriorRoom = new Stack<>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room danubio, amazonas, po, misisipi, nilo;

        // create the rooms
        danubio = new Room("estas en el Danubio");
        danubio.addItem("Cocodrilo", 200);
        danubio.addItem("Anaconda", 100);

        amazonas = new Room("estas en el Amazonas");

        po = new Room("estas en el PO");
        po.addItem("Salamandra", 20);
        po.addItem("Pez Gato", 60);
        po.addItem("Anaconda", 90);

        misisipi = new Room("estas en el Misisipi");

        nilo = new Room("estas en el Nilo");
        nilo.addItem("Cocodrilo del nilo", 200);

        // initialise room exits
        danubio.setExits("south", nilo);
        danubio.setExits("southeast", amazonas);

        amazonas.setExits("northwest", danubio);
        amazonas.setExits("south", po);
        amazonas.setExits("southeast", misisipi);

        nilo.setExits("north", danubio);
        nilo.setExits("south", po);

        po.setExits("north", amazonas);
        po.setExits("west", nilo);

        misisipi.setExits("northwest", danubio);

        currentRoom = amazonas;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("eat")) {
            eat();
        }
        else if(commandWord.equals("back")){
            backRoom(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            anteriorRoom.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Imprime la informacion de la localizacion del usuario
     * 
     */
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }

    /**
     * Muestra la descripcion en la habitacion en la que estamos.
     */
    private void look() 
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Este comando lo que hace es imprimirnos un mensaje.
     */
    private void eat()
    {
        System.out.println("You have eaten now and you are not hungry any more");
    }

    /**
     * Este metodo nos hace volver a la habitacion anterior
     * @param recibe un commando por parametro
     * @return no devuelve nada imprime la habitacion anterior
     * 
     */
    private void backRoom(Command command)
    {
        if(command.hasSecondWord()){
            System.out.println("Quit what?");
        }
        else{
            if(anteriorRoom.isEmpty()){
                System.out.println("Lo siento tienes que moverte para volver atras");
            }

            else{currentRoom = anteriorRoom.pop();
                printLocationInfo();
                System.out.println();
            }          
        }
    }
}
