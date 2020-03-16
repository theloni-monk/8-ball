package Server;
import util.pVector;
public class SBall extends Abstracts.AbstractBall{
    public double friction; // percentage by which velocity is decreased each step
    public static final double minVelocity = 5; //TODO: tune minvelocity

    public SBall(int num, pVector pos, double f){
        this.number = num;
        this.striped = num > 8; // striped balls from balls 9-15
        this.position = pos;
        this.friction = f;
        this.rest = true;
    }

    public void step(){
        this.position = pVector.add(this.position, this.velocity); // increment position
        this.velocity = new pVector(this.velocity.x * this.friction,this.velocity.y * this.friction); // reduce velocity
        if(velocity.mag() <  minVelocity){ 
            velocity = new pVector(0,0); // if velocity gets small enough set it to 0
            this.rest = true;
        }
    }
}