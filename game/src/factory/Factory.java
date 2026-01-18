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
    ImageIcon logoIcon = null;
    
    public static void main(String[] args) {
        new Factory();
    }
    
    public Factory() {
        setTitle("Производственная компания");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Загрузка картинок
        try {
            factoryIcon = new ImageIcon(getClass().getResource("factory.png"));
            boxIcon = new ImageIcon(getClass().getResource("box.png"));
            logoIcon = new ImageIcon(getClass().getResource("logo.png"));
        } catch (Exception e) {
            // Если не загрузились - ничего страшного
        }
        
        // Панель игры
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Фон
                if (factoryIcon != null) {
                    g.drawImage(factoryIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(new Color(173, 216, 230));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(150, 250, 500, 200);
                    g.setColor(Color.YELLOW);
                    for (int i = 0; i < 6; i++) {
                        g.fillRect(180 + i*80, 280, 40, 40);
                    }
                }
                
                // Логотип
                if (logoIcon != null) {
                    int logoWidth = 150;
                    int logoHeight = 150;
                    int logoX = getWidth()/2 - logoWidth/2;
                    g.drawImage(logoIcon.getImage(), logoX, 10, logoWidth, logoHeight, this);
                } else {
                    g.setColor(Color.BLUE);
                    g.setFont(new Font("Arial", Font.BOLD, 36));
                    String logoText = "ПРОИЗВОДСТВЕННАЯ КОМПАНИЯ";
                    int textWidth = g.getFontMetrics().stringWidth(logoText);
                    g.drawString(logoText, getWidth()/2 - textWidth/2, 60);
                }
                
                // Ящик
                if (boxIcon != null) {
                    g.drawImage(boxIcon.getImage(), boxX, boxY, 80, 80, this);
                } else {
                    g.setColor(new Color(160, 82, 45));
                    g.fillRect(boxX, boxY, 80, 80);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 20));
                    g.drawString("ЯЩИК", boxX + 15, boxY + 50);
                }
                
                // Информация
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Продукция: " + products, 30, 120);
                g.drawString("Финансы: $" + (products * 100), 30, 160);
                g.drawString("Персонал: " + (5 + products/3), 30, 200);
                g.drawString("Грузовики: " + (2 + products/5), 30, 240);
                
                // Инструкция
                g.setColor(Color.white);
                g.setFont(new Font("Arial", Font.BOLD, 18));
                g.drawString("КЛИКАЙТЕ НА ПАДАЮЩИЕ ЯЩИКИ", getWidth()/2 - 180, getHeight() - 50);
            }
        };
        
        // Клик по ящику
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                
                if (x >= boxX && x <= boxX + 80 && y >= boxY && y <= boxY + 80) {
                    products++;
                    boxY = 0;
                    boxX = new Random().nextInt(700);
                    setTitle("Производство: " + products + " | Финансы: $" + (products * 100));
                    repaint();
                }
            }
        });
        
        // Таймер для анимации
        Timer timer = new Timer(30, new ActionListener() {
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