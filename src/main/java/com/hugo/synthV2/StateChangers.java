package com.hugo.synthV2;


import com.hugo.ListenerTest.ThrowListener;

import java.util.ArrayList;
import java.util.List;

public class StateChangers {
    List<myStateListener> listeners = new ArrayList<>();
    public void addThrowListener(myStateListener toAdd) {
        listeners.add(toAdd);
    }

    public void Throw(double value) {
        for (myStateListener hl : listeners) {
            hl.stateChanged(value);
            System.out.println("Something thrown");
        }
    }
}
