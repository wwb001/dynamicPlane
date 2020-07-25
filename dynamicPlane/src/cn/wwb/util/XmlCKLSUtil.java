package cn.wwb.util;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import cn.wwb.dbc.DatabaseConnection;
import cn.wwb.factory.DAOFactory;

public class XmlCKLSUtil {
	public static String path1 = "D:\\航班动态可视化\\航班数据"; // XML文件夹路径

	public List<Map<String, String>> initCKLSData() {
		int n = 0;
		File[] files1 = new File(path1).listFiles();
		List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
		for (File file1 : files1) {
			String path2 = file1.getAbsolutePath();
			File[] files2 = new File(path2).listFiles();
			for (File file2 : files2) {
				if (file2.getName().contains("CKLS")) { // 航班值机柜台动态信息更新
					SAXReader saxReader = new SAXReader();
					Document document = null;
					try {
						document = saxReader.read(file2);
					} catch (DocumentException e) {
						e.printStackTrace();
					}
					Map<String, String> map = new HashMap<String, String>();
					Element element1 = (Element) document.selectObject("//FLID"); // 航班标识标签
					String FLID = element1.getText();
					map.put("FLID", FLID);
					Element element2 = (Element) document.selectObject("//FFID"); // 航班标识
					String FFID = element2.getText();
					map.put("FFID", FFID);
					Element element3 = (Element) document.selectObject("//FATT"); // 航班属性
					String FATT = element3.getText();
					map.put("FATT", FATT);
					List<Node> nodes1 = document.selectNodes("//CKLS");
					for (Node node1 : nodes1) {
						Element ele1 = (Element)node1;
						String FCES = ele1.elementText("FCES");  // 航班旅客值机预计开始时间
						map.put("FCES", FCES);
						String FCEE = ele1.elementText("FCEE");  // 航班旅客值机预计开始时间
						map.put("FCEE", FCEE);
						List<Node> nodes2 = node1.selectNodes("//CNTR");
						for (Node node2 : nodes2) {
							Element ele2 = (Element)node2;
							String CODE1 = ele2.elementText("CODE");  // 值机柜台编号
							if(map.get("CODE") != null) {
								map.put("CODE", map.get("CODE")+" "+CODE1);
							}else {
								map.put("CODE", CODE1);
							}
							String CKAT = ele2.elementText("CKAT");  // 值机柜台属性
							map.put("CKAT", CKAT);
							String BTSC = ele2.elementText("BTSC");  // 资源所属楼号
							map.put("BTSC", BTSC);
						}
					}
					list1.add(map);
				}
			}
		}
		System.out.println(list1);
		return list1;
	}

	public static void main(String[] args) {
		DatabaseConnection dbc = new DatabaseConnection();
		XmlCKLSUtil xmlUtil = new XmlCKLSUtil();
		List<Map<String, String>> list = xmlUtil.initCKLSData();
		try {
			DAOFactory.getCKLSdaoinstance(dbc.getConn()).insertCKLS(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbc.close();
		}
	}
}
