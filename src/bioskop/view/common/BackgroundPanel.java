package bioskop.view.common;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    private Image background;

    public BackgroundPanel(String path) {
        ImageIcon icon = null;
        try {
            // Memasukkan objek film ke dalam list
            icon = new ImageIcon(getClass().getResource(path));
            background = icon.getImage();
        } catch (Exception e) {
            // Error handling jika file gambar tidak ditemukan
            System.out.println("Background image not found: " + path);
            background = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // memastikan komponen di atas panel
        if (background != null) {
            // Agar ukuran background selalu mengikuti ukuran panel saat ini
            g.drawImage(
                background,
                0,
                0,
                getWidth(),
                getHeight(),
                this
            );
        }
    }
}
