package com.AWT;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        JFrame jFrame = getFrame(); // создание формы
        JPanel jPanel = new JPanel(); // и панели
        jFrame.add(jPanel);

        JButton button1 = new JButton("click 1"); //  создание кнопки
        button1.addActionListener(new ActionListener() { // действие
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setTitle(jFrame.getTitle()+"+"); // изминение титульника у формы
            }
        });
        jPanel.add(button1); // добавить кнопку на панель

        JButton button2 = new JButton("click 2"); // создание кнопки
        button2.addActionListener(new ActionListener() { // действие
            @Override
            public void actionPerformed(ActionEvent e) {
                GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment(); // добаляем графику ПК
                GraphicsDevice device = environment.getDefaultScreenDevice(); // узанём размеры экрана
                try {
                    Robot mrRobot = new Robot(device); // создаём робота, с полем действия

                    mrRobot.mouseMove(500+225, 500+50); // перевести мышку
                    mrRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // нажать на кнопку мышки
                    mrRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // отпустить

                    mrRobot.keyPress(KeyEvent.VK_TAB); // нажать клавишу таб
                    mrRobot.keyRelease(KeyEvent.VK_TAB); // отпустить

                    Rectangle rectangle = new Rectangle(0,0,100,100); // размеры квадрата
                    BufferedImage image = mrRobot.createScreenCapture(rectangle); // сделать скриншот

                    ImageIO.write(image, "png", new File("1.png")); // чтение файла и сохранение его в png

                    // для того что-бы сохранить картинку
                    ImageWriter writer = null; // создаём врайтер
                    Iterator<ImageWriter> iterator1 = ImageIO.getImageWritersByFormatName("JPEG"); // формат картинки
                    if(iterator1.hasNext()) writer = iterator1.next();

                    // запись картинки в папку
                    ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(new File("1.png"));
                    writer.setOutput(imageOutputStream);

                } catch (AWTException | IOException ex) {
                    ex.printStackTrace();// если есть ошибки
                }
            }
        });
        jPanel.add(button2); // добавить кнопку на панель
        jPanel.revalidate(); // перерисовать

    }

    static JFrame getFrame(){ // создание формы
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true); // видимость
        jFrame.setBounds(500,500,500,200); // размеры
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // закрытие
        return jFrame;
    }
}
