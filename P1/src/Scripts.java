import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Scripts {
    JFrame f;
    final int NUM;

    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                Scripts game = new Scripts();

                game.play();

            }
        });
    }

    public void play() {

        TunnelEffect board = new TunnelEffect(this);

        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(board);
        f.pack();
        f.setSize(400,400);
        f.setVisible(true);

        Timer timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateScripts();
                board.repaint();
            }
        });
        timer.start();
    }

    public Scripts() {
        NUM = 10;
        script = new Script(200, 200);
        scripts = new ArrayList<Script>();
        for(int i=0; i<NUM; i++){
            scripts.add(script);
            script = new Script(200, 200);
        }
    }

    protected void updateScripts() {

        rotateScripts();
        moveScripts();
        if(scripts.size()< NUM){
            for(int i = scripts.size(); i<NUM; i++){
                scripts.add(new Script(f.getWidth()/2, f.getHeight()/2));
            }
        }

    }

    public void drawScripts(Graphics2D g) {
        System.out.println(f.getWidth());
        for (int i = 0; i< scripts.size(); i++) {
            scripts.get(i).drawScript(g, f.getWidth(), f.getHeight());
            if(scripts.get(i).isOut()){
                scripts.remove(i);
                i--;
            }
        }
    }

    protected void rotateScripts() {
        for (Script scr : scripts) {
            scr.rotateScript();
        }
    }

    protected void moveScripts() {
        for (Script scr : scripts) {
            scr.move();
        }
    }

    private Script script;
    private ArrayList<Script> scripts;
}