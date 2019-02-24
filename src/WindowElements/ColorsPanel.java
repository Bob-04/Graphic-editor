package WindowElements;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ColorsPanel extends JPanel {
    private DrawArea drawArea;

    ColorsPanel(DrawArea area) {
        this.drawArea = area;

        super.setLayout(new GridLayout(3, 4, 2, 2));

        super.add(addColor(Color.white));
        super.add(addColor(Color.black));
        super.add(addColor(Color.pink));
        super.add(addColor(Color.orange));
        super.add(addColor(Color.red));
        super.add(addColor(Color.blue));
        super.add(addColor(Color.green));
        super.add(addColor(Color.yellow));
        super.add(addColor(Color.darkGray));
        super.add(addColor(Color.cyan));
        super.add(addColor(Color.magenta));
        super.add(addColor(Color.lightGray));
    }

    private JButton addColor(Color color) {
        JButton colorBut = new JButton();
        colorBut.setBackground(color);
        colorBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawArea.setColor(color);
            }});
        return colorBut;
    }
}