/**
 * Write a description of class Ball here.
 * 
 * @author King 
 * @version May 2017
 */
public class Ball
{
    private double x, y, vx, vy;
    private int r;
    
    public Ball(int rad, int vx, int vy)
    {
        x = 175;
        y = 250;
        r = rad;
        this.vx = vx;
        this.vy = vy;
    }
    
    // change coordinates to move this Ball
    public void move()
    {
        x += vx;
        y += vy;
    }
    
    // bounce horizontally off of wall
    public void hitSideWall()
    {
        vx = -vx;
    }
    
    // bounce vertically off of wall
    public void hitTopBotWall()
    {
        vy = -vy;
    }
    
    // increase velocity
    public void addVel()
    {
        vx *= 1.5;
        vy *= 1.5;
    }
    
    // decrease velocity
    public void decVel()
    {
        vx *= 0.5;
        vy *= 0.5;
    }
    
    /* Getters */
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public double getR()
    {
        return r;
    }
}
