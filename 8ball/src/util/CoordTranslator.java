package util;

public class CoordTranslator{
    public static int screenWidth;
    public static int screenHeight;

    public static double[] xRange;
    public static double[] yRange;

    public CoordTranslator(int[] screenDim,double[] xR,double[] yR){
        screenWidth = screenDim[0];
        screenHeight = screenDim[1];
        xRange = xR;
        yRange = yR;
    }
    
    public static pVector Convert_PixToPos(int x, int y){
        // takes in coords and outputs the corrisponding vals from 2,-2
        double scaleh = (yRange[1] - yRange[0]) / screenHeight;
        // what one px corresponds to
        double scalew = (xRange[1] - xRange[0]) / screenWidth;
        double _x = xRange[0] + (x * scalew);
        double _y = yRange[1] - (y * scaleh);
        // because computer coords have 0,0 in the top left
        return new pVector(_x,_y);
    }

    public static int[] Convert_PosToPix(pVector pos){
        double scaleh = (yRange[1] - yRange[0]) / screenHeight;
        //what one px corresponds to
        double scalew = (xRange[1] - xRange[0]) / screenWidth;
        int c_x = (int) ((pos.x - xRange[0]) / scalew);
        int c_y = (int) ((-1 * (pos.y - xRange[1])) / scaleh);
        return new int[]{c_x, c_y};
    }
}