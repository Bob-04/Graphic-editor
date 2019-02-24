package WindowElements.Dialogs;

import javax.swing.*;

public class ImageNotFoundDialog extends JFrame {
    public ImageNotFoundDialog(){
        super.setBounds(400, 200, 245, 80);
        super.setVisible(true);

        JLabel lab = new JLabel("Буффер обміну не містить картинок !");
        super.add(lab);
    }
}