package cn.wwb.util;

import java.util.UUID;

public class UploadUtil {
	public static String getUuidFileName(String fileName) {
		//解决文件重名问题     获得后缀名     生成一段随机字符串
		int idx = fileName.lastIndexOf(".");
		String exName = fileName.substring(idx);  //拿到扩展名
		//生成随机字符串
		String uuidFileName = UUID.randomUUID().toString().replace("-", "") + exName;
		return uuidFileName;
	}
}
