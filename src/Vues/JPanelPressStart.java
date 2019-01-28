package Vues;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The type J panel press start.
 */
public class JPanelPressStart extends JPanel {

    private Image image;


    /**
     * Instantiates a new J panel press start.
     *
     * @param image the image
     * @throws IOException the io exception
     */
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
