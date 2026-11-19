package bioskop.view.common;

import bioskop.view.common.BackgroundPanel;
import bioskop.view.staff.LoginStaffFrame;
import bioskop.view.pelanggan.LoginPelangganFrame;
import javax.swing.*;
import java.awt.*;

public class MenuUtamaFrame extends JFrame {

    public MenuUtamaFrame() {
        setTitle("Cinema Box");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel Background
        BackgroundPanel bgPanel =
                new BackgroundPanel("/bioskop/assets/bg_cinema.jpg");
        bgPanel.setLayout(null);

        // Icon Staff
        ImageIcon staffNormal = new ImageIcon(
                getClass().getResource("/bioskop/assets/btn_staff.png"));
        ImageIcon staffHover = scaleIcon(staffNormal, 10);

        // Icon Pelanggan
        ImageIcon pelangganNormal = new ImageIcon(
                getClass().getResource("/bioskop/assets/btn_pelanggan.png"));
        ImageIcon pelangganHover = scaleIcon(pelangganNormal, 6);

        // Button Staff
        JButton btnStaff = new JButton(staffNormal);
        setupButton(btnStaff);

        btnStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnStaff.setIcon(staffHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnStaff.setIcon(staffNormal);
            }
        });

        btnStaff.addActionListener(e -> {
            new LoginStaffFrame();
            dispose();
        });

        // Button Pelanggan
        JButton btnPelanggan = new JButton(pelangganNormal);
        setupButton(btnPelanggan);

        btnPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnPelanggan.setIcon(pelangganHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnPelanggan.setIcon(pelangganNormal);
            }
        });

        btnPelanggan.addActionListener(e -> {
            new LoginPelangganFrame();
            dispose();
        });

        bgPanel.add(btnStaff);
        bgPanel.add(btnPelanggan);

        setContentPane(bgPanel);
        setVisible(true);

        // Posisi Kiri Bawah
        SwingUtilities.invokeLater(() -> {
            int marginLeft = 120;
            int marginBottom = 95;
            int gap = 50;

            int panelHeight = bgPanel.getHeight();
            int btnY = panelHeight - staffNormal.getIconHeight() - marginBottom;

            btnStaff.setBounds(
                    marginLeft,
                    btnY,
                    staffNormal.getIconWidth(),
                    staffNormal.getIconHeight()
            );

            btnPelanggan.setBounds(
                    marginLeft + staffNormal.getIconWidth() + gap,
                    btnY,
                    pelangganNormal.getIconWidth(),
                    pelangganNormal.getIconHeight()
            );
        });
    }

    // Style Button
    private void setupButton(JButton btn) {
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Icon Scale
    private ImageIcon scaleIcon(ImageIcon icon, int plus) {
        Image img = icon.getImage();
        int w = icon.getIconWidth() + plus;
        int h = icon.getIconHeight() + plus;
        Image scaled = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
