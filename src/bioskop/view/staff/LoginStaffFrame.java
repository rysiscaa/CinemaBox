package bioskop.view.staff;

import bioskop.model.Staff;
import bioskop.util.DataStore;
import bioskop.view.common.BackgroundPanel;

import javax.swing.*;
import java.awt.*;

public class LoginStaffFrame extends JFrame {

    public LoginStaffFrame() {
        setTitle("Login Staff");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Background
        BackgroundPanel bgPanel =
                new BackgroundPanel("/bioskop/assets/bg_loginstaff.jpg");
        bgPanel.setLayout(null);
        setContentPane(bgPanel);

        // Icon
        ImageIcon inputIcon = new ImageIcon(
                getClass().getResource("/bioskop/assets/input.png"));
        ImageIcon btnIcon = new ImageIcon(
                getClass().getResource("/bioskop/assets/btn_masuk.png"));
        ImageIcon btnHover = new ImageIcon(
                getClass().getResource("/bioskop/assets/btn_masuk_hover.png"));

        // Nama
        JTextField tfNama = new JTextField();
        styleTextField(tfNama);

        JLabel bgNama = new JLabel(inputIcon);
        bgNama.setLayout(null);
        bgNama.add(tfNama);

        // Id
        JTextField tfId = new JTextField();
        styleTextField(tfId);

        JLabel bgId = new JLabel(inputIcon);
        bgId.setLayout(null);
        bgId.add(tfId);

        // Button Masuk
        JButton btnMasuk = new JButton(btnIcon);
        styleButton(btnMasuk);

        btnMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnMasuk.setIcon(btnHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnMasuk.setIcon(btnIcon);
            }
        });

        
        // Login Logic
        btnMasuk.addActionListener(e -> {
            
            String nama = tfNama.getText().trim();
            String id = tfId.getText().trim();

            Staff login = null;

            for (Staff s : DataStore.daftarStaff) {
                if (s.getNama().equalsIgnoreCase(nama)
                        && s.getIdStaff().equalsIgnoreCase(id)) {
                    login = s;
                    break;
                }
            }

            if (login != null) {
                new MenuStaffFrame(login);
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Nama atau ID Staff salah",
                        "Login Gagal",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            
        });

        
        // ADD
        bgPanel.add(bgNama);
        bgPanel.add(bgId);
        bgPanel.add(btnMasuk);

        setVisible(true);

        // Posisi Tengah
       SwingUtilities.invokeLater(() -> {

            int screenW = bgPanel.getWidth();
            int screenH = bgPanel.getHeight();

            int startX = (screenW - inputIcon.getIconWidth()) / 2;
            int startY = (int) (screenH * 0.42); 

            // Input Nama
            bgNama.setBounds(
                    startX,
                    startY,
                    inputIcon.getIconWidth(),
                    inputIcon.getIconHeight()
            );
            tfNama.setBounds(20, 15,
                    inputIcon.getIconWidth() - 40,
                    inputIcon.getIconHeight() - 30
            );

            // Input ID
            bgId.setBounds(
                    startX,
                    startY + inputIcon.getIconHeight() + 80,
                    inputIcon.getIconWidth(),
                    inputIcon.getIconHeight()
            );
            tfId.setBounds(20, 15,
                    inputIcon.getIconWidth() - 40,
                    inputIcon.getIconHeight() - 30
            );

            // Button Masuk
            btnMasuk.setBounds(
                    startX,
                    startY + inputIcon.getIconHeight() * 2 + 140,
                    btnIcon.getIconWidth(),
                    btnIcon.getIconHeight()
            );
        });

    }

    // Style
    private void styleTextField(JTextField tf) {
        tf.setBorder(null);
        tf.setOpaque(false);
        tf.setFont(new Font("Segoe UI", Font.BOLD, 22));
    }

    private void styleButton(JButton btn) {
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
