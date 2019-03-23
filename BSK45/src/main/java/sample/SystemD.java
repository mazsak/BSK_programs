package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SystemD {
    private FlipFlopD[] system;

    public SystemD(int numberOfFlipFlop, List<Integer> input) {
        system = new FlipFlopD[numberOfFlipFlop];

        for (int i = 0; i < system.length; i++) {
            system[i] = new FlipFlopD();
            system[i].calculate(input.get(i), 1);
        }
    }

    public void next(int inputFirst) {
        List<Integer> QMinusOne = getOutput();
        system[0].calculate(inputFirst, 1);
        for (int i = 1; i < system.length; i++) {
            system[i].calculate(QMinusOne.get(i - 1), 1);
        }
    }

    public List<Integer> getOutput() {
        return Arrays.stream(system).map(FlipFlopD::getQ).collect(Collectors.toList());
    }

    public int getOutputByIndex(int idx) {
        return system[idx].getQ();
    }

    public List<Integer> getOutputByIndexs(List<Integer> indexs) {
        List<Integer> bits = new ArrayList<>();

        for (int idx : indexs)
            bits.add(system[idx - 1].getQ());

        return bits;
    }

}
