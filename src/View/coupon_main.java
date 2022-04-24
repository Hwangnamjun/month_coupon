package View;

import java.awt.Container;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Canvas;
import java.awt.Color;


public class coupon_main extends JFrame{

	public JRadioButton jr0 = new JRadioButton("공무원");
	public JRadioButton jr1 = new JRadioButton("두산회원");
	public JRadioButton jr2 = new JRadioButton("한국노총");
	public JRadioButton jr3 = new JRadioButton("시설공단");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new coupon_main();
	}
	public coupon_main ()
	{
		Container c1 = this.getContentPane();
		JPanel p1 = new JPanel();
		JButton btn1 = new JButton("쿠폰 생성");
		btn1.setBounds(115, 130, 150, 50);
		JLabel lb1 = new JLabel("회원번호");
		lb1.setBounds(88, 50, 80, 20);
		ButtonGroup bg = new ButtonGroup();
		
		bg.add(jr0);
		bg.add(jr1);
		bg.add(jr2);
		bg.add(jr3);
		p1.setLayout(null);
		jr0.setBounds(293, 90, 80, 20);
		p1.add(jr0);
		jr1.setBounds(209, 90, 80, 20);
		p1.add(jr1);
		jr2.setBounds(125, 90, 80, 20);
		p1.add(jr2);
		jr3.setBounds(41, 90, 80, 20);
		p1.add(jr3);
		JTextField tf1 = new JTextField();
		tf1.setBounds(190, 50, 100, 20);
		p1.add(btn1);
		p1.add(tf1);
		p1.add(lb1);
		c1.add(p1);
		

		setSize(400, 250);
		setLocationRelativeTo(null);
		setTitle("쿠폰 임의지급");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//enter키 입력
		tf1.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == '\n')
				{
					keyPressEvent(tf1);
				}
			}
		});
		//버튼 클릭
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					keyPressEvent(tf1);
			}
		});
		
	}
	
	public void keyPressEvent(JTextField ta1)
	{
		int chk = 0;
		String insertCoupon = "";
		
		/***************************번호 입력여부 확인*****************************/		
		if(ta1.getText().equals(""))
		{
			insertCoupon = "회원번호를 입력하세요";
		}
		else
		{
			/***************************라디오 버튼 체크 확인*****************************/	
			if(jr0.isSelected())
			{
				chk = 25;
			}
			else if(jr1.isSelected())
			{
				chk = 26;
			}
			else if(jr2.isSelected())
			{
				chk = 13;
			}
			else if(jr3.isSelected())
			{
				chk = 14;
			}
			/***************************회원 선택여부 확인*****************************/
			if(chk == 0)
			{
				insertCoupon = "회원구분을 확인하세요";
			}
			else
			{
				createData cd = new createData();
				/***************************중복 지급여부 확인*****************************/
				int checkMember = cd.checkMem(ta1.getText());
		
				/*************************기지급자 제외 및 쿠폰지급*************************************/
				
				if(checkMember == 1)
				{
					insertCoupon = cd.insertCoupon(ta1.getText(),chk);
					ta1.setText("");
				}
				else
				{
					insertCoupon = "이미 임의지급된 회원입니다";
					ta1.setText("");
				}
			}
		}
		JOptionPane.showMessageDialog(null, insertCoupon,"Message",JOptionPane.INFORMATION_MESSAGE);
	}
}








