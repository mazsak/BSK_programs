package sample;

import java.util.List;
import java.util.stream.Collectors;

public class CA {

    public List<Integer> encrypt(List<Integer> numberOfPower, List<Integer> input, List<Integer> X) {
        List<Integer> reply;

        reply = AuxiliaryMethods.systemLDFR(numberOfPower, input, X).stream().map(e -> e.get(0)).collect(Collectors.toList()).subList(0, X.size());

        return reply;
    }

}
