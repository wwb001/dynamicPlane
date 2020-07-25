package cn.wwb.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;

import cn.wwb.util.UploadUtil;

/**
 * Servlet implementation class uploadServlet
 */
@WebServlet("/uploadServlet")
public class uploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.创建磁盘文件项工厂
				DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
				//2.创建核心解析类
				ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
				//3.解析请求对象，将请求分成几个部分(FileItem)
				try {
					List<FileItem> list = fileUpload.parseRequest(request);
					//4.遍历集合获得每个部分的对象
					for (FileItem fileItem : list) {
						//判断是普通项还是文件上传项
						if (fileItem.isFormField()) {
							//普通项
							//获得普通项的名称：
							String name = fileItem.getFieldName();
							//获得普通项的值：
							String value = fileItem.getString("UTF-8");
							System.out.println(name+"  "+value);
						}else {
							//文件上传项
							//获得文件的名称：
							String filename = fileItem.getName();
							//获得唯一文件名
							String uuidFileName = UploadUtil.getUuidFileName(filename);
							//获得文件的输入流：
							InputStream inputStream = fileItem.getInputStream();
							//需要将文件写入到服务器的某个路径即可：
							String path = getServletContext().getRealPath("/upload");
							System.out.println(path);
							//创建输出流与输入流进行对接：
							OutputStream outputStream = new FileOutputStream(path+"\\"+uuidFileName);
							String path1 = path + "\\" +uuidFileName;
							int len = 0;
							byte[] b = new byte[1024];
							len = inputStream.read(b);
							while(len!=-1) {
								outputStream.write(b,0,len);
								len=inputStream.read(b);
							}
							inputStream.close();
							outputStream.close();
							Map<String, String> map = new HashMap<String, String>();
							map.put("code", "0");
							map.put("msg", "");
							map.put("path", path1);
							String json = JSON.toJSONString(map);
							response.getWriter().println(json);
						}
					}
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
