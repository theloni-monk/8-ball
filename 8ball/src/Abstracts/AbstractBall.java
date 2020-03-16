package Abstracts;
import util.pVector;
//abstract classes used to share code between server and client
abstract public class AbstractBall{
    public pVector position;
    public pVector velocity;
    public boolean rest;
    public final double radius = 6; //cm ~ 2.375 inches (standard billiard ball size)
    public final double mass = 0.6; //TODO: tune mass
    protected int number;
    protected boolean striped; // for rendering

    public void step(){}
    /**
     * @param velocity the velocity to set
     */
    public void setVelocity(pVector velocity) {
        this.velocity = velocity;
        this.rest = false;
    }

}