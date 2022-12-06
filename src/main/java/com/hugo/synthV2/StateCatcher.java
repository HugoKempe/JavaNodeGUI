package com.hugo.synthV2;

public class StateCatcher implements myStateListener{
    @Override
    public void stateChanged(double value) {
        System.out.println(value);
    }
}
