import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Diego Antonio
 * @version 2018.03.13
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> salida;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        this.salida = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExits(String direccion, Room siguienteHabitacion)
    {
        salida.put(direccion, siguienteHabitacion);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Devuelve el valor asociado a la clave direccion.
     * 
     * @return devuelve el valor asociado a la clave
     * si no hay nada devuelve null.
     */
    public Room getExit(String direccion)
    {
        return salida.get(direccion);
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        Set<String> nombreDeDireccion = salida.keySet();
        String descripcionSalida = "Exit ";

        for(String direccion : nombreDeDireccion){
            descripcionSalida += direccion + " ";
        }

        return descripcionSalida;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        return "Estas en " + description + ".\n" + getExitString();
    }
}
