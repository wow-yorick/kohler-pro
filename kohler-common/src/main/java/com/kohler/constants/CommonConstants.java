package com.kohler.constants;

import java.util.HashMap;
import java.util.List;

import com.kohler.bean.ZookeeperNodeInfo;

/**
 * 公共常量类
 * @author kangmin_Qu
 *
 */
public class CommonConstants {

    /*
     * 用户登陆SESSION
     */
    public final static String USER_LOGIN_SESSION = "USER_LOGIN_SESSION";
    
    /**
     * 用户 session key
     */
    public static final String USER_SESSION_KEY = "userSessionKey";
    
    /**
     * redis session key
     */
    public static final String REDIS_SESSION_KEY = "redisSessionKey";
    
    /**
     * redis cache key
     */
    public static final String REDIS_CACHE_KEY = "redisCacheKey";
    
    
    public final static String ZOOKEEPER_REDIS_IPADDRESS = "ZOOKEEPER_REDIS_IPADDRESS";
    
    public final static String ZOOKEEPER_REDIS_JEDISPOOL = "ZOOKEEPER_REDIS_JEDISPOOL";
    
    public final static String ZOOKEEPER_REDIS_IPADDRESS_STRING = "ZOOKEEPER_REDIS_IPADDRESS_STRING";
    
    public final static String ZOOKEEPER_REDIS_NODE_INFO = "ZOOKEEPER_REDIS_NODE_INFO";
    
    /*
     * ZOOKEEPER NODE INFO IN LIST 
     */
    public static List<ZookeeperNodeInfo> ZOOKEEPER_NODE_LISR = null;
    
    
    /*
     * LANG (CN)
     */
    public static int LOCALE_CN = 1;
    
    /*
     * LANG (EN)
     */
    public static int LOCALE_EN = 2;
    
    /*
     * INFO_COMMON_CREATE_SUCCESS
     */
    public final static String INFO_COMMON_CREATE_SUCCESS = "info.common.create.success";
    
    /*
     * INFO_COMMON_CREATE_FAILED
     */
    public final static String INFO_COMMON_CREATE_FAILED = "info.common.create.failed";
    
       /*
     * INFO_COMMON_UPDATE_SUCCESS
     */
    public final static String INFO_COMMON_UPDATE_SUCCESS = "info.common.update.success";
    
    /*
     * INFO_COMMON_UPDATE_FAILED
     */
    public final static String INFO_COMMON_UPDATE_FAILED = "info.common.update.failed";
    
    /*
     * INFO_COMMON_DELETE_SUCCESS
     */
    public final static String INFO_COMMON_DELETE_SUCCESS = "info.common.delete.success";
    
    /*
     * INFO_COMMON_DELETE_FAILED
     */
    public final static String INFO_COMMON_DELETE_FAILED = "info.common.delete.failed";
    
    /*
     * JSON ERROR MESSAGE
     */
    public final static String ERROR_COMMON_ACTION_001 = "error.common.action.001";
    
    /**
     * VELOCITY TEMPLATE DIR
     */
    public final static String VELOCITY_DIR = "file.velocity.template.dir";
    
    /**
     * PUBLISH HTML DIR
     */
    public final static String PUBLISH_DIR = "file.publish.html.dir";
    
    /**
     * DATATYPE XSD DIR
     */
    public final static String DATATYPE_XSD_DIR = "file.datatype.xsd.dir";
    
    /**
     * DATATYPE XML DIR
     */
    public final static String DATATYPE_XML_DIR = "file.datatype.xml.dir";
    
    /**
     * DATATYPE XMLLIST DIR
     */
    public final static String DATATYPE_XMLLIST_DIR = "file.datatype.xmlList.dir";
    
    /**
     * DATATYPE XMLEDIT DIR
     */
    public final static String DATATYPE_XMLEDIT_DIR = "file.datatype.xmlEdit.dir";
    
    /**
     * INFO_USER_LOGIN_SUCCESS
     */
    public final static String INFO_USER_LOGIN_SUCCESS="Login Success";
    
    /**
     * INFO_USER_lOGIN_USERNAME
     */
    public final static String INFO_USER_lOGIN_USERNAME="info.user.login.userName";
    
    /**
     * INFO_USER_lOGIN_PASSWORD
     */
    public final static String INFO_USER_lOGIN_PASSWORD="info.user.login.password";
    
    /**
     * MSG_LIST_UI_SCHEMA_XML
     */
    public final static String MSG_LIST_UI_SCHEMA_XML="error.admin.datatype.001";
    
    /**
     * MSG_EDIT_UI_SCHEMA_XML
     */
    public final static String MSG_EDIT_UI_SCHEMA_XML="error.admin.datatype.002";
    
    /**
     * INFO_CONTENT_CREATE_SUCCESS
     */
    public final static String INFO_CONTENT_CREATE_SUCCESS="info.content.create.success";
    
    /**
     * INFO_CONTENT_CREATE_FAILED
     */
    public final static String INFO_CONTENT_CREATE_FAILED="info.content.create.failed";
    
    /**
     * INFO_CONTENT_EDIT_SUCCESS
     */
    public final static String INFO_CONTENT_EDIT_SUCCESS="info.content.edit.success";
    
    /**
     * INFO_CONTENT_EDIT_FAILED
     */
    public final static String INFO_CONTENT_EDIT_FAILED="info.content.edit.failed";
    
    /**
     * INFO_PUBLISH_LOCK_FAILED
     */
    public final static String INFO_PUBLISH_LOCK_FAILED = "info.publish.lock.failed";
    
    /**
     * INFO_PUBLISH_LOCK_SUCCESS
     */
    public final static String INFO_PUBLISH_LOCK_SUCCESS = "info.publish.lock.success";
    
    /**
     * INFO_PUBLISH_UNLOCK_FAILED
     */
    public final static String INFO_PUBLISH_UNLOCK_FAILED = "info.publish.unlock.failed";
    
    /**
     * INFO_PUBLISH_UNLOCK_SUCCESS
     */
    public final static String INFO_PUBLISH_UNLOCK_SUCCESS = "info.publish.unlock.success";
    
    /**
     * INFO_PUBLISH_ROLLBACK_FAILED
     */
    public final static String INFO_PUBLISH_ROLLBACK_FAILED = "info.publish.rollback.failed";
    
    /**
     * INFO_PUBLISH_ROLLBACK_SUCCESS
     */
    public final static String INFO_PUBLISH_ROLLBACK_SUCCESS = "info.publish.rollback.success";

    
    /**
     * 页面类型
     */
    public final static int PAGE_DATATYPE_HOMEPAGE = 92;
    
    /**
     * SECTION ROOT
     */
    public final static int SECTION_HOME_PC_CN = 11;
    public final static int SECTION_HOME_PC_EN = 21;
    public final static int SECTION_HOME_MOBILE_CN = 31;
    public final static int SECTION_HOME_MOBILE_EN = 41;
    
    /**
     * CONTENT_FIELD_VALUES 中表示文件ID的动态字段
     */
    public final static String IMAGE_ID="Image Id";
    public final static String FILE_ID="File Id";
    
    /*
     * STATIC HTML CONTENT
     */
    public final static String STATIC_HTML_CONTENT_FIELD = "HTML Content";
    
    /**
     * WEBSITE SITE SETTING
     */
    public final static String PC_WEBSITE = "pc_website";
    public final static String MOBILE_WEBSITE = "mobile_website";
    public final static String FILE_SERVER = "file_server";
    public final static String IMAGE_SERVER = "image_server";
    public final static String PREVIEW_SERVER = "preview_server";
    
    /**
     * 平台类型
     */
    public final static String PC_PLATFORM = "pc";
    public final static String MOBILE_PLATFORM = "mobile";
    public final static String FILE_PLATFORM = "file";
    public final static String PREVIEW_PLATFORM = "preview";
    public final static String IMAGE_PLATFORM = "image";
    
    //发布状态
    public final static Integer PUBLISH_STATUS_OPEN = 10120001; //发布状态 开启
    public final static Integer PUBLISH_STATUS_LOCKED = 10120002; //发布状态锁定 
    public final static Integer PUBLISH_STATUS_PUBLISHED = 10120003; //已发布
    public final static Integer PUBLISH_STATUS_ROLLBACKED = 10120004; //以回滚
    
    //发布类型
    public final static Integer AUTO_PUBLISH = 000000; //自动
    public final static Integer MANUAL_PUBLISH = 111111; //手动
    
    //文件引用方式
    public final static Integer FILE_SOURCE_INTERNAL = 10030001; //内部文件资源
    public final static Integer FILE_SOURCE_EXTERNAL = 10030002; //外部文件资源
    
    /**
     * 模板类型
     */
    public static final int TYPE_TEMPLATE_TYPE = 1001;
    public static final int TEMPLATE_TYPE_CATALOG = 10010001;
    public static final int TEMPLATE_TYPE_CATEGORY = 10010002;
    public static final int TEMPLATE_TYPE_PRODUCT = 10010003;
    public static final int TEMPLATE_TYPE_SUITE = 10010004;
    public static final int TEMPLATE_TYPE_NEW_PRODUCT = 10010005;
    public static final int TEMPLATE_TYPE_PAGE = 10010006;
    public static final int TEMPLATE_TYPE_PRODUCT_SKU_JSFILE = 10010007;// product sku js file
    public static final int TEMPLATE_TYPE_PAGE_SOLRMAP = 10010008; //solrmap
    public static final int TEMPLATE_TYPE_PAGE_SUITE_LIST = 10010009; //suite list
    
    /**
     * 数据准备xml 
     */
    public static final String XML_FOR_SECTION_PC_CN = "section_pc_cn.xml";
    public static final String XML_FOR_CATEGORY_PC_CN = "category_pc_cn.xml";
    public static final String XML_FOR_NEW_PRODUCT_PC_CN = "new_product_pc_cn.xml";
    public static final String XML_FOR_PAGE = "pageData.xml";
    public static final String XML_FOR_PAGE_SOLRMAP = "pageData_forSolrMap.xml";
    public static final String XML_FOR_PRODUCT = "productData.xml";
    public static final String XML_FOR_SUITE_LIST = "suitelist_pc_cn.xml";
    public static final String XML_FOR_SUITE = "suite_pc_cn.xml";
    public static final String XML_FOR_PRODUCT_SKU_JSFILE = "product_sku_jsfile.xml";
    
    
    /**
     * CAD类型
     */
    public static final int TYPE_CAD_TYPE = 1005;
    public static final int CAD_TYPE_2DPLAN = 10050001;
    public static final int CAD_TYPE_2DFONT = 10050002;
    public static final int CAD_TYPE_3D = 10050003;
    public static final int CAD_TYPE_CUTOUT = 10050004;
    public static final int PDF_TYPE_SIZE = 10060001;
    
    /**
     * 产品属性输入方式
     */
    public static final int TYPE_COMMON_ATTRIBUTE_INPUT_TYPE = 1002;    
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_TEXTBOX = 10020001;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_DROPDOWNLIST = 10020002;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_TEXTAREA = 10020003;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_RICHTEXTEDIT = 10020004;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_SKU_PICKER_SINGLE = 10020005;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_SKU_PICKER_MULTI = 10020006;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_PRODUCT_PICKER_SINGLE = 10020007;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_PRODUCT_PICKER = 10020008;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_FILE_ASSET_PICKER_IMAGE_SINGLE = 10020009;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_FILE_ASSET_PICKER_IMAGE_MULTI = 10020010;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_FILE_ASSET_PICKER_VIDEO_SINGLE = 10020011;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_FILE_ASSET_PICKER_VIDEO_MULTI = 10020012;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_FILE_ASSET_PICKER_CAD_SINGLE = 10020013;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_FILE_ASSET_PICKER_CAD_MULTI = 10020014;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_FILE_ASSET_PICKER_PDF_SINGLE = 10020015;
    public static final int COMMON_ATTRIBUTE_INPUT_TYPE_FILE_ASSET_PICKER_PDF_MULTI = 10020016;


    public final static String CONTENT_HERO_AREAS = "93";
    public final static String CONTENT_BANNER_UNITS= "94";
    public final static String CONTENT_HOT_SPOTS = "95";
    
    /*
     * solr server address
     */
    public final static String SOLR_SERVER = "solr.server.addr";
    
    /*
     * solr time name
     */
    public final static String SOLR_TIME_NAME = "solr.time.name";
    
    /*
     * back_data_dir_suffix
     */
    public final static String SUFFIX = ".bat";
    
    public static HashMap<String,List<Object>> DATATYPE_DEFINATION_XML_HASHMAP = new HashMap<String,List<Object>>();

}


