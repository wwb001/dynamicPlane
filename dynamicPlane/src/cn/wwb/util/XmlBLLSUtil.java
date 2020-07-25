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

public class XmlBLLSUtil {
	public static String path1 = "D:\\航班动态可视化\\航班数据"; // XML文件夹路径

	public List<Map<String, String>> initBLLSData() {
		int n = 0;
		File[] files1 = new File(path1).listFiles();
		List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
		for (File file1 : files1) {
			String path2 = file1.getAbsolutePath();
			File[] files2 = new File(path2).listFiles();
			for (File file2 : files2) {
				if (file2.getName().contains("BLLS")) { // 航班行李提取转盘动态信息更新
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
					List<Node> nodes = document.selectNodes("//BELT");
					for (Node node : nodes) {
						Element emp = (Element) node;
						String BTNO = emp.elementText("BTNO"); // 行李提取转盘序号
						map.put("BTNO", BTNO);
						String ID = emp.elementText("ID"); // 行李提取转盘唯一标识
						map.put("ID", ID);
						String CODE = emp.elementText("CODE"); // 行李提取转盘编号
						map.put("CODE", CODE);
						String BTAT = emp.elementText("BTAT"); // 行李提取转盘属性
						map.put("BTAT", BTAT);
						String ESTR = emp.elementText("ESTR"); // 预计开始使用时间
						map.put("ESTR", ESTR);
						String EEND = emp.elementText("EEND"); // 预计结束使用时间
						map.put("EEND", EEND);
						String RSTR = emp.elementText("RSTR"); // 实际开始使用时间
						map.put("RSTR", RSTR);
						String REND = emp.elementText("REND"); // 实际结束使用时间
						map.put("REND", REND);
						String BTSC = emp.elementText("BTSC"); // 资源所属楼号
						map.put("BTSC", BTSC);
					}
					list1.add(map);
				}
			}
		}
		System.out.println(list1);
		return list1;
	}

	public static void main(String[] args) {
		XmlBLLSUtil xmlUtil = new XmlBLLSUtil();
		DatabaseConnection dbc = new DatabaseConnection();
		List<Map<String, String>> list = xmlUtil.initBLLSData();
		try {
			DAOFactory.getBLLSdaoinstance(dbc.getConn()).insertBLLS(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbc.close();
		}
	}

}
