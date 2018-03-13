import java.util.HashMap;

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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
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
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     * @param west The southeast exit.
     * @param west The southeast exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southeast, Room northWest) 
    {
        if(north != null)
            salida.put("norte", north);
        if(east != null)
            salida.put("este", east);
        if(south != null)
            salida.put("sur", south);
        if(west != null)
            salida.put("oeste", west);
        if(southeast != null)
            salida.put("sureste", southeast);
        if(northWest != null)
            salida.put("noroeste", northWest);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * 
     */
    public Room getExit(String direccion)
    {
        Room aDevolver = null;

        if(direccion.equals("north")){
            aDevolver = salida.get("north");
        }
        if(direccion.equals("east")){
            aDevolver = salida.get("east");
        }
        if(direccion.equals("south")){
            aDevolver = salida.get("south");
        }
        if(direccion.equals("west")){
            aDevolver = salida.get("west");
        }
        if(direccion.equals("southeast")){
            aDevolver = salida.get("southeast");
        }
        if(direccion.equals("northWest")){
            aDevolver = salida.get("northWest");
        }

        return aDevolver;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        String exit = "Exits: ";

        if(salida.get("north") != null){
            exit += "north ";
        }
        if(salida.get("east") != null){
            exit += "east ";
        }
        if(salida.get("south") != null){
            exit += "south ";
        }
        if(salida.get("west") != null){
            exit += "west ";
        }
        if(salida.get("southeast") != null){
            exit += "southeast ";
        }
        if(salida.get("northWest") != null){
            exit += "northWest ";
        }

        return exit;
    }
}
