package Matrix;

import java.awt.Dimension;
import java.io.File;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import resources.SoundEffects;

public class Main {

	public static SoundEffects sound = new SoundEffects();
	public static void main(String[] args) throws Exception {
		 sound.Run("Matrix.wav");
		 SoundEffects.clip1.loop(Clip.LOOP_CONTINUOUSLY);
        // Swing GUIs should be created and altered on the EDT.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new RouteCipherGUI();
            }
        });
    }
	
}
