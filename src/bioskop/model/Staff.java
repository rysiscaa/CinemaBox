package bioskop.model;

public class Staff extends User {
    private String idStaff;
    private String jabatan;

    // Constructor untuk membuat objek Staff
    public Staff(String idStaff, String username, String jabatan) {
        super(username);
        this.idStaff = idStaff;
        this.jabatan = jabatan;
    }

    // LOGIN (Konsep Polimorfisme)
    
    // Mengganti cara login dari class User (hanya pakai nama)
    @Override
    public boolean login(String input) {
        return username.equalsIgnoreCase(input);
    }

    // Memungkinkan staff login dengan dua cara 
    public boolean login(String inputNama, String inputId) {
        return username.equalsIgnoreCase(inputNama)
                && idStaff.equalsIgnoreCase(inputId);
    }

    // Getter
    public String getNama() {
        return username;
    }
    public String getIdStaff() {
        return idStaff;
    }
    public String getJabatan() {
        return jabatan;
    }

    // Admin
    public boolean isAdmin() {
        return jabatan.equalsIgnoreCase("Admin");
    }

    // Kasir
    public boolean isKasir() {
        return jabatan.equalsIgnoreCase("Kasir");
    }
}
