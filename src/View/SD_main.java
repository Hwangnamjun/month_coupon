package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SD_main extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SD_main();
	}

	public SD_main()
	{
		JLabel lb1 = new JLabel();
		ImageIcon ic = new ImageIcon(SD_main.class.getResource("../image/title_img.png"));
		lb1.setIcon(ic);
		System.out.println(ic.getIconWidth());
		lb1.setBounds(150, 50, ic.getIconWidth(), ic.getIconHeight());
		JPanel p1 = new JPanel();
		
		p1.setLayout(null);
		JButton b1 = new JButton("쿠폰 임의지급");
		JButton b2 = new JButton("중복회원 통합");
		b1.setBounds(100,150,120,70);
		b2.setBounds(280,150,120,70);
		p1.add(b1);
		p1.add(b2);
		p1.add(lb1);
		add(p1);

		
		
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new coupon_main();
			}
		});
		
		
		setSize(500, 350);
		setLocationRelativeTo(null);
		setTitle("S/D 사용");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
