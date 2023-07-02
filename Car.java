import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

class Musicplayer extends Thread 
{
    String music;
    public Musicplayer(String mcName)
    {
        music = mcName;
    }

    Clip clip;
    AudioInputStream audio;
    public void run()
    {
        Scanner sc = new Scanner(System.in);

        File file = new File(music);
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

class Road extends Thread
{
    public void run()
    {
        int x,y;
        while(Car.c)
        {
            for(int i=6;i>=0;i--)
            {
                x = Car.t[i].getX();
                y = Car.t[i].getY();

                if(y >= 650)
                {                    
                    Car.t[i].setLocation(250,-50);
                    try{
                        Thread.sleep(5);
                    }
                    catch(Exception e){}
                }
                else
                {
                    Car.t[i].setLocation(x,y+20);
                    try{
                        Thread.sleep(10);
                    }
                    catch(Exception e){}
                }
                if(Car.c==false)
                    break;
            }
        }    
    }
}

class Crash extends Thread
{
    public void run()
    {
        while(Car.c)
        {
            for(int i=0;i<3;i++)
            {
                int x = Car.ecar[i].getX();
                int y = Car.ecar[i].getY();
                int px = Car.pcar.getX();
                int py = Car.pcar.getY();

                if (y+85 >= py && y <= py &&  x+50 >= px && x <= px+20)
                {
                    Musicplayer bms = new Musicplayer("blast_music.wav");
                    bms.start();
                    
                    Car.c = false;
                    Car.ecar[i].setIcon(new ImageIcon(new ImageIcon("blast.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                    Car.pcar.setIcon(new ImageIcon(new ImageIcon("blast.png").getImage().getScaledInstance(70, 100, Image.SCALE_DEFAULT)));
                    Car.pcar.setLocation(Car.pcar.getX(),Car.pcar.getY()-50);
                }
                if(Car.c==false)
                {
                    Car.ms.stop_ms();  
                    GameOver go = new GameOver();               
                    break;
                }
            }
        }
    }
}

class EnemyCars extends Thread
{
    public void run()
    { 
        while(Car.c)
        {
            for(int j=0;j<4;j++)
            {

                int cx = Car.ecar[j].getX();
                int cy = Car.ecar[j].getY();
               

                Car.ecar[j].setLocation(cx,cy+30);
                try{
                    Thread.sleep(50);
                }
                catch(Exception e){}
                if(Car.ecar[j].getY()>700)
                {
                    Car.ecar[j].setLocation(Car.arX[j], Car.arY[j]);
                }
                if(Car.c==false)
                    break;
            }
        }
    }
}

public class Car extends JFrame implements KeyListener 
{
    Start s = new Start();
    static JTextField t[] = new JTextField[10];
    JTextField gt1,gt2;
    static JLabel pcar;
    static JLabel ecar[]= new JLabel[4];
    static int arX[] = {360,250,150,50};
    static int arY[] ={-100,-300,-400,-200};
    static boolean c = true; 
    static Musicplayer ms = new Musicplayer("bgmusic.wav");

    public Car() throws IOException,UnsupportedAudioFileException, LineUnavailableException, IllegalArgumentException
    {
        setSize(500,700);
        setLocation(1100,300);
        setUndecorated(true);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setResizable(false);

        gt1 = new JTextField();
        gt2 = new JTextField();
        gt1.setBackground(new Color(130, 205, 71));
        gt2.setBackground(new Color(130, 205, 71));
        gt1.setBounds(0, 0, 50, 700);
        gt2.setBounds(450, 0, 50, 700);
        gt1.setEditable(false);
        gt2.setEditable(false);

        pcar = new JLabel();
        ImageIcon im = new ImageIcon(new ImageIcon("player_car.png").getImage().getScaledInstance(70, 130, Image.SCALE_DEFAULT));
        pcar.setIcon(im);
        Dimension size = pcar.getPreferredSize();
        pcar.setBounds(220, 300, size.width, size.height);

        ecar[0] = new JLabel();
        ImageIcon im1 = new ImageIcon(new ImageIcon("bot_car2.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        ecar[0].setIcon(im1);
        Dimension size1 = ecar[0].getPreferredSize();
        ecar[0].setBounds(360, 200, size1.width, size1.height);

        ecar[1] = new JLabel();
        ImageIcon im2 = new ImageIcon(new ImageIcon("bot_car2.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        ecar[1].setIcon(im2);
        Dimension size2 = ecar[1].getPreferredSize();
        ecar[1].setBounds(50, 50, size2.width, size2.height);

        ecar[2] = new JLabel();
        ImageIcon im3 = new ImageIcon(new ImageIcon("bot_car4.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        ecar[2].setIcon(im3);
        Dimension size3 = ecar[2].getPreferredSize();
        ecar[2].setBounds(250, 330, size3.width, size3.height);

        ecar[3] = new JLabel();
        ImageIcon im4 = new ImageIcon(new ImageIcon("bot_car2.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        ecar[3].setIcon(im4);
        Dimension size4 = ecar[3].getPreferredSize();
        ecar[3].setBounds(150, 330, size4.width, size4.height);

        gt1.addKeyListener(this);

        add(gt1);
        add(gt2);
        add(pcar);
        add(ecar[0]);
        add(ecar[1]);
        add(ecar[2]);
        add(ecar[3]);

        textField();

        EnemyCars e = new EnemyCars();
        e.start();
        setVisible(true);
        s.dispose();
        new Crash().start();
        ms.start();
    }

    public void textField()
    {
        int x=250;
        int y=0;
        for(int i=0;i<7;i++)
        {
            t[i] = new JTextField();
            t[i].setSize(10, 80);
            t[i].setLocation(x, y);
            t[i].setEditable(false);
            y += 100;
            add(t[i]);
        }
        Road r = new Road();
        r.start();
        new EnemyCars().start();
    }
    
    public void keyPressed(KeyEvent e)
    {
        if(Car.c)
        { 
            if(e.getKeyCode()==KeyEvent.VK_LEFT)
            {
                int x = pcar.getX();
                int y = pcar.getY();
                if(x>=50 )
                    pcar.setLocation(x-10,y);
            }
            else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            {
                int x = pcar.getX();
                int y = pcar.getY();
                if(x<=380)
                    pcar.setLocation(x+10,y);
            }
        }
    }

    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

   public static void main(String[] ar) throws IOException,UnsupportedAudioFileException, LineUnavailableException
   {
        new Car();   
   }
}