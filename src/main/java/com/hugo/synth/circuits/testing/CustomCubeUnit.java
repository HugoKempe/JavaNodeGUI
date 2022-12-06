package com.hugo.synth.circuits.testing;

import com.jsyn.unitgen.UnitFilter;

public class CustomCubeUnit extends UnitFilter {
    @Override
    public void generate(int start, int limit) {
        double[] inputs = input.getValues();
        double[] outputs = output.getValues();

        for (int i = start; i < limit; i++) {
            double x = inputs[i];

            outputs[i] = x * x * x;
        }
    }
}
