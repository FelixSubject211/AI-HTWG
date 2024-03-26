package puzzle8.Aufgabe1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Klasse Board für 8-Puzzle-Problem
 * @author Ihr Name
 */
public class Board {

	/**
	 * Problmegröße
	 */
	public static final int N = 8;

	/**
	 * Board als Feld. 
	 * Gefüllt mit einer Permutation von 0,1,2, ..., 8.
	 * 0 bedeutet leeres Feld.
	 */
	protected int[] board = new int[N+1];

	/**
	 * Generiert ein zufälliges Board.
	 */
	public Board() {
		while (true) {
			List<Integer> numberList = new ArrayList<>(IntStream.range(0, N + 1)
                    .boxed()
                    .toList());
			Collections.shuffle(numberList);

			int[] intArray = numberList.stream()
					.mapToInt(Integer::intValue)
					.toArray();

			Board newBoard = new Board(intArray);

			if (newBoard.parity()) {
				this.board = intArray;
				return;
			}
		}
	}
	
	/**
	 * Generiert ein Board und initialisiert es mit board.
	 * @param board Feld gefüllt mit einer Permutation von 0,1,2, ..., 8.
	 */
	public Board(int[] board) {
		this.board = board;
	}

	@Override
	public String toString() {
		return "Puzzle{" + "board=" + Arrays.toString(board) + '}';
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Board other = (Board) obj;
		return Arrays.equals(this.board, other.board);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 67 * hash + Arrays.hashCode(this.board);
		return hash;
	}
	
	/**
	 * Paritätsprüfung.
	 * @return Parität.
	 */
	public boolean parity() {
		return h1() % 2 == 0;
	}
	
	/**
	 * Heurstik h1. (siehe Aufgabenstellung)
	 * @return Heuristikwert.
	 */
	public int h1() {
		int falseItemCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i] < board[j] && i > j) {
					falseItemCount++;
				}
			}
		}
		return falseItemCount;
	}
	
	/**
	 * Heurstik h2. (siehe Aufgabenstellung)
	 * @return Heuristikwert.
	 */
	public int h2() {
		int distance = 0;
		for (int i = 0; i < N + 1; i++) {
			if (board[i] != 0) {
				distance += manhattanDistance(i, board[i]);
			}
		}
		return distance;
	}

	private int manhattanDistance(int currentPos, int targetPos) {
		int currentRow = currentPos / 3;
		int currentCol = currentPos % 3;
		int targetRow = targetPos / 3;
		int targetCol = targetPos % 3;
		return Math.abs(currentRow - targetRow) + Math.abs(currentCol - targetCol);
	}
	
	/**
	 * Liefert eine Liste der möglichen Aktion als Liste von Folge-Boards zurück.
	 * @return Folge-Boards.
	 */
	public List<Board> possibleActions() {
		List<Board> boardList = new LinkedList<>();
		int zeroIndex = findZeroIndex();

		List<Integer> possibleMoves = getPossibleMoves(zeroIndex);

		for (int move : possibleMoves) {
			boardList.add(createNewBoard(zeroIndex, move));
		}

		return boardList;
	}

	private int findZeroIndex() {
		return IntStream.range(0, board.length)
				.filter(index -> board[index] == 0)
				.findFirst()
				.orElse(-1);
	}

	private List<Integer> getPossibleMoves(int zeroIndex) {
		List<List<Integer>> moves = Arrays.asList(
				Arrays.asList(1, 3),
				Arrays.asList(0, 2, 4),
				Arrays.asList(1, 5),
				Arrays.asList(0, 4, 6),
				Arrays.asList(1, 3, 5, 7),
				Arrays.asList(2, 4, 8),
				Arrays.asList(3, 7),
				Arrays.asList(4, 6, 8),
				Arrays.asList(5, 7)
		);
		return moves.get(zeroIndex);
	}

	private Board createNewBoard(int zeroIndex, int moveIndex) {
		int[] newBoard = Arrays.copyOf(board, board.length);
		swapElements(newBoard, zeroIndex, moveIndex);
		return new Board(newBoard);
	}

	private static void swapElements(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	/**
	 * Prüft, ob das Board ein Zielzustand ist.
	 * @return true, falls Board Ziestzustand (d.h. 0,1,2,3,4,5,6,7,8)
	 */
	public boolean isSolved() {
		for (int i = 0; i < N; i++) {
			if (board[i] != i) {
				return false;
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		Board b = new Board(new int[]{7,2,4,5,0,6,8,3,1});		// abc aus Aufgabenblatt
		Board goal = new Board(new int[]{0,1,2,3,4,5,6,7,8});
				
		System.out.println(b);
		System.out.println(b.parity());
		System.out.println(b.h1());
		System.out.println(b.h2());
		
		for (Board child : b.possibleActions())
			System.out.println(child);
		
		System.out.println(goal.isSolved());
	}
}
	
