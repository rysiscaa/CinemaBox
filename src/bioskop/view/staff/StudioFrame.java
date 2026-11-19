package bioskop.view.staff;

import bioskop.model.Studio;
import bioskop.model.Film;
import bioskop.util.DataStore;
import bioskop.view.common.BackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudioFrame extends JFrame {
    // Combobox untuk pilihan, Texfield untuk teks dan angka
    private JComboBox<Studio> cbStudio;
    private JTextField tfNoStudio, tfKapasitas, tfTanggal, tfJam;
    private JComboBox<Film> cbFilm;
    private JLabel btnSimpan;

    public StudioFrame() {
        
        setTitle("Kelola Studio");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Background
        BackgroundPanel bg = new BackgroundPanel("/bioskop/assets/bg_kelolastudio.jpg");
        bg.setLayout(null);
        setContentPane(bg);

        // Font & Style
        Font lblFont = new Font("Segoe UI", Font.BOLD, 20);
        Color lblColor = Color.WHITE;
        int xLabel = 300;
        int xColon = 500;
        int xField = 520;
        int fieldW = 420;
        int fieldH = 36;
        int y = 170;
        int gap = 60;

        //  Studio
        addLabel(bg, "Pilih Studio", xLabel, y, lblFont, lblColor);
        addColon(bg, xColon, y);
        cbStudio = new JComboBox<>();
        for (Studio s : DataStore.daftarStudio) cbStudio.addItem(s);
        cbStudio.setBounds(xField, y, fieldW, fieldH);
        bg.add(cbStudio);
        y += gap;

        // No Studio
        addLabel(bg, "No Studio", xLabel, y, lblFont, lblColor);
        addColon(bg, xColon, y);
        tfNoStudio = addField(bg, xField, y, fieldW, fieldH);
        y += gap;

        // Kapasitas
        addLabel(bg, "Kapasitas", xLabel, y, lblFont, lblColor);
        addColon(bg, xColon, y);
        tfKapasitas = addField(bg, xField, y, fieldW, fieldH);
        y += gap;

        // Tanggal
        addLabel(bg, "Tanggal", xLabel, y, lblFont, lblColor);
        addColon(bg, xColon, y);
        tfTanggal = addField(bg, xField, y, fieldW, fieldH);
        y += gap;

        // Jam Tayang
        addLabel(bg, "Jam Tayang", xLabel, y, lblFont, lblColor);
        addColon(bg, xColon, y);
        tfJam = addField(bg, xField, y, fieldW, fieldH);
        y += gap;

        // Film
        addLabel(bg, "Film", xLabel, y, lblFont, lblColor);
        addColon(bg, xColon, y);
        cbFilm = new JComboBox<>();
        for (Film f : DataStore.daftarFilm) cbFilm.addItem(f);
        cbFilm.setBounds(xField, y, fieldW, fieldH);
        bg.add(cbFilm);

        // Button SImpan
        ImageIcon btnNormal = new ImageIcon(
                getClass().getResource("/bioskop/assets/btn_simpan.png"));
        ImageIcon btnHover = new ImageIcon(
                getClass().getResource("/bioskop/assets/btn_simpan_hover.png"));

        btnSimpan = new JLabel(btnNormal);
        btnSimpan.setBounds(390, 550, btnNormal.getIconWidth(),
                btnNormal.getIconHeight());
        bg.add(btnSimpan);

        // Event Handlers
        cbStudio.addActionListener(e -> tampilkanStudio());

        btnSimpan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSimpan.setIcon(btnHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSimpan.setIcon(btnNormal);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                simpanStudio();
            }
        });

        tampilkanStudio();
        setVisible(true);
    }

    // Helper Methods
    private void addLabel(JPanel bg, String text, int x, int y, Font f, Color c) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(f);
        lbl.setForeground(c);
        lbl.setBounds(x, y, 180, 30);
        bg.add(lbl);
    }

    private void addColon(JPanel bg, int x, int y) {
        JLabel colon = new JLabel(":");
        colon.setFont(new Font("Segoe UI", Font.BOLD, 20));
        colon.setForeground(Color.WHITE);
        colon.setBounds(x, y, 20, 30);
        bg.add(colon);
    }

    private JTextField addField(JPanel bg, int x, int y, int w, int h) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, w, h);
        bg.add(tf);
        return tf;
    }

    // Logic Data
    private void tampilkanStudio() {
        Studio s = (Studio) cbStudio.getSelectedItem();
        if (s == null) return;

        tfNoStudio.setText(s.getNoStudio());
        tfKapasitas.setText(String.valueOf(s.getKapasitas()));
        tfTanggal.setText(s.getTanggal());
        tfJam.setText(s.getJam());
        cbFilm.setSelectedItem(s.getFilm());
    }

    private void simpanStudio() {
        Studio s = (Studio) cbStudio.getSelectedItem();
        if (s == null) return;

        try {
            s.setNoStudio(tfNoStudio.getText());
            s.setKapasitas(Integer.parseInt(tfKapasitas.getText()));
            s.setTanggal(tfTanggal.getText());
            s.setJam(tfJam.getText());
            s.setFilm((Film) cbFilm.getSelectedItem());

            JOptionPane.showMessageDialog(this, "Data studio berhasil diperbarui");
            cbStudio.repaint();

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, 
                    "Kapasitas harus berupa angka!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Input tidak valid", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}