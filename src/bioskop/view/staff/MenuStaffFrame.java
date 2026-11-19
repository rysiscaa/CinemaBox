package bioskop.view.staff;

import bioskop.view.staff.StudioFrame;
import bioskop.view.staff.KursiFrame;
import bioskop.view.staff.FilmFrame;
import bioskop.model.Staff;
import bioskop.view.common.BackgroundPanel;
import bioskop.view.common.MenuUtamaFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuStaffFrame extends JFrame {

    public MenuStaffFrame(Staff staff) {

        setTitle("Menu Staff - " + staff.getJabatan());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        BackgroundPanel bgPanel =
                new BackgroundPanel("/bioskop/assets/bg_menustaff.jpg");
        bgPanel.setLayout(null); // Untuk menentukan posisi tombol secara manual
        setContentPane(bgPanel);

        // Button Image
        JButton btnFilm = createButton(
                "/bioskop/assets/btn_kelola_film.png",
                "/bioskop/assets/btn_kelola_film_hover.png"
        );

        JButton btnStudio = createButton(
                "/bioskop/assets/btn_kelola_studio.png",
                "/bioskop/assets/btn_kelola_studio_hover.png"
        );

        JButton btnKursi = createButton(
                "/bioskop/assets/btn_kelola_kursi.png",
                "/bioskop/assets/btn_kelola_kursi_hover.png"
        );

        JButton btnTransaksi = createButton(
                "/bioskop/assets/btn_transaksi.png",
                "/bioskop/assets/btn_transaksi_hover.png"
        );

        JButton btnKeluar = createButton(
                "/bioskop/assets/btn_keluar.png",
                "/bioskop/assets/btn_keluar_hover.png"
        );

        // Hak akses kasir
        if (staff.isKasir()) {
            btnFilm.setEnabled(false);
            btnStudio.setEnabled(false);
            btnKursi.setEnabled(false);
        }

        // Action
        btnFilm.addActionListener(e -> new FilmFrame()); // Membuka frame pengelolaan data film
        btnStudio.addActionListener(e -> new StudioFrame());
        btnKursi.addActionListener(e -> new KursiFrame());
        btnTransaksi.addActionListener(e -> new TransaksiStaffFrame());
        btnKeluar.addActionListener(e -> {
            new MenuUtamaFrame();
            dispose();
        });

        // Menambah tombool ke dalam panel bg
        bgPanel.add(btnFilm);
        bgPanel.add(btnStudio);
        bgPanel.add(btnKursi);
        bgPanel.add(btnTransaksi);
        bgPanel.add(btnKeluar);

        setVisible(true);

        // Posisi Dinamis
        SwingUtilities.invokeLater(() -> {

            int w = bgPanel.getWidth(); // Mendapatkan lebar layar saat ini
            int h = bgPanel.getHeight();

            int btnW = btnFilm.getIcon().getIconWidth(); // Mengambil lebar tombol berdasarkan ukuran gambar
            int btnH = btnFilm.getIcon().getIconHeight();

            int centerX = (w - btnW) / 2; // Menghitung koordinat X agar tombol tepat di tengah horizontal
            int startY = (int) (h * 0.32); // Menentukan titik awal Y (mulai dari 32% tinggi layar)
            int gap = 85; // Jarak antar tombol (pixel)

            // Mengatur lokasi dan ukuran setiap tombol
            btnFilm.setBounds(centerX, startY, btnW, btnH);
            btnStudio.setBounds(centerX, startY + gap, btnW, btnH);
            btnKursi.setBounds(centerX, startY + gap * 2, btnW, btnH);
            btnTransaksi.setBounds(centerX, startY + gap * 3, btnW, btnH);
            btnKeluar.setBounds(centerX, startY + gap * 4, btnW, btnH);

        });
    }

    // Button image + hover 
    private JButton createButton(String normal, String hover) {
        ImageIcon icon = new ImageIcon(getClass().getResource(normal));
        ImageIcon hoverIcon = new ImageIcon(getClass().getResource(hover));

        JButton btn = new JButton(icon);
        btn.setBorderPainted(false); // Menghilangkan garis tepi tombol
        btn.setContentAreaFilled(false); // Menghilangkan warna background bawaan tombol
        btn.setFocusPainted(false); // Menghilangkan garis fokus saat diklik
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            // Mengganti gambar saat kursor di area tombol
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setIcon(hoverIcon);
            }

            // Mengembalikan gambar saat kursor keluar dari area gambar
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setIcon(icon);
            }
        });

        return btn;
    }
}
