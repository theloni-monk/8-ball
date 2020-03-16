package Abstracts;
import java.util.ArrayList;
//abstract classes used to share code between server and client
abstract public class AbstractTable{
    protected ArrayList<AbstractBall> balls;
    protected double friction;

    protected boolean rest; // for when all the balls have 0 velocity

    // stanard billiard table dimensions
    final double length = 304.8; //cm ~ 10ft 
    final double width = 152.4; //cm ~ 5ft 
    // NOTE: 0,0 in the coordinate space of the table will be at the center of the table
}
