package sample;

public class FlipFlopD {

    private int Q;

    public void calculate(int D, int Clk) {
        if (Clk == 1) {
            Q = D;
        }
    }

    public int getQ() {
        return Q;
    }

    public int getNotQ() {
        return Math.abs(Q - 1);
    }
}
