import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ShowRecordDialog extends JDialog implements ActionListener{
	File gradeFile;
	JButton clear;
	JTextArea showArea=null;
	TreeSet<People>treeSet;//����ʵ�� Set �ӿڣ��ýӿ��� TreeMap ʵ��֧�֡����ౣ֤������ set ������������Ԫ�أ�����ʹ�õ�
	//���췽����ͬ�����ܻᰴ��Ԫ�ص���Ȼ˳�� �������򣨲μ� Comparable���������ڴ��� set ʱ���ṩ�ıȽ�����������
	public ShowRecordDialog(){
		treeSet=new TreeSet<People>();
		showArea=new JTextArea(6,4);
		showArea.setFont(new Font("����",Font.BOLD,20));
		clear=new JButton("������а�");
		clear.addActionListener(this);
		add(new JScrollPane(showArea),BorderLayout.CENTER);
		add(clear,BorderLayout.SOUTH);
		setBounds(100,100,320,185);
		setModal(true);//��ģʽ�Ի��򣬵�ǰֻ�д˶Ի����Ǳ������
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				setVisible(true);
			}
		});
	}
	public void setGradeFile(File f){
		gradeFile=f;
		setTitle(f.getName());
	}
	public void showRecord(){
		showArea.setText(null);
		treeSet.clear();
		try{
			RandomAccessFile in=new RandomAccessFile(gradeFile,"rw");
			long fileLength=in.length();
			long readPosition=0;
			while(readPosition<fileLength){
				String name=in.readUTF();//�Ӵ��ļ���ȡһ���ַ�����
				int time=in.readInt();//�Ӵ��ļ���ȡһ���з��ŵ� 32 λ������
				readPosition=in.getFilePointer();
				People people=new People(name,time);
				treeSet.add(people);	
			}
			in.close();
			Iterator<People>iter=treeSet.iterator();//������������÷����ö������õ������ڵ����ڼ�ӵ�������ָ���
			//�����Ƴ�Ԫ�ء� 
			while(iter.hasNext()){
				People p=iter.next();
				showArea.append("����:"+p.getName()+",�ɼ���"+p.getTime()+"��");
				showArea.append("\n");
			}	
		}
		catch(IOException exp){System.out.println(exp);}
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==clear){
			try{
				File f=gradeFile.getAbsoluteFile();//���س���·�����ľ���·������ʽ��
				gradeFile.delete();
				f.createNewFile();
				showArea.setText("���а����");
			}
			catch(Exception ee){}
		}
	}
}
