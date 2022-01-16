

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class SwingFC implements ActionListener {
    JTextField jtfFirst;
    JTextField jtfSecond;
    JButton jcompareBtn;
    JLabel jlabFirst, jlabSecond;
    JLabel jlabResult;
    JCheckBox jcheck;

    public SwingFC() {
        JFrame jframe = new JFrame("Compare files");
        jframe.setLayout(new FlowLayout());
        jframe.setSize(300, 300);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jtfFirst = new JTextField(14);
        jtfSecond = new JTextField(14);
        jtfFirst.setActionCommand("FileA");
        jtfSecond.setActionCommand("FileB");
        jcompareBtn = new JButton("Compare");
        jcompareBtn.addActionListener(this);

        jlabFirst = new JLabel("First file: ");
        jlabSecond = new JLabel("Second file: ");
        jlabResult = new JLabel("");
        jcheck = new JCheckBox("show difference");

        jframe.add(jlabFirst);
        jframe.add(jtfFirst);
        jframe.add(jlabSecond);
        jframe.add(jtfSecond);
        jframe.add(jcheck);
        jframe.add(jcompareBtn);
        jframe.add(jlabResult);

        jframe.setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        int i = 0, j = 0, count = 0;
        if (jtfFirst.getText().equals("")) {
            jlabResult.setText("First file name missing.");
            return;
        }

        if (jtfSecond.getText().equals("")) {
            jlabResult.setText("Second file name missing.");
            return;
        }

        try (FileInputStream f1 = new FileInputStream(jtfFirst.getText());
             FileInputStream f2 = new FileInputStream(jtfSecond.getText())) {

            do {
                i = f1.read();
                j = f2.read();
                if (i != j) break;
                count++;
            } while (i != -1 && j != -1);

            if (i != j) {
                if (jcheck.isSelected())
                    jlabResult.setText("Files differ, starting from position " + count);
                else
                    jlabResult.setText("Files are different");
            }
            else
                jlabResult.setText("Files are the same");

        } catch (IOException e) {
            jlabResult.setText("File error");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingFC();
            }
        });
    }
}
