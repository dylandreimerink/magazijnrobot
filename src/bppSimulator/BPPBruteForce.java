package bppSimulator;

import java.util.ArrayList;
import java.util.Stack;

import shared.Product;

public class BPPBruteForce implements BPPAlgorithm, Runnable {

	//buffer variable for the algorithm
	protected ArrayList<Box> usedBoxes = new ArrayList<Box>();
	
	//best solution
	protected ArrayList<Box> bestBoxes = new ArrayList<Box>();
	
	//products that the algorithm uses
	protected ArrayList<Product> products;
	
	//the thread in which a instance of this class runs
	protected Thread t = new Thread(this);

	//makes the thread pause if true
	protected boolean waiting = false;
	
	//makes the thread stop if true
	protected boolean stop = false;

	//three dimentional array made of boxunits(booleans) that indicate if a space in the box is used
	boolean[][][] boxUnits;

	//instance of the parent - is used for the callback method when the thread is done
	MainGUI onComplete;

	public BPPBruteForce(PickList picklist, int boxWidth, int boxHeight, int boxLength) {
		products = picklist.getProducts();
		boxUnits = new boolean[boxWidth][boxHeight][boxLength];
	}

	public boolean getPauzed() {
		return waiting;
	}
	
	//sets the callback class
	public void setOnDoneListner(MainGUI listnerClass) {
		onComplete = listnerClass;
	}

	@Override
	public void start() {
		//if we were waiting, resume
		//otherwise start thread
		if (waiting) {
			waiting = false;
		} else {
			t.start();
		}
	}

	@Override
	public void stop() {
		System.out.println("stopping brute force");
		stop = true;
		
	}

	public void pauze() {
		waiting = true;
	}

	@Override
	public ArrayList<Box> getResult() {
		return bestBoxes;
	}

	public void permutations(ArrayList<Product> items, Stack<Product> permutation, int size) {
		//stop the algorithm so the thread can use stop
		if(stop){
			return;
		}
		
		//if the permutations stack has reached the same length as we want(size if the product list)
		if (permutation.size() == size) {
			
			//run fit algorithm
			
			//list of products in this box
			ArrayList<Product> boxlist = new ArrayList<Product>();
			
			//for every product in the permutations
			for (Product p : permutation.toArray(new Product[0])) {
				
				//the space used 
				double usedSpace = 0;

				//get all the used space in the box until now
				for (Product pp : boxlist) {
					usedSpace += pp.getHeight() * pp.getLenght() * pp.getWidth();
				}

				//add the space of the current product to the box
				usedSpace += p.getHeight() * p.getLenght() * p.getWidth();
				
				//if usedSpace(current used space + space that we want to add) is smaller then what fits in the box and if it fits(see fitProduct()) add it to the box
				if ((boxUnits.length * boxUnits[0].length * boxUnits[0][0].length) > usedSpace && fitProduct(p)) {
					boxlist.add(p);
				} else {
					//else make a new box
					
					//make a new list of products
					boxlist = new ArrayList<Product>();
					
					//make the new box
					usedBoxes.add(
							new Box(boxUnits.length, boxUnits[0].length, boxUnits[0][0].length, new PickList(boxlist)));
					
					//add the product to the box
					boxlist.add(p);
					
					//set the spaces in the new box all to null
					boxUnits = new boolean[boxUnits.length][boxUnits[0].length][boxUnits[0][0].length];
				}
			}
			//add the last box to the used boxes buffer
			usedBoxes.add(new Box(boxUnits.length, boxUnits[0].length, boxUnits[0][0].length, new PickList(boxlist)));
			
			//check if the new solution was better then the last one
			checkBoxes(usedBoxes);
			
			//clear the buffer
			usedBoxes = new ArrayList<Box>();
		}

		
		/* items available for permutation */
		Product[] availableItems = items.toArray(new Product[0]);
		for (Product i : availableItems) {
			/* add current item */
			permutation.push(i);

			/* remove item from available item set */
			items.remove(i);

			/* pass it on for next permutation */
			permutations(items, permutation, size);

			/* pop and put the removed item back */
			items.add(permutation.pop());
		}
	}
	

	private void checkBoxes(ArrayList<Box> used) {
		
		//if we have less boxes then the best solution, we make it the best solution
		if (bestBoxes.size() > used.size()) {

			bestBoxes = used;
		} else if (bestBoxes.size() == 0) {
			
			//else if we the best solution has no items this must be the first solution 
			bestBoxes = used;
		}
	}

	@Override
	public void run() {
		
		//start the algotithm
		permutations(products, new Stack<Product>(), products.size());

		//if we have a callback function
		if (onComplete != null) {
			//callback to parent
			callBack();
		}
	}

	protected void callBack() {
		onComplete.BruteForceCallback();
	}

	private boolean fitProduct(Product p) {
		//if we found a position where the product fits in the box
		boolean fit = false;
		
		//if the product fits in this position
		boolean thisTry = true;
		
		//product dimensions
		int productWidth = (int) p.getWidth();
		int productHeight = (int) p.getHeight();
		int productLength = (int) p.getLenght();
		
		//offset of product in box
		int xOff = 0, yOff = 0, zOff = 0;
		
		//the max offsets 
		int maxXOff = boxUnits.length - productWidth;
		int maxYOff = boxUnits[0].length - productHeight;
		int maxZOff = boxUnits[0][0].length - productLength;

		//remembers if we turned a product on the x y and z axis 
		boolean[] turned = { false, false, false };
		
		//if the products width and length are the same, we don't need to turn it in the z axis
		if (productWidth == productLength) {
			turned[2] = true;
		}
		
		//if the products width and height are the same, we don't need to turn it in the y axis
		if (productWidth == productHeight) {
			turned[1] = true;
		}
		
		//if the products height and length are the same, we don't need to turn it in the x axis
		if (productLength == productHeight) {
			turned[0] = true;
		}

		//try it at least one time
		do {
			//this try is successful unless we find a reason why it's not
			thisTry = true;
			
			//for every boxunit the product will occupy in the x axis 
			for (int x = xOff; x < (xOff + productWidth); x++) {
				//for every boxunit the product will occupy in the y axis 
				for (int y = yOff; y < (yOff + productHeight); y++) {
					//for every boxunit the product will occupy in the z axis 
					for (int z = zOff; z < (zOff + productLength); z++) {
						//if one of the box units is already occupied(is true)
						if (boxUnits[x][y][z]) {
							//set this try to failed
							thisTry = false;
							//set all iterative variables of the for loops to the threshold value so all for loops will stop
							x = (xOff + productWidth);
							y = (yOff + productHeight);
							z = (zOff + productLength);
						}
					}
				}
			}
			
			//if this try was not successful
			if (!thisTry) {
				if (xOff < maxXOff) {
					//if we didn't reach the max x offset increment the offset
					xOff++;
				} else if (yOff < maxYOff) {
					//if we didn't reach the max y offset increment the offset and reset the x offset
					yOff++;
					xOff = 0;
				} else if (zOff < maxZOff) {
					//if we didn't reach the max z offset increment the offset and reset the x and y offset
					zOff++;
					yOff = 0;
					xOff = 0;
				} else {
					//if we already tried all the possible positions in this orientation
					
					if (turned[0] && turned[1] && turned[2]) {
						//if we already turned the product every possible way, give up.... it will not fit in this box
						break;
					} else {
						if (!turned[2] && productWidth != productLength) {
							//if we haven't turned it on its z axis
							//put the width in a buffer variable
							int buff = productWidth;
							
							//switch the length to the width
							productWidth = productLength;
							
							//switch the buffered width to length
							productLength = buff;
							
							//remember we turned on this axis
							turned[2] = true;
						} else if (!turned[1] && productWidth != productHeight) {
							//if we haven't turned it on its y axis
							//put the width in a buffer variable
							int buff = productWidth;
							
							//switch the height to the width
							productWidth = productHeight;
							
							//switch the buffered width to height
							productHeight = buff;
							
							//remember we turned on this axis
							turned[1] = true;
							
							//if it makes a difference to turn the product on its z axis, reset turned for z
							if (productWidth != productLength){
								turned[2] = false;
							}
						} else if (!turned[0] && productLength != productHeight) {
							//if we haven't turned it on its x axis
							//put the length in a buffer variable
							int buff = productLength;
							
							//switch the height to the length
							productLength = productHeight;
							
							//switch the buffered length to height
							productHeight = buff;
							
							///remember we turned on this axis
							turned[0] = true;
							
							//if it makes a difference to turn the product on its y axis, reset turned for y
							if (productWidth != productHeight){
								turned[1] = false;
							}
							//if it makes a difference to turn the product on its z axis, reset turned for z
							if (productWidth != productLength){
								turned[2] = false;
							}
						}
						
						//set axis that don't need to be turned to true
						if (productWidth == productLength) {
							turned[2] = true;
						}
						if (productWidth == productHeight) {
							turned[1] = true;
						}
						if (productLength == productHeight) {
							turned[0] = true;
						}
						
						//reset the offsets to start position after turning
						xOff = 0;
						yOff = 0;
						zOff = 0;
						
						//Adjust max offsets for new dimensions
						maxXOff = boxUnits.length - productWidth;
						maxYOff = boxUnits[0].length - productHeight;
						maxZOff = boxUnits[0][0].length - productLength;
					}
				}
			}
			//if this try was not successful keep going
		} while (!thisTry);

		//if we exited loop because the try was successful
		if (thisTry) {
			//the product fits
			fit = true;

			//for every boxunit that now should be occupied by the new product in the x axis
			for (int x = xOff; x < (xOff + productWidth); x++) {
				//for every boxunit that now should be occupied by the new product in the y axis
				for (int y = yOff; y < (yOff + productHeight); y++) {
					//for every boxunit that now should be occupied by the new product in the z axis
					for (int z = zOff; z < (zOff + productLength); z++) {
						//set boxunit in this position to occupied
						boxUnits[x][y][z] = true;
					}
				}
			}
		}

		return fit;
	}

}