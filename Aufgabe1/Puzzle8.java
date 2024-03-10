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
		Board b = new Board(); // Loesbares Puzzle b zufällig genrieren.
		System.out.println(b);

		Deque<Board> res = A_Star.aStar(b);
		int n = res == null ? -1 : res.size();
		System.out.println("Anz.Zuege: " + n + ": " + res);
		
		res = IDFS.idfs(b);
		n = res == null ? -1 : res.size();
		System.out.println("Anz.Zuege: " + n + ": " + res);
	}
}