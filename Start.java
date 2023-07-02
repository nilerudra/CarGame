import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;


class Start_Musicplayer extends Thread
 {
    Clip clip;
    AudioInputStream audio;
    public void run()
    {
        Scanner sc = new Scanner(System.in);

        File file = new File("321.wav");
        try
        {
            audio = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            String res = sc.next();
        }
        catch(Exception e){}
    }
    public void stop_ms()
    {
        clip.stop();
    }
 }
 

public class Start extends JFrame
{
    JLabel lb;
    Start_Musicplayer ms = new Start_Musicplayer();

    public Start() throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        ms.start();
        setSize(500,700);
        setLocation(1100,300);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
        
        try
        {
            Thread.sleep(300);
        }
        catch(Exception e){}

        lb = new JLabel(new ImageIcon("4.png"));
        add(lb);
        setVisible(true);
       
        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e){}

        lb.setIcon(new ImageIcon("3.png"));

        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e){}

        lb.setIcon(new ImageIcon("2.png"));

        try
        {
            Thread.sleep(1500);
        }
        catch(Exception e){}

        lb.setIcon(new ImageIcon("1.png"));
        try
        {
            Thread.sleep(1700);
        }
        catch(Exception e){}
        ms.stop_ms();
    }

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException 
    {
        new Start();
    }
}