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
                .collect(Collectors.toList());
        
        while (reply.size() < X.size()) {
            reply.addAll(reply);
        }
        
        if (reply.size() > X.size()) {
            reply = reply.subList(0, X.size());
        }

        return reply;
    }

    public List<Integer> decipher(List<Integer> numberOfPower, List<Integer> input, List<Integer> X) {
        List<Integer> reply = new ArrayList<>();

        int maxPower = numberOfPower.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax();
        SystemD system = new SystemD(maxPower, input);
        List<Integer> output;

        int idx = 0;
        do {
            reply.add(XOR.calculate(Lists.newArrayList(system.getOutputByIndex(0), system.getOutputByIndex(maxPower - 1), X.get(idx))));
            system.next(X.get(idx));
            idx++;
        } while (idx != X.size());

        return reply;
    }

}
