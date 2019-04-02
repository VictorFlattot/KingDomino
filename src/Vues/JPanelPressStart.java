package Vues;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The type J panel press start.
 */
public class JPanelPressStart extends JPanel {

    /**
     * Le paramètre Image pour afficher ladite image
     *
     * @see Image
     */
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


    /**
     * Permet d'afficher les composants graphique sur l'affichage
     *
     * @param g Le graphics de l'affichage
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),null);
    }
}
