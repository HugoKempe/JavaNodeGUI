package com.hugo.synth.circuits;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.FilterStateVariable;
import com.jsyn.unitgen.MultiplyAdd;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.RedNoise;
import com.jsyn.unitgen.UnitSource;
import com.jsyn.unitgen.WhiteNoise;

public class WindCircuit extends Circuit implements UnitSource {
    /* Declare units that will be part of the circuit. */
    private WhiteNoise myNoice;
    private FilterStateVariable myFilter;
    private RedNoise myLFO;
    private MultiplyAdd myScalar;

    /* Declare ports. */
    public UnitInputPort noiseAmp;
    public UnitInputPort modRate;
    public UnitInputPort modDepth;
    public UnitInputPort cutoff;
    public UnitInputPort resonance;
    public UnitInputPort amplitude;
    public UnitOutputPort output;

    public PassThrough passThrough;

    public WindCircuit() {
        add(myNoice = new WhiteNoise());
        add(myFilter = new FilterStateVariable());
        add(myLFO = new RedNoise());
        add(myScalar = new MultiplyAdd());
        add(passThrough = new PassThrough());

        //addPort(noiseAmp = passThrough.input, "NoiceAmp");
        addPort(noiseAmp = myNoice.amplitude, "NoiceAmp");
        addPort(modRate = myLFO.frequency, "ModRate");
        addPort(modDepth = myScalar.inputB, "ModDepth");
        addPort(cutoff = passThrough.input, "Cutoff");
        addPort(resonance = myFilter.resonance);
        addPort(amplitude = myFilter.amplitude);
        addPort(output = myFilter.output);

        System.out.println(cutoff.getValue());
        passThrough.output.connect(myFilter.frequency);
        System.out.println(myFilter.frequency.getMaximum());
        System.out.println(myFilter.frequency.getMinimum());
        myLFO.output.connect(myScalar.inputA);
        myScalar.output.connect(myFilter.frequency);
        myNoice.output.connect(myFilter.input);

        noiseAmp.setup(0.0, 0.3, 0.4);
        modRate.setup(0.0, 1.0, 10.0);
        modDepth.setup(0.0, 300.0, 1000.0);
        cutoff.setup(0.0, 600.0, 1000.0);
        resonance.setup(0.0, 0.066, 0.2);
        amplitude.setup(0.0, 0.9, 0.999);
    }


    @Override
    public UnitOutputPort getOutput() {
        return output;
    }
}
