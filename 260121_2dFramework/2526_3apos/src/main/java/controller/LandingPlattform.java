package controller;

import lombok.extern.java.Log;

import java.awt.*;

@Log

public class LandingPlattform extends Object{
    private Color color = Color.cyan;

    public LandingPlattform(Vector location, int width, int height) {
        this.location = location;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(){
        // Not yet in use
        // Note: wont ever get in use -> checkOnTop is the new update()
    }

    @Override
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        int x = (int) location.getX();
        int y = (int) location.getY();

        int topHeight = height / 4; // only this part is landable

        // === FLAT TOP (landable area) ===
        g2.setColor(color);
        g2.fillRect(x, y, width, topHeight);

        // Outline the top to make it clear
        g2.setColor(color.darker());
        g2.drawRect(x, y, width, topHeight);

        // === SPIKY BOTTOM (not landable) ===
        g2.setColor(color.darker());

        int spikeCount = width / 10;
        int spikeWidth = width / spikeCount;

        for (int i = 0; i < spikeCount; i++) {
            int spikeX = x + i * spikeWidth;

            int[] xPoints = {
                    spikeX,
                    spikeX + spikeWidth / 2,
                    spikeX + spikeWidth
            };

            int[] yPoints = {
                    y + topHeight,
                    y + height,
                    y + topHeight
            };

            g2.fillPolygon(xPoints, yPoints, 3);
        }
    }

    public boolean checkOnTop(Vector l, int h, int w){
        // Simple checker - checks if on top or 5 px under the top
        if((int) location.getY() <= (int)(l.getY() + h) && (int) location.getY() >= (int) (l.getY() + h - 5)){
            if((int) location.getX() <= (int) (l.getX() + w) && (int) (location.getX() + width) >= (int) l.getX()){
                return true;
            }
        }

        return false;
    }
}
