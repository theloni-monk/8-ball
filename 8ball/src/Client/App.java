package Client;

import processing.core.PApplet;

// written as an all inclusive independent client application by using the classes from the server package
//BOILERPLATE
public class App extends PApplet{
	
	public void settings(){
		size(500, 500);
	}
	
	public void draw(){
		ellipse(mouseX, mouseY, 50, 50);
	}
	
	public void mousePressed(){
		background(64);
	}
	
	public static void main(String[] args){
		String[] processingArgs = {"8ball"};
		App sketch = new App();
		PApplet.runSketch(processingArgs, sketch);
	}
}