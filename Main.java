package assignment5;


import java.util.List;

import javafx.application.*;

import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

import javafx.scene.text.*;

import javafx.scene.control.*;



public class Main extends Application{
	
	private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
	
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }
	
	 public static void main(String[] args) {
	        launch(args);
	    }
	    
	 
	    @Override
	    public void start(Stage primaryStage) {
	    	createGrid(primaryStage);

	    }
	    
	    public void createGrid(Stage primaryStage) {
	        

	        //Creating a GridPane container
	        GridPane grid = new GridPane();
	        grid.setPadding(new Insets(10, 10, 10, 10));
	        grid.setVgap(5);
	        grid.setHgap(5);
	        Critter.displayWorld(grid);
	        
	        //Defining the crittername text field
	        final TextField critterName = new TextField();
	        critterName.setPromptText("Critter Type");
	        critterName.setPrefColumnCount(10);
	        critterName.getText();
	        GridPane.setConstraints(critterName, 0, 0);
	        grid.getChildren().add(critterName);
	        //Defining the critter number text field
	        final TextField critterNumber = new TextField();
	        critterNumber.setPromptText("Number of Critters");
	        GridPane.setConstraints(critterNumber, 0, 1);
	        grid.getChildren().add(critterNumber);	        
	        //Defining the Submit make button
	        Button submit = new Button("Make Critter");
	        GridPane.setConstraints(submit, 1, 0);
	        grid.getChildren().add(submit);
	        
	        //Defining the step text field
	        final TextField stepField = new TextField();
	        stepField.setPromptText("Number of Steps");
	        GridPane.setConstraints(stepField, 0, 2);
	        grid.getChildren().add(stepField);
	        //Defining the step button
	        Button step = new Button("Step");
	        GridPane.setConstraints(step, 1, 2);
	        grid.getChildren().add(step);

	       	//Defining the Seed text field
	        final TextField seedField = new TextField();
	        seedField.setPromptText("Seed Number");
	        GridPane.setConstraints(seedField, 0, 3);
	        grid.getChildren().add(seedField);
	        //Defining the seed button
	        Button seed = new Button("Seed");
	        GridPane.setConstraints(seed, 1, 3);
	        grid.getChildren().add(seed); 
	        
	        //Defining the stats field
	        final TextField statsField = new TextField();
	        statsField.setPromptText("Critter type for Stats");
	        GridPane.setConstraints(statsField, 0, 4);
	        grid.getChildren().add(statsField);
	        
	        //Defining the stats button
	        Button stats = new Button("Stats");
	        GridPane.setConstraints(stats, 1, 4);
	        grid.getChildren().add(stats);
	        
	        //Defining the quit button
	        Button quit = new Button("Quit");
	        GridPane.setConstraints(quit, 0, 5);
	        grid.getChildren().add(quit);

	        //making field to fill out
	        final Label label = new Label();
	        GridPane.setConstraints(label, 0, 6);
	        GridPane.setColumnSpan(label, 2);
	        grid.getChildren().add(label);

	        submit.setOnAction(new EventHandler<ActionEvent>() {

	        	@Override
	        	    public void handle(ActionEvent e) {
	        			String critterType = critterName.getText();
	        			if (critterNumber.getText().isEmpty()) {
	        				try {
								Critter.makeCritter(critterType);
								Critter.displayWorld(grid);
							} catch (InvalidCritterException e1) {
								label.setText("error processing: " + critterName.getText() + " " + critterNumber.getText());
							}
	        			}else if (isNumber(critterNumber.getText())) {
	        				Integer num = Integer.valueOf(critterNumber.getText());
	        				for (int i = 0; i < num; i++) {
	        					try {
	        						Critter.makeCritter(critterType);
	        					}
	        					catch (InvalidCritterException v) {
									label.setText("error processing: " + critterName.getText() + " " + critterNumber.getText());
	        					}
	        				}
	        				Critter.displayWorld(grid);
	        			}else {
	        				label.setText("error processing: " + critterName.getText() + " " + critterNumber.getText());
	        			}
	    			}
	        	 });
	        
	        step.setOnAction(new EventHandler<ActionEvent>() {
	        	
	        	@Override
	        		public void handle(ActionEvent e) {
	        			if (stepField.getText().isEmpty()) {
	        				Critter.worldTimeStep();
	        				Critter.displayWorld(grid);
	        			} else if (isNumber(stepField.getText())) {
	        				Integer num = Integer.valueOf(stepField.getText());
	        				for (int i = 0; i < num; i++) {
	        					Critter.worldTimeStep();
	        				}
	        				Critter.displayWorld(grid);
	        			}else {
	        				label.setText("error processing: step " + stepField.getText());
	        			}
    			}
        	 });
	        
	        quit.setOnAction(new EventHandler<ActionEvent>() {
	        	
	        	@Override
	        		public void handle(ActionEvent e) {
	        			System.exit(0);
    			}
        	 });
	        
	        stats.setOnAction(new EventHandler<ActionEvent>() {
	        	
	        	@Override
	        		public void handle(ActionEvent e) {
	        		String critterType = statsField.getText();
	        		if (critterType.isEmpty()) {
	        			label.setText("Please enter a critter to run stats on.");
	        		}else {
	        			try {
	        				List<Critter> critterList = Critter.getInstances(critterType);
	        				String className = critterType.substring(0, 1).toUpperCase() + critterType.substring(1);
	        				String classType = myPackage + "." + className;
	        				Class c = Class.forName(classType);
	        				Object v = c.newInstance();
	        				java.lang.reflect.Method method1;
	        				method1 = v.getClass().getMethod("runStats", List.class);
	        				if (critterList.size() > 0) {
	        					String stats = new String();
	        					stats = (String) method1.invoke(v, critterList);
	        					label.setText(stats);
	        				}else {
	        					label.setText(Critter.runStats(critterList));
	        				}
	        			} catch (InvalidCritterException v) {
	        				label.setText("error processing: stats " + statsField.getText());
	        			} catch (Exception v) {
	        				label.setText("error processing: stats " + statsField.getText());
	        			}
	        		}
	        	}
        	 });
	        
	        seed.setOnAction(new EventHandler<ActionEvent>() {
	        	
	        	@Override
	        		public void handle(ActionEvent e) {
	        			if (isNumber(seedField.getText()) && !seedField.getText().isEmpty()) {
	        				Critter.setSeed(Long.valueOf(seedField.getText()));
	        				
	        			}else {
	        				label.setText("error processing: seed " + seedField.getText());
	        			}
    			}
        	 });
	        

	        
	        primaryStage.setScene(new Scene(grid, 1000, 1000));
	        primaryStage.show();
	    }
	    
	    public boolean isNumber(String number) {
	    	for (int i = 0; i < number.length(); i++) {
				if (number.charAt(i) < '0' || number.charAt(i) > '9') {
					return false;
				}
	    	}
	    	return true;
	    }

}
