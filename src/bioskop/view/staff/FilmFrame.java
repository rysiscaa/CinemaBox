package bioskop.view.staff;

import bioskop.model.Film;
import bioskop.util.DataStore;
import bioskop.view.common.BackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// JFrame untuk mengedit data film
public class FilmFrame extends JFrame {

    // Komponen UI (Inputan dan Label)
    private JComboBox<Film> cbFilm;  // Daftar drop-down film
    private JComboBox<String> cbStatus;  // Pilihan status
    private JTextField tfJudul, tfDurasi, tfSutradara, tfRating;
    private JTextArea taSinopsis;
    private JLabel lblPoster;

    
    public FilmFrame() {

        setTitle("Kelola Data Film");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        BackgroundPanel bg =
                new BackgroundPanel("/bioskop/assets/bg_film.jpg");
        bg.setLayout(null); // Posisi diatur manual dengan setbounds
        setContentPane(bg); // memasang backgroud sebagai panel utama

        // Untuk mempermudah pengaturan tata letak (Layouting)
        Font lblFont = new Font("Segoe UI", Font.BOLD, 20);
        Color lblColor = Color.WHITE;
        int xLabel = 120; // Label Text
        int xColon = 300; // Tanda titik
        int xField = 330; // Kotak input
        int fieldW = 620;  
        int fieldH = 36;
        int y = 125; // Posisi Y awal (atas)
        int gap = 55; // Jarak antar baris input

        // Film
        addLabel(bg, "Pilih Film", xLabel, y, lblFont, lblColor);
        addColon(bg, xColon, y);
        cbFilm = new JComboBox<>();
        // Mengisi pilihan dengan data film yang ada di DataStore
        for (Film f : DataStore.daftarFilm) cbFilm.addItem(f);
        cbFilm.setBounds(xField, y, fieldW, fieldH);
        bg.add(cbFilm);
        y += gap; // Menambah nilai Y agar baris berikutnya turun ke bawah

        // Judul
        addLabel(bg, "Judul", xLabel, y, lblFont, lblColor);
        addColon(bg, xColon, y);
        tfJudul = addField(bg, xField, y, fieldW, fieldH);
        y += gap;

        // Durasi
        addLabel(bg, "Durasi", xLabel, y, lblFont, lblColor);
        addColon(bg, xColon, y);
        tfDurasi = addField(bg, xField, y, fieldW, fieldH);
        y += gap;

        // Sutradara
        addLabel(bg, "Sutradara", xLabel, y, lblFont, lblColor);
        addColon(bg, xColon, y);
        tfSutradara = addField(bg, xField, y, fieldW, fieldH);
        y += gap;

        // Rating
        addLabel(bg, "Rating", xLabel, y, lblFont, lblColor);
        addColon(bg, xColon, y);
        tfRating = addField(bg, xField, y, fieldW, fieldH);
        y += gap;

        // Status Tayang
        addLabel(bg, "Status", xLabel, y, lblFont, lblColor);
        addColon(bg, xColon, y);
        cbStatus = new JComboBox<>(new String[]{"Tayang", "Segera"});
        cbStatus.setBounds(xField, y, fieldW, fieldH);
        bg.add(cbStatus);
        y += gap;

        // Sinopsis
        addLabel(bg, "Sinopsis", xLabel, y, lblFont, lblColor);
        addColon(bg, xColon, y);
        taSinopsis = new JTextArea();
        taSinopsis.setLineWrap(true); //Ganti baris 
        taSinopsis.setWrapStyleWord(true); // Kata tidak terpotong saat ganti baris
        JScrollPane sp = new JScrollPane(taSinopsis);
        sp.setBounds(xField, y, fieldW, 160);
        bg.add(sp);

        // Poster
        lblPoster = new JLabel();
        lblPoster.setBounds(980, 150, 260, 390);
        lblPoster.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        bg.add(lblPoster);

        // Button Simpan
        ImageIcon iconNormal = new ImageIcon(
                getClass().getResource("/bioskop/assets/btn_simpan.png"));
        ImageIcon iconHover = new ImageIcon(
                getClass().getResource("/bioskop/assets/btn_simpan_hover.png"));

        JButton btnSimpan = new JButton(iconNormal);
        btnSimpan.setBounds(980, 560, 260, 48);
        btnSimpan.setBorderPainted(false); // Menghilangkan garis border tombol
        btnSimpan.setContentAreaFilled(false); // Menghilangkan background standar tombol
        btnSimpan.setFocusPainted(false);  // Menghilangkan garis biru saat diklik
        btnSimpan.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Kursor berubah jadi tangan

        // Hover
        btnSimpan.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnSimpan.setIcon(iconHover);
            }

            public void mouseExited(MouseEvent e) {
                btnSimpan.setIcon(iconNormal);
            }
        });

        bg.add(btnSimpan);

        // Event
        cbFilm.addActionListener(e -> tampilkanFilm());
        btnSimpan.addActionListener(e -> simpanFilm());

        tampilkanFilm();
        setVisible(true); // Munculkan jendela ke layar
    }

    // Helper
    
    // Membuat label teks di panel
    private void addLabel(JPanel bg, String text, int x, int y, Font f, Color c) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(f);
        lbl.setForeground(c); //Mengubah warna teks
        lbl.setBounds(x, y, 180, 30);
        bg.add(lbl);
    }

    // Membuat (:) otomatis
    private void addColon(JPanel bg, int x, int y) {
        JLabel colon = new JLabel(":");
        colon.setFont(new Font("Segoe UI", Font.BOLD, 20));
        colon.setForeground(Color.WHITE);
        colon.setBounds(x, y, 20, 30);
        bg.add(colon);
    }

    // Membuat kotak input teks otomatis
    private JTextField addField(JPanel bg, int x, int y, int w, int h) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, w, h);
        bg.add(tf);
        return tf;
    }

    // Data
    
    // Fungsi untuk mengambil data dari objek Film dan menampilkannya ke form input
    private void tampilkanFilm() {
        Film f = (Film) cbFilm.getSelectedItem();
        if (f == null) return;

        tfJudul.setText(f.getJudul());
        tfDurasi.setText(String.valueOf(f.getDurasi()));
        tfSutradara.setText(f.getSutradara());
        tfRating.setText(String.valueOf(f.getRating()));
        taSinopsis.setText(f.getSinopsis());
        cbStatus.setSelectedItem(f.getStatus());

        updatePoster(f);
    }

    // Fungsi untuk memuat gambar poster dan menyesuaikan ukurannya (Scaling)
    private void updatePoster(Film f) {
        String path = f.getPosterPath() != null
                ? f.getPosterPath()
                : "/bioskop/assets/poster_default.jpg";

        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(
                260, 390, Image.SCALE_SMOOTH);

        lblPoster.setIcon(new ImageIcon(img));
    }

    // Fungsi untuk menyimpan perubahan dari form ke objek Film
    private void simpanFilm() {
        Film f = (Film) cbFilm.getSelectedItem();
        if (f == null) return;

        try {
            // Mengambil input dari form
            f.setJudul(tfJudul.getText());
            f.setDurasi(Integer.parseInt(tfDurasi.getText()));
            f.setSutradara(tfSutradara.getText());

            f.setRating(tfRating.getText()); 

            f.setSinopsis(taSinopsis.getText());
            f.setStatus(cbStatus.getSelectedItem().toString());

            JOptionPane.showMessageDialog(this, "Data Film berhasil diperbarui!");

        } catch (NumberFormatException ex) {
            // Error hanya muncul jika tfDurasi bukan angka
            JOptionPane.showMessageDialog(this, "Durasi harus berupa angka!");
        }
    }
}
