import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 *
 * @author Diego Antonio Almonte
 * @version 01.  2018/03/23
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private Stack<Room> anteriorRoom;

    /**
     * Constructor for objects of class Player
     */
    public Player(Room room)
    {
        // initialise instance variables
        anteriorRoom = new Stack<Room>();
        currentRoom = room;      
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
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
     * Imprime la informacion de la localizacion del usuario
     * 
     */
    public void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }

    /**
     * Muestra la descripcion en la habitacion en la que estamos.
     */
    public void look() 
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    
    /**
     * Este comando lo que hace es imprimirnos un mensaje.
     */
    public void eat()
    {
        System.out.println("You have eaten now and you are not hungry any more");
    }

    /**
     * Este metodo nos hace volver a la habitacion anterior
     * @param recibe un commando por parametro
     * @return no devuelve nada imprime la habitacion anterior
     * 
     */
    public void backRoom(Command command)
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
