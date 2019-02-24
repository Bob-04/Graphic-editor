package WindowElements;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class InstrumentsPanel extends JPanel {
    private DrawArea drawArea;

    InstrumentsPanel(DrawArea area) {
        this.drawArea = area;

        super.setLayout(new GridLayout(2, 2, 1,1));
        super.setBackground(Color.cyan);

        super.add(penInstrument());
        super.add(areaInstrument());
        super.add(rubberInstrument());
    }

    private JButton penInstrument() {
        JButton pen = new JButton(new ImageIcon(new ImageIcon(
                "resources\\penInstrumentImg.jpg")
                .getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT)));
        pen.setBackground(Color.white);
        pen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawArea.setType(Type.penInstrumentType);
            }});
        return pen;
    }

    private JButton areaInstrument() {
        JButton area = new JButton(new ImageIcon(new ImageIcon(
                "resources\\areaInstrumentImg.jpg")
                .getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT)));
        area.setBackground(Color.white);
        area.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawArea.setType(Type.areaInstrumentType);
            }});
        return area;
    }

    private JButton rubberInstrument() {
        JButton rubber = new JButton(new ImageIcon(new ImageIcon(
                "resources\\rubberInstrumentImg.jpg")
                .getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT)));
        rubber.setBackground(Color.white);
        rubber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawArea.setType(Type.rubberInstrumentType);
            }});
        return rubber;
    }
}