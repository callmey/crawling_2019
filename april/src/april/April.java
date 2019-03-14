package april;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class April {
	
	static String BASE_URL = "https://aprilskin.com";// 페이지 나오기 전
	
	static String BASE = BASE_URL + "/product/list.html?cate_no=48";
	static String CLEANSING = BASE_URL + "/product/list.html?cate_no=45";
	static String SKIN = BASE_URL +"/product/list.html?cate_no=42";
	static String HAIRBODY = BASE_URL + "/product/list.html?cate_no=52";
	static String MAKEUP = BASE_URL + "/product/list.html?cate_no=55";

	public void category(String link, String category) throws IOException {
		//String fileName = "C:\\Users\\soldesk\\Documents\\april.txt";
		try {
		//BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));
		
		Document doc = Jsoup.connect(link).get(); // 카테고리 링크 
		Elements img = doc.select("div.thumbnail a");	
		Elements img1 = doc.select("div.thumbnail a img");	
		Elements count = doc.select("p.prdCount strong");
		
		int c = Integer.parseInt(count.text());
		//System.out.println("상품 수 :" + c);
		
		for(int i = 0; i<c; i++) {
			String str = img.eq(i).attr("href").replaceAll("&display_group=1", ""); // 불필요한 링크 삭제
			String imgsrc = img1.eq(i).attr("src"); // img의 src 속성
			String detail = BASE_URL + str;
			Document doc1 = Jsoup.connect(detail).get();
			Elements title = doc1.select("div.infoArea h2"); 
			
			System.out.print("에이프릴스킨" + "\t");
			//fw.write("에이프릴스킨" + "\t");
			System.out.print(category + "\t");
			//fw.write(category + "\t");
			System.out.print(detail + "\t");	// 제품상세 페이지 주소
			//fw.write(detail + "\t");
			System.out.print(title.text() + "\t"); // 제품명
			//fw.write(detail + "\t");
			
			System.out.print(imgsrc + "\t"); // 제품이미지 주소
			Elements t = doc1.select("div.table_april tr th");
			String[] arr = new String[t.size()];
			//fw.write(title.text() + "\t");
			//fw.write(imgsrc + "\t");
			for(int j = 0; j< t.size(); j++) {
				arr[j] = t.eq(j).text();
				if(arr[j].contains("전성분")) {
					Elements ingdnt = doc1.select("div.table_april tr td");
					System.out.print(ingdnt.eq(j).text() + "\t"); // 전성분
					//fw.write(ingdnt.eq(j).text() + "\t");
				}
			}
			System.out.println();
			//fw.write("\n");
				
		} // for 문 end
			//fw.close();
		}catch(Exception e) {
			//e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		
		April april = new April();
		
		//System.out.println("======================베이스============================" + "\n");
		april.category(BASE, "base");
		//System.out.println("======================클렌징============================" + "\n");
		april.category(CLEANSING, "skin");
		//System.out.println("======================스킨케어============================" + "\n");
		april.category(SKIN, "skin");
		//System.out.println("======================헤어바디============================" + "\n");
		april.category(HAIRBODY, "hairbody");
		//System.out.println("======================메이크업============================" + "\n");
		april.category(MAKEUP, "makeup");
		

	}
}