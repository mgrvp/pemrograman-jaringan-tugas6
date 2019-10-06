package pushbackstream;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PushbackReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author lenovo
 */
public class Control {

    private final ReadFile view;

    public Control(ReadFile view) {
        this.view = view;

        this.view.getBtnLoad().addActionListener((ActionEvent e) -> {
            try {
                baca();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadLocationException | IOException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void baca() throws FileNotFoundException, BadLocationException, IOException {
        JFileChooser loadFile = view.getLoadFile();
        StyledDocument doc = view.getjTextPane1().getStyledDocument();
        if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
            PushbackReader reader = new PushbackReader(new FileReader(loadFile.getSelectedFile()));
            LineNumberReader line = new LineNumberReader(new FileReader(loadFile.getSelectedFile()));

            char[] words = new char[(int) loadFile.getSelectedFile().length()];

            try {
                reader.read(words);

                String data = null;
                String data1 = null;
                doc.insertString(0, "", null);

                int countKar = 0;
                int countKata = 0;

                if ((data = new String(words)) != null) {
                    String[] wordlist = data.split("\\s+");

                    countKar += words.length;
                    countKata += wordlist.length;
                    view.getjTextPane1().setText("");
                    doc.insertString(doc.getLength(), data + "\n", null);

                }
                while ((data1 = line.readLine()) != null) {

                }
                int countBar = line.getLineNumber();

                JOptionPane.showMessageDialog(null, "File Berhasil dibaca." + "\n"
                        + "Jumlah baris = " + countBar + "\n"
                        + "Jumlah kata = " + countKata + "\n"
                        + "Jumlah karakter = " + countKar );

            } catch (IOException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
