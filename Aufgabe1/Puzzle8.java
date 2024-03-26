package puzzle8.Aufgabe1;

import puzzle8.Aufgabe1.A_Star;
import puzzle8.Aufgabe1.Board;
import puzzle8.Aufgabe1.IDFS;

import java.util.Deque;

/**
 * Hauptprogramm für 8-Puzzle-Problem.
 * @author Ihr Name
 */
public class Puzzle8 {
	
	public static void main(String[] args) {
		// Board b = new Board(); // Loesbares Puzzle b zufällig genrieren.
		Board b = new Board(new int[]{7, 2, 4, 5, 0, 6, 8, 3, 1});
		// Board b = new Board(new int[]{3,1,2,4,0,5,6,7,8});
		System.out.println(b);

		Deque<Board> res = A_Star.aStar(b);
		int n = res == null ? -1 : res.size();
		System.out.println("Anz.Zuege: " + n + ": " + res);
		
		res = IDFS.idfs(b);
		n = res == null ? -1 : res.size();
		System.out.println("Anz.Zuege: " + n + ": " + res);
	}
}
