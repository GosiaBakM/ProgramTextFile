import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static javax.swing.JOptionPane.*;

public class Menu extends JFrame implements ActionListener {

    JMenuBar menuBar;
    JMenu menuPlik, menuNarzedzia, menuPomoc, menuOpcje;
    JMenuItem mOtworz, mZapisz, mWyjscie, mNarz1, mNarz2, mOProgramie, mOpcja1, mOpcja2;
    JCheckBoxMenuItem chOpcja2;
    JTextArea notatnik;

    Menu() {
        setSize(800, 800);
        setTitle("Przykladowe menu");
        setLayout(null);

        menuBar = new JMenuBar();
        menuPlik = new JMenu("Plik");

        mOtworz = new JMenuItem("Otwórz", 'O');
            mOtworz.addActionListener(this);
        mZapisz = new JMenuItem("Zapisz");
            mZapisz.addActionListener(this);
        mWyjscie = new JMenuItem("Wyjście");
            mWyjscie.addActionListener(this);
        mWyjscie.addActionListener(this);
        mWyjscie.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));

        menuPlik.add(mOtworz);
        menuPlik.add(mZapisz);
        menuPlik.addSeparator();
        menuPlik.add(mWyjscie);

        menuNarzedzia = new JMenu("Narzędzia");
        mNarz1 = new JMenuItem("Narz1");
        mNarz1.setEnabled(false);
        mNarz2 = new JMenuItem("Narz2");
        mNarz2.addActionListener(this);

        menuOpcje = new JMenu("Opcje");
        mOpcja1 = new JMenuItem("Opcje1");
        chOpcja2 = new JCheckBoxMenuItem("Opcje2");
        chOpcja2.addActionListener(this);
        menuOpcje.add(mOpcja1);
        menuOpcje.add(chOpcja2);
        menuNarzedzia.add(menuOpcje);

        menuNarzedzia.add(mNarz1);
        menuNarzedzia.add(mNarz2);

        menuPomoc = new JMenu("Pomoc");
        setJMenuBar(menuBar);
        menuBar.add(menuPlik);
        menuBar.add(menuNarzedzia);
        menuBar.add(menuPomoc);


        menuBar.add(Box.createHorizontalGlue());
        mOProgramie = new JMenuItem("O programie");
        menuPomoc.add(mOProgramie);
        mOProgramie.addActionListener(this);

        notatnik = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(notatnik);
        scrollPane.setBounds(50,50,600,600);
        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if (z == mOtworz){
            JFileChooser fc = new JFileChooser();
                if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ){
                    File plik = fc.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Wybrany plik to: " + plik.getAbsolutePath());
                }
        }
        else if (z == mZapisz){
            JFileChooser fc = new JFileChooser();
            if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File plik = fc.getSelectedFile();
                JOptionPane.showMessageDialog(null, "Wybrany plik to: " + plik.getAbsolutePath());
            }
        }

        else if (z == mWyjscie) {
            int odp = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjść", "Pytanie", JOptionPane.YES_NO_OPTION);

            if (odp == JOptionPane.YES_OPTION)
                dispose();
            else if (odp == JOptionPane.NO_OPTION)
                JOptionPane.showMessageDialog(null, "widziałem");
            else if (odp == JOptionPane.CLOSED_OPTION)
                JOptionPane.showConfirmDialog(null, "Tak nie robimy", "Tytuł", JOptionPane.WARNING_MESSAGE);

        }
        if (z == chOpcja2) {
            if (chOpcja2.isSelected()) {
                mNarz1.setEnabled(true);
            } else if (!chOpcja2.isSelected()) {
                mNarz1.setEnabled(false);
            }
        }
        if (z == mOProgramie)
            JOptionPane.showMessageDialog(null, "Program 1.0", "To jest ostrzeżenie", JOptionPane.WARNING_MESSAGE);
        if (z == mNarz2) {
            String sMetry = JOptionPane.showInputDialog("Podaj długość w metrach");
            double metry = Double.parseDouble(sMetry);
            double stopy = metry/0.3048;
            String sStopy = String.format("%.2f", stopy);
            JOptionPane.showMessageDialog(null, metry + "metrów" + stopy + "stóp");
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
    }


}
