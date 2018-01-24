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
	
	//����������
	JLabel jlb1, jlb2, jlb3 = null;
	JButton jb1, jb2, jb3 , jb4 = null;
	JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7 = null;
	JTextField jtf = null;
	
	
	public ClientUI() {
		usable = false;
		//connected = false;
		//��̬UI���沼��
		jlb1 = new JLabel("δ���ӷ�����");
		jlb2 = new JLabel("���ƺţ�");
		jlb3 = new JLabel("�����Ϣ");
		
		jlb1.setFont(new Font("Dialog", 1, 20));
		jlb1.setForeground(Color.red);
		jlb2.setFont(new Font("Dialog", 1, 20));
		jlb2.setForeground(Color.red);
		jlb3.setFont(new Font("Dialog", 1, 15));
		jlb3.setForeground(Color.red);
		
		
		jb1 = new JButton("ʻ��");
		jb2 = new JButton("ʻ��");
		jb3 = new JButton("ȷ�Ϸ������Ѵ򿪲�����");
		jb4 = new JButton("�Ͽ����Ӳ��˳�");
		
		//���ü���
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
		
		
		//���ò��ֹ�����  
        this.setLayout(new GridLayout(6,1,50,50));  
        //���������ñ���  
        this.setTitle("ͣ��������ϵͳ");  
        //���ô����С  
        this.setSize(300,600);  
        //���ô����ʼλ��  
        this.setLocation(1000, 150);  
        //���õ��رմ���ʱ����֤JVMҲ�˳�  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //��ʾ����  
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
			JOptionPane.showMessageDialog(null, "�ͻ����Ҳ���������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
		} catch (IOException t) {
			JOptionPane.showMessageDialog(null, "�ͻ���IO����", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
		}
		
		
		
	}
	
	public static void main(String[] args) {
		ClientUI c = new ClientUI();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		if (e.getActionCommand() == "ȷ�Ϸ������Ѵ򿪲�����" && !usable) {
			int y = JOptionPane.showConfirmDialog(null, "��ȷ���Ƿ��Ѵ򿪷�����", "ѯ����Ϣ", JOptionPane.YES_NO_OPTION);
			if (y == 0) {
				usable = true;
				message = readMessage();
				jb3.setText("�Ͽ�����");
				jlb1.setText("�����ӵ�������");
			}
		}
		if (e.getActionCommand() == "ʻ��" && usable) {
			String CarID = jtf.getText();
			message = "i" + CarID;
			sendMessage(message);
			message = readMessage();
			jlb3.setText(message);
		}
		if (e.getActionCommand() == "ʻ��" && !usable) {
			JOptionPane.showMessageDialog(null, "�������ӷ�����", "������Ϣ", JOptionPane.ERROR_MESSAGE);
		}
		if (e.getActionCommand() == "ʻ��" && !usable) {
			JOptionPane.showMessageDialog(null, "�������ӷ�����", "������Ϣ", JOptionPane.ERROR_MESSAGE);
		}
		
		if (e.getActionCommand() == "ʻ��" && usable) {
			String CarID = jtf.getText();
			message = "o" + CarID;
			sendMessage(message);
			message = readMessage();
			jlb3.setText(message);
		}
		if (e.getActionCommand() == "�Ͽ�����" && usable) {
			usable = false;
			sendMessage("stop");
			//JOptionPane.showMessageDialog(null, "�ȴ��������򿪺�����ٴ�����", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
			jb3.setText("ȷ�Ϸ������Ѵ򿪲�����");
			jlb1.setText("��������Ͽ�");
		}
		if (e.getActionCommand() == "�Ͽ����Ӳ��˳�" && usable) {
			JOptionPane.showMessageDialog(null, "��δ�Ͽ��������������", "������Ϣ", JOptionPane.ERROR_MESSAGE);
			//sendMessage("end");
			//System.exit(0);
		}
		if (e.getActionCommand() == "�Ͽ����Ӳ��˳�" && !usable) {
			System.exit(0);
			
		}
		/*
		if (e.getActionCommand() == "���ӷ�����" && !usable) {
			JOptionPane.showMessageDialog(null, "���ȵȴ�ͣ��������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
			jlb1.setText(getLotInfo(usable, connected));
			
		}
		
		
		if (e.getActionCommand() == "���ӷ�����" && !connected && usable) {
			try {
				message = readMessage();
			}catch (Exception t) {
				
			}
			connected = true;
			jb3.setText("�Ͽ�����");

			jlb1.setText(getLotInfo(usable, connected));
			
			//JOptionPane.showMessageDialog(null, "�رճ�����ʾ��ʾ��", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
			//System.exit(0);
			//usable = false;
			//message = readMessage();
			//if(message.equals("on")) usable = true;
		}
		
		//else if (e.getActionCommand() == "���ӷ�����" && usable && connected) {
			
		//}
		
		
		
		//else if (e.getActionCommand() == "�����ʼ" && usable) {
		//	JOptionPane.showMessageDialog(null, "���ȵȴ�������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
		//}
		
		if (e.getActionCommand() == "�Ͽ�����" && usable && connected) {
			usable = false;
			connected = false;
			message = "stop";
			sendMessage(message);
			jlb1.setText(getLotInfo(usable, connected));
			jb3.setText("���ӷ�����");
		}
		if (e.getActionCommand() == "ʻ��" && usable && connected) {
			
			String CarID = jtf.getText();
			message = "i" + CarID;
			sendMessage(message);
			message = readMessage();
			jlb3.setText(message);
		}
		if (e.getActionCommand() == "ʻ��" && usable && connected) {
			String CarID = jtf.getText();
			message = "o" + CarID;
			sendMessage(message);
			message = readMessage();
			jlb3.setText(message);
			}
		
		if (e.getActionCommand() == "�˳�" && !usable) {
			System.exit(0);
		}
		
		//if (e.getActionCommand() == "�˳�" && !connected && usable) {
		//	JOptionPane.showMessageDialog(null, "��δ���ӷ�����", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
		//}
		else if (e.getActionCommand() == "�˳�" && usable && connected) {
			JOptionPane.showMessageDialog(null, "���ȶϿ�������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
		}
		*/
	}

	public void sendMessage(String message) {
		try {
			dataoutputStream.writeUTF(message);
				//return;
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "�Ҳ���������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "�ͻ���IO����", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public String readMessage() {
		try {
			message = dataStream.readUTF();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "�Ҳ���������", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "�ͻ���IO����", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
		}
		return message;
	}

	//public String getLotInfo(boolean u, boolean c) {
	//	return "yes";
	//}
}
