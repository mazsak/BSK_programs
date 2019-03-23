package sample;

import java.util.List;

public class XOR {

    public static int calculate(List<Integer> inputs) {
        int amountOne = 0;
        for (int bit : inputs) {
            if (bit == 1) {
                amountOne++;
            }
        }

        if (amountOne % 2 == 1)
            return 1;
        else
            return 0;
    }
}
