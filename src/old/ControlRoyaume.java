package old;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlRoyaume implements ActionListener {
    Fenetre fen;
    public ControlRoyaume(Fenetre fen){
        this.fen = fen;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("passe bien par la");
        fen.ajouteDomino(fen.idRoyaumeAPlacer,Integer.parseInt(e.getActionCommand()));
    }
}
