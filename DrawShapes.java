package assignment5;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawShapes {
	
	public static void drawCircle(GraphicsContext gfx, int x, int y, int critterSize, Color color) {
		gfx.setFill(color);
		gfx.fillOval(x * critterSize, y * critterSize, critterSize, critterSize);
	}
	
	public static void drawSquare(GraphicsContext gfx, int x, int y, int critterSize, Color color) {
		gfx.setFill(color);
		gfx.fillRect(x, y, critterSize, critterSize);
	}
	
	public static void drawTriangle(GraphicsContext gfx, int x, int y, int critterSize, Color color) {
		
	}
	
	public static void drawDiamond(GraphicsContext gfx, int x, int y, int critterSize, Color color) {
		
	}
	
	public static void drawStar(GraphicsContext gfx, int x, int y, int critterSize, Color color) {
		
	}

}
