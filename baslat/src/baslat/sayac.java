package baslat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class sayac extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer Zamanlayici;
    private JLabel ZamanG;
    private JButton BaslatB;
    private JButton SifirlaB;
    private boolean kronometreCalisiyor;
    private int saniye, dakika, saat;

    public sayac() {
        setTitle("Kronometre");
        setSize(200, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel anaPanel = new JPanel(new BorderLayout());

        JPanel zamanPaneli = new JPanel();
        zamanPaneli.setBackground(Color.decode("#ced3d7"));
        
        ZamanG = new JLabel("00:00:00");
        ZamanG.setHorizontalAlignment(SwingConstants.CENTER);
        ZamanG.setFont(new Font("gothic", Font.ITALIC, 45));
        zamanPaneli.add(ZamanG);

        JPanel dugmePaneli = new JPanel(new FlowLayout());
        dugmePaneli.setBackground(Color.decode("#5e606c"));

        BaslatB = new JButton("Başlat");
        SifirlaB = new JButton("Sıfırla");
        BaslatB.setBackground(Color.decode("#ced3d7"));
        SifirlaB.setBackground(Color.decode("#ced3d7"));
        
        
        
        BaslatB.addActionListener(this);
        SifirlaB.addActionListener(this);

        dugmePaneli.add(BaslatB);
        dugmePaneli.add(SifirlaB);

        anaPanel.add(zamanPaneli, BorderLayout.CENTER);
        anaPanel.add(dugmePaneli, BorderLayout.SOUTH);

        add(anaPanel);

        Zamanlayici = new Timer(1000, this); // 1 saniyede bir güncelle
        kronometreCalisiyor = false;
        saniye = dakika = saat = 0;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BaslatB) {
            if (kronometreCalisiyor) {
                Zamanlayici.stop();
                BaslatB.setText("Başlat");
            } else {
                Zamanlayici.start();
                BaslatB.setText("Durdur");
            }
            kronometreCalisiyor = !kronometreCalisiyor;
        } else if (e.getSource() == SifirlaB) {
            Zamanlayici.stop();
            kronometreCalisiyor = false;
            saniye = dakika = saat = 0;
            ZamanG.setText("00:00:00");
            BaslatB.setText("Başlat");
        } else {
            saniye++;
            if (saniye == 60) {
                saniye = 0;
                dakika++;
                if (dakika == 60) {
                    dakika = 0;
                    saat++;
                }
            }
            String zaman = String.format("%02d:%02d:%02d", saat, dakika, saniye);
            ZamanG.setText(zaman);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new sayac().setVisible(true);
            }
        });
    }
}
