package com.hugo.utilz;

import com.jsyn.data.FloatSample;
import com.jsyn.util.SampleLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return img;
    }

    public static FloatSample loadSample(String fileName) {
        FloatSample sample = null;
        SampleLoader.setJavaSoundPreferred(false);
        try {
            sample = SampleLoader.loadFloatSample(new File(fileName));
            System.out.println("Sample has: channels  = " + sample.getChannelsPerFrame());
            System.out.println("            frames    = " + sample.getNumFrames());
            System.out.println("            rate      = " + sample.getFrameRate());
            System.out.println("            loopStart = " + sample.getSustainBegin());
            System.out.println("            loopEnd   = " + sample.getSustainEnd());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sample;
    }
}
