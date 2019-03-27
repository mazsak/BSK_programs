package sample;

import com.google.inject.internal.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SSC {

    private List<Integer> seed;

    public void setSeed(List<Integer> seed) {
        this.seed = seed;
    }

    public List<Integer> encryptAndDecipher(List<Integer> numberOfPower, List<Integer> X) {
        List<Integer> reply = new ArrayList<>();

        List<List<Integer>> matrix = AuxiliaryMethods.systemLDFR(numberOfPower, seed, null);

        while (matrix.size() < X.size()) {
            matrix.addAll(matrix);
        }

        if (matrix.size() > X.size()) {
            matrix = matrix.subList(0, X.size());
        }

        seed = matrix.get(matrix.size() - 1);
        List<Integer> LSFR = matrix.stream().map(e -> e.get(0)).collect(Collectors.toList());

        for (int i = 0; i < X.size(); i++) {
            reply.add(XOR.calculate(Lists.newArrayList(X.get(i), LSFR.get(i))));
        }
        
        return reply;
    }
    
}
