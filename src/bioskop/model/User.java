package bioskop.model;

// "abstract" berarti class ini adalah kerangka umum dan tidak bisa dibuat objeknya secara langsung
public abstract class User {
    
    // "protected" agar variabel ini bisa diakses langsung oleh class anaknya (Pelanggan & Staff)
    protected String username;

    // Constructor: Menyiapkan data username dasar untuk semua jenis user
    public User(String username) {
        this.username = username;
    }
    
    // Getter standar untuk mengambil nama user
    public String getUsername() {
        return username;
    }
    
    // "abstract method": Ini adalah metode "kosong" tanpa isi.
    // Tujuannya agar setiap anak (Pelanggan/Staff) WAJIB membuat cara login-nya sendiri.
    public abstract boolean login(String input);
}