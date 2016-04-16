/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * 生成静态html
 * 
 * @author shefeng
 * @Date 2014年9月25日
 */
public class GenerateHtml {
    
    private final static Logger logger = Logger.getLogger(GenerateHtml.class);

	public static void main(String[] args) {
		
		Properties properties = new Properties();
		properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "C:/workspace");
		
		// String style = "";
		//String str = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/><title>$title</title>#parse('style.vm')</head><body><table border='1'>#parse('headFile.vm')<tr><td>velocity</td></tr></table></body></html>";
		//String head = "<tr><td>$title</td></tr>";
		// String foot = "";
		
		//File temp = new File("C:/workspace1/temp.vm");
		//File headFile = new File("C:/workspace/headFile.vm");
		//File footFile = new File("C:/workspace/footFile.vm");
		//File styleFile = new File("C:/workspace/style.vm");
		try {
			
			//createFile(temp,str);
			//createFile(headFile,head);
			//createFile(footFile,foot);
			//createFile(styleFile,style);
			
			VelocityContext context = new VelocityContext();
			context.put("seo_title", "科勒中国  bnnice");
			context.put("seo_keywords", "高端大气美观科勒中国 KOHLER");
			context.put("seo_description", "领导者科勒中国 KOHLER");
			context.put("has_suite", "1");//${has_suite}为1是卫浴产品
			//top5
			Map<String,Map<String,String >> productCategoryMap = new LinkedHashMap<String,Map<String,String>>();
			Map<String,String> bathProduct = new LinkedHashMap<String,String>();
			bathProduct.put("title", "卫浴产品");
			bathProduct.put("url", "bathroom.html");
			Map<String,String> kitchenProduct = new LinkedHashMap<String,String>();
			kitchenProduct.put("title", "厨房产品");
			kitchenProduct.put("url", "kitchen.html");
			Map<String,String> homeImagination = new LinkedHashMap<String,String>();
			homeImagination.put("title", "家居畅想");
			homeImagination.put("url", "");
			Map<String,String> designerHouse = new LinkedHashMap<String,String>();
			designerHouse.put("title", "设计师之家");
			designerHouse.put("url", "");
			Map<String,String> latestInformation = new LinkedHashMap<String,String>();
			latestInformation.put("title", "最新资讯");
			latestInformation.put("url", "");
			productCategoryMap.put("1",bathProduct);
			productCategoryMap.put("2",kitchenProduct);
			productCategoryMap.put("3",homeImagination);
			productCategoryMap.put("4",designerHouse);
			productCategoryMap.put("5",latestInformation);
			
			context.put("productCategoryMap", productCategoryMap);
			
			
			Map<Object,Object>  productcategory=new LinkedHashMap<Object,Object>();
			// Map<Object,Object>  breadcrumb_name=new LinkedHashMap<Object,Object>();
			// breadcrumb_name.put("1", "spring");
			productcategory.put("index_name", "首页1");
			// productcategory.put("index_url", "http://www.163.com");
			productcategory.put("category_name", "卫浴产品1");
			productcategory.put("category_url", "kkkkkkkkkk");
			productcategory.put("subcategory_name", null);
			productcategory.put("subcategory_url", "dddkkkkkkkkkk");
			productcategory.put("breadcrumb_name", "玫瑰金套间love");
			// productcategory.put("breadcrumb_url", "kkkkkkkkkk");
			
		
			// bannerunits.put("2",kitchenFaucetSubject2);	
			// bannerunits.put("3",kitchenFaucetSubject3);
			
			
			
			// kitchenCategoryMap1.put("1",kitchenFaucet);
			context.put("productcategory",productcategory);
			
			
				Map<Object,Object>  heroAreas=new LinkedHashMap<Object,Object>();
			Map<Object,Object>  breadcrumb_name=new LinkedHashMap<Object,Object>();
			// breadcrumb_name.put("1", "spring");
			// productcategory.put("index_name", "首页1");
			// productcategory.put("index_url", "http://www.163.com");
			breadcrumb_name.put("image_url", "images/banner2.jpg");
			// productcategory.put("category_url", "kkkkkkkkkk");
			// productcategory.put("subcategory_name", null);
			// productcategory.put("subcategory_url", "dddkkkkkkkkkk");
			// productcategory.put("breadcrumb_name", "玫瑰金套间love");
			// productcategory.put("breadcrumb_url", "kkkkkkkkkk");
			
		
			// bannerunits.put("2",kitchenFaucetSubject2);	
			// bannerunits.put("3",kitchenFaucetSubject3);
			
			
			
			heroAreas.put("1",breadcrumb_name);
			context.put("heroAreas",heroAreas);
			
			
			//seo image
			Map<String,Map<String,String >> heroAreasMap = new LinkedHashMap<String,Map<String,String>>();
			Map<String,String> bathProduct1 = new LinkedHashMap<String,String>();
			bathProduct1.put("image_src", "images/banner1.jpg");
			bathProduct1.put("image_overlay_text", "bathroom.html");
			bathProduct1.put("image_alt", "bathroom.html");
			bathProduct1.put("target_url", "bathroom.html");
			bathProduct1.put("target_operation", "bathroom.html");
			Map<String,String> kitchenProduct1 = new LinkedHashMap<String,String>();
			kitchenProduct1.put("image_src", "images/banner2.jpg");
			kitchenProduct1.put("image_overlay_text", "kitchen.html");
			kitchenProduct1.put("image_alt", "kitchen.html");
			kitchenProduct1.put("target_url", "kitchen.html");
			kitchenProduct1.put("target_operation", "kitchen.html");
			Map<String,String> homeImagination1 = new LinkedHashMap<String,String>();
			homeImagination1.put("image_src", "images/banner3.jpg");
			homeImagination1.put("image_overlay_text", "");
			homeImagination1.put("image_alt", "");
			homeImagination1.put("target_url", "");
			homeImagination1.put("target_operation", "");
			Map<String,String> designerHouse1 = new LinkedHashMap<String,String>();
			designerHouse1.put("image_src", "images/banner4.jpg");
			designerHouse1.put("image_overlay_text", "");
			designerHouse1.put("image_alt", "");
			designerHouse1.put("target_url", "");
			
			
			
			
			designerHouse1.put("target_operation", "");
			Map<String,String> latestInformation1 = new LinkedHashMap<String,String>();
			latestInformation1.put("image_src", "images/banner5.jpg");
			latestInformation1.put("image_overlay_text", "");
			latestInformation1.put("image_alt", "");
			latestInformation1.put("target_url", "");
			latestInformation1.put("target_operation", "");
			heroAreasMap.put("1",bathProduct1);
			heroAreasMap.put("2",kitchenProduct1);
			heroAreasMap.put("3",homeImagination1);
			heroAreasMap.put("4",designerHouse1);
			heroAreasMap.put("5",latestInformation1);
			
			context.put("heroareas", heroAreasMap);
			
			
			Map<Object,Object>  hotspot=new LinkedHashMap<Object,Object>();
			hotspot.put("content","<img src='images/detail_bk1.jpg'>");
			context.put("hotspot",hotspot);
			//seo image end
			
			// Map<Object,Object> categoryMap = new LinkedHashMap<Object,Object>();
			
			//厨房龙头
			// Map<Object,Object> kitchenCategoryMap1 = new LinkedHashMap<Object,Object>();
			// Map<object,object> m1 = new HashMap<object,object>();
			// kitchenCategoryMap1.put("category_name","厨房龙头");
			// kitchenCategoryMap1.put("product_list_url","http://www.baidu.com");
			
			
			
			Map<Object,Object>  category=new LinkedHashMap<Object,Object>();
			
			
			Map<Object,Object>  kitchenFaucetSubject1=new LinkedHashMap<Object,Object>();
			kitchenFaucetSubject1.put("list_image_url", "images/details1.jpg");
			kitchenFaucetSubject1.put("collection", "Simplice® 星珀丽11");
			kitchenFaucetSubject1.put("name", "单控下抽拉厨房龙头1");
			kitchenFaucetSubject1.put("image_overlay_text", "my heart in this area");
			kitchenFaucetSubject1.put("target_url", "http://www.qq.com");
			kitchenFaucetSubject1.put("image_alt", "http://www.qq.com");
			kitchenFaucetSubject1.put("sku_code", "http://www.qq.com");
			
			Map<Object,Object>  kitchenFaucetSubject2=new LinkedHashMap<Object,Object>();
			kitchenFaucetSubject2.put("list_image_url", "images/details2.jpg");
			kitchenFaucetSubject2.put("collection", "Simplice® 星珀丽22");
			kitchenFaucetSubject2.put("name", "单控下抽拉厨房龙头2");
			kitchenFaucetSubject2.put("image_overlay_text", "East or west,home is the best");
			kitchenFaucetSubject2.put("target_url", "httP://www.renren.com");
			kitchenFaucetSubject2.put("image_alt", "K-3614T-0");
			kitchenFaucetSubject2.put("sku_code", "K-3614T-0");
			
			Map<Object,Object>  kitchenFaucetSubject3=new LinkedHashMap<Object,Object>();
			kitchenFaucetSubject3.put("list_image_url", "images/details4.jpg");
			// kitchenFaucetSubject3.put("collection", "Simplice® 星珀丽33");
			kitchenFaucetSubject3.put("name", "单控下抽拉厨房龙头3");
//			kitchenFaucetSubject3.put("name", null);
			kitchenFaucetSubject3.put("image_overlay_text", "Cling to your sweet,the Sun shines very day ");
			kitchenFaucetSubject3.put("target_url", "http://www.baidu.com");
//			kitchenFaucetSubject3.put("image_alt", "K-649");
			kitchenFaucetSubject3.put("image_alt", "K-649");
			kitchenFaucetSubject3.put("sku_code", "K-649");
			
			category.put("1",kitchenFaucetSubject1);
			category.put("2",kitchenFaucetSubject2);	
			category.put("3",kitchenFaucetSubject3);
			// kitchenCategoryMap1.put("1",kitchenFaucet);
			context.put("category",category);
			
			
			//卫浴新品
			
			
			
			
			VelocityEngine velocityEngine = new VelocityEngine();
			velocityEngine.init(properties);
			
			Template t = velocityEngine.getTemplate("temp.vm","UTF-8");
			
			String dir = "C:/workspace/temp.html";
			
			PrintWriter pw = new PrintWriter(dir);
			t.merge(context, pw);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 在程序退出时删除临时文件
		}
		
	}

	public static void createFile(File f,String str) throws Exception{
		f.createNewFile();
		// 向临时文件中写入内容
		BufferedWriter out = new BufferedWriter(new FileWriter(f));
		out.write(str);
		out.close();
	}
	
	public static void generateHtml(String basePath, String vmName, String dir,
			VelocityContext context) throws Exception {
		PrintWriter pw = null;
		try {

			VelocityEngine velocityEngine = new VelocityEngine();

			Properties properties = new Properties();
			// 也可以在这里指定绝对路径。当指定相对路径时， 在不同的环境下是有区别的。
			// 比如把程序部署到tomcat以后，相对路径相对到哪里是个很恶心的事情。
			// String basePath = "";
			// 可设置绝对路径
			// String basePath = "E:/maven/test/velocity/src/main/resources";
			// String path =
			properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, basePath);

			velocityEngine.init(properties);
			// 初始化vm模板
			Template template = velocityEngine.getTemplate(vmName, "UTF-8");
			// 初始化上下文
			// VelocityContext context = new VelocityContext();
			// 添加数据到上下文中
			// context.put("title", "我的第一个velocity页面");
			// 生成html页面
			pw = new PrintWriter(dir);
			template.merge(context, pw);
			// 关闭流
			pw.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			logger.debug("file not found:"+basePath+" vmName:"+vmName+ " dir:"+dir);
			throw new FileNotFoundException(e.getMessage());
		} catch (ResourceNotFoundException e) {
			//e.printStackTrace();
			logger.debug("resource not found:"+basePath+" vmName:"+vmName+ " dir:"+dir);
			throw new ResourceNotFoundException(e.getMessage());
		} catch (ParseErrorException e) {
			//e.printStackTrace();
			logger.debug("parse error:"+basePath+" vmName:"+vmName+ " dir:"+dir);
			throw new ParseErrorException(e.getMessage());
		} catch (Exception e) {
			//e.printStackTrace();
			logger.debug("unknown exception:"+basePath+" vmName:"+vmName+ " dir:"+dir);
			throw new Exception(e.getMessage());
		} finally {

			if (pw != null) {
				pw.close();
			}

		}
	}
}
