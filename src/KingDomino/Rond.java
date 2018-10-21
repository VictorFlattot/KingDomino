package KingDomino;

import javax.swing.*;
import java.awt.*;

public class Rond extends JPanel {

    public void paint(Graphics g) {
        super.paint(g);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(0,0,80,80);
        g.setColor(Color.BLUE);
        g.fillOval(150,50,80,80);
        g.setColor(c);
    }

}

