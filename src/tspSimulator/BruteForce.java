
package tspSimulator;

/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SingleSelectionModel;

import shared.Algorithm;
import shared.Resultaat;
import shared.Route;

/**
 * Authors: Jan Willem en Henri Class: ICTM2A
 */

public class BruteForce implements Algorithm {

	private String name;
	private ArrayList<Location> picklist;
	private DrawPanel panel;

	public BruteForce(String name, ArrayList<Location> picklist) {
		this.name = name;
		this.picklist = picklist;
		
		panel = new DrawPanel("Brute Force", 0,0);
	}

	@Override
	public Route calculateRoute(ArrayList<Location> locationList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resultaat getResultaat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlgorithmName() {
		// TODO Auto-generated method stub
		return null;
	}

	public DrawPanel getPanel() {
		return panel;
	}
	
	

}
