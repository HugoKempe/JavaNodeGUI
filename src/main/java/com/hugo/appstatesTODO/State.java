package com.hugo.appstatesTODO;

import com.hugo.main.App;

import java.awt.event.MouseEvent;

public class State {

    protected App app;
    public State(App app) {
        this.app = app;

    }

    public App getApp() {
        return app;
    }
}
