package WindowElements.Dialogs;

import javax.swing.JFileChooser;
import java.io.File;

public class FileChooserDialog {
    private File file;

    public FileChooserDialog() {
        JFileChooser chooser = new JFileChooser();

        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION)
            file = chooser.getSelectedFile();
    }

    public File getFile() {
        return file;
    }
}