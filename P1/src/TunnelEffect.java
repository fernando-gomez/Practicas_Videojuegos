import javax.swing.*;
import java.awt.*;

public class TunnelEffect extends JPanel {

    private Scripts game;

    public TunnelEffect(Scripts engine) {
        game = engine;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.drawScripts((Graphics2D) g);
    }


}