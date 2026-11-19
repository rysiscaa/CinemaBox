package bioskop.view.pelanggan;

import bioskop.model.Film;
import bioskop.util.DataStore;
import bioskop.view.common.BackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PelangganFilmFrame extends JFrame {

    private JPanel panelFilm;

    public PelangganFilmFrame() {

        // Konfigurasi dasar jendela (Judul, Ukuran Fullscreen, dan Operasi Tutup)
        setTitle("Pilih Film");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Pengaturan Background utama menggunakan panel kustom bergambar
        BackgroundPanel bgPanel =
                new BackgroundPanel("/bioskop/assets/bg_filmpelanggan.jpg");
        bgPanel.setLayout(null);
        setContentPane(bgPanel);

        // Inisialisasi label yang berfungsi sebagai tombol filter kategori film
        JLabel btnTayang = createButtonLabel("/bioskop/assets/btn_tayang.png", 10, 80);
        JLabel btnSegera = createButtonLabel("/bioskop/assets/btn_segera.png", 200, 80);

        bgPanel.add(btnTayang);
        bgPanel.add(btnSegera);

        // Inisialisasi area daftar film menggunakan GridLayout 3 kolom dan JScrollPane agar bisa di-scroll
        panelFilm = new JPanel(new GridLayout(0, 3, 40, 40));
        panelFilm.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(panelFilm);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);

        bgPanel.add(scrollPane);

        setVisible(true);

        // Pengaturan posisi JScrollPane secara dinamis agar presisi di tengah layar
        SwingUtilities.invokeLater(() -> {
            int w = bgPanel.getWidth();
            int h = bgPanel.getHeight();

            scrollPane.setBounds(
                    (int) (w * 0.07),
                    180,
                    (int) (w * 0.86),
                    (int) (h * 0.85)
            );
        });

        // Logika interaksi mouse (Klik dan Hover) untuk tombol filter kategori 'Tayang'
        ImageIcon iconTayangNormal = new ImageIcon(getClass().getResource("/bioskop/assets/btn_tayang.png"));
        ImageIcon iconTayangHover = new ImageIcon(getClass().getResource("/bioskop/assets/btn_tayang_hover.png"));

        btnTayang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadFilm("Tayang");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnTayang.setIcon(iconTayangHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnTayang.setIcon(iconTayangNormal);
            }
        });

        // Logika interaksi mouse (Klik dan Hover) untuk tombol filter kategori 'Segera'
        ImageIcon iconSegeraNormal = new ImageIcon(getClass().getResource("/bioskop/assets/btn_segera.png"));
        ImageIcon iconSegeraHover = new ImageIcon(getClass().getResource("/bioskop/assets/btn_segera_hover.png"));

        btnSegera.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadFilm("Segera");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSegera.setIcon(iconSegeraHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSegera.setIcon(iconSegeraNormal);
            }
        });

        // Menampilkan daftar film kategori 'Tayang' secara otomatis saat aplikasi dibuka
        loadFilm("Tayang");
    }

    // Fungsi pembantu untuk membuat objek label bergambar dengan kursor tangan
    private JLabel createButtonLabel(String path, int x, int y) {
        JLabel label = new JLabel();
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        label.setIcon(icon);
        label.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        return label;
    }

    // Fungsi untuk memfilter dan memuat data film dari DataStore ke dalam panel film
    private void loadFilm(String status) {
        panelFilm.removeAll();
        for (Film film : DataStore.daftarFilm) {
            if (film.getStatus().equalsIgnoreCase(status)) {
                panelFilm.add(createFilmCard(film));
            }
        }
        panelFilm.revalidate();
        panelFilm.repaint();
    }

    // Fungsi untuk membuat desain kartu (Poster) tiap film beserta efek zoom saat kursor mendekat
    private JPanel createFilmCard(Film film) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(false);

        JLabel lblPoster = new JLabel();
        lblPoster.setAlignmentX(Component.CENTER_ALIGNMENT);

        String path = film.getPosterPath() != null ? film.getPosterPath() : "/bioskop/assets/poster_default.jpg";
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(220, 330, Image.SCALE_SMOOTH);
        lblPoster.setIcon(new ImageIcon(img));

        lblPoster.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblPoster.setIcon(new ImageIcon(icon.getImage().getScaledInstance(240, 360, Image.SCALE_SMOOTH)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                lblPoster.setIcon(new ImageIcon(icon.getImage().getScaledInstance(220, 330, Image.SCALE_SMOOTH)));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                new DetailFilmFrame(film);
                dispose();
            }
        });
        
        card.add(lblPoster);
        return card;
    }
}