import java.awt.BorderLayout;
import javax.swing.JFrame;

import WindowElements.*;

class Window extends JFrame {

    Window() {
        DrawArea drawArea = new DrawArea();

        super.setBounds(130, 50, 1000, 630);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setResizable(false);
        super.setVisible(true);
        super.setJMenuBar(new MenuBar(drawArea));

        super.setLayout(new BorderLayout());
        super.add(new OptionsArea(drawArea), BorderLayout.LINE_START);
        super.add(drawArea, BorderLayout.CENTER);
    }
}