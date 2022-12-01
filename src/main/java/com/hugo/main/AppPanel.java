package com.hugo.main;

import com.hugo.inputs.KeyboardInputs;
import com.hugo.inputs.MouseInputs;
import com.hugo.utilz.Constants;

import javax.swing.*;

import java.awt.*;

import static com.hugo.main.App.APP_WIDTH;
import static com.hugo.main.App.APP_HEIGHT;

public class AppPanel extends JPanel {

    private MouseInputs mouseInputs;
    private App app;
    private Dimension size;
    public AppPanel(App app) {
        super(true); // set double buffered (anti-aliasing);

        mouseInputs = new MouseInputs(this);
        this.app = app;

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        addMouseWheelListener(mouseInputs);


    }

    private void setPanelSize() {
        size = new Dimension(APP_WIDTH, APP_HEIGHT);
        setPreferredSize(size);
        System.out.println("Size: (" + size.width + " x " + size.height + ")");
    }

    public void updateApp() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Constants.UI.Colors.DARK);
        Graphics2D graphics2D = (Graphics2D) g;
        //Set  anti-alias!
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Set anti-alias for text
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        app.render(g);
    }

    public App getApp() {
        return app;
    }

    public int getWidth() {
        return (int)size.getWidth();
    }

    public int getHeight() {
        return (int)size.getHeight();
    }
}
