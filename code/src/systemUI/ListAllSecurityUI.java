package systemUI;

import javax.swing.*;  

import parking.ParkingLot;

import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;

import man.*;

public class ListAllSecurityUI extends JFrame implements ActionListener{
	
	public Manager myManager;
	public  String[] allSecurity;
	private int number;
	
	JLabel jlb[];
	JPanel jp[];
	JButton jb;
	
	public ListAllSecurityUI(String name, String[] securityname) {
		myManager = new Manager(name);
		number = securityname.length;
		
		
		String text = "the number of Security is" + number;
		
		jlb = new JLabel[number+1];
		jb = new JButton("返回");
		jb.addActionListener(this);
		jp = new JPanel[number+2];
		
		for (int i = 0; i < number+2; i++) {
			
			jp[i] = new JPanel();
			this.add(jp[i]);
		}
		
		for (int i = 0; i < number+1; i++) {
			jlb[i] = new JLabel();
			if (i == 0) {
				jlb[i].setText(text);
				jlb[i].setForeground(Color.red);
				
			}
			else jlb[i].setText(securityname[i-1]);
			jlb[i].setFont(new Font("Dialog", 1, 20));
			jp[i].add(jlb[i]);
			
		}
		jp[number+1].add(jb);
		
		
		//设置布局管理器  
		//this.setLayout(new FlowLayout());
        this.setLayout(new GridLayout(number+2,1));  
        //给窗口设置标题 
        String tittle = "停车场管理系统" + "  Manager:" + name;
        this.setTitle(tittle);  
        //设置窗体大小  
        this.setSize(500,700);  
        //设置窗体初始位置  
        this.setLocation(500, 100);  
        //设置当关闭窗口时，保证JVM也退出  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //显示窗体  
        this.setVisible(true);  
        this.setResizable(true);  
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand() == "返回") {
		//	JOptionPane.showMessageDialog(null, "返回异常", "提示消息", JOptionPane.WARNING_MESSAGE);
			dispose();
			ManagerUI ui = new ManagerUI(myManager.getName());
		}
	}
}
