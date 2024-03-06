package com.sds.project0306.gallery;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class GalleryCanvas extends Canvas{
	
	GalleryMain galleryMain;
	
	int index; // 이미지 접근을 결정짓는 변수
	
	Toolkit kit = Toolkit.getDefaultToolkit();
	
	String[] filename = {
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0306\\images\\redvelvet\\rv1.jpg",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0306\\images\\redvelvet\\rv2.jpg",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0306\\images\\redvelvet\\rv3.jpg",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0306\\images\\redvelvet\\rv4.jpg",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0306\\images\\redvelvet\\rv5.jpg",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0306\\images\\redvelvet\\rv6.jpg",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0306\\images\\redvelvet\\rv7.jpg",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0306\\images\\redvelvet\\rv8.jpg",
			"D:\\MULTICAMPUS\\JAVA_workspace\\project0306\\images\\redvelvet\\rv9.jpg"
	};
	
	Image[] imageArray = new Image[filename.length]; // String 배열만큼 배열의 크기 지정
	
	public GalleryCanvas(GalleryMain galleryMain) {
		this.galleryMain = galleryMain; // 프레임, 오류메시지 출력을 위해
		for(int i=0; i<imageArray.length; i++) {
			imageArray[i] = kit.getImage(filename[i]);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		// 프로그램 가동과 동시에 0번째 이미지 부착
		g.drawImage(imageArray[index], 0, 0, 400, 300, this);
	}
}
