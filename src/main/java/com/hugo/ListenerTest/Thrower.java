package com.hugo.ListenerTest;

import java.util.ArrayList;
import java.util.List;

public class Thrower {
    List<ThrowListener> listeners = new ArrayList<>();
    public void addThrowListener(ThrowListener toAdd) {
        listeners.add(toAdd);
    }

    public void Throw() {
        for (ThrowListener hl : listeners) {
            hl.Catch();
            System.out.println("Something thrown");
        }
    }
}
