package systemUI;

/* 
 * 功能：停车场管理系统的登陆界面 
 * 步骤1、登录界面的静态实现 
 * 步骤2：实现界面的切换 
 * 步骤3：调用类方法来验证用户名和密码 
 * author：cbb
 */  
import javax.swing.*;  

import parking.ParkingLot;

import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

import man.*;
  
public class ServerUI extends JFrame  implements ActionListener {  
	
    //定义登录界面的组件  
    JButton jb1,jb2,jb3=null;  
    JRadioButton jrb1,jrb2=null;  
    JPanel jp1,jp2,jp3,jp4=null;  
    JTextField jtf=null;  
    JLabel jlb1,jlb2,jlb3=null;  
    JPasswordField jpf=null;  
    ButtonGroup bg=null;  
    
    Security mySecurity;
    Manager myManager;
    //设定用户名和密码  
    //String username;  
    //String password;  
  
    public static void main(String[] args) {   
        ServerUI  ms = new ServerUI();          
    }  
    
    //构造函数  
    public ServerUI()  
    {  	
    	mySecurity = new Security("");
    	myManager = new Manager("");
         //创建组件  
        jb1=new JButton("登录");  
        jb2=new JButton("重置");  
        jb3=new JButton("退出");  
        
        //设置监听  
        jb1.addActionListener(this);  
        jb2.addActionListener(this);  
        jb3.addActionListener(this);  
        //以上三个按钮的监听统一放在了actionPerformance()中  
        
        jrb1=new JRadioButton("保安");  
        jrb2=new JRadioButton("经理");  
        bg=new ButtonGroup();  
        bg.add(jrb1);  
        bg.add(jrb2);  
        jrb1.setSelected(true);  
          
        jp1=new JPanel();  
        jp2=new JPanel();  
        jp3=new JPanel();  
        jp4=new JPanel();                 
          
        jlb1=new JLabel("用户名：");  
        jlb2=new JLabel("密    码：");  
        jlb3=new JLabel("权    限：");  
          
        jtf=new JTextField(10);  
        jpf=new JPasswordField(10); 
        
        //加入到JPanel中  
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
          
        //加入JFrame中  
        this.add(jp1);  
        this.add(jp2);  
        this.add(jp3);  
        this.add(jp4); 
        
        //设置布局管理器  
        this.setLayout(new GridLayout(4,1));  
        //给窗口设置标题  
        this.setTitle("停车场管理系统");  
        //设置窗体大小  
        this.setSize(400,300);  
        //设置窗体初始位置  
        this.setLocation(500, 350);  
        //设置当关闭窗口时，保证JVM也退出  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //显示窗体  
        this.setVisible(true);  
        this.setResizable(true);  
          
    }  
    @Override  
    public void actionPerformed(ActionEvent e) {  
    	
        if(e.getActionCommand()=="退出")  
        {  
        	int y = JOptionPane.showConfirmDialog(null, "是否要退出系统", "询问消息", JOptionPane.YES_NO_OPTION);
			if (y == 0) {
				ParkingLot.getInstance().clear();
	            System.exit(0);  
			}
        }
        else if(e.getActionCommand()=="登录") {  
        	
            //如果选中保安登录  
            if(jrb1.isSelected()) securitylogin();  
            
            //经理在登录系统  
            else if(jrb2.isSelected()) managerlogin();
        }
        else if(e.getActionCommand()=="重置")  
        {  	
            clear();  
        }               
    }  
          
      
//清空文本框和密码框  
    public  void clear() {  
            jtf.setText("");  
            jpf.setText("");  
    }  
  
//学生登录判断方法  
    public void securitylogin() {   
    
    	try {
	        if (mySecurity.Login(jtf.getText(), jpf.getText())) {	  
	        	mySecurity.setName(jtf.getText());
	            JOptionPane.showMessageDialog(null,"登录成功！","提示消息",JOptionPane.WARNING_MESSAGE);  
	            clear();  
	            //关闭当前界面  
	             dispose();  
	             //创建一个新界面  
	             SecurityUI ui=new SecurityUI(mySecurity.getName());  
	        }
	        else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty()) {  
	            JOptionPane.showMessageDialog(null,"请输入用户名和密码！","提示消息",JOptionPane.WARNING_MESSAGE);  
	        }
	        else if(jtf.getText().isEmpty()) {  
	            JOptionPane.showMessageDialog(null,"请输入用户名！","提示消息",JOptionPane.WARNING_MESSAGE);  
	        }
	        else if(jpf.getText().isEmpty()) {  
	            JOptionPane.showMessageDialog(null,"请输入密码！","提示消息",JOptionPane.WARNING_MESSAGE);  
	        }
	        else {  
	            JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);  
	             //清空输入框  
	            clear();  
	        }  
    	}catch (Exception e) {
    		JOptionPane.showMessageDialog(null,"Security.txt读取失败！","提示消息",JOptionPane.ERROR_MESSAGE);  
    	}
    }  
              
    //教师登录判断方法  
    public void managerlogin() {
    	//username = "jmf";
		//password = "jmf";
    	
    	try {
    		if(myManager.Login(jtf.getText(), jpf.getText())) {  
    			myManager.setName(jtf.getText());
                JOptionPane.showMessageDialog(null,"登录成功！","提示消息",JOptionPane.WARNING_MESSAGE);  
                clear();  
                //关闭当前界面  
                 dispose();  
                 //创建一个新界面  
                 ManagerUI ui=new ManagerUI(myManager.getName());  
            }
            else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty()) {  
                JOptionPane.showMessageDialog(null,"请输入用户名和密码！","提示消息",JOptionPane.WARNING_MESSAGE);  
            }
            else if(jtf.getText().isEmpty()) {  
                JOptionPane.showMessageDialog(null,"请输入用户名！","提示消息",JOptionPane.WARNING_MESSAGE);  
            }
            else if(jpf.getText().isEmpty()) {  
                JOptionPane.showMessageDialog(null,"请输入密码！","提示消息",JOptionPane.WARNING_MESSAGE);  
            }
            else {  
                JOptionPane.showMessageDialog(null,"用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);  
                 //清空输入框  
                clear();  
            }  
    	}catch (Exception e) {
    		JOptionPane.showMessageDialog(null,"Manager.txt读取失败！","提示消息",JOptionPane.ERROR_MESSAGE);  
    	}
    }      
}  