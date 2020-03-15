package Abstracts;
import java.util.ArrayList;
//abstract classes used to share code between server and client
abstract public class AbstractTable{
    ArrayList<AbstractBall> balls;

    // stanard billiard table dimensions
    final double length = 304.8; //cm ~ 10ft 
    final double width = 152.4; //cm ~ 5ft 
}
