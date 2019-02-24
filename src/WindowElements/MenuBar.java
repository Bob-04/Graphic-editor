package WindowElements;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import WindowElements.Dialogs.ImageNotFoundDialog;


public class MenuBar extends JMenuBar {
    private Font font;
    DrawArea drawPanel;

    public MenuBar(DrawArea drawPanel) {
        this.font = new Font("Verdana", Font.PLAIN, 11);
        this.drawPanel = drawPanel;

        super.setBackground(Color.green);
        super.add(fileMenu());
        super.add(editMenu());
    }

    private JMenu fileMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(font);

        fileMenu.add(buttonSave());
        fileMenu.add(buttonClear());
        fileMenu.add(buttonPasteImg());

        fileMenu.addSeparator();
        fileMenu.add(buttonExit());
        return fileMenu;
    }

    private JMenu editMenu() {
        JMenu editMenu = new JMenu("Edit");
        editMenu.setFont(font);
        editMenu.add(buttonPaste());
        editMenu.add(buttonCopy());
        editMenu.add(buttonCut());
        return editMenu;
    }

    private JMenuItem buttonSave() {
        JMenuItem save = new JMenuItem("Save");
        save.setFont(font);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawPanel.saveImg();
            }
        });
        return save;
    }

    private JMenuItem buttonClear() {
        JMenuItem clear = new JMenuItem("Clear");
        clear.setFont(font);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawPanel.clearScreen();
            }
        });
        return clear;
    }

    private JMenuItem buttonPasteImg() {
        JMenuItem paste = new JMenuItem("Paste");
        paste.setFont(font);
        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    List<File> fileList = (List<File>) clipboard.getData(DataFlavor.javaFileListFlavor);
                    if(fileList == null)
                        return;
                    for(File file : fileList)
                        drawPanel.pasteImg(file);
                } catch (Exception e) {
                    new ImageNotFoundDialog();
                }
            }
        });
        return paste;
    }

    private JMenuItem buttonExit() {
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(font);
        exitItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return exitItem;
    }

    private JMenuItem buttonPaste() {
        JMenuItem pas = new JMenuItem("Paste (ctrl+V)");
        pas.setFont(font);
        pas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawPanel.pasteArea();
            }
        });
        return pas;
    }

    private JMenuItem buttonCopy() {
        JMenuItem cop = new JMenuItem("Copy (ctrl+C)");
        cop.setFont(font);
        cop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                drawPanel.copyArea();
            }
        });
        return cop;
    }

    private JMenuItem buttonCut() {
        JMenuItem cut = new JMenuItem("Cut (ctrl+X)");
        cut.setFont(font);
        cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {///////////
                drawPanel.cutArea();
            }
        });
        return cut;
    }
}