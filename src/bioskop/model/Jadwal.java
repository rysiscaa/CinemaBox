package bioskop.model;

import bioskop.model.Film;

public class Jadwal {
    // Atribut Privat (Encapsulation)
    private Film film;    // Menghubungkan class Jadwal dengan class Film
    private String jam;   
    private int harga;    

    // Constructor untuk mengisi data saat objek Jadwal dibuat pertama kali
    public Jadwal(Film film, String jam, int harga) {
        this.film = film;  
        this.jam = jam;     
        this.harga = harga; 
    }

    // Method untuk menggabungkan info film, jam, dan harga jadi satu kalimat (String)
    public String getInfo() {
           return film.getJudul() + " | " + jam + " | Rp" + harga;
    }

    public int getHarga() {
        return harga;
    }
}