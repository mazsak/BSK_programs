package sample;

import com.google.inject.internal.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CA {

    public List<Integer> encrypt(List<Integer> numberOfPower, List<Integer> input, List<Integer> X) {
        List<Integer> reply;

        reply = AuxiliaryMethods
                .systemLDFR(numberOfPower, input, X)
                .stream()
                .map(e -> e.get(0))
                .collect(Collectors.toList())
                .subList(0, X.size());

        return reply;
    }

    public List<Integer> decipher(List<Integer> numberOfPower, List<Integer> input, List<Integer> X) {
        List<Integer> reply = new ArrayList<>();

        int maxPower = numberOfPower.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax();
        SystemD system = new SystemD(maxPower, input);

        int idx = 0;
        do {
            List<Integer> xorArg = new ArrayList<>();
            xorArg.add(X.get(idx));
            for(int potega : numberOfPower){
                xorArg.add(system.getOutputByIndex(potega-1));
            }
            reply.add(XOR.calculate(xorArg));
            system.next(X.get(idx));
            idx++;
        } while (idx != X.size());

        return reply;
    }

}
