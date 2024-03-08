package com.sds.project0308.sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

// paint()메서드를 개발자가 정의하기 위해 상속받기
public class MyPanel extends JPanel{

	//스프라이트 이미지는 배열로 선언
	Toolkit kit=Toolkit.getDefaultToolkit(); //이미지를 얻기 위한 도구
	
	String[] path = {
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image1.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image2.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image3.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image4.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image5.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image6.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image7.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image8.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image9.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image10.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image11.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image12.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image13.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image14.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image15.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image16.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image17.png",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0308\\res\\hero\\image18.png"
	};
	
	Image[] imgArray = new Image[path.length]; // 공간 확보
	int index = 0;
	
	public MyPanel() {
		for(int i=0; i<imgArray.length; i++)
			imgArray[i] = kit.getImage(path[i]);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.WHITE); // 색상 설정
		g.fillRect(0, 0, 500, 400);      // fillRect: 채워진 사각형을 그리는 메서드
		
		g.drawImage(imgArray[index], 50, 50, 200, 213, this);
	}
}
