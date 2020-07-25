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
import org.dom4j.io.SAXReader;

import cn.wwb.dbc.DatabaseConnection;
import cn.wwb.factory.DAOFactory;

public class XmlDEPEUtil {
	public static String path1 = "D:\\航班动态可视化\\航班数据"; // XML文件夹路径

	public List<Map<String, String>> initDEPEData() {
		int n = 0;
		File[] files1 = new File(path1).listFiles();
		List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
		for (File file1 : files1) {
			String path2 = file1.getAbsolutePath();
			File[] files2 = new File(path2).listFiles();
			for (File file2 : files2) {
				if (file2.getName().contains("DEPE")) { // 航班本站起飞消息
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
					Element element4 = (Element) document.selectObject("//STAT"); // 航班发布状态
					String STAT = element4.getText();
					map.put("STAT", STAT);
					Element element5 = (Element) document.selectObject("//ISTA"); // 航班状态
					String ISTA = element5.getText();
					map.put("ISTA", ISTA);
					Element element6 = (Element) document.selectObject("//FRTT"); // 实际起飞时间
					String FRTT = element6.getText();
					map.put("FRTT", FRTT);
					list1.add(map);
				}
			}
		}
		System.out.println(list1);
		return list1;
	}

	public static void main(String[] args) {
		DatabaseConnection dbc = new DatabaseConnection();
		XmlDEPEUtil xmlUtil = new XmlDEPEUtil();
		List<Map<String, String>> list = xmlUtil.initDEPEData();
		try {
			DAOFactory.getDEPEdaoinstance(dbc.getConn()).insertDEPE(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbc.close();
		}
	}
}
