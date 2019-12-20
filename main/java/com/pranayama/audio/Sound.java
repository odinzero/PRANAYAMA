package com.pranayama.audio;

import com.pranayama.util.utils;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JPanel;

// https://www.developer.com/java/other/article.php/3071021/java-sound-audio-file-conversion.htm
// https://stackoverflow.com/questions/2579030/saving-part-of-an-audio-file-java
public class Sound implements AutoCloseable, Runnable {

    private boolean released = false;
    private AudioInputStream stream = null;
    private Clip clip = null;
    private FloatControl volumeControl = null;
    private boolean playing = false;
    Thread sndThread;
    
    JComponent component; // utils.buttonsWindow
    JPanel panel;

    public Sound(String pathName) {
        try {
            File f = new File(pathName);
            stream = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.addLineListener(new Listener());
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            released = true; 
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
            released = false;

            close();
        }
    }

    public Sound(File f) {
        try {
            stream = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.addLineListener(new Listener());
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            released = true;
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
            released = false;

            close();
        }
    }

    public void setComponent(JComponent component) {
        this.component = component;
    }

    public JComponent getComponent() {
        return component;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getPanel() {
        return panel;
    }

    // true если звук успешно загружен, false если произошла ошибка
    public boolean isReleased() {
        return released;
    }

    // проигрывается ли звук в данный момент
    public boolean isPlaying() {
        return playing;
    }

    // Запуск
    /*
	  breakOld определяет поведение, если звук уже играется
	  Если breakOld==true, о звук будет прерван и запущен заново
	  Иначе ничего не произойдёт
     */
    public void play(boolean breakOld) {
        if (released) {
            if (breakOld) {
                clip.stop();
                clip.setFramePosition(0);
                clip.start();
                playing = true;
            } else if (!isPlaying()) {
                clip.setFramePosition(0);
                clip.start();
                playing = true;
            }
        }
    }

    // То же самое, что и play(true)
    public void play() {
        play(true);
    }

    // Останавливает воспроизведение
    public void stop() {
        System.out.println("Sound b:" + playing);
//        if (playing) {
        clip.stop();
        stopThread();

        System.out.println("Sound a:" + playing);

        // Mute the clip
//            BooleanControl muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
//            if (muteControl != null) {
//                muteControl.setValue(true); // True to mute the line, false to unmute
//            }
//            clip.loop(0); // Stops the sound after it runs through its buffer
//            clip.flush();
//        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
        }

        if (stream != null) {
            try {
                stream.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    // Установка громкости
    /*
	  x долже быть в пределах от 0 до 1 (от самого тихого к самому громкому)
     */
    public void setVolume(float x) {
        if (x < 0) {
            x = 0;
        }
        if (x > 1) {
            x = 1;
        }
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        volumeControl.setValue((max - min) * x + min);
    }

    // Возвращает текущую громкость (число от 0 до 1)
    public float getVolume() {
        float v = volumeControl.getValue();
        float min = volumeControl.getMinimum();
        float max = volumeControl.getMaximum();
        return (v - min) / (max - min);
    }

    // Дожидается окончания проигрывания звука
    public void join() {
        if (!released) {
            return;
        }
        startThread();
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    @Override
    public void run() {
        if (!released) {
            return;
        }
        try {
            synchronized (clip) {
                try {
                    while (isPlaying()) {
                        System.out.println("playing run 1 :" + playing);
                        if(component != null) {
                          ((utils.buttonsWindow)component).setDisabledState(false);
                           panel.repaint();
                        }  
                        clip.wait();
                    }
                    if(component != null) {
                        ((utils.buttonsWindow)component).setDisabledState(true); 
                        panel.repaint();
                    }
                    System.out.println("playing run 2 :" + playing);
                } catch (InterruptedException exc) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

       // stopThread();
    }

    public void startThread() {
        if (sndThread == null) {
            sndThread = new Thread(this);
            playing = true;
            sndThread.start();
            synchronized (sndThread) {
                sndThread.notify();
            }
        }
    }

    public void stopThread() {
        if (sndThread != null) {
            if (sndThread.isAlive()) {
                playing = false;
                // Notify mainthread
                synchronized (sndThread) {
                    sndThread.notify();
                }
            }
        }
    }

    // Статический метод, для удобства
    public Sound playSound(String path) {
        File f = new File(path);
        Sound snd = new Sound(f);
        snd.play();
        return snd;
    }

    private class Listener implements LineListener {

        public void update(LineEvent ev) {
            if (ev.getType() == LineEvent.Type.STOP) {
                playing = false;
                System.out.println("listener:" + playing);
                synchronized (clip) {
                    clip.notify();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Sound s = new Sound(new File("./src/main/recources/sound/file_example_WAV_1MG.wav"));
        s.play();
        s.startThread();
//        s.stopThread();
        
        // s.playSound("./src/main/recources/sound/file_example_WAV_1MG.wav").join(); //ok
    }
}
