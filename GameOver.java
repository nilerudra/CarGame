import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;

class End_Musicplayer extends Thread
 {
    Clip clip;
    AudioInputStream audio;
    public void run()
    {
        Scanner sc = new Scanner(System.in);

        File file = new File("game-over.wav");
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

public class GameOver  extends JFrame implements ActionListener
{
    static JLabel lb = new JLabel();
    static JLabel lb1 = new JLabel();

    private Timer timer;
    private Color[] colors = { Color.BLACK, Color.WHITE };
    private int index = 0;
    
    public GameOver()
    {
        setSize(500,700);
        setLocation(1100,300);
        setLayout(null);
        setUndecorated(true);
        getContentPane().setBackground(Color.BLACK);
        setOpacity(0.5f);
        setResizable(false);
        setVisible(true);

        lb.setText("G");
        lb.setBounds(90, 200, 300, 70);
        lb.setHorizontalAlignment(SwingConstants.CENTER);
        lb1.setHorizontalAlignment(SwingConstants.CENTER);
        lb1.setBounds(95, 280, 300, 70);
        lb.setOpaque(false);
        lb.setForeground(Color.WHITE);
        lb1.setForeground(Color.WHITE);
        add(lb);
        add(lb1);
        
        lb.setFont(new Font("Arial", Font.BOLD, 70));
        lb1.setFont(new Font("Arial", Font.BOLD, 70));
        
        try
        {
            Thread.sleep(700);
        }
        catch(Exception e){}
        lb.setText(lb.getText() + "A");
        try
        {
            Thread.sleep(700);
        }
        catch(Exception e){}
        lb.setText(lb.getText() + "M");
        try
        {
            Thread.sleep(700);
        }
        catch(Exception e){}
        lb.setText(lb.getText() + "E");
        try
        {
            Thread.sleep(700);
        }
        catch(Exception e){}

        lb1.setText("O");
        try
        {
            Thread.sleep(700);
        }
        catch(Exception e){}
        lb1.setText(lb1.getText() + "V");
        try
        {
            Thread.sleep(700);
        }
        catch(Exception e){}
        lb1.setText(lb1.getText() + "E");
        try
        {
            Thread.sleep(700);
        }
        catch(Exception e){}
        lb1.setText(lb1.getText() + "R");
        try
        {
            Thread.sleep(700);
        }
        catch(Exception e){}

        timer = new Timer(700, this);
        timer.start();
        End_Musicplayer em = new End_Musicplayer();
        em.start();
    }
    public static void main(String[] args) {
        new GameOver();
    }
   
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        lb.setForeground(colors[index]);
        lb1.setForeground(colors[index]);
        index = (index + 1) % colors.length;
    }
}
