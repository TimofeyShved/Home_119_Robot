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
        JFrame jFrame = getFrame();
        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);

        JButton button1 = new JButton("click 1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setTitle(jFrame.getTitle()+"+");
            }
        });
        jPanel.add(button1);

        JButton button2 = new JButton("click 2");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice device = environment.getDefaultScreenDevice();
                try {
                    Robot mrRobot = new Robot(device);

                    mrRobot.mouseMove(500+225, 500+50);
                    mrRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    mrRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                    mrRobot.keyPress(KeyEvent.VK_TAB);
                    mrRobot.keyRelease(KeyEvent.VK_TAB);

                    Rectangle rectangle = new Rectangle(0,0,100,100);
                    BufferedImage image = mrRobot.createScreenCapture(rectangle);

                    ImageIO.write(image, "png", new File("1.png")); // чтение файла и сохранение его в png

                    // для того что-бы сохранить картинку
                    ImageWriter writer = null; // создаём врайтер
                    Iterator<ImageWriter> iterator1 = ImageIO.getImageWritersByFormatName("JPEG"); // формат картинки
                    if(iterator1.hasNext()) writer = iterator1.next();

                    // запись картинки в папку
                    ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(new File("1.png"));
                    writer.setOutput(imageOutputStream);
                } catch (AWTException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        jPanel.add(button2);
        jPanel.revalidate();

    }

    static JFrame getFrame(){
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setBounds(500,500,500,200);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return jFrame;
    }
}
