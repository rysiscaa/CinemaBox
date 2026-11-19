package bioskop.view.pelanggan;

import bioskop.model.Studio;
import bioskop.model.Kursi;
import bioskop.util.DataStore;
import bioskop.model.Transaksi;
import bioskop.view.common.BackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PembayaranFrame extends JFrame {

    // Constructor menerima data studio, kursi, dan harga
    public PembayaranFrame(Studio studio,
                           ArrayList<Kursi> kursiDipilih,
                           int totalHarga) {

        setTitle("Pembayaran"); 
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        // BACKGROUND 
        BackgroundPanel bgPanel = new BackgroundPanel("/bioskop/assets/bg_pembayaranframe.jpg");
        bgPanel.setLayout(new GridBagLayout()); // Agar konten di tengah
        setContentPane(bgPanel); // Memasang backgroud sebagai panel utama

        // Pengaturan tata letak komponen untuk GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Kolom
        gbc.gridy = 0; // Baris
        gbc.anchor = GridBagConstraints.CENTER; // Komponen tetap di tegah

        // PANEL UTAMA
        JPanel panelUtama = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create(); 
                g2.setColor(getBackground()); 
                g2.fillRect(0, 0, getWidth(), getHeight()); // Menggambar kotak putih transparan
                g2.dispose(); 
                super.paintComponent(g); // Membersihkan sisa gambar lama
            }
        };
        panelUtama.setLayout(new BoxLayout(panelUtama, BoxLayout.Y_AXIS)); // Susunan Vertikal
        panelUtama.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60)); // Margin Dalam 
        panelUtama.setBackground(new Color(255, 255, 255, 235)); // Putih sedikit transparan
        panelUtama.setOpaque(false); 

        bgPanel.add(panelUtama, gbc); 

        // PANEL INFO FILM 
        JPanel panelInfoFilm = new JPanel(new BorderLayout(30, 0)) {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground()); 
                g.fillRect(0, 0, getWidth(), getHeight()); // Latar Transparan
                super.paintComponent(g); 
            }
        };
        panelInfoFilm.setBackground(new Color(255, 255, 255, 200)); // Mengatur warna latar belakang panel info 
        panelInfoFilm.setOpaque(false); // Memastikan transparansi bekerja dengan benar
        panelInfoFilm.setBorder(BorderFactory.createCompoundBorder( 
                BorderFactory.createLineBorder(Color.GRAY, 2), 
                BorderFactory.createEmptyBorder(20, 20, 20, 20) // Memberi jarak teks dalam border
        ));

        // POSTER
        JLabel lblPoster = new JLabel(); 
        String posterPath = studio.getFilm().getPosterPath() != null
                ? studio.getFilm().getPosterPath()
                : "/bioskop/assets/poster_default.jpg"; 

        try {
            ImageIcon posterIcon = new ImageIcon(getClass().getResource(posterPath)); 
            Image posterImg = posterIcon.getImage().getScaledInstance(180, 270, Image.SCALE_SMOOTH); // Merubah ukuran gambar 
            lblPoster.setIcon(new ImageIcon(posterImg));
        } catch (Exception e) {
            lblPoster.setText("No Poster"); 
        }
        panelInfoFilm.add(lblPoster, BorderLayout.WEST);

        // DETAIL FILM
        JPanel panelDetail = new JPanel();
        panelDetail.setOpaque(false); // Mengatur panel detail agar transparan
        panelDetail.setLayout(new BoxLayout(panelDetail, BoxLayout.Y_AXIS)); // Mengatur teks detail agar tersusun berderet ke bawah

        Font fontJudul = new Font(Font.SERIF, Font.BOLD, 28); 
        Font fontDetail = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        Font fontHarga = new Font(Font.SERIF, Font.BOLD, 22);

        JLabel lblJudul = new JLabel(studio.getFilm().getJudul()); 
        lblJudul.setFont(fontJudul);
        lblJudul.setForeground(Color.BLACK); 
        
        JLabel lblStudio = new JLabel("Studio   : " + studio.getNoStudio()); 
        lblStudio.setFont(fontDetail);
        lblStudio.setForeground(Color.BLACK);
        
        JLabel lblJadwal = new JLabel("Jadwal  : " + studio.getTanggal() + " " + studio.getJam()); 
        lblJadwal.setFont(fontDetail); 
        lblJadwal.setForeground(Color.BLACK);
        
        StringBuilder kursiText = new StringBuilder(); 
        for (Kursi k : kursiDipilih) {
            kursiText.append(k.getNoKursi()).append(" "); // Perulangan untuk mengambil setiap nomor kursi yang dipilih
        }
        JLabel lblKursi = new JLabel("Kursi      : " + kursiText); 
        lblKursi.setFont(fontDetail); 
        lblKursi.setForeground(Color.BLACK); 
        
        JLabel lblTotal = new JLabel("Total Bayar : Rp " + totalHarga); 
        lblTotal.setFont(fontHarga);
        lblTotal.setForeground(Color.BLACK); 

        // Menambahkan komponen-komponen detail ke dalam panel detail 
        panelDetail.add(lblJudul);
        panelDetail.add(Box.createVerticalStrut(15)); // Spasi kosong vertikal
        panelDetail.add(lblStudio);
        panelDetail.add(Box.createVerticalStrut(8)); 
        panelDetail.add(lblJadwal);
        panelDetail.add(Box.createVerticalStrut(8));
        panelDetail.add(lblKursi);
        panelDetail.add(Box.createVerticalStrut(20)); 
        panelDetail.add(lblTotal);

        panelInfoFilm.add(panelDetail, BorderLayout.CENTER); // Panel detail di bagian tengah panel info
        panelUtama.add(panelInfoFilm);
        panelUtama.add(Box.createVerticalStrut(30)); 

        // PANEL METODE PEMBAYARAN 
        JPanel panelMetode = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground()); // Mengatur warna grafik sesuai background
                g.fillRect(0, 0, getWidth(), getHeight()); // Mengisi area panel dengan kotak transparan
                super.paintComponent(g);
            }
        };
        panelMetode.setBackground(new Color(255, 255, 255, 235));
        panelMetode.setOpaque(false);
        panelMetode.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbcM = new GridBagConstraints();
        gbcM.anchor = GridBagConstraints.WEST; // Elemen agar rapat kiri
        gbcM.insets = new Insets(0, 0, 0, 25); // Memberikan jarak antar komponen 

        JLabel lblMetode = new JLabel("Metode Pembayaran");
        lblMetode.setFont(new Font(Font.SERIF, Font.BOLD, 25)); 
        lblMetode.setForeground(Color.BLACK);
        
        gbcM.gridx = 0; 
        gbcM.gridy = 0;
        panelMetode.add(lblMetode, gbcM); 

        // Membuat dropdown untuk memilih cara pembayaran
        JComboBox<String> cbMetode = new JComboBox<>(new String[]{"Cash", "QRIS", "Transfer"});
        cbMetode.setPreferredSize(new Dimension(250, 40));
        cbMetode.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        
        gbcM.gridx = 1; 
        gbcM.insets = new Insets(0, 0, 0, 0); 
        panelMetode.add(cbMetode, gbcM); 

        panelUtama.add(panelMetode); 
        panelUtama.add(Box.createVerticalStrut(40)); 

        // PANEL TOMBOL (AUTO SIZE)
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0)); // Layout horizontal posisi tengah 
        panelTombol.setOpaque(false); 

        // Menyiapkan resource gambar icon untuk tombol 
        ImageIcon iconKembali = new ImageIcon(getClass().getResource("/bioskop/assets/btn_kembali.png"));
        ImageIcon iconKembaliHover = new ImageIcon(getClass().getResource("/bioskop/assets/btn_kembali_hover.png"));
        ImageIcon iconBayar = new ImageIcon(getClass().getResource("/bioskop/assets/btn_bayar.png"));
        ImageIcon iconBayarHover = new ImageIcon(getClass().getResource("/bioskop/assets/btn_bayar_hover.png"));

        // Button Kembali
        JButton btnKembali = new JButton(iconKembali); 
        btnKembali.setRolloverIcon(iconKembaliHover); 
        styleImageButton(btnKembali); 
        btnKembali.addActionListener(e -> {
            dispose(); 
            new KursiFramePelanggan(studio); 
        });

        // Button Bayar Sekarang
        JButton btnBayar = new JButton(iconBayar); 
        btnBayar.setRolloverIcon(iconBayarHover);
        styleImageButton(btnBayar); 
        btnBayar.addActionListener(e -> {
            for (Kursi k : kursiDipilih) k.setStatus("Terisi"); // Merubah status data kursi 
            
            // Inisialisasi objek transaksi baru dengan data yang sudah diinputkan
            Transaksi transaksi = new Transaksi(
                    "KASIR01", 
                    cbMetode.getSelectedItem().toString(), 
                    totalHarga, 
                    kursiDipilih
            );
            DataStore.daftarTransaksi.add(transaksi); // Menyimpan objek transaksi ke dalam DataStore
            dispose(); 
            
            // Menjalankan frame tiket secara aman pada thread GUI (EDT)
            SwingUtilities.invokeLater(() -> new TiketFrame(transaksi, studio));
        });

        panelTombol.add(btnKembali);
        panelTombol.add(btnBayar); 
        panelUtama.add(panelTombol);

        setVisible(true); 
    }

    private void styleImageButton(JButton btn) {
        btn.setBorderPainted(false); // Menghilangkan garis pinggir tombol
        btn.setContentAreaFilled(false); // Menghilangkan warna latar belakang default tombol
        btn.setFocusPainted(false); // Menghilangkan kotak fokus saat tombol diklik
        btn.setOpaque(false); 
        btn.setMargin(new Insets(0, 0, 0, 0)); 
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    }
}