package puzzle8.Aufgabe1;

import puzzle8.Aufgabe1.Board;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Klasse IDFS für iterative deepening depth-first search
 * @author Ihr Name
 */

public class IDFS {
	static Deque<Board> cutOff = new LinkedList<>();
	private static Deque<Board> dfs(Board curBoard, Deque<Board> path, int limit) {
		if (curBoard.isSolved()) return path;
		if (limit == 0) return null;
		boolean cutOffOccurred = false;
		for (Board child: curBoard.possibleActions()) {
			if (path.contains(child)) continue;
			path.add(child);
			Deque<Board> result = dfs(child, path, limit - 1);
			if (result == cutOff) cutOffOccurred = true;
			else if (result != null) return result;
			path.removeLast();
		}
		return null;
	}
	
	private static Deque<Board> idfs(Board curBoard, Deque<Board> path) {
		for (int limit = 5; limit < Integer.MAX_VALUE; limit++) {
			Deque<Board> result = dfs(curBoard,path,limit);
			if (result != null)
				return result;
		}
		return null;
	}
	
	public static Deque<Board> idfs(Board curBoard) {
		Deque<Board> path = new LinkedList<>();
		path.addLast(curBoard);
        return idfs(curBoard, path);
	}
}
