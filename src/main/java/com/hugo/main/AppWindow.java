package com.hugo.main;

import com.hugo.synth.SynthPanel;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import static com.hugo.main.App.TITLE;

public class AppWindow extends JFrame {

    public AppWindow(AppPanel appPanel) {
        setTitle(System.getProperty("os.name") + " (" + TITLE + ") " + appPanel.getWidth() + " x " + appPanel.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(appPanel);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                appPanel.getApp().windowGainedFocus();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                appPanel.getApp().windowFocusLost();
            }
        });
    }

    public AppWindow(SynthPanel synthPanel) {
        setTitle(System.getProperty("os.name") + " (" + TITLE + ") " + synthPanel.getWidth() + " x " + synthPanel.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(synthPanel);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                synthPanel.getApp().windowGainedFocus();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                synthPanel.getApp().windowFocusLost();
            }
        });
    }
}
