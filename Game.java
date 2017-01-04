/**
 * Game.java
 * @author: Ruzan Sasuri            rps7183@g.rit.edu
 * @author: Akash Venkatachalam     av2833@g.rit.edu
 * @author: Ghodratollah Aalipour   ga5481@g.rit.edu
 *
 * Creates 2 objects of Ocean and Player, validates the Ocean, fills the Board and plays the game between the two
 * player objects.
 *
 * Id: $ Game.java v1.0, 2016/11/07$
 * Revision: First Revision
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game
{
    static JRadioButton []jr;
    static int current;
    static final int MAX_ROW = 10, MAX_COLUMN = 10;
    //129.21.103.106
    private static final String IPADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    static final String SHIPS[] = {"Destroyer","Cruiser","Battleship","Carrier"};

    public void createUI(Container c)throws IOException
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Opponents IP: ");
        String ip = s.next();
        Pattern p = Pattern.compile(IPADDRESS_PATTERN);
        Matcher m = p.matcher(ip);
        if(!m.find() && !ip.equals("localhost"))
        {
            System.err.print("This is not an IP address. You shall not pass.");
            System.exit(1);
        }
        System.out.println("Please enter your name.");
        String pc = s.next();
        System.out.println("Please enter your opponents name.");
        String o = s.next();
        Ocean aOcean = new Ocean(MAX_ROW,MAX_COLUMN);
        Controller c1 = new Controller(ip, aOcean, pc, o);
        JOptionPane.showMessageDialog(null, "WELCOME TO BATTLESHIP");
        c.setLayout(null);
        JButton sb = new JButton("Connect");
        //JLabel l[] = new JLabel[5];
        /*l[0] = new JLabel("Ships Destroyed:");
        l[1]= new JLabel("Destroyer");
        l[2]= new JLabel("Cruiser");
        l[3]= new JLabel("Battleship");
        l[4]= new JLabel("Carrier");*/
        JLabel player = new JLabel("Player "+ pc);
        player.setBounds(280, 20, 200, 15);
        JLabel turn = new JLabel();
        turn.setBounds(280, 50, 200, 15);
        c.add(player);
        c.add(turn);
        OceanScreen oc = new OceanScreen(c1, turn);
        FleetScreen bb = new FleetScreen(c1, sb);
        bb.setBounds(20,20,250,250);
        oc.setBounds(490,20,250,250);
        /*for(int i = 0 ; i < l.length; i ++)
        {
            l[i].setBounds(280, 270 + i * 20 , 200, 15);
            l[i].setEnabled(false);
            c.add(l[i]);
        }*/
        //l[0].setEnabled(true);
        //oc.wake(false);
        c.add(bb);
        c.add(oc);
        JRadioButton r1 = new JRadioButton("Destroyer(2)");
        JRadioButton r2 = new JRadioButton("Cruiser(3)");
        JRadioButton r3 = new JRadioButton("Battelship(4)");
        JRadioButton r4 = new JRadioButton("Carrier(5)");
        TurnAndVictory tv = new TurnAndVictory(c1,oc);
        JButton res = new JButton("Disconnect");
        JProgressBar pb = new JProgressBar(0,1000);
        sb.setBounds(280, 140,200,30);
        pb.setBounds(280,180,200,10);
        res.setBounds(280, 200,200,30);
        sb.setEnabled(false);
        res.setEnabled(false);
        jr = new JRadioButton[4];
        jr[0] = r1;
        jr[1] = r2;
        jr[2] = r3;
        jr[3] = r4;
        r1.setSelected(true);
        //r1.setEnabled(false);
        current = 2;
        r1.setBounds(40,300,100,30);
        r2.setBounds(40,330,100,30);
        r3.setBounds(40,360,100,30);
        r4.setBounds(40,390,100,30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        bg.add(r4);
        c.add(r1);
        c.add(r2);
        c.add(r3);
        c.add(r4);
        c.add(sb);
        c.add(pb);
        c.add(res);
        sb.addActionListener(new ServerConnection(bb,c1,sb,res,pb,oc,tv));
        r1.addActionListener(new ShipChanger(2,bb,c1,sb));
        r2.addActionListener(new ShipChanger(3,bb,c1,sb));
        r3.addActionListener(new ShipChanger(4,bb,c1,sb));
        r4.addActionListener(new ShipChanger(5,bb,c1,sb));
        res.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                TurnAndVictory.vic = 'F';
            }
        });
    }
}
