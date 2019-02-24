package WindowElements;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import WindowElements.Dialogs.FileChooserDialog;


public class DrawArea extends JPanel {

    private Image image;
    private Graphics2D g2;
    private int currentX, currentY;
    private int oldX, oldY;
    private int finishX, finishY;
    private Type type;
    private int radius;

    private BufferedImage copyImg;
    private int vImgX, vImgY, vImgWidth, vImgHeight;
    private int timeImgX, timeImgY, timeImgWidth, timeImgHeight;
    private boolean allowPaste;
    private TransformArea transformArea;

    private enum TransformArea {
        none, move, changeLeft, changeRight, changeTop, changeBot
    }

    public DrawArea() {
        this.type = Type.penInstrumentType;
        this.radius = 10;
        this.allowPaste = false;
        this.transformArea = TransformArea.none;

        super.requestFocus();
        super.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        super.addKeyListener(setKeyAdapter());
        super.addMouseListener(setMouseAdapter());
        super.addMouseMotionListener(setMouseMotionAdapter());
    }

    private KeyAdapter setKeyAdapter() {
        return new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                // Ctrl + C (to copy area).
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C &&
                        type==Type.areaInstrumentType && !allowPaste){
                    copyArea();
                }
                // Ctrl + X (to cut area).
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_X &&
                        type==Type.areaInstrumentType && !allowPaste){
                    cutArea();
                }
                // Ctrl + V (to paste area).
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V){
                    pasteArea();
                }
                // Enter (to fixed area).
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    allowPaste = false;
                    currentX=0; oldX=0; currentY=0; oldY=0;
                    g2.drawImage(copyImg, timeImgX, timeImgY, timeImgWidth, timeImgHeight, null);
                }
                // Esc (to cancel last area)
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    allowPaste = false;
                    currentX=0; oldX=0; currentY=0; oldY=0;
                }
            }
        };
    }

    private MouseAdapter setMouseAdapter() {
        // Adapter for drawing shapes
        return new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
            public void mouseReleased(MouseEvent e) {
                finishX = e.getX();
                finishY = e.getY();

                switch (type) {
                    case lineFigureType:
                        g2.drawLine(oldX, oldY, finishX, finishY);
                        repaint();
                        break;
                    case rectangleFigureType:
                        g2.drawRect(finishX>oldX?oldX:finishX, finishY>oldY?oldY:finishY,
                                Math.abs(finishX-oldX), Math.abs(finishY-oldY));
                        repaint();
                        break;
                    case triangleFigureType:
                        g2.drawLine(finishX>oldX?oldX:finishX, finishY>oldY?finishY:oldY,
                                (finishX>oldX?oldX:finishX) + Math.abs(finishX-oldX)/2,
                                finishY>oldY?oldY:finishY);
                        g2.drawLine((finishX>oldX?oldX:finishX) + Math.abs(finishX-oldX)/2,
                                finishY>oldY?oldY:finishY, finishX>oldX?finishX:oldX, finishY>oldY?finishY:oldY);
                        g2.drawLine(finishX>oldX?finishX:oldX, finishY>oldY?finishY:oldY,
                                finishX>oldX?oldX:finishX, finishY>oldY?finishY:oldY);
                        repaint();
                        break;
                    case circleFigureType:
                        g2.drawOval(finishX>oldX?oldX:finishX, finishY>oldY?oldY:finishY,
                                Math.abs(finishX-oldX), Math.abs(finishY-oldY));
                        repaint();
                        break;
                }
                transformArea = TransformArea.none;
            }
        };
    }

    private MouseMotionAdapter setMouseMotionAdapter() {
        return new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();

                // For drawing using rubber, pen or other styles.
                switch (type) {
                    case rubberInstrumentType:
                        g2.setPaint(Color.white);
                        g2.fillRect(oldX- radius, oldY- radius, 2* radius, 2* radius);
                        oldX = currentX;
                        oldY = currentY;
                        repaint();
                        break;
                    case penInstrumentType:
                        g2.drawLine(oldX, oldY, currentX, currentY);
                        oldX = currentX;
                        oldY = currentY;
                        repaint();
                        break;
                    case circleStyleType:
                        g2.fillOval(oldX- radius, oldY- radius, 2* radius, 2* radius);
                        oldX = currentX;
                        oldY = currentY;
                        repaint();
                        break;
                    case squareStyleType:
                        g2.fillRect(oldX- radius, oldY- radius, 2* radius, 2* radius);
                        oldX = currentX;
                        oldY = currentY;
                        repaint();
                        break;
                    case sprayStyleType:
                        for(int i = 0; i< radius; i++)
                            g2.fillOval((int)(Math.random()* radius) + oldX,
                                    (int)(Math.random()* radius) + oldY, 1, 1);
                        oldX = currentX;
                        oldY = currentY;
                        repaint();
                        break;
                }
                // Operations with area.
                if(allowPaste)
                    switch (transformArea) {
                        case none:
                            if(currentX>=timeImgX+5 && currentX<=(timeImgX+timeImgWidth)-5 &&
                                    currentY>=timeImgY+5 && currentY<=(timeImgY+timeImgHeight)-5) {
                                transformArea = TransformArea.move;
                            }
                            if(currentX>=timeImgX-5 && currentX<=timeImgX+5 && currentY>=timeImgY &&
                                    currentY<=(timeImgY+timeImgHeight) ) {
                                transformArea = TransformArea.changeLeft;
                            }
                            if(currentX>=(timeImgX+timeImgWidth)-5 && currentX<=(timeImgX+timeImgWidth)+5 &&
                                    currentY>=timeImgY && currentY<=(timeImgY+timeImgHeight) ) {
                                transformArea = TransformArea.changeRight;
                            }
                            if(currentY>=timeImgY-5 && currentY<=timeImgY+5 && currentX>=timeImgX &&
                                    currentX<=(timeImgX+timeImgWidth) ) {
                                transformArea = TransformArea.changeTop;
                            }
                            if(currentY>=(timeImgY+timeImgHeight)-5 && currentY<=(timeImgY+timeImgHeight)+5 &&
                                    currentX>=timeImgX && currentX<=(timeImgX+timeImgWidth) ) {
                                transformArea = TransformArea.changeBot;
                            }
                            break;
                        case move:
                            timeImgX = currentX-timeImgWidth/2;
                            timeImgY = currentY-timeImgHeight/2;
                            break;
                        case changeLeft:
                            timeImgWidth += (timeImgX-currentX);
                            timeImgX = currentX;
                            break;
                        case changeRight:
                            timeImgWidth -= (timeImgX+timeImgWidth-currentX);
                            break;
                        case changeTop:
                            timeImgHeight += (timeImgY-currentY);
                            timeImgY = currentY;
                            break;
                        case changeBot:
                            timeImgHeight -= (timeImgY+timeImgHeight-currentY);
                            break;
                    }
                }
        };
    }

    @Override
    public void paintComponent(Graphics g)
    {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clearScreen();
        }
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;

        if(type == Type.areaInstrumentType && !allowPaste){
            for(int i=currentX>oldX?oldX:currentX;
                i<Math.abs(currentX-oldX)+(currentX>oldX?oldX:currentX); i+=10) {
                graphics2D.drawLine(i, currentY>oldY?oldY:currentY, i+5, currentY>oldY?oldY:currentY);
                graphics2D.drawLine(i, currentY>oldY?currentY:oldY, i+5, currentY>oldY?currentY:oldY);
            }
            for(int i=currentY>oldY?oldY:currentY;
                i<Math.abs(currentY-oldY)+(currentY>oldY?oldY:currentY); i+=10) {
                graphics2D.drawLine(currentX>oldX?oldX:currentX, i, currentX>oldX?oldX:currentX, i+5);
                graphics2D.drawLine(currentX>oldX?currentX:oldX, i, currentX>oldX?currentX:oldX, i+5);
            }
        }
        if(allowPaste){
            graphics2D.drawImage(copyImg, timeImgX, timeImgY, timeImgWidth, timeImgHeight, null);
            repaint();
        }
        repaint();
    }

    void clearScreen()
    {
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();
    }

    void pasteImg(File file) throws IOException
    {
        Image image = ImageIO.read(file);
        g2.drawImage(image, 0, 0, getSize().width, getSize().height, null);
        repaint();
    }

    void saveImg()
    {
        BufferedImage image = (BufferedImage)super.createImage(super.getWidth(), super.getHeight());
        super.paint(image.getGraphics());
        try {
            ImageIO.write(image, "jpg", new FileChooserDialog().getFile());
        }
        catch(IOException io) {
            System.out.println(io.getMessage());
        }
    }

    void setType(Type typ){
        this.type=typ;
        super.requestFocus();
    }
    void setRadius(int rad){
        g2.setStroke(new BasicStroke(rad));
        this.radius =rad;
        super.requestFocus();
    }
    void setColor(Color c){
        g2.setPaint(c);
        super.requestFocus();
    }

    void copyArea()
    {
        timeImgX = currentX>oldX?oldX:currentX;
        timeImgY = currentY>oldY?oldY:currentY;
        vImgX = timeImgX;
        vImgY = timeImgY;
        timeImgWidth = Math.abs(currentX-oldX);
        timeImgHeight = Math.abs(currentY-oldY);
        vImgWidth = timeImgWidth;
        vImgHeight = timeImgHeight;
        copyScreen();
    }

    void cutArea()
    {
        timeImgX = currentX>oldX?oldX:currentX;
        timeImgY = currentY>oldY?oldY:currentY;
        vImgX = timeImgX;
        vImgY = timeImgY;
        timeImgWidth = Math.abs(currentX-oldX);
        timeImgHeight = Math.abs(currentY-oldY);
        vImgWidth = timeImgWidth;
        vImgHeight = timeImgHeight;
        copyScreen();

        Color lastColor = g2.getColor();
        g2.setPaint(Color.white);
        g2.fillRect(vImgX, vImgY, timeImgWidth, timeImgHeight);
        g2.setPaint(lastColor);
    }

    void pasteArea()
    {
        try {
            BufferedImage timeImg = ImageIO.read(new File("resources\\tempImg.jpg"));
            copyImg = timeImg.getSubimage(vImgX, vImgY, vImgWidth, vImgHeight);
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
        allowPaste = true;
    }

    private void copyScreen()
    {
        BufferedImage panelImg =(BufferedImage)super.createImage(super.getWidth(), super.getHeight());
        Graphics g = panelImg.getGraphics();
        super.paint(g);

        try {
            ImageIO.write(panelImg, "jpg", new File("resources\\tempImg.jpg") );
        }
        catch(IOException io) {
            System.out.println(io.getMessage());
        }
    }
}