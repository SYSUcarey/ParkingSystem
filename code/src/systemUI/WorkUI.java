package systemUI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.io.*;
import java.net.*;
import man.*;
import java.util.*;

public class WorkUI extends JFrame implements ActionListener {
	
	//定义JavaWeb socket组件
	public ServerSocket svrSocket = null;
	public Socket socket = null;
	public InputStream inputStream = null;
	public OutputStream outputStream = null;
	public DataInputStream dataStream = null;
	public PrintStream printStream = null;
	public DataOutputStream dataoutputStream = null;
	public String message;
	public BufferedReader charStream = new BufferedReader(new InputStreamReader(System.in));
	
	public int order;
	public String echo;
	public Security mySecurity;
	public String myname;
	
	//定义界面组件
	JPanel jp1, jp2, jp3, jp4= null;
	JLabel jlb1, jlb2 = null;
	JButton jb1, jb2 = null;
	
	
	public static void main(String[] args) {
		//todo
	}
	
	public WorkUI(String name) {
		mySecurity = new Security(name);
		//界面组件静态布置
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		
		jlb1 = new JLabel("行驶信息");
		jlb2 = new JLabel("结果信息");
		jlb1.setFont(new Font("Dialog", 1, 20));
		jlb1.setForeground(Color.red);
		jlb1.setFont(new Font("Dialog", 1, 30));
		jlb1.setForeground(Color.red);
		
		jb1 = new JButton("退出");
		jb2 = new JButton("打开服务器");
		
		//设置监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		jp1.add(jlb1);
		jp2.add(jlb2);
		jp3.add(jb1);
		jp4.add(jb2);
		
		this.add(jp4);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		//设置布局管理器  
        this.setLayout(new GridLayout(4,1,50,50));  
        //给窗口设置标题  
        String tittle = name + "的工作";
        this.setTitle(tittle);  
        //设置窗体大小  
        this.setSize(300,600);  
        //设置窗体初始位置  
        this.setLocation(200, 150);  
        //设置当关闭窗口时，保证JVM也退出  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //显示窗体  
        this.setVisible(true);  
        this.setResizable(true); 
		
        try {
			svrSocket = new ServerSocket(528);
			socket = svrSocket.accept();
			
			inputStream = socket.getInputStream();
			dataStream = new DataInputStream(inputStream);
			outputStream = socket.getOutputStream();
			dataoutputStream = new DataOutputStream(outputStream);
			//message = dataStream.readUTF();
			//System.out.println(message + "\n");
		} catch (UnknownHostException t) {
			JOptionPane.showMessageDialog(null, "找不到客户端", "提示消息", JOptionPane.WARNING_MESSAGE);
		} catch (IOException t) {
			JOptionPane.showMessageDialog(null, "服务器IO错误", "提示消息", JOptionPane.WARNING_MESSAGE);
		}
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "退出") {
			int y = JOptionPane.showConfirmDialog(null, "是否要退出工作模式", "询问消息", JOptionPane.YES_NO_OPTION);
			if (y == 0) {
				JOptionPane.showMessageDialog(null,"退出成功\n请记得将车辆提示显示器关闭", "提示消息", JOptionPane.WARNING_MESSAGE);	
				dispose();
				SecurityUI ui = new SecurityUI(mySecurity.getName());
			}
			
		}
		else if (e.getActionCommand() == "打开服务器") {
			
			sendMessage("begin");
			
			
			while (true) {
				String style;
				String realCarID;
				message = readMessage();
				if (message.equals("stop")) {
					try {
						svrSocket.close();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "关闭socket", "提示消息", JOptionPane.WARNING_MESSAGE);
					}
					break;
				}
				else if (message.equals("end")) {
					try {
						svrSocket.close();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "关闭socket", "提示消息", JOptionPane.WARNING_MESSAGE);
					}
					//System.exit(0);
					//dispose();
					//SecurityUI ui = new SecurityUI(mySecurity.getName());
					//System.exit(0);
				}
				else {
					style = message.substring(0,1);
					realCarID = message.substring(1);
					Date date = new Date();
					if (style.equals("i")) {
						message = mySecurity.CarIn(realCarID, date, mySecurity.getName());
						JOptionPane.showMessageDialog(null, realCarID, "In",JOptionPane.WARNING_MESSAGE);
						String m = "驶入--ID：" + realCarID;
						jlb1.setText(m);
						//m = message;
						//jlb2.setText(m);
					}
					else if (style.equals("o")) {
						message = mySecurity.CarOut(realCarID, date, mySecurity.getName());
						JOptionPane.showMessageDialog(null, realCarID, "Out",JOptionPane.WARNING_MESSAGE);
						jlb1.setText(realCarID);
						String m = "驶出--ID：" + realCarID;
						jlb1.setText(m);
						//m = message;
						//jlb2.setText(m);
					}
					
					sendMessage(message);
				}
			}
		}
	}

	public void sendMessage(String message) {
		try {
			dataoutputStream.writeUTF(message);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "找不到客户端", "提示消息", JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "服务器IO错误", "提示消息", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public String readMessage() {
		try {
			message = dataStream.readUTF();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "找不到客户端", "提示消息", JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "服务器readIO错误", "提示消息", JOptionPane.WARNING_MESSAGE);
		}
		return message;
	}
	
}
