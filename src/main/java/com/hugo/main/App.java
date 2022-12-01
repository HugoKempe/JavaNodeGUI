package com.hugo.main;

import com.hugo.appstatesTODO.Testing;

import java.awt.*;

public class App implements Runnable {

    private AppWindow window;
    private AppPanel appPanel;
    private Thread mainThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Testing testing;


    public final static float SCALE = 2f;
    public final static int APP_WIDTH = (int)(640 * SCALE);
    public final static int APP_HEIGHT = (int)(480 * SCALE);
    public final static String TITLE = "untitled";

    public App() {
        initClasses();

        appPanel = new AppPanel(this);
        window = new AppWindow(appPanel);
        appPanel.requestFocus();

        startAppLoop();
    }

    private void initClasses() {
        testing = new Testing(this);
    }

    private void startAppLoop() {
        mainThread = new Thread(this);
        mainThread.start();
    }

    public void update() {
        //testing.update();
    }

    public void render(Graphics g) {
        //testing.draw(g);
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                appPanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                //System.out.println("FPS: " + frames + " | " + "UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost() {

    }

    public void windowGainedFocus() {

    }

    public Testing getTesting() {
        return testing;
    }
}
