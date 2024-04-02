package puzzle8.Aufgabe1;

import java.util.*;

/**
 *
 * @author Ihr Name
 */
public class A_Star {
	// cost ordnet jedem Board die Aktuellen Pfadkosten (g-Wert) zu.
	// pred ordnet jedem Board den Elternknoten zu. (siehe Skript S. 2-25). 
	// In cost und pred sind genau alle Knoten der closedList und openList enthalten!
	// Nachdem der Zielknoten erreicht wurde, lässt sich aus cost und pred der Ergebnispfad ermitteln.
	private static final HashMap<Board,Integer> cost = new HashMap<>();
	private static final HashMap<Board,Board> pred = new HashMap<>();
	
	// openList als Prioritätsliste.
	// Die Prioritätswerte sind die geschätzen Kosten f = g + h (s. Skript S. 2-66)
	private static final IndexMinPQ<Board, Integer> openList = new IndexMinPQ<>();
	
	public static Deque<Board> aStar(Board startBoard) {
		if (startBoard.isSolved())
			return new LinkedList<>();

		openList.add(startBoard, startBoard.h2());
		cost.put(startBoard, 0);
		Set<Board> closedList = new HashSet<>();

		while (!openList.isEmpty()) {
			Board n = openList.removeMin();
			if (n.isSolved()) {
				return computeResult(n);
			}
			closedList.add(n);
			for(Board board : n.possibleActions()) {
				if(!closedList.contains(board) && openList.get(board) == null) {
					cost.put(board, cost.get(n) + 1);
					pred.put(board, n);
					openList.add(board, cost.get(board) + board.h2());
				} else if (openList.get(board) != null) {
					if (cost.get(n) + 1 < cost.get(board)) {
						cost.put(board, cost.get(n) + 1);
						openList.change(board, cost.get(board) + board.h2());
					}
				}
			}
		}
		
		return null; // Keine Lösung
	}

	private static Deque<Board> computeResult(Board board) {
		List<Board> all = new ArrayList<>();
		Board current = board;
		while (current != null) {
			all.add(current);
			current = pred.get(current);
		}
		Collections.reverse(all);
		all.remove(0);
		return new LinkedList<>(all);
	}
}
