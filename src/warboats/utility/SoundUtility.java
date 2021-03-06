/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warboats.utility;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author clo006
 */
public final class SoundUtility {

    private static File soundFile;
    private static AudioInputStream audioIn;
    private static Clip clip;

    public static void startup() {
        soundFile = new File("src/resources/sounds/startup.wav");
        playAudio();
    }

    public static void shutdown() {
        soundFile = new File("src/resources/sounds/shutdown.wav");
        playAudio();
    }

    public static void restart() {
        soundFile = new File("src/resources/sounds/restart.wav");
        playAudio();
    }

    public static void waitTurn() {
        soundFile = new File("src/resources/sounds/waitTurn.wav");
        playAudio();
    }

    public static void logoClick() {
        soundFile = new File("src/resources/sounds/logoClick.wav");
        playAudio();
    }

    public static void win() {
        soundFile = new File("src/resources/sounds/win.wav");
        playAudio();
    }

    public static void loss() {
        soundFile = new File("src/resources/sounds/loss.wav");
        playAudio();
    }

    public static void hit() {
        soundFile = new File("src/resources/sounds/hit.wav");
        playAudio();
    }

    public static void miss() {
        soundFile = new File("src/resources/sounds/miss.wav");
        playAudio();
    }

    public static void sunk() {
        soundFile = new File("src/resources/sounds/sunk.wav");
        playAudio();
    }

    public static void playerNotReady() {
        soundFile = new File("src/resources/sounds/playerNotReady.wav");
        playAudio();
    }

    public static void beginGame() {
        soundFile = new File("src/resources/sounds/beginGame.wav");
        playAudio();
    }

    private static void playAudio() {
        try {
            audioIn = AudioSystem.getAudioInputStream(
                    soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            audioIn.close();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(SoundUtility.class.getName()).log(Level.SEVERE,
                                                               null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SoundUtility.class.getName()).log(Level.SEVERE,
                                                               null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(SoundUtility.class.getName()).log(Level.SEVERE,
                                                               null, ex);
        }
    }

}
