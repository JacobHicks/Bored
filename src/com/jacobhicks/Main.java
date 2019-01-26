package com.jacobhicks;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new Display();
        frame.setLayout(null);
        frame.setBounds(0,0,400,400);
        frame.setVisible(true);
    }
}

class Display extends JFrame {
    Queue<Integer> xs;
    Queue<Integer> ys;
    Queue<Color> colors;
    boolean[][] dp;
    boolean debounce;
    Display() {
        debounce = true;
        dp = new boolean[400][400];
        xs = new LinkedList<>();
        ys = new LinkedList<>();
        colors = new LinkedList<>();
        xs.offer(200);
        ys.offer(200);
        colors.offer(Color.black);
    }

    public void loop(Graphics g) {
        while (true) {
            for(int i = 0; i < Math.random() * 50; i++) {
                xs.offer((int) (Math.random() * 400));
                ys.offer((int) (Math.random() * 400));
                colors.offer(new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
            }
            dp = new boolean[400][400];
            while (!xs.isEmpty()) {
                paint(this.getGraphics());
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        int x = xs.poll();
        int y = ys.poll();
        Color color = colors.poll();
        if(y < 400 && x < 400 && y >= 0 && x >= 0 && !dp[y][x]) {
            //this.setBounds((this.getX() + 1) % 800, 0, this.getWidth(), this.getHeight());
            dp[y][x] = true;
            g.setColor(color);
            g.fillRect(x, y, 1, 1);
            int val = (int)(Math.random() * 4);
            color = new Color((color.getRed() + (val == 1 ? ((int)(Math.random() * 5)) : 0)) % 256, (color.getGreen() + (val == 2 ? ((int)(Math.random() * 5)) : 0)) % 256, (color.getBlue() + (val == 3 ? ((int)(Math.random() * 5)) : 0)) % 256);
            xs.offer(x + 1);
            ys.offer(y);
            colors.offer(color);
            xs.offer(x - 1);
            ys.offer(y);
            colors.offer(color);
            xs.offer(x);
            ys.offer(y + 1);
            colors.offer(color);
            xs.offer(x);
            ys.offer(y - 1);
            colors.offer(color);
            xs.offer(x + 1);
            ys.offer(y - 1);
            colors.offer(color);
            xs.offer(x + 1);
            ys.offer(y + 1);
            colors.offer(color);
            xs.offer(x - 1);
            ys.offer(y + 1);
            colors.offer(color);
            xs.offer(x - 1);
            ys.offer(y - 1);
            colors.offer(color);
            if (debounce) {
                debounce = false;
                loop(g);
            }
        }
    }
}