package bioskop.view.staff;

import bioskop.view.common.BackgroundPanel;
import bioskop.model.Transaksi;
import bioskop.util.DataStore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TransaksiStaffFrame extends JFrame {

    public TransaksiStaffFrame() {

        setTitle("Data Transaksi");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Background
        BackgroundPanel bgPanel =
                new BackgroundPanel("/bioskop/assets/bg_transaksistaff.jpg");
        bgPanel.setLayout(null);
        setContentPane(bgPanel);

        // Tabel
        String[] kolom = {
                "Kode Booking",
                "ID Kasir",
                "Tanggal",
                "Metode Bayar",
                "Total Harga"
        };

        // Wadah penampung data untuk JTable
        DefaultTableModel model = new DefaultTableModel(kolom, 0);

        //Mengambil data dari datastore
        for (Transaksi t : DataStore.daftarTransaksi) {
            model.addRow(new Object[]{
                    t.getKodeBooking(),
                    t.getIdKasir(),
                    t.getTanggal(),
                    t.getMetodeBayar(),
                    "Rp " + t.getTotalHarga()
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );

        // Agar tabel bisa di-scroll jika datanya sangat banyak
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        bgPanel.add(scrollPane);

        setVisible(true);

        // Posisi Responsive (Layouting)
        SwingUtilities.invokeLater(() -> {

            int w = bgPanel.getWidth();
            int h = bgPanel.getHeight();

            // Mengatur posisi tabel
            scrollPane.setBounds(
                    // Layar
                    (int) (w * 0.08), 
                    (int) (h * 0.26),
                    // Tabel
                    (int) (w * 0.84),
                    (int) (h * 0.6)
            );
        });
    }
}
