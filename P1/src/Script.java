import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Script {

    private double x;
    private double y;
    private int escala;
    private double offsetX;
    private double offsetY;
    private boolean out;

    private double angle = 0;
    private BufferedImage script;

    public Script(int x, int y) {
        out = false;
        escala = 5;
        this.x = x;
        this.y = y;
        offsetX = 5 + Math.random() * 5;
        offsetY = 5 + Math.random() * 5;


        if(Math.random() > 0.5){
            offsetX = -offsetX;
        }

        if(Math.random() > 0.5){
            offsetY = -offsetY;
        }
        //double n = Math.sqrt(Math.pow(offsetX, 2) + Math.pow(offsetY, 2));
        //offsetX = offsetX/n;
        //offsetY = offsetY/n;
        angle = Math.random()*360;
        try
        {
            script = ImageIO.read( new File("facebook.png" ));
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
        }
    }

    public void drawScript(Graphics2D g, int w, int h) {

        double maxDistance = distance(0,0, w/2, h/2);
        double scale = distance(w/2, h/2, (int)x, (int)y)/maxDistance;

        try
        {
            script = ImageIO.read( new File("facebook.png" ));
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
        }

        Graphics2D g2 = (Graphics2D) g.create();
        double originalScale = h/script.getHeight();
        Image tmp = script.getScaledInstance((int)escala, (int)escala, Image.SCALE_FAST);

        BufferedImage bi = new BufferedImage(
                tmp.getWidth(null), tmp.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = bi.createGraphics();



        graphics2D.drawImage(tmp, 0, 0, null);
        graphics2D.dispose();

        g2.rotate(angle, x + tmp.getWidth(null) / 2, y + tmp.getHeight(null) / 2);
        System.out.println(x + " - " + y + "/" + w + " - " + h);
        int dx = (int)x - (bi.getWidth()/2);
        int dy = (int)y - (bi.getHeight()/2);



        g2.drawImage(bi, dx, dy, null);

        if(dx - bi.getHeight() >= w || dy - bi.getWidth()>= h || dx + bi.getHeight() <= 0 || dy + bi.getHeight() <=0){
            out = true;
        }


        g2.dispose();
    }

    public void rotateScript() {
        angle += 0.05;
    }

    public void move() {
        x += offsetX;
        y += offsetY;
        escala += 3;
    }


    @Override
    public String toString() {
        return "Pipe [x = " + x + ", y = " + y + ", rectangle = " + script + "]";
    }

    public BufferedImage getScript() {
        return script;
    }

    public double getX() {
        return x;
    }

    public double getY(){
        return y;
    }

    public BufferedImage getImg(){
        return script;
    }

    static double distance(int p1x, int p1y, int p2x, int p2y){
        return Math.sqrt(Math.pow(p2x - p1x, 2) + Math.pow(p2y - p1y, 2));
    }


    boolean isOut(){
        return out;
    }

}
