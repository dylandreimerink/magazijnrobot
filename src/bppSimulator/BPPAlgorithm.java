package bppSimulator;

import java.util.ArrayList;

import shared.Product;

public interface BPPAlgorithm {
	
	public void start();
	public void stop();
	public ArrayList<Box> getResult();
	
}
