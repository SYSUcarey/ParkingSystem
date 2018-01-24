package systemUI;

/*
 *���ܣ�ͣ��������ϵͳ�ı�����������
 *����1����½����ľ�̬ʵ��
 *

 */

import javax.swing.*;  

import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

import man.*;

public class SecurityUI extends JFrame implements ActionListener {  
	
	//�����½��������
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
	
	//��̬UI���沼��
	public SecurityUI(String name) {
		mySecurity = new Security(name);
		this.name = name;
		
		//�������
		jb1 = new JButton("ͨ��ͣ��λ��ѯ���ƺ�");
		jb2 = new JButton("ͨ�����ƺŲ�ѯͣ��λ");
		jb3 = new JButton("ͨ�����ƺŲ�ѯʻ��ʱ��");
		jb4 = new JButton("ͨ�����ƺŲ�ѯʻ��ʱ��");
		jb5 = new JButton("���빤��ģʽ");
		jb6 = new JButton("ע��");
		
		//���ü���
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
		//�����ĸ���ť�ļ���ͳһ������actionPerformance()��
		
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

		jlb1 = new JLabel("���ƺţ�");
		jlb2 = new JLabel("ͣ��λ��");
		jlb3 = new JLabel("��ѯ����� ");
		jlb3.setFont(new Font("Dialog", 1, 20));
		jlb3.setForeground(Color.red);
		
		//���뵽JPanel��
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

		//����JFrame��
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
		this.add(jp8);
		this.add(jp9);
		
		
		//���ò��ֹ�����  
        this.setLayout(new GridLayout(5,2,50,30));  
        //���������ñ���  
        String tittle = "ͣ��������ϵͳ" + "  Security:" + mySecurity.getName();
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
	
	//��������
    @Override  
    public void actionPerformed(ActionEvent e) {
    	
       	if(e.getActionCommand()=="ͨ��ͣ��λ��ѯ���ƺ�") {
       		queryCarID();
       	}
       	else if (e.getActionCommand()=="ͨ�����ƺŲ�ѯͣ��λ") {
       		queryCarPosition();
       	}
       	else if (e.getActionCommand() == "ͨ�����ƺŲ�ѯʻ��ʱ��") {
       		queryCarInTime();
       	}
       	else if (e.getActionCommand() == "ͨ�����ƺŲ�ѯʻ��ʱ��") {
       		queryCarOutTime();
       	}
       	else if (e.getActionCommand() == "���빤��ģʽ") {
       		work();
       	}
       	else if (e.getActionCommand() == "ע��") {
       		quit();
       	}
              
    } 
    
    //ͨ��ͣ��λ��ѯ���ƺ�
    public void queryCarID() {
    	if(pos_jtf.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "������ͣ��λ", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE );
    	}
    	else {
    		String pos = pos_jtf.getText();
    		String result;
    		result = "��ѯ����� " +  mySecurity.getCarID(pos);
    		jlb3.setText(result);
    	}
    }
    
    //ͨ�����ƺŲ�ѯͣ��λ
    public void queryCarPosition() {
    	if(id_jtf.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "�����복�ƺ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE );
    	}
    	else {
    		String id = id_jtf.getText();
    		String result;
    		result = "��ѯ����� " +  mySecurity.getCarPosition(id);
    		jlb3.setText(result);
    	}
    }
	
    //ͨ�����ƺŲ�ѯʻ��ʱ��
    public void queryCarInTime() {
    	if(id_jtf.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "�����복�ƺ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE );
    	}
    	else {
    		String id = id_jtf.getText();
    		String result;
    		result = "��ѯ����� " +  mySecurity.getInTime(id);
    		jlb3.setText(result);
    	}
    }
    
    //ͨ�����ƺŲ�ѯʻ��ʱ��
    public void queryCarOutTime() {
    	if(id_jtf.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "�����복�ƺ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE );
    	}
    	else {
    		String id = id_jtf.getText();
    		String result;
    		result = "��ѯ����� " +  mySecurity.getOutTime(id);
    		jlb3.setText(result);
    	}
    }
    
    //���빤��ģʽ
    public void work() {
    	JOptionPane.showMessageDialog(null, "��򿪳�����ʾ��ʾ��", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
    	
    	clear();
    	
    	dispose();
    	
    	WorkUI ui1 = new WorkUI(mySecurity.getName());
    	
    	//todo
    }
    
    //ע��
    public void quit() {
    	JOptionPane.showMessageDialog(null, "ע���ɹ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE );
    	//�رյ�ǰ����
    	clear();
    	//���ص���½����
    	dispose();
    	ServerUI ui = new ServerUI();
    }
    
    
	//����ı���
    public void clear() {
		id_jtf.setText("");
		pos_jtf.setText("");
	}
	
	
	
	
	
}  