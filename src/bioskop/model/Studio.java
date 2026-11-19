package bioskop.model;

public class Studio {
    // Atribut Privat (Encapsulation)
    private String noStudio;
    private int kapasitas;
    private String tanggal;
    private String jam;
    private Film film;

    // Constructo menyiapkan data studio lengkap dengan film yang akan diputar
    public Studio(String noStudio, int kapasitas, String tanggal, String jam, Film film) {
        this.noStudio = noStudio;
        this.kapasitas = kapasitas;
        this.tanggal = tanggal;
        this.jam = jam;
        this.film = film;
    }

    // Getter
    public String getNoStudio() { return noStudio; }
    public int getKapasitas() { return kapasitas; }
    public String getTanggal() { return tanggal; }
    public String getJam() { return jam; }
    public Film getFilm() { return film; }

    // Setter
    public void setNoStudio(String noStudio) { this.noStudio = noStudio; }
    public void setKapasitas(int kapasitas) { this.kapasitas = kapasitas; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public void setJam(String jam) { this.jam = jam; }
    public void setFilm(Film film) { this.film = film; }

    // Mengubah data studio menjadi teks yang rapi untuk ditampilkan ke user
    @Override
    public String toString() {
        // Menggunakan String.format agar lebih rapi jika ingin ditampilkan di UI/Console
        return String.format("%s | %s [%s] - Film: %s", 
                noStudio, tanggal, jam, (film != null ? film.getJudul() : "Tidak ada film"));
    }
}