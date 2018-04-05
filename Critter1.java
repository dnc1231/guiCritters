package assignment5;

import assignment5.Critter;
import assignment5.Critter1;
import assignment5.Params;

/* CRITTERS Critter1.java
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * Daniel Canterino
 * djc3323
 * 15460
 * Spring 2018
 */

/**
 * @author Daniel Canterino
 * @version 2.0
 * This class extends the Critter class. It focuses on reproducing as much as possible spawning up to two children if it has enough energy and a
 * guaranteed 1 if it has enough to reproduce and not die
 */
public class Critter1 extends Critter{
	private int numberSpawned;
	int fearfullness;
	
	@Override
	public String toString() {return "1";}
	
	/**
	 * if the rolled number is higher than the critter1s fearfullness, it will fight
	 */
	public boolean fight(String not_used) { 
		int num = getRandomInt(100);
		if (num >= fearfullness) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Critter1 constructor
	 * rolls for a fearfullness
	 */
	public Critter1() {
		numberSpawned = 0;
		while (fearfullness < 50) {//at minimum fearfullness is 50%
			fearfullness = getRandomInt(100);
		}
	}
	
	/**
	 * Critter2 timestep
	 * walks in a random direction when low on energy to try and find food, and will reproduce if it has enough energy potentially up to 2 children
	 */
	public void doTimeStep() {

		if (getEnergy() < 6 * Params.min_reproduce_energy && getEnergy() > Params.min_reproduce_energy) {
			Critter1 child = new Critter1();
			reproduce(child, getRandomInt(8));
			numberSpawned += 1;
		}else if(getEnergy() < Params.min_reproduce_energy) {
				int i = getRandomInt(8);
				String hi = look(i, true);
				if (look(i, true) == (null) || look(i, true).equals("@")) {
					walk(i);
				}else {
					walk(getRandomInt(8));
			}
			return;
		}else {
			Critter1 child1 = new Critter1();
			reproduce(child1, getRandomInt(8));
			Critter1 child2 = new Critter1();
			reproduce(child2, getRandomInt(8));
			numberSpawned += 2;
		}	
	}
	
	/**
	 * Will show the stats for the Critter1s average fear rating percentage and the total number of critter1s spawned by the living critter 1s 
	 */
	public static String runStats(java.util.List<Critter> critter1s) {
		String stats = new String();
		int averageFear = 0;
		int totalNumberSpawned = 0;
		for (Critter c : critter1s) {
			Critter1 critter = (Critter1) c;
			averageFear += critter.fearfullness;
			totalNumberSpawned += critter.numberSpawned;
		}
		averageFear = averageFear/(critter1s.size());
		stats = "Total number of critter1s spawned by the living critter1s: " + totalNumberSpawned + "\nAverage fearfullness of critters1s: " + averageFear + "%";
		return stats;
	}
	
	@Override
	public CritterShape viewShape() { return CritterShape.STAR; }

	@Override
	public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.PURPLE; }
	
}
