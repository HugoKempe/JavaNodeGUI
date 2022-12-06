package com.hugo.ListenerTest;

public class Test {
    public static void main(String[] args) {
        Catcher catcher = new Catcher();
        Thrower thrower = new Thrower();

        thrower.addThrowListener(catcher);

        thrower.Throw();
    }
}
