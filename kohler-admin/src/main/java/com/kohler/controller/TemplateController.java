/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kohler.dao.FolderDao;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.TemplateEntity;
import com.kohler.service.TemplateService;
@Controller
@RequestMapping(value = "/template")
public class TemplateController {
	@Autowired
	public TemplateService templateService;
	@Value("#{settings['uploadImgPath']}")
    private String uploadImgPath;
	
	@Value("#{settings['file.velocity.template.dir']}")
    private String velocityDir;
	@Value("#{settings['file.website.dir']}")
    private String websiteDir;
	@Autowired
	private FolderDao folderDao;
	/**
	 * 初始化页面
     * @param request
     * @param pager
     * @param template
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/templateList")
	public String init(HttpServletRequest request,
			Pager<Map<String, Object>> pager,TemplateEntity template) throws IOException, JAXBException  {
		String uri = request.getRequestURI() + "?";
		if(template.getTemplateName() != null){
			uri += "templateName=" + template.getTemplateName()+"&";
		}
		if(template.getTemplateType() != null){
			uri += "templateType=" + template.getTemplateType()+"&";
		}
		pager.setUrl(uri);
		List<Object> typeLists = new ArrayList<Object>();
		typeLists.add(1);
		List<Map<String, Object>> typeList = templateService.getAllType(typeLists);
		request.setAttribute("typeList", typeList);
		Pager<Map<String, Object>> list = templateService.getAllTemplate(pager, template);
		request.setAttribute("list", list);
		if(template != null){
			request.setAttribute("searchTem", template);
		}
		return "admin/templateList";
	}
	/**
	 * 跳转new页面
	 * @param request
	 * @param template
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	@RequestMapping(value = "/create")
	public String createTemplatePage(HttpServletRequest request,TemplateEntity template) throws IOException, JAXBException  {
		List<Object> typeLists = new ArrayList<Object>();
		typeLists.add(1);
		//取所有类型
        List<Map<String, Object>> typeList = templateService.getAllType(typeLists);
		//取所有目录
		List<Map<String, Object>> folderList = templateService.getAllFolder(typeLists);
		//新增一条空数据
		template = templateService.createTemplate(template);
		request.setAttribute("template", template);
		request.setAttribute("typeList", typeList);
		request.setAttribute("folderList", folderList);
		return "admin/templatesNew";
	}
	/**
	 * 新增
	 * @param request
	 * @param template
	 * @return int
	 * @throws IOException
	 * @throws JAXBException
	 */
	@RequestMapping(value = "/createSave")
	@ResponseBody
	public String createTemplate(HttpServletRequest request,TemplateEntity template,HttpServletResponse response) throws IOException, JAXBException  {
	    List<Object> templateList = new ArrayList<Object>();
	    templateList.add(template.getTemplateName());
	    List<Map<String, Object>> selecforname = templateService.selecForName(templateList);
	    if(selecforname.size() > 0){
	        return "<script>alert('The file name already exists!');parent.layer.close(parent.layer.getFrameIndex(window.name));</script>";
	    }
	    template.setIsEnable(true);
		String uploadImage = uploadImage(request, "templateImagev");
		template.setTemplateImage(uploadImage);
		int num = templateService.updateTemplte(template);
		//创建文件 k
		String str = template.getTemplateContent();//"需要写入的字";
        //String fileName = "D:\\a\\a.xml";
        OutputStream output = null;// 输出字节流
        OutputStreamWriter outputWrite = null;// 输出字符流
        PrintWriter print = null;// 输出缓冲区
        
        String velocityTemplateDir = websiteDir + velocityDir;
        output = new FileOutputStream(velocityTemplateDir+"/"+template.getTemplateId()+template.getTemplateName());
        outputWrite = new OutputStreamWriter(output);
        print = new PrintWriter(outputWrite);
        print.print(str);
        print.flush();// 一定不要忘记此句，否则数据有可能不能被写入文件
        output.close();
		//j
		if(num>0){
			return "<script>alert('create sucess!');parent.location.reload();"
			        + "parent.layer.close(parent.layer.getFrameIndex(window.name));</script>";
		}else{
			response.getWriter().write("create sucess");
			return "<script>alert('Successful modification!');parent.location.reload();"
			        + "parent.layer.close(parent.layer.getFrameIndex(window.name));</script>";
		}
	}
	
	/**
	 * 删除
	 * @param data
	 * @return
	 */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> deleteSectionOrPage(String[] data) {
        Map<String, Object> result = new HashMap<String, Object>();
        TemplateEntity tempate = new TemplateEntity();
        for (int i = 0; i < data.length; i++) {
        	tempate.setTemplateId(Integer.parseInt(data[i]));
        	templateService.deleteTemplte(tempate);
		}
        result.put("msg","Successfully deleted！");
        return result;
    }
    /**
     * 查询单条记录
     * @param request
     * @param templateId
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    @RequestMapping(value = "/edit")
	public String selectOne(HttpServletRequest request,int templateId,String isEdit) throws IOException, JAXBException  {
        if(isEdit.equals("1")){
            List<Object> typeLists = new ArrayList<Object>();
            typeLists.add(1);
            List<Object> templateList = new ArrayList<Object>();
            templateList.add(templateId);
            List<Map<String, Object>> temList = templateService.selectOne(templateList);
            request.setAttribute("tem", temList);
            request.setAttribute("isEdit", 1);
            return "admin/templateEdit";
        }
		List<Object> typeLists = new ArrayList<Object>();
		typeLists.add(1);
		List<Object> templateList = new ArrayList<Object>();
		templateList.add(templateId);
		//取所有类型
        List<Map<String, Object>> typeList = templateService.getAllType(typeLists);
		//取所有目录
		List<Map<String, Object>> folderList = templateService.getAllFolder(typeLists);
		List<Map<String, Object>> temList = templateService.selectOne(templateList);
		request.setAttribute("tem", temList);
		request.setAttribute("typeList", typeList);
		request.setAttribute("folderList", folderList);
		return "admin/templateEdit";
	}
    /**
     * 修改
     * @param request
     * @param template
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    @RequestMapping(value = "/editSave")
	@ResponseBody
	public String updateTemplate(HttpServletRequest request,TemplateEntity template,
	        HttpServletResponse response) throws IOException, JAXBException  {
		template.setIsEnable(true);
		String uploadImage = uploadImage(request, "templateImagev");
		if(uploadImage != null && !uploadImage.equals(""))
			template.setTemplateImage(uploadImage);
		int num = templateService.updateTemplte(template);
		if(num>0){
            return "<script>alert('create sucess!');parent.location.reload();"
                    + "parent.layer.close(parent.layer.getFrameIndex(window.name));</script>";
        }else if(num == 0){
            return "<script>alert('templateName Already exists!');parent.layer.close(parent.layer.getFrameIndex(window.name));</script>";
        }else{
            response.getWriter().write("create sucess");
            return "<script>alert('Successful modification!');parent.location.reload();"
                    + "parent.layer.close(parent.layer.getFrameIndex(window.name));</script>";
        }
		
	}
    /**
     * 只读查询
     * @param request
     * @param templateId
     * @return
     * @throws IOException
     * @throws JAXBException
     */
//    @RequestMapping(value = "/searchOne")
//	public String searchOne(HttpServletRequest request,int templateId) throws IOException, JAXBException  {
//		
//	}
    private String uploadImage(HttpServletRequest request, String fileParameter){
        String imageDir = uploadImgPath+("/upload/");
        File filePath = new File(imageDir);
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        OutputStream output = null;
        InputStream input = null;
        String imageName = null;
        String imageUrl = null;
        // 转型为MultipartHttpRequest：
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得文件：

        MultipartFile file = multipartRequest.getFile(fileParameter);
        try {

            if (file != null && file.getSize() > 0) {
                input = file.getInputStream();
                String fileName = file.getOriginalFilename();
                long timeStamp = System.currentTimeMillis();
                imageName = timeStamp + "_" + fileName;
                imageUrl = imageDir + File.separator + imageName;
                output = new FileOutputStream(imageUrl);
                byte buffer[] = new byte[16*1024];
                while ((input.read(buffer)) != -1) {
                    output.write(buffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imageName;
    }
    @RequestMapping(value = "/view")
	public String preView(HttpServletRequest request,Integer templateId) throws IOException, JAXBException  {
    	List<Object> typeLists = new ArrayList<Object>();
		typeLists.add(templateId);
		List<Map<String, Object>> tem =  templateService.selectOne(typeLists);
    	request.setAttribute("imgName", tem);
    	request.setAttribute("imgUrl", "/upload/");
    	return "admin/templateView";
	}
}











