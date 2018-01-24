package systemUI;

/*
 *功能：停车场管理系统的保安操作界面
 *步骤1：登陆界面的静态实现
 *

 */

import javax.swing.*;  

import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

import man.*;

public class SecurityUI extends JFrame implements ActionListener {  
	
	//定义登陆界面的组件
	JButton jb1, jb2, jb3, jb4, jb5, jb6 = null;
	JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9 = null;
	JTextField id_jtf, pos_jtf = null;
	JLabel jlb1, jlb2, jlb3 = null;
	String id, position;
	Security mySecurity;
	String name;

	public static void main(String[] args) {
		//todo
	}
	
	//静态UI界面布置
	public SecurityUI(String name) {
		mySecurity = new Security(name);
		this.name = name;
		
		//创建组件
		jb1 = new JButton("通过停车位查询车牌号");
		jb2 = new JButton("通过车牌号查询停车位");
		jb3 = new JButton("通过车牌号查询驶入时间");
		jb4 = new JButton("通过车牌号查询驶出时间");
		jb5 = new JButton("进入工作模式");
		jb6 = new JButton("注销");
		
		//设置监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
		//以上四个按钮的监听统一放在了actionPerformance()中
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();
		jp7 = new JPanel();
		jp8 = new JPanel();
		jp9 = new JPanel();
		
		id_jtf = new JTextField(10);
		pos_jtf = new JTextField(10);

		jlb1 = new JLabel("车牌号：");
		jlb2 = new JLabel("停车位：");
		jlb3 = new JLabel("查询结果： ");
		jlb3.setFont(new Font("Dialog", 1, 20));
		jlb3.setForeground(Color.red);
		
		//加入到JPanel中
		jp1.add(jlb1);
		jp1.add(id_jtf);

		jp2.add(jlb2);
		jp2.add(pos_jtf);

		jp3.add(jb1);
		jp4.add(jb2);
		jp5.add(jb3);
		jp6.add(jb4);
		jp7.add(jb5);
		jp8.add(jb6);
		jp9.add(jlb3);

		//加入JFrame中
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
		this.add(jp8);
		this.add(jp9);
		
		
		//设置布局管理器  
        this.setLayout(new GridLayout(5,2,50,30));  
        //给窗口设置标题  
        String tittle = "停车场管理系统" + "  Security:" + mySecurity.getName();
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
	
	//监听控制
    @Override  
    public void actionPerformed(ActionEvent e) {
    	
       	if(e.getActionCommand()=="通过停车位查询车牌号") {
       		queryCarID();
       	}
       	else if (e.getActionCommand()=="通过车牌号查询停车位") {
       		queryCarPosition();
       	}
       	else if (e.getActionCommand() == "通过车牌号查询驶入时间") {
       		queryCarInTime();
       	}
       	else if (e.getActionCommand() == "通过车牌号查询驶出时间") {
       		queryCarOutTime();
       	}
       	else if (e.getActionCommand() == "进入工作模式") {
       		work();
       	}
       	else if (e.getActionCommand() == "注销") {
       		quit();
       	}
              
    } 
    
    //通过停车位查询车牌号
    public void queryCarID() {
    	if(pos_jtf.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "请输入停车位", "提示消息", JOptionPane.WARNING_MESSAGE );
    	}
    	else {
    		String pos = pos_jtf.getText();
    		String result;
    		result = "查询结果： " +  mySecurity.getCarID(pos);
    		jlb3.setText(result);
    	}
    }
    
    //通过车牌号查询停车位
    public void queryCarPosition() {
    	if(id_jtf.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "请输入车牌号", "提示消息", JOptionPane.WARNING_MESSAGE );
    	}
    	else {
    		String id = id_jtf.getText();
    		String result;
    		result = "查询结果： " +  mySecurity.getCarPosition(id);
    		jlb3.setText(result);
    	}
    }
	
    //通过车牌号查询驶入时间
    public void queryCarInTime() {
    	if(id_jtf.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "请输入车牌号", "提示消息", JOptionPane.WARNING_MESSAGE );
    	}
    	else {
    		String id = id_jtf.getText();
    		String result;
    		result = "查询结果： " +  mySecurity.getInTime(id);
    		jlb3.setText(result);
    	}
    }
    
    //通过车牌号查询驶出时间
    public void queryCarOutTime() {
    	if(id_jtf.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "请输入车牌号", "提示消息", JOptionPane.WARNING_MESSAGE );
    	}
    	else {
    		String id = id_jtf.getText();
    		String result;
    		result = "查询结果： " +  mySecurity.getOutTime(id);
    		jlb3.setText(result);
    	}
    }
    
    //进入工作模式
    public void work() {
    	JOptionPane.showMessageDialog(null, "请打开车辆提示显示器", "提示信息", JOptionPane.WARNING_MESSAGE);
    	
    	clear();
    	
    	dispose();
    	
    	WorkUI ui1 = new WorkUI(mySecurity.getName());
    	
    	//todo
    }
    
    //注销
    public void quit() {
    	JOptionPane.showMessageDialog(null, "注销成功", "提示消息", JOptionPane.WARNING_MESSAGE );
    	//关闭当前界面
    	clear();
    	//返回到登陆界面
    	dispose();
    	ServerUI ui = new ServerUI();
    }
    
    
	//清空文本框
    public void clear() {
		id_jtf.setText("");
		pos_jtf.setText("");
	}
	
	
	
	
	
}  