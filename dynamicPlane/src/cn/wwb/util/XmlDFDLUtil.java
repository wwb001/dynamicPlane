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

public class XmlDFDLUtil {
	public static String path1 = "D:\\航班动态可视化\\航班数据"; // XML文件夹路径

	public List<Map<String, String>> initDFDLData() {
		File[] files1 = new File(path1).listFiles();
		List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
		for (File file1 : files1) {
			String path2 = file1.getAbsolutePath();
			File[] files2 = new File(path2).listFiles();
			for (File file2 : files2) {
				if (file2.getName().contains("DFDL")) { // 动态航班整表同步事件
					SAXReader saxReader = new SAXReader();
					Document document = null;
					try {
						document = saxReader.read(file2);
					} catch (DocumentException e) {
						e.printStackTrace();
					}					
					List<Node> nods = document.selectNodes("//DFLT");
					for (Node nod : nods) {
						Map<String, String> map = new HashMap<String, String>();
						Element ele = (Element) nod;
						String FLID = ele.elementText("FLID"); // 航班标识标签
						map.put("FLID", FLID);
						String AFID = ele.elementText("AFID"); // 关联航班ID
						map.put("AFID", AFID);
						String FFID = ele.elementText("FFID"); // 航班标识
						map.put("FFID", FFID);
						String AWCD = ele.elementText("AWCD"); // 航空公司二字码
						map.put("AWCD", AWCD);
						String FLNO = ele.elementText("FLNO"); // 航班号
						map.put("FLNO", FLNO);
						String FEXD = ele.elementText("FEXD"); // 航班执行日期
						map.put("FEXD", FEXD);
						String FLIO = ele.elementText("FLIO"); // 航班进出标志
						map.put("FLIO", FLIO);
						String FLTK = ele.elementText("FLTK"); // 航班任务
						map.put("FLTK", FLTK);
						String FATT = ele.elementText("FATT"); // 航班属性
						map.put("FATT", FATT);
						String CFTP = ele.elementText("CFTP"); // 机型代码
						map.put("CFTP", CFTP);
						String CFNO = ele.elementText("CFNO"); // 机号代码
						map.put("CFNO", CFNO);
						String ABST = ele.elementText("ABST"); // 航班不正常状态
						map.put("ABST", ABST);
						String ABRS = ele.elementText("ABRS"); // 航班发布不正常原因
						map.put("ABRS", ABRS);
						String IAST = ele.elementText("IAST"); // 内部航班不正常状态
						map.put("IAST", IAST);
						String IARS = ele.elementText("IARS"); // 内部航班不正常原因
						map.put("IARS", IARS);
						List<Node> nodes1 = nod.selectNodes("AIRL");
						for (Node nod1 : nodes1) {
							List<Node> nodes = nod1.selectNodes("ARPT");
							int n = nodes.size();
							if (n==2) {
								for (Node node : nodes) {
									Element emp = (Element) node;
									String APNO = emp.elementText("APNO");
									if (APNO.equals("1")) {
										map.put("起飞站APNO", APNO); // 航站序号
										String APCD = emp.elementText("APCD");
										map.put("起飞站航站三字码", APCD); // 航站三字码
										String FPTT = emp.elementText("FPTT");
										map.put("起飞站计划起飞时间", FPTT); // 计划起飞时间
										String FETT = emp.elementText("FETT");
										map.put("起飞站预计起飞时间", FETT); // 预计起飞时间
										String FRTT = emp.elementText("FRTT");
										map.put("起飞站实际起飞时间", FRTT); // 实际起飞时间
									} else {
										map.put("目的站APNO", APNO); //航站序号
										String APCD = emp.elementText("APCD");
										map.put("目的站航站三字码", APCD); //航站三字码
										String FPLT = emp.elementText("FPLT");
										map.put("目的站计划降落时间", FPLT); //计划降落时间
										String FELT = emp.elementText("FELT");
										map.put("目的站预计降落时间", FELT); //预计降落时间
										String FRLT = emp.elementText("FRLT");
										map.put("目的站实际降落时间", FRLT); //实际降落时间
									}
								}
							}
							if (n==3) {
								for (Node node : nodes) {
									Element emp = (Element) node;
									String APNO = emp.elementText("APNO");
									if (APNO.equals("1")) {
										map.put("起飞站APNO", APNO); // 航站序号
										String APCD = emp.elementText("APCD");
										map.put("起飞站航站三字码", APCD); // 航站三字码
										String FPTT = emp.elementText("FPTT");
										map.put("起飞站计划起飞时间", FPTT); // 计划起飞时间
										String FETT = emp.elementText("FETT");
										map.put("起飞站预计起飞时间", FETT); // 预计起飞时间
										String FRTT = emp.elementText("FRTT");
										map.put("起飞站实际起飞时间", FRTT); // 实际起飞时间
									}
								else if (APNO.equals("2")) {
										map.put("中转站APNO", APNO); //航站序号
										String APCD = emp.elementText("APCD");
										map.put("中转站航站三字码", APCD); //航站三字码
										String FPTT = emp.elementText("FPTT");
										map.put("中转站计划起飞时间", FPTT); //计划起飞时间
										String FETT = emp.elementText("FETT");
										map.put("中转站预计起飞时间", FETT); //预计起飞时间
										String FRTT = emp.elementText("FRTT");
										map.put("中转站实际起飞时间", FRTT); //实际起飞时间
										String FPLT = emp.elementText("FPLT");
										map.put("中转站计划降落时间", FPLT); //计划降落时间
										String FELT = emp.elementText("FELT");
										map.put("中转站预计降落时间", FELT); //预计降落时间
										String FRLT = emp.elementText("FRLT");
										map.put("中转站实际降落时间", FRLT); //实际降落时间
									}else {
										map.put("目的站APNO", APNO); //航站序号
										String APCD = emp.elementText("APCD");
										map.put("目的站航站三字码", APCD); //航站三字码
										String FPLT = emp.elementText("FPLT");
										map.put("目的站计划降落时间", FPLT); //计划降落时间
										String FELT = emp.elementText("FELT");
										map.put("目的站预计降落时间", FELT); //预计降落时间
										String FRLT = emp.elementText("FRLT");
										map.put("目的站实际降落时间", FRLT); //实际降落时间
									}
								}
							}
							

//							
						}
						list1.add(map);
					}

				}
			}
		}
		System.out.println(list1);
		return list1;
	}

	public static void main(String[] args) {
		DatabaseConnection dbc = new DatabaseConnection();
		XmlDFDLUtil xmlUtil = new XmlDFDLUtil();
		xmlUtil.initDFDLData();
		List<Map<String, String>> list = xmlUtil.initDFDLData();
		try {
			DAOFactory.getDFDLdaoinstance(dbc.getConn()).insertDFDL(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbc.close();
		}
	}
}
