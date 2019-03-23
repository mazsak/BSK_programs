package sample;

import com.google.inject.internal.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SSC {

    public List<Integer> encryptAndDecipher(List<Integer> numberOfPower, List<Integer> input, List<Integer> X) {
        List<Integer> reply = new ArrayList<>();

        List<Integer> LSFR = AuxiliaryMethods.systemLDFR(numberOfPower, input, null).stream().map(e -> e.get(0)).collect(Collectors.toList()).subList(0, X.size());

        for (int i = 0; i < X.size(); i++) {
            reply.add(XOR.calculate(Lists.newArrayList(X.get(i), LSFR.get(i))));
        }

//            reply = AuxiliaryMethods.systemLDFR(numberOfPower, input,  X).stream().map(e -> e.get(0)).collect(Collectors.toList()).subList(0, X.size());

        return reply;
    }


}
