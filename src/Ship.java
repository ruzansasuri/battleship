/**
 * Ship.java
 * @author: Ruzan Sasuri            rps7183@g.rit.edu
 * @author: Akash Venkatachalam     av2833@g.rit.edu
 * @author: Ghodratollah Aalipour   ga5481@g.rit.edu
 * Id: $ Ship.java v1.0, 2016/11/07$
 * Revision: First Revision
 */

public class Ship
{
    private int xCoord, yCoord, len, left;
    private char orien;
    private String name;
/**
     * Constructor for ship class
     * @param x x-coordinate
     * @param y y-coordinate
     * @param ln length
     * @param orn orientation
     * @param n name of the ship
     */
    protected Ship( int x, int y, int ln, char orn, String n)
    {
        xCoord=x;
        yCoord=y;
        len=ln;
        orien=orn;
        left = len;
        name = n;
    }
/**
     *Method to get the x co-ordinate of ship
     */
    public int getxCoord()
    {
        return xCoord;
    }
/**
     *Method to get the y co-ordinate of ship
     */
    public int getyCoord()
    {
        return yCoord;
    }
    public int getLen()
    {
        return len;
    }
/**
     *Method to get the orientation of the ship
     */
    public char getOrien()
    {
        return orien;
    }
    public void decrLeft()
    {
        left--;
    }
    public String getName()
    {
        return name;
    }

    public int getLeft()
    {
        return this.left;
    }
    /*public static void main(String a[])
    {
        Ship s = new Ship(1,1,2,'H',"Trial ship");
        System.out.println(s.getLeft());
        s.hit(1,1);
        System.out.println(s.getLeft());
        s.hit(1,2);
        System.out.println(s.getLeft());
    }*/
}
