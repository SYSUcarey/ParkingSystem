package systemUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;  

import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

import javax.swing.JFrame;

public class ClientUI extends JFrame implements ActionListener {
	OutputStream outputStream = null;
	DataOutputStream dataoutputStream = null;
	InputStream inputStream = null;
	DataInputStream dataStream = null;
	BufferedReader charStream = null;
	
	// Initialize Socket
	Socket socket = null;
	String message;
	boolean usable;
	//boolean connected;
	
	//定义界面组件
	JLabel jlb1, jlb2, jlb3 = null;
	JButton jb1, jb2, jb3 , jb4 = null;
	JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7 = null;
	JTextField jtf = null;
	
	
	public ClientUI() {
		usable = false;
		//connected = false;
		//静态UI界面布置
		jlb1 = new JLabel("未连接服务器");
		jlb2 = new JLabel("车牌号：");
		jlb3 = new JLabel("结果信息");
		
		jlb1.setFont(new Font("Dialog", 1, 20));
		jlb1.setForeground(Color.red);
		jlb2.setFont(new Font("Dialog", 1, 20));
		jlb2.setForeground(Color.red);
		jlb3.setFont(new Font("Dialog", 1, 15));
		jlb3.setForeground(Color.red);
		
		
		jb1 = new JButton("驶入");
		jb2 = new JButton("驶出");
		jb3 = new JButton("确认服务器已打开并连接");
		jb4 = new JButton("断开连接并退出");
		
		//设置监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		
		jtf = new JTextField(10);
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();
		jp7 = new JPanel();
		
		jp1.add(jlb1);
		jp2.add(jlb2);
		jp2.add(jtf);
		jp3.add(jb1);
		jp4.add(jb2);
		jp5.add(jlb3);
		jp7.add(jb3);
		jp6.add(jb4);
		
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp7);
		//this.add(jp6);
		
		
		//设置布局管理器  
        this.setLayout(new GridLayout(6,1,50,50));  
        //给窗口设置标题  
        this.setTitle("停车场管理系统");  
        //设置窗体大小  
        this.setSize(300,600);  
        //设置窗体初始位置  
        this.setLocation(1000, 150);  
        //设置当关闭窗口时，保证JVM也退出  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //显示窗体  
        this.setVisible(true);  
        this.setResizable(true); 
		
        try {
			charStream = new BufferedReader(new InputStreamReader(System.in));
			socket = new Socket("127.0.0.1", 528);
			inputStream = socket.getInputStream();
			dataStream = new DataInputStream(inputStream);
			outputStream = socket.getOutputStream();
			dataoutputStream = new DataOutputStream(outputStream);
			//dataoutputStream.writeUTF(message);
		} catch (UnknownHostException t) {
			JOptionPane.showMessageDialog(null, "客户端找不到服务器", "提示消息", JOptionPane.WARNING_MESSAGE);
		} catch (IOException t) {
			JOptionPane.showMessageDialog(null, "客户端IO错误", "提示消息", JOptionPane.WARNING_MESSAGE);
		}
		
		
		
	}
	
	public static void main(String[] args) {
		ClientUI c = new ClientUI();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		if (e.getActionCommand() == "确认服务器已打开并连接" && !usable) {
			int y = JOptionPane.showConfirmDialog(null, "请确认是否已打开服务器", "询问消息", JOptionPane.YES_NO_OPTION);
			if (y == 0) {
				usable = true;
				message = readMessage();
				jb3.setText("断开连接");
				jlb1.setText("已连接到服务器");
			}
		}
		if (e.getActionCommand() == "驶入" && usable) {
			String CarID = jtf.getText();
			message = "i" + CarID;
			sendMessage(message);
			message = readMessage();
			jlb3.setText(message);
		}
		if (e.getActionCommand() == "驶入" && !usable) {
			JOptionPane.showMessageDialog(null, "请先连接服务器", "警告消息", JOptionPane.ERROR_MESSAGE);
		}
		if (e.getActionCommand() == "驶出" && !usable) {
			JOptionPane.showMessageDialog(null, "请先连接服务器", "警告消息", JOptionPane.ERROR_MESSAGE);
		}
		
		if (e.getActionCommand() == "驶出" && usable) {
			String CarID = jtf.getText();
			message = "o" + CarID;
			sendMessage(message);
			message = readMessage();
			jlb3.setText(message);
		}
		if (e.getActionCommand() == "断开连接" && usable) {
			usable = false;
			sendMessage("stop");
			//JOptionPane.showMessageDialog(null, "等待服务器打开后才能再次连接", "提示消息", JOptionPane.WARNING_MESSAGE);
			jb3.setText("确认服务器已打开并连接");
			jlb1.setText("与服务器断开");
		}
		if (e.getActionCommand() == "断开连接并退出" && usable) {
			JOptionPane.showMessageDialog(null, "尚未断开与服务器的连接", "警告消息", JOptionPane.ERROR_MESSAGE);
			//sendMessage("end");
			//System.exit(0);
		}
		if (e.getActionCommand() == "断开连接并退出" && !usable) {
			System.exit(0);
			
		}
		/*
		if (e.getActionCommand() == "连接服务器" && !usable) {
			JOptionPane.showMessageDialog(null, "请先等待停车场开放", "提示消息", JOptionPane.WARNING_MESSAGE);
			jlb1.setText(getLotInfo(usable, connected));
			
		}
		
		
		if (e.getActionCommand() == "连接服务器" && !connected && usable) {
			try {
				message = readMessage();
			}catch (Exception t) {
				
			}
			connected = true;
			jb3.setText("断开连接");

			jlb1.setText(getLotInfo(usable, connected));
			
			//JOptionPane.showMessageDialog(null, "关闭车辆提示显示器", "提示消息", JOptionPane.WARNING_MESSAGE);
			//System.exit(0);
			//usable = false;
			//message = readMessage();
			//if(message.equals("on")) usable = true;
		}
		
		//else if (e.getActionCommand() == "连接服务器" && usable && connected) {
			
		//}
		
		
		
		//else if (e.getActionCommand() == "点击开始" && usable) {
		//	JOptionPane.showMessageDialog(null, "请先等待服务器", "提示消息", JOptionPane.WARNING_MESSAGE);
		//}
		
		if (e.getActionCommand() == "断开连接" && usable && connected) {
			usable = false;
			connected = false;
			message = "stop";
			sendMessage(message);
			jlb1.setText(getLotInfo(usable, connected));
			jb3.setText("连接服务器");
		}
		if (e.getActionCommand() == "驶入" && usable && connected) {
			
			String CarID = jtf.getText();
			message = "i" + CarID;
			sendMessage(message);
			message = readMessage();
			jlb3.setText(message);
		}
		if (e.getActionCommand() == "驶出" && usable && connected) {
			String CarID = jtf.getText();
			message = "o" + CarID;
			sendMessage(message);
			message = readMessage();
			jlb3.setText(message);
			}
		
		if (e.getActionCommand() == "退出" && !usable) {
			System.exit(0);
		}
		
		//if (e.getActionCommand() == "退出" && !connected && usable) {
		//	JOptionPane.showMessageDialog(null, "还未连接服务器", "提示消息", JOptionPane.WARNING_MESSAGE);
		//}
		else if (e.getActionCommand() == "退出" && usable && connected) {
			JOptionPane.showMessageDialog(null, "请先断开服务器", "提示消息", JOptionPane.WARNING_MESSAGE);
		}
		*/
	}

	public void sendMessage(String message) {
		try {
			dataoutputStream.writeUTF(message);
				//return;
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "找不到服务器", "提示消息", JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "客户端IO错误", "提示消息", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public String readMessage() {
		try {
			message = dataStream.readUTF();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "找不到服务器", "提示消息", JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "客户端IO错误", "提示消息", JOptionPane.WARNING_MESSAGE);
		}
		return message;
	}

	//public String getLotInfo(boolean u, boolean c) {
	//	return "yes";
	//}
}
