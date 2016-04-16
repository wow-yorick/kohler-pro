package com.kohler.tag;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kohler.entity.CategoryAccessoryEntity;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.ProductEntity;
import com.kohler.entity.SKUAccessoryEntity;
import com.kohler.service.CategoryAccessoryService;
/**
 * 
 * ProductAccessoryTag Tag
 *
 * @author whh
 * @Date 2014年11月24日
 */
public class ProductAccessoryTag extends TagSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 1051914200559391955L;
    
    private String skuMetadataId;  //skuMetadataId from sku
    
    private String lang;     //lang from sku
    
    private String categoryMetadataId;  //categoryMetadataId from sku
    
    
    /**
     * 
     * @return categoryMetadataId
     * @version
     */
    public String getCategoryMetadataId() {
        return categoryMetadataId;
    }
    
    /**
     * 
     * @param categoryMetadataId categoryMetadataId to set
     * @author whh
     * Date 2014年11月14日
     * @version
     */
    public void setCategoryMetadataId(String categoryMetadataId) {
        this.categoryMetadataId = categoryMetadataId;
    }

    /**
     * 
     * @return skuMetadataId
     * @version
     */
    public String getSkuMetadataId() {
        return skuMetadataId;
    }

    /**
     * 
     * @param skuMetadataId to set
     * @version
     */
    public void setSkuMetadataId(String skuMetadataId) {
        this.skuMetadataId = skuMetadataId;
    }

    /**
     * 
     * @return lang
     * @version
     */
    public String getLang() {
        return lang;
    }

    /**
     * 
     * @param lang lang to set
     * @author whh
     * Date 2014年11月14日
     * @version
     */
    public void setLang(String lang) {
        this.lang = lang;
    }
    
    private  CategoryAccessoryService categoryAccessoryService;

    /**
     * Tag init
     * @author whh
     * Date 2014年11月14日
     * @version
     */
    public void init() {
        try {
            ServletContext sc = pageContext.getServletContext();
            categoryAccessoryService = (CategoryAccessoryService) WebApplicationContextUtils
                    .getRequiredWebApplicationContext(sc).getBean("categoryAccessoryService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int doStartTag() {
        init();
        
        JspWriter out = pageContext.getOut();// 用pageContext获取out，他还能获取session等，基本上jsp的内置对象都能获取
        List<CategoryAccessoryEntity> calist =  categoryAccessoryService.getCategoryAccessoryListByCategoryMetadataId(categoryMetadataId,lang);
        try {
            if(calist!=null&&calist.size()>0){
                for (CategoryAccessoryEntity ca : calist) {
                    //根据AccessoryType创建不同的输入框
                    if(ca.getCategoryAccessoryType()==MasterDataCodeConstant.CATEGORY_ACCESSORY_SKU_PICKER){
                        String outString = createSkuPicker(ca);
                        out.print(outString);
                    }
                    if(ca.getCategoryAccessoryType()==MasterDataCodeConstant.CATEGORY_ACCESSORY_DESCRIPTION){
                        String outString = createTextarea(ca);
                        out.print(outString);
                    }
                    
                   
                }
                
            }
        }catch(Exception e){
            
        }
        return EVAL_PAGE;// 标签执行完后，继续执行后面的
    }
    

    
    /**
     * 创建多行输入框
     * @param cca
     * @return
     * @author whh
     * Date 2014年11月14日
     * @version
     */
    public String createTextarea(CategoryAccessoryEntity ca) {
        StringBuffer sb = new StringBuffer();
       
        String showname = ca.getCategoryAccessoryName();
        String name = "";
        String value = "";
        
        
        //String value = getProductValue(ca.getCategoryComAttrMetadataId().toString()); //file value
        //使用id作为页面textarea的name
        if(ca.getCategoryAccessoryMetadataId()!=null){
            name = ca.getCategoryAccessoryMetadataId().toString();
            List<SKUAccessoryEntity> list = categoryAccessoryService.getSKUAccessoryBySKUId(skuMetadataId, lang,name);
            if(list!=null&&list.size()>0){
                value = list.get(0).getAccessoryDescription();
            }
            
        }
        
        
        sb.append("<div class='row large'><div class='col-md-3'> ");
        sb.append(showname);
        sb.append("</div><div class='col-md-9'>");
        sb.append("<textarea class='textarea' name='"+name+"'>"+value+"</textarea>");
        sb.append("</div></div>");
        
        return sb.toString();
    }
    


    
    /**
     * 创建sku选择器
     * @param cca
     * @return
     * @author sana
     * Date 2014年11月14日
     * @version
     */
    public String createSkuPicker(CategoryAccessoryEntity ca) {
        StringBuffer sb = new StringBuffer();
        String name = "";
        String showname = ca.getCategoryAccessoryName();  //左侧显示的名称
        String hiddenvalue = ""; //实际存于数据库的值
        String showvalue = "";  //显示在页面的值
        
        
        if(ca.getCategoryAccessoryMetadataId()!=null){
            name = ca.getCategoryAccessoryMetadataId().toString();
            List<SKUAccessoryEntity> list = categoryAccessoryService.getSKUAccessoryBySKUId(skuMetadataId, lang, name);
            if(list!=null&&list.size()>0){
                StringBuffer strb = new StringBuffer();
                StringBuffer showsb = new StringBuffer();
                for (SKUAccessoryEntity skuAccessoryEntity : list) {
                    strb.append(skuAccessoryEntity.getSkuPickId());
                    strb.append(",");
                    showsb.append(getSKUProductName(skuAccessoryEntity.getSkuPickId().toString()));
                    showsb.append(",");
                }
                hiddenvalue = strb.toString();
                hiddenvalue = hiddenvalue.substring(0, hiddenvalue.length()-1);
                showvalue = showsb.toString();
                showvalue = showvalue.substring(0, showvalue.length()-1);
            }
        }
        sb.append("<div class='row'><div class='col-md-3'> ");
        sb.append(showname);
        sb.append("</div><div class='col-md-7 border'>");
        sb.append("<input type='hidden'  name='"+name+"' id='hidden"+name+lang+"' value='"+hiddenvalue+"'><input readonly='readonly'  id='show"+name+lang+"' value='"+showvalue+"'>");
        sb.append("</div><div class='col-md-2 border'><a href='#'  class='choose tn_c' id='"+name+lang+"choose'>Choose</a></div></div>");
        sb.append("<script>");
        sb.append("$('a[id=\""+name+lang+"choose\"]').on('click', function(){");
        sb.append("openSkuPicker('hidden"+name+lang+"','show"+name+lang+"');");
        sb.append("});");
        sb.append("</script>");
        return sb.toString();
    }
    
    
    /**
     * 获取skuproductname
     * @param skuid
     * @return
     * @author sana
     * Date 2014年11月15日
     * @version
     */
    public String getSKUProductName(String skuid){
        if(skuid!=null&&!"".equals(skuid)){
            List<ProductEntity> list = categoryAccessoryService.getSKUProductName(skuid);
            if(list != null && list.size() > 0){
                if(list.get(0).getProductName()!=null){
                    return list.get(0).getProductName();
                }
            }
        }
        return "";
    }
    
    public int doEndTag() {
        return SKIP_BODY;// 标签执行完后，不继续执行后面的
    }

}
