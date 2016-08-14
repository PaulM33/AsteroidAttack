
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 *
 * Program Name: SoundEffect.java
 *
 * @author Daniel Krauskopf Purpose: The player for the Asteroid Attack game.
 * Adapted from:
 * https://www.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
 * Date 14-Aug-2016
 */
public enum SoundEffect {
    LASER("Sounds/Laser.wav"),
    NUKE("Sounds/Nuke.wav");

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

    // Play or Re-play the sound effect from the beginning, by rewinding.
    public void play() {
        if (volume != Volume.MUTE) {
            if (clip.isRunning()) {
                //clip.stop();   // Stop the player if it is still running
            } else {
                clip.setFramePosition(0); // rewind to the beginning
                clip.start();     // Start playing
            }
        }
    } // play ();

    // Optional static method to pre-load all the sound files.
    static void init() {
        values(); // calls the constructor for all the elements
    } // init ();
} // SoundEffect;
