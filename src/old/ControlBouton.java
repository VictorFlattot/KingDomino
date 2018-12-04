package old;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlBouton implements ActionListener{
    Fenetre fen;


    public ControlBouton(Fenetre fen){
        this.fen = fen;
    }

    public void actionPerformed(ActionEvent e) {
        fen.idRoyaumeAPlacer = Integer.parseInt(e.getActionCommand());
        System.out.println("Controlbouton");
        fen.afficheRoyaumeJoueur();
}
}
