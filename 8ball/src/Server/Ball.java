package Server;
public class Ball extends Abstracts.AbstractBall{
    public double friction; // percentage by which velocity is decreased each step

    public Ball(double f){
        this.friction = f;
    }

    public void step(){
        
    }
}