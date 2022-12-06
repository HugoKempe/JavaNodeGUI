package com.hugo;

import org.junit.jupiter.api.Test;

import java.awt.*;

public class ListAllFonts {

    @Test
    public void listAvailableFonts() {
        String fonts[] =
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for (int i = 0; i < fonts.length; i++) {
            System.out.println(fonts[i]);
        }
    }
}
