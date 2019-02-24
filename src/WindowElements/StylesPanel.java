package WindowElements;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StylesPanel extends JPanel {
    private DrawArea drawArea;

    StylesPanel(DrawArea area) {
        this.drawArea = area;

        super.setLayout(new GridLayout(2, 2, 1, 1));
        super.setBackground(Color.cyan);

        super.add(circleStyle());
        super.add(squareStyle());
        super.add(sprayStyle());
    }

    private JButton circleStyle() {
        JButton circles = new JButton(new ImageIcon(new ImageIcon(
                "resources\\circlesStyleImg.jpg")
                .getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT)));
        circles.setBackground(Color.white);
        circles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawArea.setType(Type.circleStyleType);
            }});
        return circles;
    }

    private JButton squareStyle() {
        JButton squares = new JButton(new ImageIcon(new ImageIcon(
                "resources\\squaresStyleImg.jpg")
                .getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT)));
        squares.setBackground(Color.white);
        squares.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawArea.setType(Type.squareStyleType);
            }});
        return squares;
    }

    private JButton sprayStyle() {
        JButton spray = new JButton(new ImageIcon(new ImageIcon(
                "resources\\sprayStyleImg.jpg")
                .getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT)));
        spray.setBackground(Color.white);
        spray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawArea.setType(Type.sprayStyleType);
            }});
        return spray;
    }
}