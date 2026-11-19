package bioskop.view.staff;

import bioskop.model.Kursi;
import bioskop.model.Studio;
import bioskop.util.DataStore;
import bioskop.view.common.BackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KursiFrame extends JFrame {

    private JComboBox<Studio> cbStudio;
    private JPanel panelKursi;
    private JLabel btnSimpan;

    // Menyamakan konstanta warna dengan KursiFramePelanggan
    private static final Color WARNA_TERSEDIA = Color.LIGHT_GRAY;
    private static final Color WARNA_TERISI   = Color.WHITE;

    public KursiFrame() {
        setTitle("Kelola Kursi - Staff");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Background
        BackgroundPanel bg = new BackgroundPanel("/bioskop/assets/bg_kelolakursi.jpg");
        bg.setLayout(null);
        setContentPane(bg);

        Font lblFont = new Font("Segoe UI", Font.BOLD, 22);
        Color lblColor = Color.WHITE;

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screen.width - 500) / 2;

        // Pilih Studio
        JLabel lblStudio = new JLabel("Pilih Jadwal Studio");
        lblStudio.setFont(lblFont);
        lblStudio.setForeground(lblColor);
        lblStudio.setBounds(centerX - 240, 150, 220, 30);
        bg.add(lblStudio);

        cbStudio = new JComboBox<>();
        for (Studio s : DataStore.daftarStudio) {
            cbStudio.addItem(s);
        }
        cbStudio.setBounds(centerX, 150, 400, 40);
        cbStudio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        bg.add(cbStudio);

        // Panel Kursi
        panelKursi = new JPanel(new GridLayout(3, 5, 12, 12));
        panelKursi.setBounds(centerX, 230, 500, 280);
        panelKursi.setOpaque(false);
        bg.add(panelKursi);

        // Keterangan Warna
        JPanel legend = new JPanel();
        legend.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
        legend.setBounds(centerX, 530, 500, 40);
        legend.setOpaque(false);

        legend.add(createLegend(WARNA_TERSEDIA, "Kosong"));
        legend.add(createLegend(WARNA_TERISI, "Terisi (Manual)"));

        bg.add(legend);

        // Button Simpan
        ImageIcon normal = new ImageIcon(getClass().getResource("/bioskop/assets/btn_simpan.png"));
        ImageIcon hover = new ImageIcon(getClass().getResource("/bioskop/assets/btn_simpan_hover.png"));

        btnSimpan = new JLabel(normal);
        int btnWidth = normal.getIconWidth();
        int btnHeight = normal.getIconHeight();
        btnSimpan.setBounds((screen.width - btnWidth) / 2, 580, btnWidth, btnHeight);
        btnSimpan.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bg.add(btnSimpan);

        btnSimpan.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnSimpan.setIcon(hover);
            }
            public void mouseExited(MouseEvent e) {
                btnSimpan.setIcon(normal);
            }
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(KursiFrame.this, 
                        "Status kursi berhasil diperbarui di sistem.", 
                        "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Event saat ComboBox ganti pilihan
        cbStudio.addActionListener(e -> loadKursi());
        
        loadKursi();
        setVisible(true);
    }

    // Load Kursi
    private void loadKursi() {
        panelKursi.removeAll();

        Studio studio = (Studio) cbStudio.getSelectedItem();
        if (studio == null) return;

        for (Kursi k : DataStore.daftarKursi) {
            // Memfilter kursi berdasarkan studio yang dipilih di ComboBox
            if (k.getStudio().equals(studio)) {

                JButton btn = new JButton(k.getNoKursi());
                btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
                btn.setForeground(Color.BLACK);
                btn.setFocusPainted(false);
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn.setOpaque(true);
                btn.setBorder(null); // Menghilangkan border agar tampilan clean

                updateWarna(btn, k);

                // Logika Staff: Klik kursi untuk mematikan/menyalakan (Terisi/Kosong)
                btn.addActionListener(e -> {
                    if (k.getStatus().equalsIgnoreCase("Kosong")) {
                        k.setStatus("Terisi");
                    } else {
                        k.setStatus("Kosong");
                    }
                    updateWarna(btn, k);
                });

                panelKursi.add(btn);
            }
        }

        panelKursi.revalidate();
        panelKursi.repaint();
    }

    private void updateWarna(JButton btn, Kursi k) {
        if (k.getStatus().equalsIgnoreCase("Terisi")) {
            btn.setBackground(WARNA_TERISI);
        } else {
            btn.setBackground(WARNA_TERSEDIA);
        }
    }

    private JPanel createLegend(Color c, String text) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        p.setOpaque(false);

        JLabel box = new JLabel();
        box.setPreferredSize(new Dimension(30, 20));
        box.setOpaque(true);
        box.setBackground(c);
        box.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));

        p.add(box);
        p.add(lbl);
        return p;
    }
}