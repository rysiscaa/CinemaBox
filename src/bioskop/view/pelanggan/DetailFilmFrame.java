package bioskop.view.pelanggan;

import bioskop.view.pelanggan.PelangganFilmFrame;
import bioskop.model.Film;
import bioskop.model.Studio;
import bioskop.util.DataStore;
import bioskop.view.common.BackgroundPanel;

import javax.swing.*;
import java.awt.*;

public class DetailFilmFrame extends JFrame {

    private Film film;

    public DetailFilmFrame(Film film) {
        this.film = film;

        setTitle("Detail Film");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Background
        BackgroundPanel bgPanel = new BackgroundPanel("/bioskop/assets/bg_detailfilm.jpg");
        bgPanel.setLayout(new BorderLayout());
        setContentPane(bgPanel);

        // Panel Utama
        JPanel panelUtama = new JPanel(new BorderLayout(20, 20));
        panelUtama.setOpaque(false);
        panelUtama.setBorder(BorderFactory.createEmptyBorder(50, 60, 40, 60));
        bgPanel.add(panelUtama, BorderLayout.CENTER);

        // Poster + Detail + Sinopsis
        JPanel panelKiri = new JPanel(new BorderLayout(30, 30));
        panelKiri.setOpaque(false);

        // Poster
        JLabel lblPoster = new JLabel();
        String posterPath = film.getPosterPath() != null
                ? film.getPosterPath()
                : "/bioskop/assets/poster_default.jpg";

        ImageIcon posterIcon = new ImageIcon(getClass().getResource(posterPath));
        Image posterImg = posterIcon.getImage().getScaledInstance(160, 250, Image.SCALE_SMOOTH);
        lblPoster.setIcon(new ImageIcon(posterImg));
        panelKiri.add(lblPoster, BorderLayout.WEST);

        // Detail Film
        JPanel panelDetail = new JPanel();
        panelDetail.setOpaque(false);
        panelDetail.setLayout(new BoxLayout(panelDetail, BoxLayout.Y_AXIS));
        panelDetail.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        JLabel lblJudul = new JLabel(film.getJudul());
        lblJudul.setFont(new Font("Serif", Font.BOLD, 40));

        JLabel lblGenre = new JLabel("Genre        : " + film.getGenre());
        JLabel lblDurasi = new JLabel("Durasi       : " + film.getDurasi() + " menit");
        JLabel lblSutradara = new JLabel("Sutradara  : " + film.getSutradara());
        JLabel lblRating = new JLabel("Rating       : " + film.getRating());

        Font fontDetail = new Font("SansSerif", Font.PLAIN, 20);
        lblGenre.setFont(fontDetail);
        lblDurasi.setFont(fontDetail);
        lblSutradara.setFont(fontDetail);
        lblRating.setFont(fontDetail);

        panelDetail.add(lblJudul);
        panelDetail.add(Box.createVerticalStrut(20));
        panelDetail.add(lblGenre);
        panelDetail.add(Box.createVerticalStrut(15));
        panelDetail.add(lblDurasi);
        panelDetail.add(Box.createVerticalStrut(15));
        panelDetail.add(lblSutradara);
        panelDetail.add(Box.createVerticalStrut(15));
        panelDetail.add(lblRating);

        panelKiri.add(panelDetail, BorderLayout.CENTER);

        // Sinopsis
        JTextArea taSinopsis = new JTextArea(film.getSinopsis());
        taSinopsis.setLineWrap(true);
        taSinopsis.setWrapStyleWord(true);
        taSinopsis.setEditable(false);
        taSinopsis.setFont(new Font("SansSerif", Font.PLAIN, 20));
        taSinopsis.setPreferredSize(new Dimension(300, 230));
        taSinopsis.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Sinopsis", 0, 0, new Font("Serif", Font.BOLD, 22)
        ));

        panelKiri.add(taSinopsis, BorderLayout.SOUTH);
        panelUtama.add(panelKiri, BorderLayout.CENTER);

        // Jadwal Tayang
        JPanel panelKanan = new JPanel();
        panelKanan.setOpaque(false);
        panelKanan.setLayout(new BoxLayout(panelKanan, BoxLayout.Y_AXIS));
        panelKanan.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 60));

        JLabel lblJadwalHeader = new JLabel("Jadwal Tayang");
        lblJadwalHeader.setFont(new Font("Serif", Font.BOLD, 40));
        lblJadwalHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelKanan.add(lblJadwalHeader);
        panelKanan.add(Box.createVerticalStrut(25));

        ImageIcon jadwalNormal = new ImageIcon(getClass().getResource("/bioskop/assets/btn_jadwal.png"));
        ImageIcon jadwalHover = new ImageIcon(getClass().getResource("/bioskop/assets/btn_jadwal_hover.png"));

        boolean adaJadwal = false;
        for (Studio studio : DataStore.daftarStudio) {
            if (studio.getFilm().getJudul().equals(film.getJudul())) {
                adaJadwal = true;
                JButton btnJadwal = new JButton(studio.getTanggal() + " | " + studio.getJam() + " | " + studio.getNoStudio());
                btnJadwal.setIcon(new ImageIcon(jadwalNormal.getImage().getScaledInstance(300, 65, Image.SCALE_SMOOTH)));
                btnJadwal.setRolloverIcon(new ImageIcon(jadwalHover.getImage().getScaledInstance(300, 65, Image.SCALE_SMOOTH)));
                
                btnJadwal.setAlignmentX(Component.CENTER_ALIGNMENT); 
                btnJadwal.setHorizontalTextPosition(JButton.CENTER);
                btnJadwal.setForeground(Color.WHITE);
                btnJadwal.setFont(new Font("SansSerif", Font.BOLD, 18));
                btnJadwal.setBorderPainted(false);
                btnJadwal.setContentAreaFilled(false);
                btnJadwal.setFocusPainted(false);
                btnJadwal.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnJadwal.addActionListener(e -> {
                    new KursiFramePelanggan(studio);
                    dispose();
                });
                panelKanan.add(btnJadwal);
                panelKanan.add(Box.createVerticalStrut(15));
            }
        }

        if (!adaJadwal) {
            JLabel lblKosong = new JLabel("Belum ada jadwal tayang");
            lblKosong.setFont(fontDetail);
            lblKosong.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelKanan.add(Box.createVerticalStrut(20));
            panelKanan.add(lblKosong);
        }
        panelUtama.add(panelKanan, BorderLayout.EAST);

        // Button Kembali
        JPanel panelBawah = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelBawah.setOpaque(false);
        panelBawah.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));

        ImageIcon backNormal = new ImageIcon(getClass().getResource("/bioskop/assets/btn_kembali.png"));
        ImageIcon backHover = new ImageIcon(getClass().getResource("/bioskop/assets/btn_kembali_hover.png"));

        JButton btnBack = new JButton();
        btnBack.setIcon(backNormal);
        btnBack.setRolloverIcon(backHover);
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        btnBack.setMargin(new Insets(0, 0, 0, 0));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnBack.addActionListener(e -> {
            new PelangganFilmFrame();
            dispose();
        });

        panelBawah.add(btnBack);
        bgPanel.add(panelBawah, BorderLayout.SOUTH);

        setVisible(true);
    }
}