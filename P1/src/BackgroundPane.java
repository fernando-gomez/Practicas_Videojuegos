import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

class BackgroundPane extends JPanel {
    private BufferedImage bg;
    private int yOffset = 2;
    private int yDelta = 4;
    private int xOffset = 2;
    private int xDelta = 4;
    private int newW;
    private int newH;

    public BackgroundPane() {
        try {

            BufferedImage img = ImageIO.read(new File("imagen.png"));
            newW = img.getWidth()/4;
            newH = img.getHeight()/4;
            Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);

            bg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = bg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Timer timer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yOffset += yDelta;
                xOffset += xDelta;
                if (yOffset >= getHeight()-bg.getHeight()) {
                    yDelta = -4;
                }
                if (xOffset >= getWidth()-bg.getWidth()) {
                    xDelta = -4;
                }
                if (xOffset <= 0) {
                    xDelta = 4;
                }
                if (yOffset <= 0) {
                    yDelta = 4;
                }
                repaint();
            }
        });
        timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(bg.getWidth(),bg.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int xPos = xOffset;
        int yPos = yOffset;

        g2d.drawImage(bg, xPos, yPos, this);

        g2d.dispose();
    }
}