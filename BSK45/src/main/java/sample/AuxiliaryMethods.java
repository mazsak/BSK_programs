package sample;

import com.google.inject.internal.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuxiliaryMethods {

    public static List<List<Integer>> systemLDFR(List<Integer> numberOfPower, List<Integer> input, List<Integer> X) {
        List<List<Integer>> reply = new ArrayList<>();

        int maxPower = numberOfPower.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax();
        SystemD system = new SystemD(maxPower, input);
        List<Integer> output;

        if (X == null) {
            do {
                system.next(XOR.calculate(system.getOutputByIndexs(numberOfPower)));
                output = system.getOutput();
                reply.add(Lists.newArrayList(output));
            } while (!input.equals(output));
        } else {
            int idx = 0;
            do {
                List<Integer> inputToXOR = Lists.newArrayList(system.getOutputByIndexs(numberOfPower));
                inputToXOR.add(X.get(idx));
                idx++;
                system.next(XOR.calculate(inputToXOR));
                output = system.getOutput();
                reply.add(Lists.newArrayList(output));
            } while (X.size() != idx);
        }

        return reply;
    }
}
