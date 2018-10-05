package TowerDefence;

import java.util.ArrayList;
import java.util.Arrays;

public class Map {

		
	static int xBound = 6, yBound = 5;
	static int goalX = xBound-1, goalY = yBound-1;
	static final Cell goal = new Cell(goalX, goalY, 0, false, null);

	
	Integer[][] towers = {
			{1,1},
			{2,2}
	};

	
	Cell[][] map = new Cell[yBound][xBound];
	
	public Map() {
		for (int y = 0; y < yBound; y += 1) {
			for (int x = 0; x < xBound; x += 1 ) {
				if (checkIfTower(y, x)) {
					map[y][x] = new Cell(x, y, 0, true, null);
				} else {
					map[y][x] = new Cell(x, y, 0, false, null);
				}
 			}
		}
	}
	
	private boolean checkIfTower(int y, int x) {
		boolean isTower = false;
		
		for (int i = 0; i < towers.length; i += 1 ) {
			if (towers[i][0] == y && towers[i][1] == x) {
				return true;
			}
		}
		return isTower;
	}


	void printMap(Cell enemy) {
//		System.out.print("\033\143");

		System.out.print("  ");
		for (int i = 0; i < xBound; i += 1 ) {
			System.out.print(" "+i+" ");
		}
		System.out.println();
		for (int y = 0; y < yBound; y += 1) {
			System.out.print(y+" ");
			for (int x = 0; x < xBound; x += 1 ) {

				if (x == enemy.x && y == enemy.y)
					System.out.print(" ⁂ ");

				else if (x == goal.x && y == goal.y)
					System.out.print(" ⚑ ");
					
				else if (map[y][x].hasTower)
					System.out.print(" ♖ ");
				
				
				else 
					System.out.print(" _ ");
				
 			}
			System.out.println();
		}
	}
	
	ArrayList<Cell> generateAdjacent(Cell cell) {
		ArrayList<Cell> neighbours = new ArrayList<>();
		int x = cell.x, y = cell.y, g = cell.g;

		
		Cell adjCell = new Cell(x-1, y-1, g+1, false, cell);
		if (checkIfAllowedToMove(cell,adjCell)) {
			neighbours.add(adjCell);
		}
		
		adjCell = new Cell(x, y-1, g+1, false, cell);
		if (checkIfAllowedToMove(cell,adjCell)) {
			neighbours.add(adjCell);
		}
		
		adjCell = new Cell(x+1, y-1, g+1, false, cell);
		if (checkIfAllowedToMove(cell,adjCell)) {
			neighbours.add(adjCell);
		}
		

		adjCell = new Cell(x-1, y, g+1, false, cell);
		if (checkIfAllowedToMove(cell,adjCell)) {
			neighbours.add(adjCell);
		}
		
	
		adjCell = new Cell(x+1, y, g+1, false, cell);
		if (checkIfAllowedToMove(cell,adjCell)) {
			neighbours.add(adjCell);
		}
		
		adjCell = new Cell(x-1, y+1, g+1, false, cell);
		if (checkIfAllowedToMove(cell,adjCell)) {
			neighbours.add(adjCell);
		}
	
		adjCell = new Cell(x, y+1, g+1, false, cell);
		if (checkIfAllowedToMove(cell,adjCell)) {
			neighbours.add(adjCell);
		}
	
		adjCell = new Cell(x+1, y+1, g+1, false, cell);
		if (checkIfAllowedToMove(cell,adjCell)) {
			neighbours.add(adjCell);
		}
		
		return neighbours;

	}
	
	private boolean checkIfAllowedToMove(Cell oldCell,Cell newCell) {
		
		if (newCell.x >= 0 && newCell.x <= xBound-1 &&  newCell.y >= 0 && newCell.y <= yBound-1) {
			
			if (checkIfTower(newCell.y, newCell.x))
				
				return false;

			if (newCell.checkIfParent())
				return false;
			
			return true;
			
		} else {
			return false;
		}
	}
}
