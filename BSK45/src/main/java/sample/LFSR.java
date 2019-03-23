package sample;

import java.util.List;

public class LFSR {

    public List<List<Integer>> calculate(List<Integer> numberOfPower, List<Integer> input) {

        return AuxiliaryMethods.systemLDFR(numberOfPower, input, null);
    }

}
