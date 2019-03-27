package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CA {

    private List<Integer> seed;

    public void setSeed(List<Integer> seed) {
        this.seed = seed;
    }

    public List<Integer> encrypt(List<Integer> numberOfPower, List<Integer> X) {
        List<Integer> reply;

        List<List<Integer>> matrix = AuxiliaryMethods.systemLDFR(numberOfPower, seed, X);

        reply = matrix
                .stream()
                .map(e -> e.get(0))
                .collect(Collectors.toList())
                .subList(0, X.size());

        seed = matrix.get(matrix.size() - 1);

        return reply;
    }

    public List<Integer> decipher(List<Integer> numberOfPower, List<Integer> X) {
        List<Integer> reply = new ArrayList<>();

        int maxPower = numberOfPower.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax();
        SystemD system = new SystemD(maxPower, seed);

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

        seed = system.getOutput();

        return reply;
    }
}
