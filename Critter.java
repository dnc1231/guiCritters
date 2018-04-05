package assignment5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;

/* CRITTERS Critter.java
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
 * This is the Critter class. It provides many methods for the critter subclasses.
 */


public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.DARKGREY; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	
	private static int timestep = 0;
	private static List<Critter> alreadyMoved = new java.util.ArrayList<Critter>();
	private static int critterSize = getCritterSize();
	private static Map<Critter, Point> oldLocation = new HashMap<Critter, Point>();//x, y in arrayList (0, 1)

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	/**
	 * Looks in a given direction either one or 2 distances away
	 * @param direction, the direction for the critter to check
	 * @param steps, true is 1, false is 2
	 * @return a string representation of what is in that spot with null meaning nothing
	 */
	protected final String look(int direction, boolean steps) {
		energy = energy - Params.look_energy_cost;
		int offset;
		if (steps) {
			offset = 1;
		}else {
			offset = 2;
		}
		int newX = x_coord;
		int newY = y_coord;
			
		switch (direction) {
		case 0:
			newX = getNewXLocation(x_coord + (1 * offset));
			break;
		case 1:
			newX = getNewXLocation(x_coord + (1 * offset));
			newY = getNewYLocation(y_coord + (1 * offset));
			break;
		case 2:
			newY = getNewYLocation(y_coord + (1 * offset));
			break;
		case 3:
			newX = getNewXLocation(x_coord - (1 * offset));
			newY = getNewYLocation(y_coord + (1 * offset));
			break;
		case 4:
			newX = getNewXLocation(x_coord - (1 * offset));
			break;
		case 5:
			newX = getNewXLocation(x_coord - (1 * offset));
			newY = getNewYLocation(y_coord - (1 * offset));
			break;
		case 6:
			newY = getNewYLocation(y_coord - (1 * offset));
			break;
		case 7:
			newX = getNewXLocation(x_coord + (1 * offset));
			newY = getNewYLocation(y_coord - (1 * offset));
			break;
		}
		for (Critter c : population) {
			if (oldLocation.get(c).x == newX && oldLocation.get(c).y == newY) {
				return c.toString();
			}
		}
		return null;
	}
	
	/* rest is unchanged from Project 4 */
	
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	/**
	 * Moves the critter in a walk
	 * @param direction in with respect to a circle with 0 being directly to the right and each additional is moving around the circle in a counter
	 * clockwise fashion for a total of 8 possible directions around the critter
	 */
	protected final void walk(int direction) {
		move(direction, 1);
		energy = energy - Params.walk_energy_cost;
		
	}
	/**
	 * Moves the critter in a run
	 * @param direction in with respect to a circle with 0 being directly to the right and each additional is moving around the circle in a counter
	 * clockwise fashion for a total of 8 possible directions around the critter
	 */
	protected final void run(int direction) {
		move(direction, 2);
		energy = energy - Params.run_energy_cost;
	}
	
	/**
	 * Moves the critter with either a walk or run
	 * @param direction in with respect to a circle with 0 being directly to the right and each additional is moving around the circle in a counter
	 * clockwise fashion for a total of 8 possible directions around the critter
	 * @param offset is either 1 for walk or 2 for run
	 */
	private void move(int dir, int offset) {
		switch (dir) {
		case 0:
			x_coord = getNewXLocation(x_coord + (1 * offset));
			break;
		case 1:
			x_coord = getNewXLocation(x_coord + (1 * offset));
			y_coord = getNewYLocation(y_coord + (1 * offset));
			break;
		case 2:
			y_coord = getNewYLocation(y_coord + (1 * offset));
			break;
		case 3:
			x_coord = getNewXLocation(x_coord - (1 * offset));
			y_coord = getNewYLocation(y_coord + (1 * offset));
			break;
		case 4:
			x_coord = getNewXLocation(x_coord - (1 * offset));
			break;
		case 5:
			x_coord = getNewXLocation(x_coord - (1 * offset));
			y_coord = getNewYLocation(y_coord - (1 * offset));
			break;
		case 6:
			y_coord = getNewYLocation(y_coord - (1 * offset));
			break;
		case 7:
			x_coord = getNewXLocation(x_coord + (1 * offset));
			y_coord = getNewYLocation(y_coord - (1 * offset));
			break;
		}
	}
	
	/**
	 * The critter reproduces and spawns an offspring next to the critter
	 * @param offspring is the new critter to be placed world
	 * @param direction in with respect to a circle with 0 being directly to the right and each additional is moving around the circle in a counter
	 * clockwise fashion for a total of 8 possible directions around the critter
	 */
	protected final void reproduce(Critter offspring, int direction) {
		if (energy < Params.min_reproduce_energy) {
			return;
		}else {
			if (energy % 2 == 0) {
				offspring.energy = energy/2;
				energy = energy/2;
			}else {
				offspring.energy = energy/2;
				energy = energy/2 + 1;
			}
			offspring.x_coord = x_coord;
			offspring.y_coord = y_coord;
			offspring.walk(direction);
			Critter.babies.add(offspring);
			return;
		}
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * performs a world time step for the entire world consisting of the following tasks
	 */
	// 1. increment timestep; timestep++;
	// 2. doTimeSteps();
	// 3. Do the fights. doEncounters();
	// 4. updateRestEnergy();
	// 5. Generate Algae genAlgae();
	// 6. Move babies to general population. population.addAll(babies); babies.clear();
	public static void worldTimeStep() {
		timestep++;
		setOldLocations();
		for (Critter c : population) {
			int prevX = c.x_coord;
			int prevY = c.y_coord;
			c.doTimeStep();
			if (prevX != c.x_coord || prevY != c.y_coord) {
				alreadyMoved.add(c);
			}
		}
		for (Critter A : population) {
			for (Critter B : population) {
				if ((A != B) && (A.x_coord == B.x_coord) && (A.y_coord == B.y_coord) && (A.getEnergy() > 0) && (B.getEnergy() > 0)){
					fight(A, B);
				}
			}
		}
		alreadyMoved.clear();//resets the list tracking who moved during an encounter that turn
		for (Critter c : population) {
			c.energy = c.energy - Params.rest_energy_cost;
		}
		for (int i = 0; i < Params.refresh_algae_count; i++) {
			Algae newAlgae = new Algae();
			newAlgae.setX_coord(getRandomInt(Params.world_width));
			newAlgae.setY_coord(getRandomInt(Params.world_height));
			newAlgae.setEnergy(Params.start_energy);
			population.add(newAlgae);
		}
		population.addAll(Critter.babies);
		Critter.babies.clear();
		List<Critter> populationDead = new java.util.ArrayList<Critter>();
		for (Critter c : population) {
			if (c.getEnergy() <= 0) {
				populationDead.add(c);
			}
		}
		population.removeAll(populationDead);
		oldLocation.clear();
	}
	/**
	 * displays the created canvas of the world on the passed pane. Will only use the shapes in the crittershape enumerator
	 * @param the pane for the canvas of the world to be displayed on
	 */
	public static void displayWorld(GridPane pane) {
		
		Canvas canvas = new Canvas(Params.world_width * critterSize, Params.world_height * critterSize);
		GraphicsContext gfx = canvas.getGraphicsContext2D();
		gfx.setFill(Color.DARKGREY);
		gfx.fillRect(0, 0, Params.world_width * critterSize, Params.world_height * critterSize);
		for (Critter c : population) {
			CritterShape d = c.viewShape();
			switch (d) {
				case CIRCLE:
					DrawShapes.drawCircle(gfx, c.x_coord, c.y_coord, critterSize, c.viewFillColor(), c.viewOutlineColor());
					break;
				case SQUARE:
					DrawShapes.drawSquare(gfx, c.x_coord, c.y_coord, critterSize, c.viewFillColor(), c.viewOutlineColor());
					break;
				case TRIANGLE:
					DrawShapes.drawTriangle(gfx, c.x_coord, c.y_coord, critterSize, c.viewFillColor(), c.viewOutlineColor());
					break;
				case DIAMOND:
					DrawShapes.drawDiamond(gfx, c.x_coord, c.y_coord, critterSize, c.viewFillColor(), c.viewOutlineColor());
					break;
				case STAR:
					DrawShapes.drawStar(gfx, c.x_coord, c.y_coord, critterSize, c.viewFillColor(), c.viewOutlineColor());
					break;
			}
		}
		pane.add(canvas, 3, 6);
	} 
	/* Alternate displayWorld, where you use Main.<pane> to reach into your
	   display component.
	   // public static void displayWorld() {}
	*/
	

	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			String className = critter_class_name.substring(0, 1).toUpperCase() + critter_class_name.substring(1);
			String classType = myPackage + "." + className;
			Class c = Class.forName(classType);
			@SuppressWarnings("deprecation")
			Critter v = (Critter) c.newInstance();
			v.energy = Params.start_energy;
			v.x_coord = getRandomInt(Params.world_width);
			v.y_coord = getRandomInt(Params.world_height);
			population.add(v);
		}catch (Exception e) {
			throw new InvalidCritterException(critter_class_name);
		}
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		try{
			String className = critter_class_name.substring(0, 1).toUpperCase() + critter_class_name.substring(1);
			String classType = myPackage + "." + className;
			Class crit = Class.forName(classType);
			for (Critter c : population) {
				if (c.getClass().getName().equalsIgnoreCase(classType)) {
					result.add(c);
				}
			}
		}catch (ClassNotFoundException e){
			throw new InvalidCritterException(critter_class_name);
		}
		return result;
	}

	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 * @return a string of the stats to be displayed
	 */
	public static String runStats(List<Critter> critters) {
		String stats = new String();
		stats = stats + "" + critters.size() + " critters as follows -- ";

		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			stats = stats + prefix + s + ":" + critter_count.get(s);
			prefix = ", ";
		}
		return stats;
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure thath the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctup update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}
	
	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
		babies.clear();
	}
	
	/**
	 * Models the fight between 2 critters
	 * @param Critter A
	 * @param Critter B
	 * will either fight or attempt to run away if it does not want to fight. Unless it is algae, which in that case it will 'fight' but with a 0 for a roll
	 * to essentially give up to whatever is trying to eat it
	 */
	private static void fight(Critter A, Critter B) {
		int rollA = 0;
		int rollB = 0;
		if ( A.fight(B.toString()) || A instanceof Algae) {
			if (A instanceof Algae) {
				rollA = 0;
			}else {
				rollA = getRandomInt(A.energy);
			}
		}else {
			if(checkForFlee(A)) {
				if (!B.fight(A.toString())) {//will allow B to flee as well before returning if both B and A select to flee
					if (checkForFlee(B)) {
						return;
					}
				}
				return;
			}
		}
		if (B.fight(A.toString()) || B instanceof Algae) {
			if (B instanceof Algae) {
				rollB = 0;
			}else {
				rollB = getRandomInt(B.energy);
			}
		}else {
			if (checkForFlee(B)) {
				return;
			}
		}
		if (rollA > rollB) {
			A.energy += B.getEnergy()/2;
			B.energy = 0;
		}else {//B is default winner in case of tie as well if B roll is greater
			B.energy += A.getEnergy()/2;
			A.energy = 0;
		}
	}
	
	/**
	 * checks for empty surrounding locations and moves a critter attempting to flee if one is found
	 * @param Critter A
	 */
	private static boolean checkForFlee(Critter A) {
		if (alreadyMoved.contains(A)) {
			A.energy = A.energy - Params.run_energy_cost;
			return false;
		}
		int dir = findNewLocationDirection(A.x_coord, A.y_coord);
		if (dir >= 0) {
			A.run(dir);
			alreadyMoved.add(A);
			A.energy = A.energy - Params.run_energy_cost;
			return true;
		}else {
			A.energy = A.energy - Params.run_energy_cost;
			return false;
		}
	}
	
	/**
	 * checks to see if a given point is already occupied on the world
	 * @param x, x coordinate
	 * @param y, y coordinate
	 */
	private static boolean isLocationOccupied(int x, int y) {
		for (Critter c : population) {
			if (c.x_coord == x && c.y_coord == y) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * wraps the x coordinate around if the critter reaches the edge
	 * @param x, x coordinate
	 */
	private static int getNewXLocation(int x) {
		int newX = x;
		if (x < 0) {
			newX = Params.world_width + x;
		}else if (x > Params.world_width) {
			newX = x - Params.world_width;
		}
		return newX;
	}
	
	/**
	 * wraps the y coordinate around if the critter reaches the edge
	 * @param y, y coordinate
	 */
	private static int getNewYLocation(int y) {
		int newY = y;
		if (y < 0) {
			newY = Params.world_height + y;
		}else if (y > Params.world_height) {
			newY = y - Params.world_height;
		}
		return newY;
	}
	
	/**
	 * finds an empty space and the corresponding direction that a critter can RUN to to get there from its current position
	 * @param x, x coordinate
	 * @param y, y coordinate
	 * @return integer representing the direction as described above that points to an empty location on the world that the critter can RUN to, not walk
	 */
	private static int findNewLocationDirection(int x, int y) {
		if (!isLocationOccupied(getNewXLocation(x + 2), getNewYLocation(y))) {
			return 0;
		}else if (!isLocationOccupied(getNewXLocation(x + 2), getNewYLocation(y + 2))) {
			return 1;
		}else if (!isLocationOccupied(getNewXLocation(x), getNewYLocation(y + 2))) {
			return 2;
		}else if (!isLocationOccupied(getNewXLocation(x - 2), getNewYLocation(y + 2))) {
			return 3;
		}else if (!isLocationOccupied(getNewXLocation(x - 2), getNewYLocation(y))) {
			return 4;
		}else if (!isLocationOccupied(getNewXLocation(x - 2), getNewYLocation(y - 2))) {
			return 5;
		}else if (!isLocationOccupied(getNewXLocation(x), getNewYLocation(y - 2))) {
			return 6;
		}else if (!isLocationOccupied(getNewXLocation(x + 2), getNewYLocation(y - 2))) {
			return 7;
		}else {
			return -1;
		}
	}
	
	/**
	 * creates a map of all the critters in their current/old locations
	 */
	private static void setOldLocations() {
		for (Critter c : population) {				
			Point p = new Point(c.x_coord, c.y_coord);
			oldLocation.put(c, p);
		}
	}
	
	/**
	 * determines what the critter size scale factor should be based off of how big the params are set, ideally aiming for 600 x 600, however, it returns no smaller than 1
	 * @return the determined crittersize factor
	 */
	private static int getCritterSize() {
		if (Params.world_height >= 600 || Params.world_width >= 600) {
			return 1;
		}else {
			if (Params.world_height >= Params.world_width) {
				return 600/Params.world_height;
			}else {
				return 600/Params.world_width;
			}
		}
	}
}

