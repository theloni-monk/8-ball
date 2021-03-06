package util;
public class pVector{
    public double x;
    public double y;

    public pVector(){
        this.x = 0;
        this.y = 0;
    }

    public pVector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public pVector(double[] p){
        this.x = p[0];
        this.y = p[1];
    }

    public pVector(double r, double theta, boolean polar){
        this.x = r * Math.cos(theta);
        this.y = r * Math.sin(theta);
    }

    public pVector copy(){
        return new pVector(this.x, this.y);
    }

    // cartesian operations:
    public static pVector add(pVector p1, pVector p2){
        return new pVector(p1.x + p2.x, p1.y + p2.y);
    }
    public static pVector sub(pVector p1, pVector p2){
        return new pVector(p1.x - p2.x, p1.y - p2.y);
    }
    public static pVector mult(pVector p1, double s){
        return new pVector(p1.x * s, p1.y * s);
    }
    public static double dot(pVector p1, pVector p2){
        return (p1.x*p2.x) + (p1.y*p2.y);
    }

    // polar functions
    public double mag(){
        return Math.sqrt((this.x*this.x)+(this.y*this.y));
    }
    public double heading(){
        return Math.atan(this.y/this.x);
    }

    public pVector normalize(){
        return new pVector(1, this.heading(), true);
    }

}