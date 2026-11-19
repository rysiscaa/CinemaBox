package bioskop.view.pelanggan;

import bioskop.view.pelanggan.DetailFilmFrame;
import bioskop.util.DataStore;
import bioskop.model.Studio;
import bioskop.model.Kursi;
import bioskop.view.common.BackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class KursiFramePelanggan extends JFrame {

    private Studio studio;
    private ArrayList<Kursi> kursiDipilih = new ArrayList<>();
    private JLabel lblTotalHarga;

    private final int HARGA_TIKET = 45000;
    private JPanel panelKursi;

    private static final Color WARNA_TERSEDIA = Color.LIGHT_GRAY;
    private static final Color WARNA_TERISI   = Color.WHITE;
    private static final Color WARNA_DIPILIH  = new Color(66, 133, 244);

    public KursiFramePelanggan(Studio studio) {
        this.studio = studio;

        setTitle("Pilih Kursi - " + studio.getNoStudio());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // BACKGROUND 
        BackgroundPanel bgPanel = new BackgroundPanel("/bioskop/assets/bg_kursipelanggan.jpg");
        bgPanel.setLayout(new BorderLayout());
        setContentPane(bgPanel);

        // PANEL UTAMA 
        JPanel panelUtama = new JPanel(new BorderLayout(20, 20));
        panelUtama.setOpaque(false);
        panelUtama.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        bgPanel.add(panelUtama, BorderLayout.CENTER);

        // INFO FILM
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setOpaque(false);

        JLabel lblJudul = new JLabel(studio.getFilm().getJudul());
        lblJudul.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        lblJudul.setForeground(Color.WHITE);

        JLabel lblStudio = new JLabel("Studio: " + studio.getNoStudio());
        lblStudio.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        lblStudio.setForeground(Color.WHITE);

        JLabel lblJadwal = new JLabel("Jadwal: " + studio.getTanggal() + " " + studio.getJam());
        lblJadwal.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        lblJadwal.setForeground(Color.WHITE);

        panelInfo.add(lblJudul);
        panelInfo.add(Box.createVerticalStrut(5));
        panelInfo.add(lblStudio);
        panelInfo.add(Box.createVerticalStrut(2));
        panelInfo.add(lblJadwal);

        panelUtama.add(panelInfo, BorderLayout.NORTH);

        // PANEL KURSI
        panelKursi = new JPanel(new GridLayout(3, 5, 12, 12)); 
        panelKursi.setOpaque(false);
        panelKursi.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.WHITE),
                        "Pilih Kursi",
                        0, 0,
                        new Font(Font.SERIF, Font.BOLD, 22),
                        Color.WHITE
                )
        );
        panelUtama.add(panelKursi, BorderLayout.CENTER);

        loadKursi();

        // LEGEND
        JPanel legend = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        legend.setOpaque(false);
        legend.add(createLegend(WARNA_TERSEDIA, "Tersedia"));
        legend.add(createLegend(WARNA_TERISI, "Terisi"));
        legend.add(createLegend(WARNA_DIPILIH, "Dipilih"));
        panelUtama.add(legend, BorderLayout.SOUTH);

        // PANEL BAWAH (TOTAL HARGA & TOMBOL)
        JPanel panelBawah = new JPanel(new BorderLayout());
        panelBawah.setOpaque(false);
        panelBawah.setBorder(BorderFactory.createEmptyBorder(10, 30, 15, 30));

        // Label Harga di sebelah Kiri (WEST)
        lblTotalHarga = new JLabel("Total Harga: Rp 0");
        lblTotalHarga.setFont(new Font(Font.SERIF, Font.BOLD, 24));
        lblTotalHarga.setForeground(Color.WHITE);
        panelBawah.add(lblTotalHarga, BorderLayout.WEST);

        // Panel Tombol di sebelah Tengah-Kanan (CENTER)
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new BoxLayout(panelTombol, BoxLayout.X_AXIS));
        panelTombol.setOpaque(false);

        ImageIcon iconKembali = new ImageIcon(getClass().getResource("/bioskop/assets/btn_kembali.png"));
        ImageIcon iconKembaliHover = new ImageIcon(getClass().getResource("/bioskop/assets/btn_kembali_hover.png"));
        ImageIcon iconLanjut = new ImageIcon(getClass().getResource("/bioskop/assets/btn_lanjutkan.png"));
        ImageIcon iconLanjutHover = new ImageIcon(getClass().getResource("/bioskop/assets/btn_lanjutkan_hover.png"));

        // TOMBOL KEMBALI
        JButton btnKembali = new JButton(iconKembali);
        btnKembali.setRolloverIcon(iconKembaliHover);
        styleImageButton(btnKembali);
        btnKembali.addActionListener(e -> {
            new DetailFilmFrame(studio.getFilm()); 
            dispose();
        });

        // TOMBOL LANJUT
        JButton btnLanjut = new JButton(iconLanjut);
        btnLanjut.setRolloverIcon(iconLanjutHover);
        styleImageButton(btnLanjut);
        btnLanjut.addActionListener(e -> {
            if (kursiDipilih.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih minimal 1 kursi!");
                return;
            }
            new PembayaranFrame(studio, kursiDipilih, getTotalHarga());
            dispose();
        });

        // MENYUSUN POSISI
        panelTombol.add(Box.createHorizontalGlue()); // Mendorong tombol ke kanan
        panelTombol.add(btnKembali);                 // Tombol Kembali
        panelTombol.add(Box.createRigidArea(new Dimension(5, 0))); // Jarak antar tombol
        panelTombol.add(btnLanjut);                  // Tombol Lanjut

        // Gunakan BorderLayout.CENTER agar panelTombol melebar dan Glue bisa bekerja
        panelBawah.add(panelTombol, BorderLayout.EAST); 
        
        bgPanel.add(panelBawah, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void styleImageButton(JButton btn) {
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void loadKursi() {
        panelKursi.removeAll();
        for (Kursi k : DataStore.daftarKursi) {
            if (k.getStudio().equals(this.studio)) {
                JButton btn = new JButton(k.getNoKursi());
                btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
                btn.setForeground(Color.BLACK);
                btn.setFocusPainted(false);
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn.setOpaque(true);
                btn.setBorder(null);

                if (k.getStatus().equalsIgnoreCase("Terisi")) {
                    btn.setEnabled(false);
                    btn.setBackground(WARNA_TERISI);
                } else {
                    btn.setBackground(WARNA_TERSEDIA);
                }

                btn.addActionListener(e -> {
                    if (kursiDipilih.contains(k)) {
                        kursiDipilih.remove(k);
                        btn.setBackground(WARNA_TERSEDIA);
                    } else {
                        kursiDipilih.add(k);
                        btn.setBackground(WARNA_DIPILIH);
                    }
                    updateTotalHarga();
                });
                panelKursi.add(btn);
            }
        }
        panelKursi.revalidate();
        panelKursi.repaint();
    }

    private void updateTotalHarga() {
        lblTotalHarga.setText("Total Harga: Rp " + getTotalHarga());
    }

    private int getTotalHarga() {
        return kursiDipilih.size() * HARGA_TIKET;
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
        lbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lbl.setForeground(Color.WHITE);
        p.add(box);
        p.add(lbl);
        return p;
    }
}