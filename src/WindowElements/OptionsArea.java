package WindowElements;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsArea extends JPanel{
    private DrawArea drawArea;
    private GridBagConstraints constraints;

    public OptionsArea(DrawArea area) {
        super.setBackground(Color.cyan);
        super.setLayout(new GridBagLayout());

        this.drawArea = area;
        this.constraints = new GridBagConstraints();
        constraints.weightx=1;
        constraints.anchor=GridBagConstraints.WEST;

        setConstraints(0, 10, 30);
        super.add(programName(), constraints);

        setConstraints(1, 15, 10);
        super.add(new JLabel("Instruments:"), constraints);

        setConstraints(2, 5, 15);
        super.add(new InstrumentsPanel(drawArea), constraints);

        setConstraints(3, 15, 10);
        super.add(new JLabel("Colors:"), constraints);

        setConstraints(4, 5, 15);
        super.add(new ColorsPanel(drawArea), constraints);

        setConstraints(5, 15, 10);
        super.add(new JLabel("Size:"), constraints);

        setConstraints(6, 1, 15);
        super.add(sizeBox(), constraints);

        setConstraints(7, 15, 10);
        super.add(new JLabel("Figures:"), constraints);

        setConstraints(8, 5, 15);
        super.add(new FiguresPanel(drawArea), constraints);

        setConstraints(9, 15, 10);
        super.add(new JLabel("Styles:"), constraints);

        setConstraints(10, 5, 15);
        super.add(new StylesPanel(drawArea), constraints);

        setConstraints(11, 20, 70);
        super.add(new JLabel("\u00A9Osadchuk, 2018"), constraints);
    }

    private void setConstraints(int gridY, int top, int left) {
        constraints.gridy = gridY;
        constraints.insets = new Insets(top, left, 0, 10);
    }

    private JLabel programName() {
        JLabel name = new JLabel("Java-Paint");
        name.setFont(new Font("TimesRoman", Font.ITALIC, 30));
        name.setForeground(Color.darkGray);
        return name;
    }

    private JComboBox sizeBox() {
        final JComboBox size = new JComboBox();
        for(int i=1; i<=72; i++)
            size.addItem(i);
        size.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawArea.setRadius(size.getSelectedIndex());
            }
        });
        return size;
    }
}