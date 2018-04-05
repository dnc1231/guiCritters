package assignment5;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/* CRITTERS DrawShapes.java
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * Daniel Canterino
 * djc3323
 * 15460
 * Spring 2018
 */

/**
 * @author Daniel Canterino
 * @version 1.0
 * This class will draw the specified shapes on a graphics context.
 */
public class DrawShapes {
	/**
	 * draws a circle
	 * @param gfx, the graphics context to be drawn on
	 * @param x, the x coordinate
	 * @param y, the y coordinate
	 * @param crittersize, the scaling factor for the drawing
	 * @param fillColor the color for the shape to be filled in
	 * @param strokeColor, the color for the shape to be stroked in
	 */
	public static void drawCircle(GraphicsContext gfx, int x, int y, int critterSize, Color fillColor, Color strokeColor) {
		gfx.setStroke(strokeColor);
		gfx.strokeOval(x * critterSize, y * critterSize, critterSize, critterSize);
		gfx.setFill(fillColor);
		gfx.fillOval(x * critterSize, y * critterSize, critterSize, critterSize);
	}
	
	/**
	 * draws a square
	 * @param gfx, the graphics context to be drawn on
	 * @param x, the x coordinate
	 * @param y, the y coordinate
	 * @param crittersize, the scaling factor for the drawing
	 * @param fillColor the color for the shape to be filled in
	 * @param strokeColor, the color for the shape to be stroked in
	 */
	public static void drawSquare(GraphicsContext gfx, int x, int y, int critterSize, Color fillColor, Color strokeColor) {
		gfx.setStroke(strokeColor);
		gfx.strokeRect(x * critterSize, y * critterSize, critterSize, critterSize);
		gfx.setFill(fillColor);
		gfx.fillRect(x * critterSize, y * critterSize, critterSize, critterSize);
	}
	
	/**
	 * draws a triangle
	 * @param gfx, the graphics context to be drawn on
	 * @param x, the x coordinate
	 * @param y, the y coordinate
	 * @param crittersize, the scaling factor for the drawing
	 * @param fillColor the color for the shape to be filled in
	 * @param strokeColor, the color for the shape to be stroked in
	 */
	public static void drawTriangle(GraphicsContext gfx, int x, int y, int critterSize, Color fillColor, Color strokeColor) {
		gfx.setStroke(strokeColor);
        gfx.strokePolygon(new double[]{x * critterSize, x * critterSize + critterSize/2, x * critterSize + critterSize},
                new double[]{y * critterSize, y * critterSize - critterSize/2, y * critterSize}, 3);
        gfx.setFill(fillColor);
        gfx.fillPolygon(new double[]{x * critterSize, x * critterSize + critterSize/2, x * critterSize + critterSize},
                new double[]{y * critterSize, y * critterSize - critterSize/2, y * critterSize}, 3);		
	}
	
	/**
	 * draws a diamond
	 * @param gfx, the graphics context to be drawn on
	 * @param x, the x coordinate
	 * @param y, the y coordinate
	 * @param crittersize, the scaling factor for the drawing
	 * @param fillColor the color for the shape to be filled in
	 * @param strokeColor, the color for the shape to be stroked in
	 */
	public static void drawDiamond(GraphicsContext gfx, int x, int y, int critterSize, Color fillColor, Color strokeColor) {
		gfx.setStroke(strokeColor);
		gfx.strokePolygon(new double[]{x * critterSize, x * critterSize + critterSize/2, x * critterSize + critterSize, x * critterSize + critterSize/2},
                new double[]{y * critterSize, y * critterSize - critterSize/2, y * critterSize, y * critterSize + critterSize/2}, 4);
		gfx.setFill(fillColor);
		gfx.fillPolygon(new double[]{x * critterSize, x * critterSize + critterSize/2, x * critterSize + critterSize, x * critterSize + critterSize/2},
                new double[]{y * critterSize, y * critterSize - critterSize/2, y * critterSize, y * critterSize + critterSize/2}, 4);
	}
	
	/**
	 * draws a star
	 * @param gfx, the graphics context to be drawn on
	 * @param x, the x coordinate
	 * @param y, the y coordinate
	 * @param crittersize, the scaling factor for the drawing
	 * @param fillColor the color for the shape to be filled in
	 * @param strokeColor, the color for the shape to be stroked in
	 */
	public static void drawStar(GraphicsContext gfx, int x, int y, int critterSize, Color fillColor, Color strokeColor) {
		gfx.setStroke(strokeColor);
		gfx.strokePolygon(new double[]{x * critterSize, x * critterSize + critterSize/3, x * critterSize + critterSize/2, x * critterSize + (2 * critterSize)/3,
				x * critterSize + critterSize, x * critterSize + (2 * critterSize)/3, x * critterSize + (5 * critterSize/6), x * critterSize + critterSize/2,
				x * critterSize + critterSize/6, x * critterSize + critterSize/3},
                new double[]{y * critterSize, y * critterSize - critterSize/6, y * critterSize - critterSize/2, y * critterSize - critterSize/6,
                		y * critterSize, y * critterSize + critterSize/6, y * critterSize + critterSize/2, y * critterSize + critterSize/3,
                		y * critterSize + critterSize/2, y * critterSize + critterSize/6}, 10);
		gfx.setFill(fillColor);
		gfx.fillPolygon(new double[]{x * critterSize, x * critterSize + critterSize/3, x * critterSize + critterSize/2, x * critterSize + (2 * critterSize)/3,
				x * critterSize + critterSize, x * critterSize + (2 * critterSize)/3, x * critterSize + (5 * critterSize/6), x * critterSize + critterSize/2,
				x * critterSize + critterSize/6, x * critterSize + critterSize/3},
                new double[]{y * critterSize, y * critterSize - critterSize/6, y * critterSize - critterSize/2, y * critterSize - critterSize/6,
                		y * critterSize, y * critterSize + critterSize/6, y * critterSize + critterSize/2, y * critterSize + critterSize/3,
                		y * critterSize + critterSize/2, y * critterSize + critterSize/6}, 10);
	}
	
}
