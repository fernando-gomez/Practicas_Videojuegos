import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

/**
 * @author Erik Duijs
 */
public class StarField {

    int STARS;
    int SCREEN_W;
    int SCREEN_H;
    int X_SPR;
    int Y_SPR;

    float[] starX;
    float[] starY;
    float[] starZ;

    int x,y,z,c,s;

    public StarField(int stars, Component c) {
        STARS = stars;
        starX = new float[STARS];
        starY = new float[STARS];
        starZ = new float[STARS];
        SCREEN_W = c.getWidth();
        SCREEN_H = c.getHeight();
        X_SPR = SCREEN_W * 25;
        Y_SPR = SCREEN_H * 25;
        for (int i = 0; i < STARS; i++) {
            starX[i] = (int)(X_SPR * Math.random()) - X_SPR / 2;
            starY[i] = (int)(Y_SPR * Math.random()) - Y_SPR / 2;
            starZ[i] = (int)(1000 * Math.random()) + 1;
        }
    }

    public void update(Graphics g, float speed) {
        for (int i = 0; i < STARS; i++) {
            x = (int)(SCREEN_W / 2 + (starX[i] / starZ[i]) * 32);
            y = (int)(SCREEN_H / 2 + (starY[i] / starZ[i]) * 32);
            c = (int)((1020 - starZ[i]) / 4);
            s = c >> 6;
            //if (s < 1) s = 1;
            g.setColor(new Color(c,c,c));
            starZ[i] -= speed;
            if (starZ[i] < .1 || x < 0 || y < 0 || x >= SCREEN_W || y >= SCREEN_H) {
                starZ[i] = 1000;
                starX[i] = (int)(X_SPR * Math.random()) - X_SPR / 2;
                starY[i] = (int)(Y_SPR * Math.random()) - Y_SPR / 2;
            }
            g.fillRect(x,y,s,s);
        }
    }

}

