package bioskop.model;

public class Pelanggan extends User {

    private String email;
    private String password;

    // Constructor untuk membuat objek Pelanggan
    public Pelanggan(String username, String email, String password) {
        super(username); 
        this.email = email;
        this.password = password;
    }

    // Menulis ulang cara kerja method login milik class User
    @Override
    public boolean login(String input) {
        return username.equals(input) || email.equals(input);
    }

    // Method khusus untuk mengecek apakah password yang dimasukkan benar
    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }
}