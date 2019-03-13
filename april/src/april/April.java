package april;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class April {

	static String BASE_URL = "https://aprilskin.com";// ������ ������ ��
	
	static String BASE = BASE_URL + "/product/list.html?cate_no=48";
	static String CLEANSING = BASE_URL + "/product/list.html?cate_no=45";
	static String SKIN = BASE_URL +"/product/list.html?cate_no=42";
	static String HAIRBODY = BASE_URL + "/product/list.html?cate_no=52";
	static String MAKEUP = BASE_URL + "/product/list.html?cate_no=55";
	
	public void category(String link) throws IOException {
		Document doc = Jsoup.connect(link).get(); // ī�װ� ��ũ 
		Elements img = doc.select("div.thumbnail a");	
		Elements img1 = doc.select("div.thumbnail a img");	
		Elements count = doc.select("p.prdCount strong");
		
		int c = Integer.parseInt(count.text());
		System.out.println("��ǰ �� :" + c);
		
		for(int i = 0; i<c; i++) {
			String str = img.eq(i).attr("href").replaceAll("&display_group=1", ""); // ���ʿ��� ��ũ ����
			String imgsrc = img1.eq(i).attr("src"); // img�� src �Ӽ�
			String detail = BASE_URL + str;
			System.out.println(detail);	// ��ǰ�� ������ �ּ�
			System.out.println(imgsrc); // ��ǰ�̹��� �ּ�
			Document doc1 = Jsoup.connect(detail).get();
			Elements title = doc1.select("div.infoArea h2"); 
			System.out.println(title.text()); // ��ǰ��
			Elements t = doc1.select("div.table_april tr th");
			String[] arr = new String[t.size()];

			for(int j = 0; j< t.size(); j++) {
				arr[j] = t.eq(j).text();
				if(arr[j].contains("������")) {
					Elements ingdnt = doc1.select("div.table_april tr td");
					System.out.println(ingdnt.eq(j).text()); // ������
				}
			}
			System.out.println("\n" + "=======================" + "\n");
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		April april = new April();
		
		System.out.println("======================���̽�============================" + "\n");
		april.category(BASE);
		System.out.println("======================Ŭ��¡============================" + "\n");
		april.category(CLEANSING);
		System.out.println("======================��Ų�ɾ�============================" + "\n");
		april.category(SKIN);
		System.out.println("======================���ٵ�============================" + "\n");
		april.category(HAIRBODY);
		System.out.println("======================����ũ��============================" + "\n");
		april.category(MAKEUP);

	}
}