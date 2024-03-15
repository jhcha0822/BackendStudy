package com.sds.SEShop.product;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import com.sds.SEShop.lib.FileManager;
import com.sds.SEShop.main.Page;
import com.sds.SEShop.main.ShopMain;

public class ProductRegist extends Page{
	JLabel la_top, la_sub, la_product_name, la_price, la_brand, la_image, la_download,la_preview;
	JComboBox<String> b_top, b_sub; // 상위, 하위 선택상자 
	JTextField t_product_name, t_price, t_brand, t_url;
	JProgressBar bar; // 다운로드 현황 
	JPanel p_preview; // 이미지 미리보기 패널	
	JButton bt_collect, bt_regist, bt_list; // 수집, 상품 등록, 목록 버튼
	
	// 콤보박스는 디자인에 초점을 맞춰놓은 컴포넌트이기 때문에, 실제 데이터를 담을 수 없다
	// 콤보박스의 index와 각 아이템의 위치 index가 일치하는 배열과 같은 존재를 두자
	// 유동적인 크기를 지니게끔 ArrayList로 두자
	ArrayList<Integer> topIdxList = new ArrayList<Integer>(); // 정수와 1:1 대응하는 Wrapper 객체 Integer
	ArrayList<Integer> subIdxList = new ArrayList<Integer>();
	
	Thread thread; // 다운로드 progressbar 제어용
	Image image;
	String myName;
	int subcategory_idx;
	
	public ProductRegist(ShopMain shopMain) {
		super(Color.GREEN);
		this.shopMain = shopMain;
		
		// 컨테이너 크기 조정 및 색상 부여 
		container = new JPanel();
		container.setPreferredSize(new Dimension(600, 700));
		container.setBackground(Color.YELLOW);
			
		// 생성
		la_top = new JLabel("상위 카테고리");
		la_sub = new JLabel("하위 카테고리");
		la_product_name = new JLabel("제품명");
		la_price = new JLabel("가격");
		la_brand = new JLabel("브랜드");
		la_image = new JLabel("수집 URL");
		la_download = new JLabel("다운로드 현황");
		la_preview = new JLabel("미리보기");
		
		b_top = new JComboBox<String>();
		b_sub = new JComboBox<String>();
		t_product_name = new JTextField();
		t_price = new JTextField("30000");
		t_brand = new JTextField();
		t_url = new JTextField();
		bt_collect = new JButton("수집");
		bar = new JProgressBar();
		p_preview  = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(image, 0, 0, 280, 280, container);
			}
		}; // 페인트 메서드 재정의
		
		bt_regist = new JButton("상품등록");
		bt_list = new JButton("상품목록");
				
		// 스타일
		Dimension d = new Dimension(280, 35);
		la_top.setPreferredSize(d);
		b_top.setPreferredSize(d);
		
		la_sub.setPreferredSize(d);
		b_sub.setPreferredSize(d);
		
		la_product_name.setPreferredSize(d);
		t_product_name.setPreferredSize(d);
		
		la_price.setPreferredSize(d);
		t_price.setPreferredSize(d);
		
		la_brand.setPreferredSize(d);
		t_brand.setPreferredSize(d);
		
		la_image.setPreferredSize(d);
		t_url.setPreferredSize(new Dimension(220, 35));
		
		la_download.setPreferredSize(d);
		bar.setPreferredSize(d);
		bar.setStringPainted(true); // 바에 글씨 표현 가능
		bar.setBackground(Color.YELLOW);
		bar.setForeground(Color.ORANGE);
		
		la_preview.setPreferredSize(d);
		p_preview.setPreferredSize(new Dimension(280, 280));
		
		// 조립
		container.add(la_top);
		container.add(b_top);
		container.add(la_sub);
		container.add(b_sub);
		container.add(la_product_name);
		container.add(t_product_name);
		container.add(la_price);
		container.add(t_price);
		container.add(la_brand);
		container.add(t_brand);
		container.add(la_image);
		container.add(t_url);
		container.add(bt_collect);
		container.add(la_download);
		container.add(bar);
		container.add(la_preview);
		container.add(p_preview);
		container.add(bt_regist);
		container.add(bt_list);
		
		add(container);
		
		getTopCategory();//최상위 카테고리 데이터 불러오기
		
		// 상위 카테고리 콤보박스에 리스너 연결
		b_top.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) { // html의 onChage 와 동일
				if(e.getStateChange() == ItemEvent.SELECTED) { // 중복 이벤트 방지
					int index=b_top.getSelectedIndex(); // 선택된 콤보박스의 index 받아오기
					// 원칙은 get() 을 통해 얻어진 객체가 Integer 이지만, 개발자가 int 형으로 
					// 대입이 가능한 현상을 unBoxing 이라 한다..이것 또한 편의성때문에 지원한다..
					if(index > 0) { // 0번째는 이미 안내 문구가 들어있으므로, 0번째보다 큰 애들만 반응을 보이자 
						int topcategory_idx = topIdxList.get(index-1); // unboxing
						getSubCategory(topcategory_idx);
					}
				}
			}
		});
		
		// 하위 카테고리 콤보박스에 리스너 연결
		b_sub.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) { // 중복 이벤트 방지
					// 콤보박스의 값을 변경할때마다 subcategory_idx값을 subIdxList의 ArrayList에서 가져오자
					// 첫번째 칸은 안내문구이므로 ArrayList를 가져오지 말 것
					int index = b_sub.getSelectedIndex();
					if(index > 0) {
						subcategory_idx = subIdxList.get(index-1);	
						System.out.println("당신이 선택한 하위 카테고리의 pk는 "+subcategory_idx);
					}
				}
			}
		});
		
		bt_collect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread = new Thread() { // 쓰레드 생성: 버튼을 누를때마다 생성
					// 쓰레드로 돌릴 로직을 run에 작성한다면 JVM이 알아서 호출
					// 개발자는 생성된 쓰레드를 start() 메서드로 runnable 영역으로 밀어넣기만 하면 됨
					public void run() {
						downLoadFromURL();
					}
				}; 
				thread.start();
			}
		});
		
		// 상품등록버튼에 리스너 연결
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
		
		// 상품목록버튼에 리스너 연결
		bt_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopMain.showHide(ShopMain.PRODUCT_LIST);
				// ProductList productList = (ProductList)shopMain.pages[ShopMain.PRODUCT_LIST];
				// productList.getProductList();
				shopMain.getProductList();
				// ((ProductList)shopMain.pages[ShopMain.PRODUCT_LIST]).getProductList(); // 상품 갱신
				// ((ProductList)shopMain.pages[ShopMain.PRODUCT_LIST]).table.updateUI();
			}
		});
	}
	
	public void regist() {
		String product_name = t_product_name.getText(); // 상품명
		int price = Integer.parseInt(t_price.getText()); // 가격  
		String brand=t_brand.getText(); // 브랜드 
		String filename=myName; // 새롭게 다운로드에 의해 생성된 파일명
		// idx값은 멤버변수
		
		String sql = "insert into product(product_idx, product_name, price, brand, filename, subcategory_idx)";
		sql += " values(seq_product.nextval, '"+product_name+"', "+price+", '"+brand+"','"+filename+"', "+subcategory_idx+")";
		// System.out.println(sql);
		
		// 준비된 쿼리 실행: DML: executeUpdate() 수행 후 DML에 의해 영향을 받은 record 수 반환
		// insert: 1; update, delete: 조건에 맞는 수;
		// 0이 나오면 수행이 실패했다는 뜻
		// select : executeQuery(): ResultSet
		PreparedStatement pstmt = null;
		try {
			pstmt = shopMain.con.prepareStatement(sql);
			int result;
			result = pstmt.executeUpdate();
			if(result<1)
				JOptionPane.showMessageDialog(this, "등록 실패");
			else
				JOptionPane.showMessageDialog(this, "등록 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	// 인터넷상의 이미지 주소를 이용하여 하드디스크에 이미지를 수집하자
	// 웹 서버로부터 정적 자원을 가져오려면 http 프로토콜을 사용
	// 자바 SE에서는 HttpURLConnection 객체가 웹상의 요청을 시도할 수 있는 객체로 지원됨
	// 즉 웹브라우저의 요청 행위를 학습
	public void downLoadFromURL() {
		URLConnection urlCon = null;
		HttpURLConnection httpCon = null; // 웹상의 요청을 시도하고 응답을 가져올 수 있는 객체: 웹브라우저의 역할 일부 수행 가능
		
		InputStream is = null;
		FileOutputStream fos = null; // 파일을 대상으로 한 바이트 기반 출력 스트림
		// 모든 출력스트림은 아니지만, 이 파일 출력스트림은 빈 파일을 작성함
		try {
			URL url = new URL(t_url.getText());
			urlCon = url.openConnection();
			httpCon = (HttpURLConnection)urlCon; // 다운캐스팅
			
			int total = httpCon.getContentLength(); // 정적 자원의 바이트 용량
			// System.out.println("이미지 크기: "+total+"byte");
			int count = 0;
			
			// 웹서버에 있는 정적 자원에 대해 스트림 구성
			 is = httpCon.getInputStream();
			// 읽어들인 데이터를 출력할 파일 출력스트림 구성
			 long time = System.currentTimeMillis();
			 String ext = FileManager.getExt(t_url.getText()); // 확장자
			 myName = time + "." + ext;
			 // 출력스트림에 의해 새롭게 생성되는 파일은 추후 복사가 완료되면 위 p_preview 패널의 그림의 대상이 되어야 함
			 fos = new FileOutputStream("D:\\MULTICAMPUS\\JAVA_workspace\\SEShop\\res\\"+myName);
			 int data = -1;
			 while(true) {
				 data = is.read();
				 if(data == -1)
					 break;
				 float percent = (++count/(float)total)*100;
				 // System.out.println(percent);
                 bar.setValue((int)percent); // 너무 빠르니 쓰레드 사용 그래픽 처리가 따라오지 못함
				 fos.write(data);
				 // System.out.println(data);
			 }
			 ImageIcon icon = new ImageIcon("D:\\MULTICAMPUS\\JAVA_workspace\\SEShop\\res\\"+myName);
			 image = icon.getImage();
			 p_preview.repaint();
			 // JOptionPane.showMessageDialog(this, "이미지 수집 완료");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void getTopCategory() {
		// 콤보박스에 최상위 카테고리를 넣어주기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = shopMain.con.prepareStatement("select * from topcategory"); // 쿼리 준비
			rs = pstmt.executeQuery(); // select문 전송해 결과 표 받아옴
			// 0번째 요소에 안내 문구 채우기
			b_top.addItem("카테고리 선택");
			while(rs.next()) {
				b_top.addItem(rs.getString("topname"));
				// 상위 카테고리의 idx도 담기
				topIdxList.add(rs.getInt("topcategory_idx")); // 원칙상 int는 기본 자료형이므로 객체 자료형을 담는 ArrayList에 직접 담을 수 없어야 하나
										 // sun은 편의상 컬렉션 객체에 기본 자료형을 넣으면 자동으로 Wrapper로 변환해줌 (Boxing)
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void getSubCategory(int top_idx) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//System.out.println("select * from subcategory where topcategory="+top_idx);
		try {
			pstmt = shopMain.con.prepareStatement("select * from subcategory where topcategory_idx="+top_idx);
			rs = pstmt.executeQuery();
			b_sub.removeAllItems(); // 누적되는 현상 제거, 화면에서만 지우기
			b_sub.addItem("카테고리 선택");
			subIdxList.removeAll(subIdxList); // 메모리에서 제거
			while(rs.next()) {
				b_sub.addItem(rs.getString("subname"));
				subIdxList.add(rs.getInt("subcategory_idx")); // 콤보박스와 쌍을 이루는 ArrayList 생성
			}
			// System.out.println("현재까지 쌓인 subcategory: "+subIdxList.size()); // subIdxList는 멤버변수라 계속 쌓임. b_sub만 지우는중
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
}
