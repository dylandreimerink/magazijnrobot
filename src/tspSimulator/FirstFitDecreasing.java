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


public class FirstFitDecreasing implements Algorithm{

	private String name;
	private ArrayList<Location> picklist;
	private DrawPanel panel;
	private Resultaat resultaat;


	public FirstFitDecreasing(String name, ArrayList<Location> picklist) {
		this.name = name;
		this.picklist = picklist;
		resultaat = new Resultaat(picklist, 0);
		panel = new DrawPanel("First Fit Decreasing", resultaat);
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
		return name;
	}
	
	public DrawPanel getPanel() {
		return panel;
	}

}
