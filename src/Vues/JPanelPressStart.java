package Vues;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class JPanelPressStart extends JPanel {

    private Image image;


    JPanelPressStart(Image image) throws IOException {
        super();
        this.image = image ;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),null);
    }
}
