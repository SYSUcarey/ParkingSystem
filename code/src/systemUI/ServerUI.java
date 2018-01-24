package systemUI;

/* 
 * ���ܣ�ͣ��������ϵͳ�ĵ�½���� 
 * ����1����¼����ľ�̬ʵ�� 
 * ����2��ʵ�ֽ�����л� 
 * ����3�������෽������֤�û��������� 
 * author��cbb
 */  
import javax.swing.*;  

import parking.ParkingLot;

import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

import man.*;
  
public class ServerUI extends JFrame  implements ActionListener {  
	
    //�����¼��������  
    JButton jb1,jb2,jb3=null;  
    JRadioButton jrb1,jrb2=null;  
    JPanel jp1,jp2,jp3,jp4=null;  
    JTextField jtf=null;  
    JLabel jlb1,jlb2,jlb3=null;  
    JPasswordField jpf=null;  
    ButtonGroup bg=null;  
    
    Security mySecurity;
    Manager myManager;
    //�趨�û���������  
    //String username;  
    //String password;  
  
    public static void main(String[] args) {   
        ServerUI  ms = new ServerUI();          
    }  
    
    //���캯��  
    public ServerUI()  
    {  	
    	mySecurity = new Security("");
    	myManager = new Manager("");
         //�������  
        jb1=new JButton("��¼");  
        jb2=new JButton("����");  
        jb3=new JButton("�˳�");  
        
        //���ü���  
        jb1.addActionListener(this);  
        jb2.addActionListener(this);  
        jb3.addActionListener(this);  
        //����������ť�ļ���ͳһ������actionPerformance()��  
        
        jrb1=new JRadioButton("����");  
        jrb2=new JRadioButton("����");  
        bg=new ButtonGroup();  
        bg.add(jrb1);  
        bg.add(jrb2);  
        jrb1.setSelected(true);  
          
        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
        jp4=new JPanel();                 
          
        jlb1=new JLabel("�û�����");  
        jlb2=new JLabel("��    �룺");  
        jlb3=new JLabel("Ȩ    �ޣ�");  
          
        jtf=new JTextField(10);  
        jpf=new JPasswordField(10); 
        
        //���뵽JPanel��  
        jp1.add(jlb1);  
        jp1.add(jtf);  
          
        jp2.add(jlb2);  
        jp2.add(jpf);  
          
        jp3.add(jlb3);  
        jp3.add(jrb1);  
        jp3.add(jrb2);  
          
        jp4.add(jb1);  
        jp4.add(jb2);  
        jp4.add(jb3);  
          
        //����JFrame��  
        this.add(jp1);  
        this.add(jp2);  
        this.add(jp3);  
        this.add(jp4); 
        
        //���ò��ֹ�����  
        this.setLayout(new GridLayout(4,1));  
        //���������ñ���  
        this.setTitle("ͣ��������ϵͳ");  
        //���ô����С  
        this.setSize(400,300);  
        //���ô����ʼλ��  
        this.setLocation(500, 350);  
        //���õ��رմ���ʱ����֤JVMҲ�˳�  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //��ʾ����  
        this.setVisible(true);  
        this.setResizable(true);  
          
    }  
    @Override  
    public void actionPerformed(ActionEvent e) {  
    	
        if(e.getActionCommand()=="�˳�")  
        {  
        	int y = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫ�˳�ϵͳ", "ѯ����Ϣ", JOptionPane.YES_NO_OPTION);
			if (y == 0) {
				ParkingLot.getInstance().clear();
	            System.exit(0);  
			}
        }
        else if(e.getActionCommand()=="��¼") {  
        	
            //���ѡ�б�����¼  
            if(jrb1.isSelected()) securitylogin();  
            
            //�����ڵ�¼ϵͳ  
            else if(jrb2.isSelected()) managerlogin();
        }
        else if(e.getActionCommand()=="����")  
        {  	
            clear();  
        }               
    }  
          
      
//����ı���������  
    public  void clear() {  
            jtf.setText("");  
            jpf.setText("");  
    }  
  
//ѧ����¼�жϷ���  
    public void securitylogin() {   
    
    	try {
	        if (mySecurity.Login(jtf.getText(), jpf.getText())) {	  
	        	mySecurity.setName(jtf.getText());
	            JOptionPane.showMessageDialog(null,"��¼�ɹ���","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
	            clear();  
	            //�رյ�ǰ����  
	             dispose();  
	             //����һ���½���  
	             SecurityUI ui=new SecurityUI(mySecurity.getName());  
	        }
	        else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty()) {  
	            JOptionPane.showMessageDialog(null,"�������û��������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
	        }
	        else if(jtf.getText().isEmpty()) {  
	            JOptionPane.showMessageDialog(null,"�������û�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
	        }
	        else if(jpf.getText().isEmpty()) {  
	            JOptionPane.showMessageDialog(null,"���������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
	        }
	        else {  
	            JOptionPane.showMessageDialog(null,"�û��������������\n����������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);  
	             //��������  
	            clear();  
	        }  
    	}catch (Exception e) {
    		JOptionPane.showMessageDialog(null,"Security.txt��ȡʧ�ܣ�","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);  
    	}
    }  
              
    //��ʦ��¼�жϷ���  
    public void managerlogin() {
    	//username = "jmf";
		//password = "jmf";
    	
    	try {
    		if(myManager.Login(jtf.getText(), jpf.getText())) {  
    			myManager.setName(jtf.getText());
                JOptionPane.showMessageDialog(null,"��¼�ɹ���","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
                clear();  
                //�رյ�ǰ����  
                 dispose();  
                 //����һ���½���  
                 ManagerUI ui=new ManagerUI(myManager.getName());  
            }
            else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty()) {  
                JOptionPane.showMessageDialog(null,"�������û��������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
            }
            else if(jtf.getText().isEmpty()) {  
                JOptionPane.showMessageDialog(null,"�������û�����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
            }
            else if(jpf.getText().isEmpty()) {  
                JOptionPane.showMessageDialog(null,"���������룡","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);  
            }
            else {  
                JOptionPane.showMessageDialog(null,"�û��������������\n����������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);  
                 //��������  
                clear();  
            }  
    	}catch (Exception e) {
    		JOptionPane.showMessageDialog(null,"Manager.txt��ȡʧ�ܣ�","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);  
    	}
    }      
}  