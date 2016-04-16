package com.kohler.util;

import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

public class GenerateHtmlTest {
	
	//private GenerateHtml generateHtml = new GenerateHtml();

	@Test
	public void testMain() {
		Properties properties = new Properties();
		properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "C:/workspace");
		VelocityContext context = new VelocityContext();
		context.put("seo_title", "科勒中国 KOHLER");
		context.put("seo_keywords", "高端大气美观科勒中国 KOHLER");
		context.put("seo_description", "领导者科勒中国 KOHLER");
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
		
		//seo image
		Map<String,Map<String,String >> heroAreasMap = new LinkedHashMap<String,Map<String,String>>();
		Map<String,String> bathProduct1 = new LinkedHashMap<String,String>();
		bathProduct1.put("image_src", "images/banner1.jpg");
		bathProduct1.put("image_overlay_text", "bathroom.html");
		bathProduct1.put("image_alt", "bathroom.html");
		bathProduct1.put("image_target_url", "bathroom.html");
		bathProduct1.put("image_target_operation", "bathroom.html");
		Map<String,String> kitchenProduct1 = new LinkedHashMap<String,String>();
		kitchenProduct1.put("image_src", "images/banner2.jpg");
		kitchenProduct1.put("image_overlay_text", "kitchen.html");
		kitchenProduct1.put("image_alt", "kitchen.html");
		kitchenProduct1.put("image_target_url", "kitchen.html");
		kitchenProduct1.put("image_target_operation", "kitchen.html");
		Map<String,String> homeImagination1 = new LinkedHashMap<String,String>();
		homeImagination1.put("image_src", "images/banner3.jpg");
		homeImagination1.put("image_overlay_text", "");
		homeImagination1.put("image_alt", "");
		homeImagination1.put("image_target_url", "");
		homeImagination1.put("image_target_operation", "");
		Map<String,String> designerHouse1 = new LinkedHashMap<String,String>();
		designerHouse1.put("image_src", "images/banner4.jpg");
		designerHouse1.put("image_overlay_text", "");
		designerHouse1.put("image_alt", "");
		designerHouse1.put("image_target_url", "");
		designerHouse1.put("image_target_operation", "");
		Map<String,String> latestInformation1 = new LinkedHashMap<String,String>();
		latestInformation1.put("image_src", "images/banner5.jpg");
		latestInformation1.put("image_overlay_text", "");
		latestInformation1.put("image_alt", "");
		latestInformation1.put("image_target_url", "");
		latestInformation1.put("image_target_operation", "");
		heroAreasMap.put("1",bathProduct1);
		heroAreasMap.put("2",kitchenProduct1);
		heroAreasMap.put("3",homeImagination1);
		heroAreasMap.put("4",designerHouse1);
		heroAreasMap.put("5",latestInformation1);
		
		context.put("heroAreasMap", heroAreasMap);
		//seo image end
		
		Map<Object,Object> categoryMap = new LinkedHashMap<Object,Object>();
		
		//厨房龙头
		Map<Object,Object> kitchenCategoryMap1 = new LinkedHashMap<Object,Object>();
		// Map<object,object> m1 = new HashMap<object,object>();
		kitchenCategoryMap1.put("category_name","厨房龙头");
		kitchenCategoryMap1.put("product_list_url","http://www.baidu.com");
		
		
		
		Map<Object,Object>  kitchenFaucet=new LinkedHashMap<Object,Object>();
		
		
		Map<Object,Object>  kitchenFaucetSubject1=new LinkedHashMap<Object,Object>();
		kitchenFaucetSubject1.put("product_single_src", "images/kitchen_item1.jpg");
		kitchenFaucetSubject1.put("product_collection", "Simplice® 星珀丽");
		kitchenFaucetSubject1.put("product_name", "单控下抽拉厨房龙头");
		kitchenFaucetSubject1.put("product_code", "K-597T-4");
		kitchenFaucetSubject1.put("image_target_url", "http://www.qq.com");
		kitchenFaucetSubject1.put("image_alt", "http://www.qq.com");
		
		Map<Object,Object>  kitchenFaucetSubject2=new LinkedHashMap<Object,Object>();
		kitchenFaucetSubject2.put("product_single_src", "images/kitchen_item2.jpg");
		kitchenFaucetSubject2.put("product_collection", "Simplice® 星珀丽");
		kitchenFaucetSubject2.put("product_name", "单控下抽拉厨房龙头");
		kitchenFaucetSubject2.put("product_code", "K-3614T-0");
		kitchenFaucetSubject2.put("image_target_url", "K-3614T-0");
		kitchenFaucetSubject2.put("image_alt", "K-3614T-0");
		
		Map<Object,Object>  kitchenFaucetSubject3=new LinkedHashMap<Object,Object>();
		kitchenFaucetSubject3.put("product_single_src", "images/kitchen_item3.jpg");
		kitchenFaucetSubject3.put("product_collection", "Simplice® 星珀丽");
		kitchenFaucetSubject3.put("product_name", "单控下抽拉厨房龙头");
		kitchenFaucetSubject3.put("product_code", "K-649");
		kitchenFaucetSubject3.put("image_target_url", "K-649");
		kitchenFaucetSubject3.put("image_alt", "K-649");
		
		kitchenFaucet.put("1",kitchenFaucetSubject1);
		kitchenFaucet.put("2",kitchenFaucetSubject2);	
		kitchenFaucet.put("3",kitchenFaucetSubject3);
		kitchenCategoryMap1.put("1",kitchenFaucet);
		categoryMap.put("1",kitchenCategoryMap1);
		
		
		//厨盆
		Map<Object,Object> kitchenCategoryMap2 = new LinkedHashMap<Object,Object>();
		// Map<object,object> m1 = new HashMap<object,object>();
		kitchenCategoryMap2.put("category_name","厨盆");
		kitchenCategoryMap2.put("product_list_url","http://www.baidu.com");
		
			Map<Object,Object>  kitchenFaucet1=new LinkedHashMap<Object,Object>();
		
		
		Map<Object,Object>  kitchenFaucetSubject11=new LinkedHashMap<Object,Object>();
		kitchenFaucetSubject11.put("product_single_src", "images/kitchen_item4.jpg");
		kitchenFaucetSubject11.put("product_collection", "FORTE®芙蒂");
		kitchenFaucetSubject11.put("product_name", "灵融石大小槽台上 / 台下厨盆");
		kitchenFaucetSubject11.put("product_code", "K-45611T/45612T-1F-STA");
		kitchenFaucetSubject11.put("image_target_url", "K-45611T/45612T-1F-STA");
		kitchenFaucetSubject11.put("image_alt", "K-45611T/45612T-1F-STA");
		
		Map<Object,Object>  kitchenFaucetSubject21=new LinkedHashMap<Object,Object>();
		kitchenFaucetSubject21.put("product_single_src", "images/kitchen_item5.jpg");
		kitchenFaucetSubject21.put("product_collection", "GEOG™奇奥");
		kitchenFaucetSubject21.put("product_name", "单槽台上 / 台下双用厨盆");
		kitchenFaucetSubject21.put("product_code", "K-3746T-1F-NA");
		kitchenFaucetSubject21.put("image_target_url", "K-3746T-1F-NA");
		kitchenFaucetSubject21.put("image_alt", "K-3746T-1F-NA");
		
		Map<Object,Object>  kitchenFaucetSubject31=new LinkedHashMap<Object,Object>();
		kitchenFaucetSubject31.put("product_single_src", "images/kitchen_item6.jpg");
		kitchenFaucetSubject31.put("product_collection", "GEOG™奇奥");
		kitchenFaucetSubject31.put("product_name", "大小槽台上 / 台下双用厨盆");
		kitchenFaucetSubject31.put("product_code", "K-3748T-1F-NA");
		kitchenFaucetSubject31.put("image_target_url", "K-3748T-1F-NA");
		kitchenFaucetSubject31.put("image_alt", "K-3748T-1F-NA");
		
		kitchenFaucet1.put("1",kitchenFaucetSubject11);
		kitchenFaucet1.put("2",kitchenFaucetSubject21);
		kitchenFaucet1.put("3",kitchenFaucetSubject31);
		kitchenCategoryMap2.put("2",kitchenFaucet1);
		categoryMap.put("2",kitchenCategoryMap2);
		
		
		//净水器
		Map<Object,Object> kitchenCategoryMap3 = new LinkedHashMap<Object,Object>();
		// Map<object,object> m1 = new HashMap<object,object>();
		kitchenCategoryMap3.put("category_name","净水器");
		kitchenCategoryMap3.put("product_list_url","http://www.baidu.com");
		
			Map<Object,Object>  kitchenFaucet11=new LinkedHashMap<Object,Object>();
		
		
		Map<Object,Object>  kitchenFaucetSubject111=new LinkedHashMap<Object,Object>();
		kitchenFaucetSubject111.put("product_single_src", "images/kitchen_item7.jpg");
		kitchenFaucetSubject111.put("product_collection", "CUFF可芙 厨房净饮机");
		kitchenFaucetSubject111.put("product_name", "3000- 母婴特护款");
		kitchenFaucetSubject111.put("product_code", "K-5339T-KT100");
		kitchenFaucetSubject111.put("image_target_url", "K-5339T-KT100");
		kitchenFaucetSubject111.put("image_alt", "K-5339T-KT100");
		
		Map<Object,Object>  kitchenFaucetSubject211=new LinkedHashMap<Object,Object>();
		kitchenFaucetSubject211.put("product_single_src", "images/kitchen_item8.jpg");
		kitchenFaucetSubject211.put("product_collection", "CUFF可芙 厨房净饮机");
		kitchenFaucetSubject211.put("product_name", "3500- 小巧家庭款");
		kitchenFaucetSubject211.put("product_code", "K-5339T-KT200");
		kitchenFaucetSubject211.put("image_target_url", "K-5339T-KT200");
		kitchenFaucetSubject211.put("image_alt", "K-5339T-KT200");
		
		Map<Object,Object>  kitchenFaucetSubject311=new LinkedHashMap<Object,Object>();
		kitchenFaucetSubject311.put("product_single_src", "images/kitchen_item9.jpg");
		kitchenFaucetSubject311.put("product_collection", "CUFF可芙 厨房净饮机");
		kitchenFaucetSubject311.put("product_name", "7500- 餐厨多效款");
		kitchenFaucetSubject311.put("kitchenFaucetType", "K-5339T-KT400");
		kitchenFaucetSubject311.put("image_target_url", "K-5339T-KT400");
		kitchenFaucetSubject311.put("image_alt", "K-5339T-KT400");
		
		kitchenFaucet11.put("1",kitchenFaucetSubject111);
		kitchenFaucet11.put("2",kitchenFaucetSubject211);
		kitchenFaucet11.put("3",kitchenFaucetSubject311);
		kitchenCategoryMap3.put("3",kitchenFaucet11);
		categoryMap.put("3",kitchenCategoryMap3);
		
		//厨房新品
		Map<Object,Object> kitchenCategoryMap4 = new LinkedHashMap<Object,Object>();
		// Map<object,object> m1 = new HashMap<object,object>();
		kitchenCategoryMap4.put("category_name","厨房新品");
		kitchenCategoryMap4.put("product_list_url","http://www.baidu.com");
		
			Map<Object,Object>  kitchenFaucet111=new LinkedHashMap<Object,Object>();
		
		
		Map<Object,Object>  kitchenFaucetSubject1111=new LinkedHashMap<Object,Object>();
		kitchenFaucetSubject1111.put("product_single_src", "images/kitchen_item10.jpg");
		kitchenFaucetSubject1111.put("product_collection", "Linc™ 霖珂 Smart ");
		kitchenFaucetSubject1111.put("product_name", "一体槽(大小槽台上双用厨盆)");
		kitchenFaucetSubject1111.put("product_code", "k_3710t");
		kitchenFaucetSubject1111.put("image_target_url", "k_3710t");
		kitchenFaucetSubject1111.put("image_alt", "k_3710t");
		
		Map<Object,Object>  kitchenFaucetSubject2111=new LinkedHashMap<Object,Object>();
		kitchenFaucetSubject2111.put("product_single_src", "images/kitchen_item11.jpg");
		kitchenFaucetSubject2111.put("product_collection", "Prologue® 普罗单槽带沥水");
		kitchenFaucetSubject2111.put("product_name", "板台上台下双用厨盆");
		kitchenFaucetSubject2111.put("product_code", "k_3884t");
		kitchenFaucetSubject2111.put("image_target_url", "k_3884t");
		kitchenFaucetSubject2111.put("image_alt", "k_3884t");
		
		Map<Object,Object>  kitchenFaucetSubject3111=new LinkedHashMap<Object,Object>();
		kitchenFaucetSubject3111.put("product_single_src", "images/kitchen_item12.jpg");
		kitchenFaucetSubject3111.put("product_collection", "Prologue® 普罗单槽带沥水");
		kitchenFaucetSubject3111.put("product_name", "板台上台下双用厨盆");
		kitchenFaucetSubject3111.put("product_code", "K-4766T");
		kitchenFaucetSubject3111.put("image_target_url", "K-4766T");
		kitchenFaucetSubject3111.put("image_alt", "K-4766T");
		
		kitchenFaucet111.put("1",kitchenFaucetSubject1111);
		kitchenFaucet111.put("2",kitchenFaucetSubject2111);
		kitchenFaucet111.put("3",kitchenFaucetSubject3111);
		kitchenCategoryMap4.put("4",kitchenFaucet111);
		categoryMap.put("4",kitchenCategoryMap4);
		
		
		context.put("categoryMap", categoryMap);
		
		
		
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.init(properties);
		
		Template t = velocityEngine.getTemplate("temp.vm","UTF-8");
		
		String dir = "C:/workspace/temp.html";
		
		PrintWriter pw;
		try {
			pw = new PrintWriter(dir);
			t.merge(context, pw);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}


}
