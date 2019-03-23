package sample;

import com.google.inject.internal.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LFSR {

    public List<List<Integer>> calculate(List<Integer> numberOfPower, List<Integer> input) {
        List<List<Integer>> reply = new ArrayList<>();

        int maxPower = numberOfPower.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax();
        SystemD system = new SystemD(maxPower, input);
        List<Integer> output = Lists.newArrayList(input);

        do {
            system.next(XOR.calculate(system.getOutputByIndexs(numberOfPower)));
            output = system.getOutput();
            reply.add(Lists.newArrayList(output));
        } while (!input.equals(output));

        return reply;
    }

}
