package bioskop.model;


import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Transaksi {
    private String kodeBooking;
    private String idKasir;
    private Date tanggal;
    private String metodeBayar;
    private int totalHarga;
    private ArrayList<Kursi> kursi;

    // Constructor dijalankan saat membuat nota/transaksi baru
    public Transaksi(String idKasir,
                     String metodeBayar,
                     int totalHarga,
                     ArrayList<Kursi> kursi) {

        this.kodeBooking = generateKode(); 
        this.idKasir = idKasir;
        this.tanggal = new Date();
        this.metodeBayar = metodeBayar;
        this.totalHarga = totalHarga;
        this.kursi = kursi;
    }
    
    // Hanya digunakan di dalam class ini untuk membuat kode unik
    private String generateKode() {
        return "BK" + System.currentTimeMillis();
    }
    
    // GETTER
    public String getKodeBooking() { 
        return kodeBooking; 
    }
    public String getIdKasir() { 
        return idKasir; 
    }
    public String getTanggal() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(tanggal);
    }
    public String getMetodeBayar() { 
        return metodeBayar; 
    }
    public int getTotalHarga() { 
        return totalHarga; 
    }
    public ArrayList<Kursi> getKursi() { 
        return kursi; 
    }
}
