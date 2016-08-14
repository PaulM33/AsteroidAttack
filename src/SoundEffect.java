
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 *
 * Program Name: SoundEffect.java
 * @author Paul Magbor, Daniel Krauskopf 
 * Purpose: The sounds for the Asteroid Attack game.
 * Adapted from:
 * https://www.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
 * Date 14-Aug-2016
 */
public enum SoundEffect {
    LASER("Sounds/Laser.wav"),
    NUKE("Sounds/Nuke.wav"),
    CHERRING("Sounds/Cheering.wav"),
    BOO("Sounds/Boo.wav");

    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH;
    } // Volume;

    public Volume volume = Volume.LOW;
    public Clip clip;

    SoundEffect(String fileName) {
        try {
            // Use URL (instead of File) to read from disk and JAR.
            URL url = this.getClass().getClassLoader().getResource(fileName);
            // Set up an audio input stream piped from the sound file.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            // Get a clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // SoundEffect(String);

    /**
     * Plays the clip. If the clip is still playing it does not start.
     */
    public void play() {
        if (volume != Volume.MUTE && !clip.isRunning()) {
            // Set the clip tot he start and play the clip.
            clip.setFramePosition(0);
            clip.start();
        }
    } // play ();

    /**
     * Load all of the sounds, read from disk.
     */
    static void init() {
        values(); // calls the constructor for all the elements
    } // init ();
} // SoundEffect;
