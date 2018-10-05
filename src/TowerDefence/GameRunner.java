package TowerDefence;

import java.util.ArrayList;
import java.util.Comparator;import javax.management.openmbean.OpenDataException;

public class GameRunner {

	public static void main(String [] args) {
		Map map = new Map();
		map.printMap(new Cell(0, 0, 0, false, null));
		
		Cell origin = map.map[0][0];

		int nodesGenerated = 0;
		
		ArrayList<Cell> open = new ArrayList<>();
		open.add(origin);
		
		
		ArrayList<Cell> closed = new ArrayList<>();
		
		Comparator<Cell> cmp = new Comparator<Cell>() {
		    @Override
		    public int compare(Cell o1, Cell o2) {
		    
		    	if (o1.getFValue() < o2.getFValue()) {
					return -1;
				} else  if (o1.getFValue() == o2.getFValue()){
					return 0;
				} else {
					return -1;
				}
		    }
		};

		Cell cellToExplore = null;
		
		printOpen(open);
		
		while (!open.isEmpty()) {
			
			 cellToExplore = open.remove(0);
			if (cellToExplore.equals(Map.goal))
				break;
			ArrayList<Cell> adjacentCells = map.generateAdjacent(cellToExplore);
			nodesGenerated += adjacentCells.size();
					
			open.addAll(adjacentCells);
			open.sort(cmp);
			printOpen(open);
		}
		
		System.out.println(nodesGenerated+" Nodes Generated.");
		ArrayList<Cell> leneage = new ArrayList<>();
		cellToExplore.storeLeneage(leneage);

		printOpen(leneage);
		
		for (Cell player: leneage) {
			map.printMap(player);
		}
	}
	
	
	static void printOpen(ArrayList<Cell> open) {
		System.out.println();
		for(Cell cell: open) {
			System.out.print("("+cell.y+","+cell.x +" F:"+cell.getFValue()+")");
		}
		System.out.println();
	}
	
}


