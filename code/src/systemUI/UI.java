package systemUI;

import java.awt.*;  
import java.awt.event.*;  
  
import javax.swing.*;  
  
public class UI extends JFrame implements ActionListener  
{  
  
         //�������  
        JButton jb1,jb2=null;  
        JPanel jp1,jp2,jp3=null;  
        JLabel jlb1,jlb2,jlb3,jlb4=null;  
          
        public static void main(String[] args) {  
            // TODO Auto-generated method stub  
//          UI  ui=new UI();  
        }  
          
        //���캯��  
        public  UI()    //��������Ϊvoid!!!!!���򵯲����½���  
        {  
            //�������  
            jb1=new JButton("�γ̹���");  
            jb2=new JButton("�ɼ���ѯ");  
              
            jp1=new JPanel();  
            jp2=new JPanel();  
            jp3=new JPanel();  
              
            jlb1=new JLabel("����");  
            jlb2=new JLabel("ѧ��");  
            jlb3=new JLabel("���¹��棺");  
            jlb4=new JLabel("��У������ʮ����У���֪ͨ");  
              
            jp1.add(jlb1);  
            jp1.add(jlb2);  
              
            jp2.add(jb1);  
            jp2.add(jlb3);  
              
            jp3.add(jb2);  
            jp3.add(jlb4);  
              
              
            this.add(jp1);  
            this.add(jp2);  
            this.add(jp3);  
              
            //���ò��ֹ�����  
            this.setLayout(new GridLayout(3,3,50,50));  
            this.setTitle("ѧ���ɼ�����ϵͳ");  
            this.setSize(400,300);  
            this.setLocation(200, 200);       
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            this.setVisible(true);  
  
}  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            // TODO Auto-generated method stub  
              
        }  
}  