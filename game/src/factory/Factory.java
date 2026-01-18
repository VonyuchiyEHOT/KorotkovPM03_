package factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Factory extends JFrame {
    
    int products = 0;
    int boxX = 200;
    int boxY = 0;
    
    Image factoryImage = null;
    Image boxImage = null;
    
    public static void main(String[] args) {
        new Factory();
    }
    
    public Factory() {
        setTitle("Фабрика");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // ЗАГРУЗКА КАРТИНОК ВСЕМИ СПОСОБАМИ
        try {
            // Способ 1: Через класс (самый надежный)
            factoryImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("factory.png"));
            boxImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("box.png"));
            
            // Ждем загрузки
            MediaTracker tracker = new MediaTracker(this);
            if (factoryImage != null) tracker.addImage(factoryImage, 0);
            if (boxImage != null) tracker.addImage(boxImage, 1);
            tracker.waitForAll();
            
        } catch (Exception e1) {
            try {
                // Способ 2: Простые пути
                factoryImage = new ImageIcon("factory.png").getImage();
                boxImage = new ImageIcon("box.png").getImage();
            } catch (Exception e2) {
                // Способ 3: Из src
                factoryImage = new ImageIcon("src/factory/factory.png").getImage();
                boxImage = new ImageIcon("src/factory/box.png").getImage();
            }
        }
        
        // Проверка
        if (factoryImage == null) {
            System.out.println("ВНИМАНИЕ: Картинки не загружены!");
            System.out.println("Создай factory.png и box.png в папке с Factory.java");
        } else {
            System.out.println("Картинки загружены!");
        }
        
        // Панель игры
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Фон
                if (factoryImage != null) {
                    g.drawImage(factoryImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    // Рисуем фабрику цветами
                    g.setColor(new Color(135, 206, 235)); // небо
                    g.fillRect(0, 0, getWidth(), getHeight());
                    
                    g.setColor(Color.DARK_GRAY); // здание
                    g.fillRect(100, 250, 600, 250);
                    
                    g.setColor(Color.YELLOW); // окна
                    for (int i = 0; i < 5; i++) {
                        g.fillRect(150 + i*110, 280, 40, 40);
                    }
                    
                    g.setColor(Color.BLACK); // труба
                    g.fillRect(400, 200, 40, 50);
                    g.setColor(Color.GRAY); // дым
                    g.fillOval(390, 180, 60, 30);
                }
                
                // Ящик
                if (boxImage != null) {
                    g.drawImage(boxImage, boxX, boxY, 60, 60, this);
                } else {
                    g.setColor(new Color(160, 82, 45)); // коричневый
                    g.fillRect(boxX, boxY, 60, 60);
                    g.setColor(Color.WHITE);
                    g.drawRect(boxX + 5, boxY + 5, 50, 50);
                    g.drawString("BOX", boxX + 15, boxY + 35);
                }
                
                // Текст
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Продукция: " + products, 20, 40);
                g.drawString("Деньги: $" + (products * 100), 20, 80);
                g.drawString("Кликай на ящики!", 20, 120);
            }
        };
        
        // Клик
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
        
        // Таймер
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