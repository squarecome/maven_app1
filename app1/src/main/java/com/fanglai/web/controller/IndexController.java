package com.fanglai.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.fanglai.dubbo.registry.service.TestRegistryService;
import com.fanglai.model.Order;
import com.fanglai.model.Test;
import com.fanglai.service.OrderService;
import com.fanglai.service.TestService;
import com.fanglai.utils.page.Page;
import com.fanglai.utils.page.PageUtil;

@Controller
public class IndexController {
	@Resource
	private TestService testService;
	@Resource
	private OrderService orderService;
	/*@Resource(name="testRegistryService2")
	private TestRegistryService testRegistryService;*/

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/home")
	public String home() {
		if (true) {
			throw new IllegalArgumentException("请输入数字！");
		}
		return "home";
	}

	@RequestMapping("/save")
	public String save(String username) {
		Test test = new Test();
		test.setName(username);
		test.setSex((byte) 1);
		test.setBirthday(new Date());
		testService.save(test);
		return "home";
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		//System.out.println(testRegistryService.hello("方来"));
		Order order = orderService.get("1");
		List<Test> list = testService.list();
		request.setAttribute("tests", list);
		/*
		 * SerializeConfig config = SerializeConfig.getGlobalInstance();
		 * config.put(Test.class, new ObjectSerializer() {
		 * 
		 * public void write(JSONSerializer serializer, Object object, Object
		 * fieldName, Type fieldType, int features) throws IOException {
		 * SerializeWriter out = serializer.getWriter(); Test test = (Test)
		 * object; out.writeFieldValue('{', "id", test.getId());
		 * out.writeFieldValue(',', "name", test.getName());
		 * out.writeFieldValue(',', "sex", test.getSex()); if(test.getOrders()
		 * != null){ List<Order> orders = test.getOrders();
		 * out.write(",\"orders\":"); out.writeFieldValue(',', "orders", "[");
		 * 
		 * for (int i = 0; i < orders.size(); i++) {
		 * serializer.write(orders.get(i)); } out.write("]");
		 * serializer.write(orders); }
		 * 
		 * out.write("}"); } });
		 * 
		 * config.put(Order.class, new ObjectSerializer() {
		 * 
		 * public void write(JSONSerializer serializer, Object object, Object
		 * fieldName, Type fieldType, int features) throws IOException {
		 * SerializeWriter out = serializer.getWriter(); Order order = (Order)
		 * object; out.writeFieldValue('{', "id", order.getId());
		 * out.writeFieldValue(',', "name", order.getName()); out.write("}"); }
		 * });
		 */
		String json = "{}";/* JSONArray.toJSONString(list, new SimplePropertyPreFilter(
				Order.class, "test"));*/
		System.out.println(json);
		return "list";
	}

	@RequestMapping("/upload")
	public String addUser(@RequestParam("file") CommonsMultipartFile[] files,
			HttpServletRequest request) {
		
		Page<Test> page = PageUtil.createPage(request);
		
		testService.page(page);
		
		for (int i = 0; i < files.length; i++) {
			System.out.println("fileName---------->"
					+ files[i].getOriginalFilename());

			if (!files[i].isEmpty()) {
				int pre = (int) System.currentTimeMillis();
				try {
					// 拿到输出流，同时重命名上传的文件
					FileOutputStream os = new FileOutputStream("d:/"
							+ new Date().getTime()
							+ files[i].getOriginalFilename());
					// 拿到上传文件的输入流
					FileInputStream in = (FileInputStream) files[i]
							.getInputStream();

					// 以写字节的方式写文件
					int b = 0;
					byte[] buffer = new byte[10240];
					while ((b = in.read(buffer)) != -1) {
						os.write(buffer,0,b);
					}
					os.flush();
					os.close();
					in.close();
					int finaltime = (int) System.currentTimeMillis();
					System.out.println(finaltime - pre);

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("上传出错");
				}
			}
		}
		return "home";
	}

	@RequestMapping("/upload2")
	public String upload2(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			DefaultMultipartHttpServletRequest multiRequest = (DefaultMultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int pre = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						System.out.println(myFileName);
						// 重命名上传后的文件名
						String fileName = "demoUpload"
								+ file.getOriginalFilename();
						// 定义上传路径
						String path = "d:/" + fileName;
						File localFile = new File(path);
						file.transferTo(localFile);
					}
				}
				// 记录上传该文件后的时间
				int finaltime = (int) System.currentTimeMillis();
				System.out.println(finaltime - pre);
			}

		}
		return "home";
	}
}