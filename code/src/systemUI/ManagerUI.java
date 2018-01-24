package systemUI;

import java.awt.*;  
import java.awt.event.*;  

import javax.swing.*;  

import man.*;

public class ManagerUI extends JFrame implements ActionListener {  
	//�����½��������
	JButton jb1, jb2, jb3, jb4, jb5 = null;
	JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8 = null;
	JTextField time_jtf, id_jtf = null;
	JLabel jlb1, jlb2, jlb3 = null;
	String time, name;
	Manager myManager;

	public static void main(String[] args) {
		//todo
	}
	
	//����ľ�̬����
	public ManagerUI(String name) {
		myManager = new Manager(name);
		//�������
		jb1 = new JButton("�г����б���");
		jb2 = new JButton("ͨ������ʱ���ѯ��ֵ����");
		jb3 = new JButton("ͨ�����ƺŲ�ѯʻ��ʱ�ĵ�ֵ����");
		jb4 = new JButton("ͨ�����ƺŲ�ѯʻ��ʱ�ĵ�ֵ����");
		jb5 = new JButton("ע��");
		
		//���ü���
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		
		//�����ĸ���ť�ļ���ͳһ������actionPerformance()��
		
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
		
		
		jlb1 = new JLabel("ʱ��  (12:00:00) ��");
		jlb2 = new JLabel("���ƺţ�");
		jlb3 = new JLabel("��ѯ�����");
		jlb3.setFont(new Font("Dialog", 1, 20));
		jlb3.setForeground(Color.red);
		
		//���뵽JPanel��
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
		

		//����JFrame��
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
		this.add(jp8);

		//���ò��ֹ�����  
        this.setLayout(new GridLayout(4,2,50,30));  
        //���������ñ���  
        String tittle = "ͣ��������ϵͳ" + "  Manager:" + myManager.getName();
        this.setTitle(tittle);  
        //���ô����С  
        this.setSize(1000,600);  
        //���ô����ʼλ��  
        this.setLocation(200, 150);  
        //���õ��رմ���ʱ����֤JVMҲ�˳�  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //��ʾ����  
        this.setVisible(true);  
        this.setResizable(true);  
	}  
    
	@Override  
    public void actionPerformed(ActionEvent e) {  
        
		if(e.getActionCommand()=="�г����б���") {
       		listSecurity();
       	}
       	else if (e.getActionCommand()=="ͨ������ʱ���ѯ��ֵ����") {
       		querySecurityBytime();
       	}
       	else if (e.getActionCommand() == "ͨ�����ƺŲ�ѯʻ��ʱ�ĵ�ֵ����") {
       		queryINSecurityByCarID();
       	}
       	else if (e.getActionCommand() == "ͨ�����ƺŲ�ѯʻ��ʱ�ĵ�ֵ����") {
       		queryOUTSecurityByCarID();
       	}
       	else if (e.getActionCommand() == "ע��") {
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
			JOptionPane.showMessageDialog(null, "��ѯ�쳣", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
		}
		
		
		/*
		
		 //�ҵĵ���
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
	    		JOptionPane.showMessageDialog(null, "������ʱ��", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE );
	    	}
	    	else {
	    		String time = time_jtf.getText();
	    		String result;
	    		result = "��ѯ����� " +  myManager.FindSecurity(time);
	    		jlb3.setText(result);
	    	}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Manager.txt�ļ���ȡʧ��", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE );
		}
	}
	
	public void queryINSecurityByCarID() {
		
		try {
			if(id_jtf.getText().isEmpty()) {
	    		JOptionPane.showMessageDialog(null, "�����복�ƺ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE );
	    	}
	    	else {
	    		String id = id_jtf.getText();
	    		String result;
	    		result = "��ѯ����� " +  myManager.FindSecurityIn(id);
	    		jlb3.setText(result);
	    	}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Manager.txt�ļ���ȡʧ��", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE );
		}	
	}
	
	public void queryOUTSecurityByCarID() {
		try {
			if(id_jtf.getText().isEmpty()) {
	    		JOptionPane.showMessageDialog(null, "�����복�ƺ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE );
	    	}
	    	else {
	    		String id = id_jtf.getText();
	    		String result;
	    		result = "��ѯ����� " +  myManager.FindSecurityOut(id);
	    		jlb3.setText(result);
	    	}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Manager.txt�ļ���ȡʧ��", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE );
		}
	}
	
	public void quit() {
    	JOptionPane.showMessageDialog(null, "ע���ɹ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE );
    	//�رյ�ǰ����
    	clear();
    	//���ص���½����
    	dispose();
    	ServerUI ui = new ServerUI();
    }
	
	public void clear() {
		time_jtf.setText("");
		id_jtf.setText("");
	}

}  