package com.kohler.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kohler.service.UserfolderService;
import com.kohler.util.PropertiesUtils;
import com.kohler.constants.CommonConstants;
import com.kohler.dao.utils.Pager;
import com.kohler.entity.MasterDataCodeConstant;

/**
 * 
 * 处理用户文件夹模块相关前端请求
 *
 * @author Administrator
 * @Date 2014年10月23日
 */
@Controller
@RequestMapping(value = "/userFolder")
public class UserfolderController  extends BasicController {
    
    @Autowired
    private UserfolderService UserfolderService;
    
    /**
     * 用户文件夹列表
     * @param model
     * @param pager
     * @param request
     * @return
     * @author fujiajun
     * Date 2014年10月28日
     * @version userFolder/userFolderList.action
     */
    @RequestMapping(value = "/userFolderList")
    public String userFolderList(Model model, Pager<Map<String, Object>> pager,
            HttpServletRequest request,Integer type,Integer UserType) {
        if (logger.isInfoEnabled()) {
            logger.info("UserfolderController.userFolderList begin");
        }
        String uri = request.getRequestURI() + "?";
        pager = UserfolderService.getUserfolderList(pager, type, UserType);
        pager.setUrl(uri);
        List<Map<String,Object>> listdata=UserfolderService.getselectlist(MasterDataCodeConstant.TYPE_ACCOUNT_TYPE);
        List<Map<String,Object>> listdata1=UserfolderService.getselectlist(MasterDataCodeConstant.TYPE_ACCOUNT_FOLDER_TYPE);
        model.addAttribute("pager", pager);
        model.addAttribute("listdata", listdata);
        model.addAttribute("listdata1", listdata1);
        if(type != null){
            model.addAttribute("Type", type);
            uri +="type="+type;
        }
        if(UserType != null){
            model.addAttribute("UserType", UserType);
            uri +="UserType"+UserType;
        }
        if (logger.isInfoEnabled()) {
            logger.info("UserfolderController.userFolderList end");
        }
        return "account/userFoldersList";
    }
    /**
     * 删除用户文件夹
     * @param ACCOUNT_FOLDER_DETAIL_ID
     * @return
     * @author Administrator
     * Date 2014年10月23日
     * @version
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(Integer[] ACCOUNT_FOLDER_DETAIL_ID) {
        if (logger.isInfoEnabled()) {
            logger.info("UserfolderController.delete begin");
        }
        Map<String, Object> result = new HashMap<String, Object>();
        UserfolderService.deleteUserfolder(ACCOUNT_FOLDER_DETAIL_ID);
        String msg = PropertiesUtils.findPropertiesKey(CommonConstants.INFO_COMMON_DELETE_SUCCESS);
        result.put("msg", msg);
        result.put("success", true);
        if (logger.isInfoEnabled()) {
            logger.info("UserfolderController.delete end");
        }
        return result;
    }
    

}
