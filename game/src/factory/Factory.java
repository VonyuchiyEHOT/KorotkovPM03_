package factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Factory extends JFrame {
    
    int products = 0;
    int boxX = 200;
    int boxY = 0;
    
    ImageIcon factoryIcon = null;
    ImageIcon boxIcon = null;
    
    public static void main(String[] args) {
        new Factory();
    }
    
    public Factory() {
        setTitle("Фабрика");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        

        try {
            factoryIcon = new ImageIcon("factory.png");
            boxIcon = new ImageIcon("box.png");
        } catch (Exception e) {
        }
        

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                

                if (factoryIcon != null) {
                    g.drawImage(factoryIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(Color.CYAN);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.GRAY);
                    g.fillRect(100, 200, 600, 300);
                }
                

                if (boxIcon != null) {
                    g.drawImage(boxIcon.getImage(), boxX, boxY, 60, 60, this);
                } else {
                    g.setColor(new Color(139, 69, 19));
                    g.fillRect(boxX, boxY, 60, 60);
                }
                
                // Текст
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Продукция: " + products, 20, 40);
                g.drawString("Деньги: $" + (products * 100), 20, 80);
                g.drawString("Кликай на ящики!", 20, 120);
            }
        };
        

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                
                if (x >= boxX && x <= boxX + 60 && 
                    y >= boxY && y <= boxY + 60) {
                    products++;
                    boxY = 0;
                    boxX = new Random().nextInt(700);
                    repaint();
                }
            }
        });
        
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boxY += 5;
                if (boxY > 600) {
                    boxY = 0;
                    boxX = new Random().nextInt(700);
                }
                repaint();
            }
        });
        
        add(panel);
        setVisible(true);
        timer.start();
    }
}