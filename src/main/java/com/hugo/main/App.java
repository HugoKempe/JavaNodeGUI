package com.hugo.main;

import com.hugo.appstatesTODO.SynthState;
import com.hugo.appstatesTODO.SynthStateV2;
import com.hugo.appstatesTODO.Testing;
import com.hugo.synth.SynthPanel;

import java.awt.*;

public class App implements Runnable {

    private AppWindow window;
    private AppPanel appPanel;
    private SynthPanel synthPanel;
    private Thread mainThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Testing testing;
    private SynthState synthState;
    private SynthStateV2 synthStateV2;


    public final static float SCALE = 2f;
    public final static int APP_WIDTH = (int)(640 * SCALE);
    public final static int APP_HEIGHT = (int)(480 * SCALE);

    public final static float SYNTH_SCALE = 0.80f;
    public final static int SYNTH_WIDTH = (int) (APP_WIDTH * SYNTH_SCALE);
    public final static int SYNTH_HEIGHT = (int) (APP_HEIGHT * SYNTH_SCALE);

    public final static String TITLE = "untitled";

    public App() {
        initClasses();

        appPanel = new AppPanel(this);
        synthPanel = new SynthPanel(this);
        window = new AppWindow(appPanel);
        appPanel.requestFocus();

        startAppLoop();
    }

    private void initClasses() {
        //testing = new Testing(this);
        //synthState = new SynthState(this);
        synthStateV2 = new SynthStateV2(this);
    }

    private void startAppLoop() {
        mainThread = new Thread(this);
        mainThread.start();
    }

    public void update() {
        synthStateV2.update();
    }

    public void render(Graphics g) {
        synthStateV2.draw(g);
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
                //synthPanel.repaint();
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

    public SynthStateV2 getSynthState() {
        return synthStateV2;
    }
}
