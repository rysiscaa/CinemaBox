package bioskop.view.pelanggan;

import bioskop.view.common.BackgroundPanel;
import javax.swing.*;
import java.awt.*;

public class LoginPelangganFrame extends JFrame {

    public LoginPelangganFrame() {
        // Konfigurasi dasar jendela login pelanggan
        setTitle("Login Pelanggan");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Pengaturan background menggunakan gambar khusus login pelanggan
        BackgroundPanel bgPanel =
                new BackgroundPanel("/bioskop/assets/bg_loginpelanggan.jpg");
        bgPanel.setLayout(null);
        setContentPane(bgPanel);

        // Memuat resource gambar untuk field input dan tombol
        ImageIcon inputIcon = loadIcon("/bioskop/assets/input.png");
        ImageIcon btnIcon = loadIcon("/bioskop/assets/btn_masuk.png");
        ImageIcon btnHover = loadIcon("/bioskop/assets/btn_masuk_hover.png");

        // Konfigurasi input email dengan latar belakang gambar input.png
        JTextField tfEmail = new JTextField();
        styleTextField(tfEmail);

        JLabel bgEmail = new JLabel(inputIcon);
        bgEmail.setLayout(null);
        bgEmail.add(tfEmail);

        // Konfigurasi input password dengan latar belakang gambar input.png
        JPasswordField pfPassword = new JPasswordField();
        styleTextField(pfPassword);

        JLabel bgPassword = new JLabel(inputIcon);
        bgPassword.setLayout(null);
        bgPassword.add(pfPassword);

        // Inisialisasi tombol masuk dengan pengaturan visual kustom
        JButton btnMasuk = new JButton(btnIcon);
        styleButton(btnMasuk);

        // Logika perubahan icon saat kursor mouse masuk dan keluar tombol (efek hover)
        btnMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnMasuk.setIcon(btnHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnMasuk.setIcon(btnIcon);
            }
        });

        // Logika validasi login dan perpindahan ke frame daftar film
        btnMasuk.addActionListener(e -> {
            String email = tfEmail.getText().trim();
            String pass = new String(pfPassword.getPassword()).trim();

            if (email.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Email dan kata sandi harus diisi",
                        "Login Gagal",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // Membuka halaman katalog film untuk pelanggan
            new PelangganFilmFrame();
            dispose();
        });

        // Menambahkan semua komponen input ke panel background utama
        bgPanel.add(bgEmail);
        bgPanel.add(bgPassword);
        bgPanel.add(btnMasuk);

        setVisible(true);

        // Pengaturan posisi komponen secara dinamis tepat di tengah layar
        SwingUtilities.invokeLater(() -> {

            int screenW = bgPanel.getWidth();
            int screenH = bgPanel.getHeight();

            int startX = (screenW - inputIcon.getIconWidth()) / 2 + 270;
            int startY = (int) (screenH * 0.40);

            // Penempatan posisi field email
            bgEmail.setBounds(
                    startX,
                    startY,
                    inputIcon.getIconWidth(),
                    inputIcon.getIconHeight()
            );
            tfEmail.setBounds(
                    20, 15,
                    inputIcon.getIconWidth() - 40,
                    inputIcon.getIconHeight() - 30
            );

            // Penempatan posisi field password (berada di bawah email)
            bgPassword.setBounds(
                    startX,
                    startY + inputIcon.getIconHeight() + 70,
                    inputIcon.getIconWidth(),
                    inputIcon.getIconHeight()
            );
            pfPassword.setBounds(
                    20, 15,
                    inputIcon.getIconWidth() - 40,
                    inputIcon.getIconHeight() - 30
            );

            // Penempatan posisi tombol masuk (berada paling bawah)
            btnMasuk.setBounds(
                    startX,
                    startY + inputIcon.getIconHeight() * 2 + 130,
                    btnIcon.getIconWidth(),
                    btnIcon.getIconHeight()
            );
        });
    }

    // Fungsi untuk menyamakan tampilan teks pada field input (transparan)
    private void styleTextField(JTextField tf) {
        tf.setBorder(null);
        tf.setOpaque(false);
        tf.setFont(new Font("Segoe UI", Font.BOLD, 22));
        tf.setForeground(Color.BLACK);
    }

    // Fungsi untuk membersihkan style tombol default agar hanya gambar yang terlihat
    private void styleButton(JButton btn) {
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Fungsi untuk memuat file gambar secara aman dengan validasi null
    private ImageIcon loadIcon(String path) {
        java.net.URL url = getClass().getResource(path);
        if (url == null) {
            System.out.println("GAMBAR TIDAK DITEMUKAN: " + path);
            return new ImageIcon();
        }
        return new ImageIcon(url);
    }
}