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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kohler.dao.utils.Pager;
import com.kohler.entity.FileAssetEntity;
import com.kohler.entity.FolderEntity;
import com.kohler.pojo.FileAssetPojo;
import com.kohler.pojo.FolderPojo;
import com.kohler.service.FolerService;
import com.kohler.util.JSonUtil;

/**
 * 用于处理folder的相关的请求
 *
 * @author ys
 * @Date 2014年10月18日
 */
@Controller
@RequestMapping(value = "/folder")
public class FolderController {
	@Autowired
	private FolerService folderService;
	@Value("#{settings['uploadImgPath']}")
    private String uploadImgPath = "c:/images/";
	/**
	 * 初始化页面
	 * @param pager
	 * @param model
	 * @param request
	 * @param folder
	 * @param folderId
	 * @return
	 */
	@RequestMapping(value = "/folderList")
	public String folerList(Pager<Map<String, Object>> pager,Model model,HttpServletRequest request,FolderPojo folder,
				Integer folderId) {
		String uri = request.getRequestURI() + "?";
		if (folderId == null) {
			folderId = 5;
        }
		uri += "folderId=" + folderId + "&";
		folder.setFolderId(folderId);
		FolderEntity searchOne = folderService.searchOne(folder);
		request.setAttribute("pId", searchOne);
		//树填充
		List<Map<String, Object>> allFolder = folderService.getAllFolder(folder);
		model.addAttribute("all", JSonUtil.toJSonString(allFolder));
		model.addAttribute("folderId", folderId);
		pager.setUrl(uri);
		//table填充
		Pager<Map<String, Object>> folderList = folderService.getFolderList(pager, folder);
		request.setAttribute("folList", folderList);
		
		return "fileasset/folderList";
	}
	/**
	 * 删除
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	// 删除数据
	@RequestMapping(value = "/deleteFolder")
	@ResponseBody
	public Map<String, Object> deleteFolder(String[] data)
			throws IOException, JAXBException {
		Map<String, Object> result = new HashMap<String, Object>();
		FolderEntity folder = new FolderEntity();
		FileAssetEntity file = new FileAssetEntity();
		String[] query;
//		FolderEntity parfolder = new FolderEntity();
		for (int i = 0; i < data.length; i++) {
			query = data[i].split(":");
			if(query[1].equals("Video") || query[1].equals("Image") ||
			        query[1].equals("CAD") ||query[1].equals("PDF")){
				file.setFileAssetId(Integer.parseInt(query[0]));
				file.setIsEnable(false);
				folderService.deleteFile(file);
			}else{
				folder.setFolderId(Integer.parseInt(query[0]));
				folderService.deleteFolder(folder);
			}
//			folderService.deleteFolder(parfolder);
		}
		result.put("msg", "Successfully deleted!");
		return result;
	}
	/**
	 * 跳转新增页面
	 * @param request
	 * @param folderId
	 * @param folderName
	 * @return
	 */
	@RequestMapping(value = "/createFolder")
	public String createFolderPage(HttpServletRequest request,Integer folderId,String folderName) {
		request.setAttribute("id", folderId);
		request.setAttribute("name", folderName);
		List<Map<String, Object>> allTypeByMasterData = folderService.getAllTypeByMasterData();
		request.setAttribute("allType", allTypeByMasterData);
		return "fileasset/folderNew";
	}
	/**
	 * 新增功能folder
	 * @param request
	 * @param folder
	 * @return
	 */
	@RequestMapping(value = "/createFolderSave")
	@ResponseBody
	public Map<String, Object> createFolder(HttpServletRequest request,FolderPojo folder) {
		FolderEntity folderEntity = new FolderEntity();
		Map<String, Object> map = new HashMap<String, Object>();
		BeanUtils.copyProperties(folder,folderEntity);
		int count = folderService.createFolder(folderEntity);
		if (count > 0) {
			map.put("msg", "Create success!");
		} else {
			map.put("msg", "Creation failed!");
		}
		return map;
	}
	/**
	 * 根据id查询数据
	 * @param request
	 * @param folderId
	 * @return
	 */
	@RequestMapping(value = "/editFolder")
	public String editFolder(HttpServletRequest request,int folderId,String typeName,int isEdit) {
		if(typeName.equals("folder")){
			FolderEntity folder = new FolderEntity();
			folder.setFolderId(folderId);
			FolderEntity sercahOne = folderService.searchOne(folder);
			request.setAttribute("folder", sercahOne);
			List<Map<String, Object>> allTypeByMasterData = folderService.getAllTypeByMasterData();
			request.setAttribute("allType", allTypeByMasterData);
			if(isEdit == 1)
				request.setAttribute("isEdit", isEdit);
			return "fileasset/folderEdit";
		}
		if(!typeName.equals("folder")){
			FileAssetEntity file = new FileAssetEntity();
			file.setFileAssetId(folderId);
			List<Map<String, Object>> searchOneFile = folderService.searchOneFile(file);
			request.setAttribute("file",searchOneFile.get(0));
			if(isEdit == 1)
				request.setAttribute("isEdit", isEdit);
			return "fileasset/fileEdit";
		}
		return "";
	}
	/**
	 * 修改数据
	 * @param request
	 * @param folder
	 * @return
	 */
	@RequestMapping(value = "/editFolderSave")
	@ResponseBody
	public Map<String, Object> editFolderSave(HttpServletRequest request,FolderEntity folder) {
//		FolderEntity folderEntity = new FolderEntity();
		Map<String, Object> map = new HashMap<String, Object>();
//		BeanUtils.copyProperties(folder,folderEntity);
		int count = folderService.updateFolder(folder);
		if (count > 0) {
			map.put("msg", "Update success!");
		} else {
			map.put("msg", "Modify the failure!");
		}
		return map;
	}
	/**
	 * 修改数据
	 * @param request
	 * @param folder
	 * @return
	 */
	@RequestMapping(value = "/editFileSave")
	@ResponseBody
	public String createFileSave(MultipartFile fileImgs,HttpServletRequest request,FileAssetEntity file) {
			String uploadImage = uploadImage(fileImgs);
		if(uploadImage != null && !uploadImage.equals("")){
			file.setPhysicalFilename(uploadImage);
		}
		int count = folderService.updateFile(file);
		if(count > 0){
			return "<script>alert('create sucess!');parent.location.reload();parent.layer.close(parent.layer.getFrameIndex(window.name));</script>";
		}else{
			return "<script>alert('Creation failed');parent.location.reload();parent.layer.close(parent.layer.getFrameIndex(window.name));</script>";
		}
	}
	/**
	 * 查看数据
	 * @param request
	 * @param folderId
	 * @return
	 */
	@RequestMapping(value = "/searchOneFolder")
	public String searchOneFolder(HttpServletRequest request,int folderId) {
//		int folderId = (int) request.getAttribute("folderId"); searchOneFolder
		FolderEntity folder = new FolderEntity();
		folder.setFolderId(folderId);
		FolderEntity sercahOne = folderService.searchOne(folder);
		request.setAttribute("folder", sercahOne);
		List<Map<String, Object>> allTypeByMasterData = folderService.getAllTypeByMasterData();
		request.setAttribute("allType", allTypeByMasterData);
		request.setAttribute("isEdit", 1);
		return "fileasset/folderEdit";
	}
	/**
	 * 跳转新增页面
	 * @param request
	 * @param folderId
	 * @param folderName
	 * @return
	 */
	@RequestMapping(value = "/createFile")
	public String createFile(HttpServletRequest request,Integer folderId,String folderName) {
		request.setAttribute("id", folderId);
		request.setAttribute("name", folderName);
		List<Map<String, Object>> allTypeByMasterData = folderService.getAllTypeByMasterData();
		request.setAttribute("allType", allTypeByMasterData);
		return "fileasset/fileNew";
	}
	/**
	 * 新增功能
	 * @param request
	 * @param folder
	 * @return
	 */
	@RequestMapping(value = "/createFileSave", method=RequestMethod.POST)
	@ResponseBody
	public String createSaveFile(MultipartFile fileImgs, HttpServletRequest request,FileAssetPojo file) {
	    FolderEntity folder = new FolderEntity();
	    folder.setFolderId(file.getFolderId());
	    folder = folderService.searchOne(folder);
	    file.setFileType(folder.getFileType());
		String uploadImage = uploadImage(fileImgs);
		file.setPhysicalFilename(uploadImage);
		FileAssetEntity fileEntity = new FileAssetEntity();
		BeanUtils.copyProperties(file,fileEntity);
		int count = folderService.createFile(fileEntity);
		if(count > 0){
			return "<script>alert('create sucess!');parent.location.reload();parent.layer.close(parent.layer.getFrameIndex(window.name));</script>";
		}else{
			return "<script>alert('Creation failed');parent.location.reload();parent.layer.close(parent.layer.getFrameIndex(window.name));</script>";
		}
	}
	private String uploadImage(MultipartFile fileImgs){
        String imageDir = uploadImgPath ;
        File filePath = new File(imageDir);
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        OutputStream output = null;
        InputStream input = null;
        String imageName = null;
        String imageUrl = null;
        // 转型为MultipartHttpRequest：
        //MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获得文件：

        MultipartFile file = fileImgs;
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
}
