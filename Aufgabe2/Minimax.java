package aufgaben.Aufgabe2;

public class Minimax {

    public static KalahBoard maxAction(KalahBoard board) {
        if (board.isFinished()) return null;
        int v = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        KalahBoard res = null;
        for (KalahBoard child: board.possibleActions()) {
            int v1 = minValue(child, alpha, beta);
            if (v1 > v) {
                v = v1;
                res = child;
            }
        }
        return res;
    }

    static int maxValue(KalahBoard board, int alpha, int beta) {
        if (board.isFinished()) return board.h1();
        int v = Integer.MIN_VALUE;
        for (KalahBoard child: board.possibleActions()) {
            v = Math.max(v, minValue(child, alpha, beta));
            if (v >= beta) return v;
            alpha = Math.max(alpha, v);
        }
        return v;
    }

    static int minValue(KalahBoard board, int alpha, int beta) {
        if (board.isFinished()) return board.h1();
        int v = Integer.MAX_VALUE;
        for (KalahBoard child: board.possibleActions()) {
            v = Math.min(v, maxValue(child, alpha, beta));
            if (v <= beta) return v;
            beta = Math.min(beta, v);
        }
        return v;
    }
}
