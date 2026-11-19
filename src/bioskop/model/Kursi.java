package bioskop.model;

public class Kursi {
    // Atribut Privat (Encapsulation)
    private Studio studio; // Menghubungkan kursi dengan studio tertentu
    private String noKursi;
    private String status; // Kosong atau Terisi

    // Constructor Untuk mengisi data kursi saat pertama kali dibuat di memori
    public Kursi(Studio studio, String noKursi, String status) {
        this.studio = studio;
        this.noKursi = noKursi;
        this.status = status;
    }

    // GETTER
    public Studio getStudio() { return studio; }
    public String getNoKursi() { return noKursi; }
    public String getStatus() { return status; }

    // SETTER
    public void setStudio(Studio studio) { this.studio = studio; }
    public void setNoKursi(String noKursi) { this.noKursi = noKursi; }
    public void setStatus(String status) { this.status = status; }

    // Mengubah objek kursi menjadi teks agar bisa tampil rapi di UI/Layar
    @Override
    public String toString() {
        return studio.getNoStudio() + " - Kursi " + noKursi;
    }
}
