package sample;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {

    public static List<Integer> read(String polynomial) {
        List<Integer> reply = new ArrayList<>();
        int idx = 0;

        polynomial = polynomial.replaceAll("\\s", "");

        while (idx < polynomial.length()) {
            if (polynomial.charAt(idx) == 'x') {
                if (idx + 1 < polynomial.length())
                    if (polynomial.charAt(idx + 1) == '^') {
                        reply.add((int) polynomial.charAt(idx + 2) - 48);
                        idx += 3;
                    } else {
                        reply.add(1);
                        idx++;
                    }
                else {
                    reply.add(1);
                    idx++;
                }
            } else
                idx++;
        }

        return reply;
    }
}
