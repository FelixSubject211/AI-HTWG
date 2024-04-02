package aufgaben.Aufgabe2;

public class Minimax {

    public static KalahBoard maxAction(KalahBoard board) {
        if (board.isFinished()) return null;
        int v = Integer.MIN_VALUE;
        KalahBoard res = null;
        for (KalahBoard child: board.possibleActions()) {
            int v1 = minValue(child);
            if (v1 > v) {
                v = v1;
                res = child;
            }
        }
        return res;
    }

    static int maxValue(KalahBoard board) {
        if (board.isFinished()) return board.h1();
        int v = Integer.MIN_VALUE;
        for (KalahBoard child: board.possibleActions()) {
            v = Math.max(v, minValue(child));
        }
        return v;
    }

    static int minValue(KalahBoard board) {
        if (board.isFinished()) return board.h1();
        int v = Integer.MAX_VALUE;
        for (KalahBoard child: board.possibleActions()) {
            v = Math.min(v, maxValue(child));
        }
        return v;
    }
}
