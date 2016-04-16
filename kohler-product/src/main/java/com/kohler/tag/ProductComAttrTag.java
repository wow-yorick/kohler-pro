package com.kohler.tag;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kohler.entity.CategoryComAttrEntity;
import com.kohler.entity.CategoryComAttrMetadataEntity;
import com.kohler.entity.MasterDataCodeConstant;
import com.kohler.entity.MasterdataEntity;
import com.kohler.entity.ProductComAttrEntity;
import com.kohler.entity.ProductEntity;
import com.kohler.service.ProductComAttrService;
/**
 * 
 * ProductComAttrTag Tag
 *
 * @author whh
 * @Date 2014年11月14日
 */
public class ProductComAttrTag extends TagSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 1051914200559391955L;
    
    private String productMetadataId;  //productMetadataId from product
    
    private String lang;     //lang from product
    
    private String categoryMetadataId;  //categoryMetadataId from product
    
    private String isEdit;
    
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
     * @return productMetadataId
     * @version
     */
    public String getProductMetadataId() {
        return productMetadataId;
    }

    /**
     * 
     * @param productMetadataId to set
     * @version
     */
    public void setProductMetadataId(String productMetadataId) {
        this.productMetadataId = productMetadataId;
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
    
    
    /**
     * 
     * @return
     * @author sana
     * Date 2014年12月16日
     * @version
     */
    public String getIsEdit() {
        return isEdit;
    }

    /**
     * 
     * @param isEdit
     * @author sana
     * Date 2014年12月16日
     * @version
     */
    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }



    private ProductComAttrService productComAttrService;

    /**
     * Tag init
     * @author whh
     * Date 2014年11月14日
     * @version
     */
    public void init() {
        try {
            ServletContext sc = pageContext.getServletContext();
            productComAttrService = (ProductComAttrService) WebApplicationContextUtils
                    .getRequiredWebApplicationContext(sc).getBean("productComAttrService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int doStartTag() {
        init();
        
        JspWriter out = pageContext.getOut();// 用pageContext获取out，他还能获取session等，基本上jsp的内置对象都能获取
        List<CategoryComAttrMetadataEntity> ccalist =  productComAttrService.getCategoryComAttrMetadataById(categoryMetadataId);
        try {
            if(ccalist!=null&&ccalist.size()>0){
                for (CategoryComAttrMetadataEntity cca : ccalist) {
                    //根据input type创建不同的输入框
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_TEXTBOX){
                        String outString = createText(cca);
                        out.print(outString);
                    }
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_DROPDOWNLIST){
                        String outString = createSelect(cca);
                        out.print(outString);
                    }
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_TEXTAREA){
                        String outString = createTextarea(cca);
                        out.print(outString);
                    }
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_RICHTEXTEDIT){
                        String outString = createRichText(cca);
                        out.print(outString);
                    }
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_SKU_PICKER_SINGLE){
                        String outString = createSkuPicker(cca, 1);
                        out.print(outString);
                    }
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_SKU_PICKER_MULTI){
                        String outString = createSkuPicker(cca, 2);
                        out.print(outString);
                    }
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_PRODUCT_PICKER_SINGLE){
                        String outString = createProductPicker(cca, 1);
                        out.print(outString);
                    }
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_PRODUCT_PICKER_MULTI){
                        String outString = createProductPicker(cca, 2);
                        out.print(outString);
                    }
                    
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_PICKER_IMAGE_SINGLE){
                        String outString = createFilePicker(cca, MasterDataCodeConstant.FILE_ASSET_IMAGE, 1);
                        out.print(outString);
                    }
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_PICKER_IMAGE_MULTI){
                        String outString = createFilePicker(cca, MasterDataCodeConstant.FILE_ASSET_IMAGE, 2);
                        out.print(outString);
                    }
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_PICKER_VIDEO_SINGLE){
                        String outString = createFilePicker(cca, MasterDataCodeConstant.FILE_ASSET_VIDEO, 1);
                        out.print(outString);
                    }
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_PICKER_VIDEO_MULTI){
                        String outString = createFilePicker(cca, MasterDataCodeConstant.FILE_ASSET_VIDEO, 2);
                        out.print(outString);
                    }
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_PICKER_CAD_SINGLE){
                        String outString = createFilePicker(cca, MasterDataCodeConstant.FILE_ASSET_CAD, 1);
                        out.print(outString);
                    }
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_PICKER_CAD_MULTI){
                        String outString = createFilePicker(cca, MasterDataCodeConstant.FILE_ASSET_CAD, 2);
                        out.print(outString);
                    }
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_PICKER_PDF_SINGLE){
                        String outString = createFilePicker(cca, MasterDataCodeConstant.FILE_ASSET_PDF, 1);
                        out.print(outString);
                    }
                    if(cca.getInputType()!=null&&cca.getInputType()==MasterDataCodeConstant.PRODUCT_INPUT_TYPE_PICKER_PDF_MULTI){
                        String outString = createFilePicker(cca, MasterDataCodeConstant.FILE_ASSET_PDF, 2);
                        out.print(outString);
                    }
                }
                
            }
        }catch(Exception e){
            
        }
        return EVAL_PAGE;// 标签执行完后，继续执行后面的
    }
    
    /**
     * 创建input
     * @param cca
     * @return
     * @author whh
     * Date 2014年11月14日
     * @version
     */
    public String createText(CategoryComAttrMetadataEntity cca) {
        StringBuffer sb = new StringBuffer();
        String name = "";  //file name
        String showname = getCatrgoryAttrName(cca.getCategoryComAttrMetadataId().toString()); //左侧显示的名字
        String value = getProductValue(cca.getCategoryComAttrMetadataId().toString()); //file value
        //使用id做为页面input的name
        if(cca.getCategoryComAttrMetadataId()!=null){
            name = cca.getCategoryComAttrMetadataId().toString();
        }
        sb.append("<div class='row'><div class='col-md-2'> ");
        sb.append(showname);
        sb.append("</div><div class='col-md-10'>");
        sb.append("<input type='text' name='"+name+"' value='"+value+"'>");
        sb.append("</div></div>");
        
        return sb.toString();
    }
    
    /**
     * 创建select 
     * @param cca
     * @return
     * @author sana
     * Date 2014年11月14日
     * @version
     */
    public String createSelect(CategoryComAttrMetadataEntity cca) {
        StringBuffer sb = new StringBuffer();
        String name = ""; //file name
        String showname = getCatrgoryAttrName(cca.getCategoryComAttrMetadataId().toString());  //左侧显示的名称
        String value = getProductValue(cca.getCategoryComAttrMetadataId().toString()); // file value
        //使用id作为页面select的name
        if(cca.getCategoryComAttrMetadataId()!=null){
            name = cca.getCategoryComAttrMetadataId().toString();
        }
        sb.append("<div class='row'><div class='col-md-2'> ");
        sb.append(showname);
        sb.append("</div><div class='col-md-10'>");
        sb.append("<select name='"+name+"'>");
        sb.append("<option value=''>---请选择---</option>");
        List<MasterdataEntity> masterlist =  productComAttrService.getMasterdata(lang, cca.getDropdownTypeCode());
        if(masterlist!=null&&masterlist.size()>0){
            for (MasterdataEntity masterdataEntity : masterlist) {
                //选中与file value相同的选项
                if(value.equals(masterdataEntity.getMasterdataMetadataId().toString())){
                    sb.append("<option selected='selected' value='" + masterdataEntity.getMasterdataMetadataId() + "'>" + masterdataEntity.getMasterdataName()
                            + "</option>");
                }else{
                    sb.append("<option value='" + masterdataEntity.getMasterdataMetadataId() + "'>" + masterdataEntity.getMasterdataName()
                            + "</option>");
                }
            }
            
        }
        sb.append("</select></div></div>");
        
        return sb.toString();
    }
    
    /**
     * 创建多行输入框
     * @param cca
     * @return
     * @author whh
     * Date 2014年11月14日
     * @version
     */
    public String createTextarea(CategoryComAttrMetadataEntity cca) {
        StringBuffer sb = new StringBuffer();
        String name = "";  //file name
        String showname = getCatrgoryAttrName(cca.getCategoryComAttrMetadataId().toString()); //左侧显示的名字
        String value = getProductValue(cca.getCategoryComAttrMetadataId().toString()); //file value
        //使用id作为页面textarea的name
        if(cca.getCategoryComAttrMetadataId()!=null){
            name = cca.getCategoryComAttrMetadataId().toString();
        }
        sb.append("<div class='row large'><div class='col-md-2'> ");
        sb.append(showname);
        sb.append("</div><div class='col-md-10'>");
        sb.append("<textarea class='textarea' name='"+name+"'>"+value+"</textarea>");
        sb.append("</div></div>");
        
        return sb.toString();
    }
    
    /**
     * 创建富文本编辑器
     * @param cca
     * @return
     * @author whh
     * Date 2014年11月14日
     * @version
     */
    public String createRichText(CategoryComAttrMetadataEntity cca) {
        StringBuffer sb = new StringBuffer();
        String name = "";  //file name
        String showname = getCatrgoryAttrName(cca.getCategoryComAttrMetadataId().toString()); //左侧显示的名称
        String value = getProductValue(cca.getCategoryComAttrMetadataId().toString());  //file value
        //使用id作为页面富文本的name
        if(cca.getCategoryComAttrMetadataId()!=null){
            name = cca.getCategoryComAttrMetadataId().toString();
        }
        sb.append("<div class='row' style='height:168px'><div class='col-md-2'> ");
        sb.append(showname);
        sb.append("</div><div class='col-md-10'>");
        sb.append("<textarea name='"+name+"' class='richtext' id='product_attr_"+name.replace(" ", "")+lang+"'>"+value+"</textarea>");
        sb.append("</div></div>");
        sb.append("<script>");
        if(isEdit!=null&&"1".equals(isEdit)){
            sb.append(" var editor_"+name.replace(" ", "")+lang+" = UeditorFileLoad.init('product_attr_"+name.replace(" ", "")+lang+"',null,null,{readonly:true}); ");
        }else{
            sb.append(" var editor_"+name.replace(" ", "")+lang+" = UeditorFileLoad.init('product_attr_"+name.replace(" ", "")+lang+"'); ");
        }
        sb.append("</script>");
        
        return sb.toString();
    }
    
    /**
     * 创建文件选择器
     * @param cca
     * @param filetype 文件类型
     * @param isSingle  是否单选  1为是
     * @return
     * @author sana
     * Date 2014年11月14日
     * @version
     */
    public String createFilePicker(CategoryComAttrMetadataEntity cca, Integer filetype, Integer isSingle) {
        StringBuffer sb = new StringBuffer();
        String name = ""; //filename
        String showname = getCatrgoryAttrName(cca.getCategoryComAttrMetadataId().toString());  //左侧显示的名称
        String value = getProductValue(cca.getCategoryComAttrMetadataId().toString()); //file value
        //使用id作为页面选择器input的name
        if(cca.getCategoryComAttrMetadataId()!=null){
            name = cca.getCategoryComAttrMetadataId().toString();
        }
        sb.append("<div class='row'><div class='col-md-2'> ");
        sb.append(showname);
        sb.append("</div><div class='col-md-8 border'>");
        sb.append("<input type='text' readonly='readonly' name='"+name+"' id='"+name+lang+"' value='"+value+"'>");
        sb.append("</div><div class='col-md-2 border'><a href='#'  class='choose tn_c' id='"+name+lang+"choose'>Choose</a></div></div>");
        sb.append("<script>");
        sb.append("$('a[id=\""+name+lang+"choose\"]').on('click', function(){");
        sb.append("openDataType('"+filetype+"','"+name+lang+"','"+isSingle+"');");
        sb.append("});");
        sb.append("</script>");
        return sb.toString();
    }
    
    /**
     * 创建sku选择器
     * @param cca
     * @param isSingle 是否是单选，1为是
     * @return
     * @author sana
     * Date 2014年11月14日
     * @version
     */
    public String createSkuPicker(CategoryComAttrMetadataEntity cca, Integer isSingle) {
        StringBuffer sb = new StringBuffer();
        String name = "";
        String showname = getCatrgoryAttrName(cca.getCategoryComAttrMetadataId().toString());  //左侧显示的名称
        String value = getProductValue(cca.getCategoryComAttrMetadataId().toString());  //file value
        if(cca.getCategoryComAttrMetadataId()!=null){
            name = cca.getCategoryComAttrMetadataId().toString();
        }
        sb.append("<div class='row'><div class='col-md-2'> ");
        sb.append(showname);
        sb.append("</div><div class='col-md-8 border'>");
        sb.append("<input type='hidden'  name='"+name+"' id='hidden"+name+lang+"' value='"+value+"'><input readonly='readonly'  id='show"+name+lang+"' value='"+getSKUProductName(value)+"'>");
        sb.append("</div><div class='col-md-2 border'><a href='#'  class='choose tn_c' id='"+name+lang+"choose'>Choose</a></div></div>");
        sb.append("<script>");
        sb.append("$('a[id=\""+name+lang+"choose\"]').on('click', function(){");
        sb.append("openSkuPicker('hidden"+name+lang+"','"+isSingle+"','show"+name+lang+"');");
        sb.append("});");
        sb.append("</script>");
        return sb.toString();
    }
    
    /**
     * 创建product选择器
     * @param cca
     * @param isSingle 单选判断
     * @return
     * @author sana
     * Date 2014年11月14日
     * @version
     */
    public String createProductPicker(CategoryComAttrMetadataEntity cca, Integer isSingle) {
        StringBuffer sb = new StringBuffer();
        String name = "";
        String showname = getCatrgoryAttrName(cca.getCategoryComAttrMetadataId().toString());//左侧显示的名称
        String value = getProductValue(cca.getCategoryComAttrMetadataId().toString()); //file value
        if(cca.getCategoryComAttrMetadataId()!=null){
            name = cca.getCategoryComAttrMetadataId().toString();
        }
        sb.append("<div class='row'><div class='col-md-2'> ");
        sb.append(showname);
        sb.append("</div><div class='col-md-8 border'>");
        sb.append("<input type='hidden'  name='"+name+"' id='hidden"+name+lang+"' value='"+value+"'><input  readonly='readonly'  id='show"+name+lang+"' value='"+getProductName(value)+"'>");
        sb.append("</div><div class='col-md-2 border'><a href='#'  class='choose tn_c' id='"+name+lang+"choose'>Choose</a></div></div>");
        sb.append("<script>");
        sb.append("$('a[id=\""+name+lang+"choose\"]').on('click', function(){");
        sb.append("openProductPicker('hidden"+name+lang+"','"+isSingle+"','show"+name+lang+"');");
        sb.append("});");
        sb.append("</script>");
        return sb.toString();
    }
    
    /**
     * 根据字段名称获取字段值
     * Date 2014-11-12
     * @version
     */
    public String getCatrgoryAttrName(String categoryComAttrMetadataId) {
        
        List<CategoryComAttrEntity> valuelist = productComAttrService.getCategoryComAttrById(categoryComAttrMetadataId, lang);
        if(valuelist != null && valuelist.size()>0){
            if(valuelist.get(0).getCategoryComAttrName()!=null){
                return valuelist.get(0).getCategoryComAttrName();
            }
        }
        return "";
    }
    /**
     * 根据字段名称获取字段值
     * Date 2014-11-12
     * @version
     */
    public String getProductValue(String categoryComAttrMetadataId) {
        if (productMetadataId != null && !"".equals(productMetadataId)) {
            List<ProductComAttrEntity> valuelist = productComAttrService.getProductComAttr(lang, productMetadataId, categoryComAttrMetadataId);
            if(valuelist != null && valuelist.size() > 0){
                if(valuelist.get(0).getValue()!=null){
                    return valuelist.get(0).getValue();
                }
            }
        }
        return "";
    }
    
    /**
     * 获取productname
     * @param productMetadataId
     * @return
     * @author sana
     * Date 2014年11月15日
     * @version
     */
    public String getProductName(String productMetadataId){
        if(productMetadataId!=null&&!"".equals(productMetadataId)){
            StringBuffer sb = new StringBuffer();
            if(productMetadataId.indexOf(",")>0){
                String[] ids = productMetadataId.split(",");
                for (String string : ids) {
                    List<ProductEntity> list = productComAttrService.getProductName("1", string);
                    if(list != null && list.size() > 0){
                        if(list.get(0).getProductName()!=null){
                            sb.append(list.get(0).getProductName());
                            sb.append(",");
                        }
                    }
                }
                return sb.toString().substring(0,sb.toString().length()-1);
            }else{
                List<ProductEntity> list = productComAttrService.getProductName("1", productMetadataId);
                if(list != null && list.size() > 0){
                    if(list.get(0).getProductName()!=null){
                        return list.get(0).getProductName();
                    }
                }
            }
        }
        return "";
    }
    /**
     * 获取skucode
     * @param skuid
     * @return
     * @author sana
     * Date 2014年11月15日
     * @version
     */
    public String getSKUProductName(String skuid){
        if(skuid!=null&&!"".equals(skuid)){
            StringBuffer sb = new StringBuffer();
            if(skuid.indexOf(",") > 0){
                String[] ids = skuid.split(",");
                for (String str : ids) {
                    List<ProductEntity> list = productComAttrService.getSKUProductName(str);
                    if(list != null && list.size() > 0){
                        if(list.get(0).getProductName()!=null){
                            sb.append(list.get(0).getProductName());
                            sb.append(",");
                        }
                    }
                }
                return sb.toString().substring(0, sb.toString().length()-1);
            }else{
                List<ProductEntity> list = productComAttrService.getSKUProductName(skuid);
                if(list != null && list.size() > 0){
                    if(list.get(0).getProductName()!=null){
                        return list.get(0).getProductName();
                    }
                }
            }
            
        }
        return "";
    }
    
    public int doEndTag() {
        return SKIP_BODY;// 标签执行完后，不继续执行后面的
    }

}
