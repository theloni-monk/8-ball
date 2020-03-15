package Abstracts;
import util.pVector;
//abstract classes used to share code between server and client
abstract public class AbstractBall{
    protected pVector position;
    protected pVector velocity;
    final double radius = 6; //cm ~ 2.375 inches (standard billiard ball size)
}