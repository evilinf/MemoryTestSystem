import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;
public class MemoryGame extends JFrame implements ActionListener {
  JMenuBar bar;
  JMenu menuGrade,menuResult,menuIcon;
  JMenuItem oneGradeItem,twoGradeItem,threeGradeItem;
  JMenuItem oneGradeResult,twoGradeResult,threeGradeResult;
  JMenuItem carImageIcon,animalImageIcon;
  File fileOneGrade,fileTwoGrade,fileThreeGrade,gradeFile ;
  String imageName[];
  MemoryTestArea memoryArea;
  ShowRecordDialog showDialog=null;
  int m=5,n=6;
  final int imageNumber=7;
  MemoryGame(){
	  fileOneGrade=new File("�����������С�txt");
	  fileTwoGrade=new File("�м��������С�txt");
	  fileThreeGrade=new File("�߼��������С�txt");
	  bar=new JMenuBar();
	  menuGrade=new JMenu("ѡ�񼶱�");
	  oneGradeItem=new JMenuItem("����");
	  twoGradeItem=new JMenuItem("�м�");
	  threeGradeItem=new JMenuItem("�߼�");
	  menuGrade.add(oneGradeItem);
	  menuGrade.add(twoGradeItem);
	  menuGrade.add(threeGradeItem);
	  menuResult=new JMenu("�鿴����");
	  oneGradeResult=new JMenuItem("�������а�");
	  twoGradeResult=new JMenuItem("�м����а�");
	  threeGradeResult=new JMenuItem("�߼����а�");
	  menuResult.add(oneGradeResult);
	  menuResult.add(twoGradeResult);
	  menuResult.add(threeGradeResult);
	  menuIcon=new JMenu("ѡ��ͼ��");
      carImageIcon=new JMenuItem("����ͼ��");
      animalImageIcon=new JMenuItem("����ͼ��");
      animalImageIcon.addActionListener(this);
      carImageIcon.addActionListener(this);
	  menuIcon.add(animalImageIcon);
	  menuIcon.add(carImageIcon);
	  bar.add(menuGrade);
	  bar.add(menuResult);
	  bar.add(menuIcon);
	  setJMenuBar(bar);
	  oneGradeItem.addActionListener(this);
	  twoGradeItem.addActionListener(this);
	  threeGradeItem.addActionListener(this);
	  oneGradeResult.addActionListener(this);
	  twoGradeResult.addActionListener(this);
	  threeGradeResult.addActionListener(this);
	  if(!fileOneGrade.exists()){
		  try{
			  fileOneGrade.createNewFile();
		  }
		  catch(IOException exp){}
	  }
	  if(!fileTwoGrade.exists()){
		  try{
			  fileTwoGrade.createNewFile();
		  }
		  catch(IOException exp){}
	  }
	  if(!fileThreeGrade.exists()){
		  try{
			  fileThreeGrade.createNewFile();
		  }
		  catch(IOException exp){}
	  }
      setBounds(100,100,800,360);
      setVisible(true);
      memoryArea=new MemoryTestArea();
      imageName=new String[imageNumber];
      for(int i=0;i<imageName.length;i++){
    	  imageName[i]=new String("car"+i+".jpg");
      }
      m=5;
      n=6;
      gradeFile=fileOneGrade;
      memoryArea.initBlock(m,n,imageName,gradeFile);//
      add(memoryArea,BorderLayout.CENTER);
      showDialog=new ShowRecordDialog();
      validate();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public void actionPerformed(ActionEvent event){
	  if(event.getSource()==oneGradeItem){
		  m=5;
		  n=6;
		  gradeFile=fileOneGrade;
		  memoryArea.initBlock(m,n,imageName,gradeFile);
	  }
	  if(event.getSource()==twoGradeItem){
		  m=6;
		  n=7;
		  gradeFile=fileTwoGrade;
		  memoryArea.initBlock(m,n,imageName,gradeFile);
	  }
	  if(event.getSource()==threeGradeItem){
		  m=7;
		  n=8;
		  gradeFile=fileThreeGrade;
		  memoryArea.initBlock(m,n,imageName,gradeFile);
	  }
	  if(event.getSource()==carImageIcon){
		  for(int i=0;i<imageName.length;i++){
			  imageName[i]=new String("car"+i+".jpg");
		  }
		  memoryArea.setImageName(imageName);
		  memoryArea.initBlock(m,n,imageName,gradeFile);
	  }
	  if(event.getSource()==animalImageIcon){
		for(int i=0;i<imageName.length;i++){
			imageName[i]=new String("animal"+i+".jpg");
		}  
		memoryArea.setImageName(imageName);
		memoryArea.initBlock(m,n,imageName,gradeFile);
	  }
	  if(event.getSource()==oneGradeResult){
		  showDialog.setGradeFile(fileOneGrade);
		  showDialog.showRecord();
		  showDialog.setVisible(true);
	  }
	  if(event.getSource()==twoGradeResult){
		  showDialog.setGradeFile(fileTwoGrade);
		  showDialog.showRecord();
		  showDialog.setVisible(true);
	  }
	  if(event.getSource()==threeGradeResult){
		  showDialog.setGradeFile(fileThreeGrade);
		  showDialog.showRecord();
		  showDialog.setVisible(true);
	  }
  }
  public static void main(String args[]){
	  new MemoryGame();
  }
}