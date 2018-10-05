package TowerDefence;

import java.lang.invoke.LambdaConversionException;
import java.util.ArrayList;

public class Cell {

	final int x,y;
	final boolean hasTower;
	int cellId;
	final int h;
	int g = 0;
	Cell parent = null;
	
	public Cell (int x, int y, int g, boolean hasTower, Cell parent) {
		this.x = x;
		this.y = y;
		this.g = g;
		this.hasTower = hasTower;
		
		int genCellId = 0;
		for (int i = 0; i < Map.yBound; i += 1) {
			for (int j = 0; j < Map.xBound; j += 1) {
				genCellId += 1;
				if (i == y && j == x) {
					cellId = genCellId;
					break;
				}
			}
		}
		
		h = Map.goalY-y + Map.goalX-x; 
//		h = 0;
		this.parent = parent;
	}

	int getFValue() {
		return g+h;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Cell))
			return false;
		
		Cell newCell = (Cell) obj;
		
		if (x == newCell.x && y == newCell.y) {
			return true;
		}
		
		if (obj == this)
			 return true;
		
		return false;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new Cell(x, y, g, hasTower, parent);
	}

	void printLeneage() {
		try {
			Cell copyCell = (Cell)this.clone();
			
			if(copyCell.parent != null) {
				copyCell.parent.printLeneage();
				copyCell.print();
			} else {
				copyCell.print();
				return;
			}
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void storeLeneage(ArrayList<Cell> leneage) {
		try {
			Cell copyCell = (Cell)this.clone();
			
			if(copyCell.parent != null) {
				copyCell.parent.storeLeneage(leneage);
				leneage.add(copyCell);
			} else {
				leneage.add(copyCell);
				return;
			}
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void print() {
		System.out.print("("+y+","+x+")");
	}
	
	boolean checkIfParent() {
		
		Cell copyCell;
		try {
			copyCell = (Cell)this.clone();
			while (copyCell.parent != null) {
				copyCell = copyCell.parent;
				if ( this.equals(copyCell)) {
					return true;
				}
			}
			
			return false;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
}
