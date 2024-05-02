package test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ActionTest extends JFrame{
	
	JButton bt;
	
	public ActionTest() {
		// TODO Auto-generated constructor stub
		bt = new JButton("버튼");
		setLayout(new FlowLayout());;
		
		add(bt);
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("버튼 눌림");
			}
		});
		
		setSize(200, 250);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ActionTest();
	}
	
}
