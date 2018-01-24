package systemUI;

import java.awt.*;  
import java.awt.event.*;  

import javax.swing.*;  

import man.*;

public class ManagerUI extends JFrame implements ActionListener {  
	//定义登陆界面的组件
	JButton jb1, jb2, jb3, jb4, jb5 = null;
	JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8 = null;
	JTextField time_jtf, id_jtf = null;
	JLabel jlb1, jlb2, jlb3 = null;
	String time, name;
	Manager myManager;

	public static void main(String[] args) {
		//todo
	}
	
	//组件的静态布置
	public ManagerUI(String name) {
		myManager = new Manager(name);
		//创建组件
		jb1 = new JButton("列出所有保安");
		jb2 = new JButton("通过工作时间查询当值保安");
		jb3 = new JButton("通过车牌号查询驶入时的当值保安");
		jb4 = new JButton("通过车牌号查询驶出时的当值保安");
		jb5 = new JButton("注销");
		
		//设置监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		
		//以上四个按钮的监听统一放在了actionPerformance()中
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();
		jp7 = new JPanel();
		jp8 = new JPanel();
		
		time_jtf = new JTextField(10);
		id_jtf = new JTextField(10);
		
		
		jlb1 = new JLabel("时间  (12:00:00) ：");
		jlb2 = new JLabel("车牌号：");
		jlb3 = new JLabel("查询结果：");
		jlb3.setFont(new Font("Dialog", 1, 20));
		jlb3.setForeground(Color.red);
		
		//加入到JPanel中
		jp1.add(jlb1);
		jp1.add(time_jtf);

		jp2.add(jlb2);
		jp2.add(id_jtf);

		jp3.add(jb1);
		jp4.add(jb2);
		jp5.add(jb3);
		jp6.add(jb4);
		jp7.add(jlb3);
		jp8.add(jb5);
		

		//加入JFrame中
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
		this.add(jp8);

		//设置布局管理器  
        this.setLayout(new GridLayout(4,2,50,30));  
        //给窗口设置标题  
        String tittle = "停车场管理系统" + "  Manager:" + myManager.getName();
        this.setTitle(tittle);  
        //设置窗体大小  
        this.setSize(1000,600);  
        //设置窗体初始位置  
        this.setLocation(200, 150);  
        //设置当关闭窗口时，保证JVM也退出  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //显示窗体  
        this.setVisible(true);  
        this.setResizable(true);  
	}  
    
	@Override  
    public void actionPerformed(ActionEvent e) {  
        
		if(e.getActionCommand()=="列出所有保安") {
       		listSecurity();
       	}
       	else if (e.getActionCommand()=="通过工作时间查询当值保安") {
       		querySecurityBytime();
       	}
       	else if (e.getActionCommand() == "通过车牌号查询驶入时的当值保安") {
       		queryINSecurityByCarID();
       	}
       	else if (e.getActionCommand() == "通过车牌号查询驶出时的当值保安") {
       		queryOUTSecurityByCarID();
       	}
       	else if (e.getActionCommand() == "注销") {
       		quit();
       	}
    }  
	
	public void listSecurity() {
		
		
		try {
			//String[] allSecurity;
			//allSecurity = new String[myManager.ListAllSecurity().length];
			//for (int i = 0; i < myManager.ListAllSecurity().length; i++) {
			//	allSecurity[i] = myManager.ListAllSecurity()[i];
			//}
			//allSecurity = myManager.ListAllSecurity();
			//allSecurity[0] = "1";
			//allSecurity[1] = "2";
			clear();
			dispose();
			ListAllSecurityUI ui = new ListAllSecurityUI(myManager.getName(), myManager.ListAllSecurity());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "查询异常", "提示消息", JOptionPane.WARNING_MESSAGE);
		}
		
		
		/*
		
		 //我的调试
		int number = 5;
		String[] allSecurity = new String[number];
		//allSecurity = myManager.ListAllSecurity();
		for (int i = 0; i < number; i++) {
			allSecurity[i] = "button";
		}
		
		clear();
		dispose();
		ListAllSecurityUI ui = new ListAllSecurityUI(myManager.getName(), allSecurity);
		
		*/
		
	}
	
	public void querySecurityBytime() {
		try {
			if(time_jtf.getText().isEmpty()) {
	    		JOptionPane.showMessageDialog(null, "请输入时间", "提示消息", JOptionPane.WARNING_MESSAGE );
	    	}
	    	else {
	    		String time = time_jtf.getText();
	    		String result;
	    		result = "查询结果： " +  myManager.FindSecurity(time);
	    		jlb3.setText(result);
	    	}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Manager.txt文件读取失败", "提示消息", JOptionPane.WARNING_MESSAGE );
		}
	}
	
	public void queryINSecurityByCarID() {
		
		try {
			if(id_jtf.getText().isEmpty()) {
	    		JOptionPane.showMessageDialog(null, "请输入车牌号", "提示消息", JOptionPane.WARNING_MESSAGE );
	    	}
	    	else {
	    		String id = id_jtf.getText();
	    		String result;
	    		result = "查询结果： " +  myManager.FindSecurityIn(id);
	    		jlb3.setText(result);
	    	}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Manager.txt文件读取失败", "提示消息", JOptionPane.WARNING_MESSAGE );
		}	
	}
	
	public void queryOUTSecurityByCarID() {
		try {
			if(id_jtf.getText().isEmpty()) {
	    		JOptionPane.showMessageDialog(null, "请输入车牌号", "提示消息", JOptionPane.WARNING_MESSAGE );
	    	}
	    	else {
	    		String id = id_jtf.getText();
	    		String result;
	    		result = "查询结果： " +  myManager.FindSecurityOut(id);
	    		jlb3.setText(result);
	    	}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Manager.txt文件读取失败", "提示消息", JOptionPane.WARNING_MESSAGE );
		}
	}
	
	public void quit() {
    	JOptionPane.showMessageDialog(null, "注销成功", "提示消息", JOptionPane.WARNING_MESSAGE );
    	//关闭当前界面
    	clear();
    	//返回到登陆界面
    	dispose();
    	ServerUI ui = new ServerUI();
    }
	
	public void clear() {
		time_jtf.setText("");
		id_jtf.setText("");
	}

}  