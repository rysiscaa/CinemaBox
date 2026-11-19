package bioskop.util;

import bioskop.model.*;
import java.util.ArrayList;

public class DataStore {
     // Mendeklarasikan List Statis agar data tetap tersimpan selama aplikasi berjalan
    public static ArrayList<Staff> daftarStaff = new ArrayList<>();
    public static ArrayList<Pelanggan> daftarPelanggan = new ArrayList<>();
    public static ArrayList<Film> daftarFilm = new ArrayList<>();
    public static ArrayList<Studio> daftarStudio = new ArrayList<>();
    public static ArrayList<Kursi> daftarKursi = new ArrayList<>();
    public static ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();

    // Mengisi data awal (dummy data)
    static {
        // Film Tayang
        Film f1 = new Film("Jumbo", "Animation", 110,
                "Petualangan seru dengan animasi megah yang menghibur seluruh keluarga.",
                "SU", "Ryan Adriandhy", "Tayang", "/bioskop/assets/poster_jumbo.jpg");

        Film f2 = new Film("The Conjuring 4: Last Rites", "Horror", 125,
                "Kasus terakhir Ed dan Lorraine Warren melawan kekuatan iblis paling mengerikan.",
                "17+", "Michael Chaves", "Tayang", "/bioskop/assets/poster_conjuring.jpg");

        Film f3 = new Film("Wicked", "Fantasy", 140,
                "Kisah awal persahabatan Elphaba dan Glinda sebelum legenda Oz dimulai.",
                "SU", "Jon M. Chu", "Tayang", "/bioskop/assets/poster_wicked.jpg");

        // Film Segera
        Film f4 = new Film("Avatar Fire and Ash", "Animation", 115,
                "Jake Sully pergi ke Pandora, mengenal suku Na’vi, "
                        + "lalu berjuang melindungi planet tersebut dari eksploitasi manusia.",
                "13+", "James Cammeron", "Segera", "/bioskop/assets/poster_avatar.jpg");

        Film f5 = new Film("The Devil Wears Prada 2", "Drama", 120,
                "Dunia fashion kembali bergejolak ketika Andy dan Miranda bertemu lagi.",
                "13+", "David Frankel", "Segera", "/bioskop/assets/poster_prada.jpg");

        Film f6 = new Film("Return to Silent Hill", "Horror", 130,
                "Teror psikologis membawa kembali kengerian kota Silent Hill yang misterius.",
                "17+", "Christophe Gans", "Segera", "/bioskop/assets/poster_silenthill.jpg");

        // Memasukkan objek film ke dalam list
        daftarFilm.add(f1);
        daftarFilm.add(f2);
        daftarFilm.add(f3);
        daftarFilm.add(f4);
        daftarFilm.add(f5);
        daftarFilm.add(f6);

        // STUDIO & JADWAL 
        Studio s1 = new Studio("Studio 1", 15, "20-12-2025", "10:00", f1);
        Studio s2 = new Studio("Studio 1", 15, "20-12-2025", "12:10", f2);
        Studio s3 = new Studio("Studio 1", 15, "20-12-2025", "15:00", f1);

        Studio s4 = new Studio("Studio 2", 15, "20-12-2025", "10:00", f2);
        Studio s5 = new Studio("Studio 2", 15, "20-12-2025", "12:40", f3);
        Studio s6 = new Studio("Studio 2", 15, "20-12-2025", "15:50", f2);

        daftarStudio.add(s1);
        daftarStudio.add(s2);
        daftarStudio.add(s3);
        daftarStudio.add(s4);
        daftarStudio.add(s5);
        daftarStudio.add(s6);

        // Membuat Kursi Otomatis
        daftarKursi.clear(); 
        for (Studio s : daftarStudio) {
            String[] baris = {"A", "B", "C"};
            for (String b : baris) {
                for (int i = 1; i <= 5; i++) {
                    daftarKursi.add(new Kursi(s, b + i, "Kosong"));
                }
            }
        }

        // STAFF 
        daftarStaff.add(new Staff("adm01", "OOS", "Admin"));
        daftarStaff.add(new Staff("ksr01", "pampam", "Kasir"));

    }
}