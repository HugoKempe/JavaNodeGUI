package com.hugo.ListenerTest;

public class Catcher implements ThrowListener{
    @Override
    public void Catch() {
        System.out.println("I caught something!!");
    }
}
