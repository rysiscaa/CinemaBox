package bioskop.model;

public class Film {
    // Atribut Privat (Encapsulation)
    private String judul;
    private String genre;
    private String sinopsis;
    private int durasi; 
    private String sutradara;
    private String rating;      // Contoh: "SU", "R13", "D17"
    private String status;      // Contoh: "Sedang Tayang", "Segera"
    private String posterPath;  // Path file atau URL gambar poster

    // Constructor untuk membuat objek Film baru.
    public Film(String judul, String genre, int durasi, String sinopsis,
                String rating, String sutradara, String status, String posterPath) {
        this.judul = judul;
        this.genre = genre;
        this.sinopsis = sinopsis;
        this.durasi = durasi;
        this.sutradara = sutradara;
        this.rating = rating;     
        this.status = status;
        this.posterPath = posterPath;
    }

    // Getter
    public String getJudul() {
        return judul; 
    }
    public String getGenre() { 
        return genre; 
    }
    public String getSinopsis() {
        return sinopsis; 
    }
    public int getDurasi() {
        return durasi;
    }
    public String getSutradara() {
        return sutradara; 
    }
    public String getRating() { 
        return rating; 
    }
    public String getStatus() { 
        return status;
    }
    public String getPosterPath() {
        return posterPath; 
    }

    // Setter
    public void setJudul(String judul) { 
        this.judul = judul; 
    }
    public void setGenre(String genre) { 
        this.genre = genre; 
    }
    public void setSinopsis(String sinopsis) { 
        this.sinopsis = sinopsis;
    }
    public void setDurasi(int durasi) {
        this.durasi = durasi; 
    }
    public void setSutradara(String sutradara) { 
        this.sutradara = sutradara; 
    }
    public void setRating(String rating) { 
        this.rating = rating;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setPosterPath(String posterPath) { 
        this.posterPath = posterPath;
    }

    /**
     * Mengembalikan representasi String dari objek Film.
     * Sangat berguna untuk debugging atau menampilkan data di JList/ComboBox.
     */
    @Override
    public String toString() {
        return judul + " (" + status + ")";
    }
}

