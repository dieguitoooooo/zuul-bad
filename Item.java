
/**
 * Write a description of class Item here.
 *
 * @author Diego Antonio Almonte Zarzuela
 * @version 14.03.2018
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String descripcion;
    private int peso;

    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcion, int peso)
    {
        // initialise instance variables
        this.descripcion = descripcion;
        this.peso = peso;
    }
    
    /**
     * Devuelve la descripcion del item.
     */
    public String getDescripcion()
    {
        return descripcion;
    }
    
    /**
     * Devuelve el peso del objecto item.
     */
    public int getPeso()
    {
        return peso;
    }
}
