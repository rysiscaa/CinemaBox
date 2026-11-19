package bioskop.view.pelanggan;

import bioskop.model.Studio;
import bioskop.model.Kursi;
import bioskop.util.DataStore;
import bioskop.model.Transaksi;
import bioskop.view.common.MenuUtamaFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TiketFrame extends JFrame { 

    // Constructor menerima objek Transaksi dan Studio 
    public TiketFrame(Transaksi t, Studio s) {

        setTitle("Tiket Bioskop"); 
        setSize(400, 300); // Mengatur ukuran jendela 
        setLocationRelativeTo(null); // Jendela muncul di tengah layar

        // MENGAMBIL DATA KURSI
        String kursiText = ""; 
        for (Kursi k : t.getKursi()) { // looping setiap kursi yg ada pada transaksi
            kursiText += k.getNoKursi() + " "; // Menggabungkan setiap no kursi
        }
 
        // KOMPONEN TAMPILAN TIKET
        JTextArea area = new JTextArea(); 
        area.setEditable(false); // Teks tiket tidak bisa diedit/diketik 
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        // Menggabungkan data transaksi dan film
        area.setText(
                "===== TIKET BIOSKOP =====\n\n" +
                "Kode Booking : " + t.getKodeBooking() + "\n" + 
                "Film         : " + s.getFilm().getJudul() + "\n" + 
                "Studio       : " + s.getNoStudio() + "\n" + 
                "Jadwal       : " + s.getTanggal() + " " + s.getJam() + "\n" + 
                "Metode Bayar : " + t.getMetodeBayar() + "\n" + 
                "Total Bayar  : Rp " + t.getTotalHarga() + "\n\n" + 
                "Selamat Menonton!" // Pesan penutup
        );

        // TOMBOL NAVIGASI
        JButton btnSelesai = new JButton("Selesai"); 

        // Event Listener
        btnSelesai.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Terima kasih, selamat menonton!");
            
            new MenuUtamaFrame(); 
            dispose(); 
        });

        // MENYUSUN KOMPONEN PADA FRAME
        add(new JScrollPane(area)); 
        add(btnSelesai, BorderLayout.SOUTH); 
        
        setVisible(true); 
    }
}