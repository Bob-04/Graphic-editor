package WindowElements;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class FiguresPanel extends JPanel {

    private DrawArea drawArea;

    FiguresPanel(DrawArea area) {
        this.drawArea = area;

        super.setLayout(new GridLayout(2, 2, 1, 1));

        super.add(lineFigure());
        super.add(rectangleFigure());
        super.add(triangleFigure());
        super.add(circleFigure());
    }

    private JButton lineFigure() {
        JButton line = new JButton(new ImageIcon(new ImageIcon(
                "resources\\lineFigureImg.jpg")
                .getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT)));
        line.setBackground(Color.white);
        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawArea.setType(Type.lineFigureType);
            }});
        return line;
    }

    private JButton rectangleFigure() {
        JButton rectangle = new JButton(new ImageIcon(new ImageIcon(
                "resources\\rectangleFigureImg.jpg")
                .getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT)));
        rectangle.setBackground(Color.white);
        rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawArea.setType(Type.rectangleFigureType);
            }});
        return rectangle;
    }

    private JButton triangleFigure() {
        JButton triangle = new JButton(new ImageIcon(new ImageIcon(
                "resources\\triangleFigureImg.jpg")
                .getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT)));
        triangle.setBackground(Color.white);
        triangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawArea.setType(Type.triangleFigureType);
            }});
        return triangle;
    }

    private JButton circleFigure() {
        JButton circle = new JButton(new ImageIcon(new ImageIcon(
                "resources\\sircleFigureImg.jpg")
                .getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT)));
        circle.setBackground(Color.white);
        circle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawArea.setType(Type.circleFigureType);
            }});
        return circle;
    }
}