package com.kohler.entity;

public class TemplateEntity extends BaseEntity{

	private static final long serialVersionUID = -9160215633305115592L;
	private Integer templateId;//系列Id
	private String templateContent;//模版文件 CONTENT
	private String templateName;//模版名
	private Integer templateType;//模版类型
	private String remark;//备注
	private Integer publishFolderId;//发布目录id
	private String physicalName;
	private String templateImage;//图片路径
	private String generateName; //发布的文件名
	private String generateUrl; //发布的文件名
	private String dataGettingXmlName; //获取xml名称(数据库中新增字段)
	
	public String getTemplateContent() {
		return templateContent;
	}
	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}
	public String getTemplateImage() {
		return templateImage;
	}
	public void setTemplateImage(String templateImage) {
		this.templateImage = templateImage;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public Integer getTemplateType() {
		return templateType;
	}
	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getPublishFolderId() {
		return publishFolderId;
	}
	public void setPublishFolderId(Integer publishFolderId) {
		this.publishFolderId = publishFolderId;
	}
	public String getPhysicalName() {
		return physicalName;
	}
	public void setPhysicalName(String physicalName) {
		this.physicalName = physicalName;
	}
    public String getGenerateName() {
        return generateName;
    }
    public void setGenerateName(String generateName) {
        this.generateName = generateName;
    }
    public String getGenerateUrl() {
        return generateUrl;
    }
    public void setGenerateUrl(String generateUrl) {
        this.generateUrl = generateUrl;
    }
    public String getDataGettingXmlName() {
        return dataGettingXmlName;
    }
    public void setDataGettingXmlName(String dataGettingXmlName) {
        this.dataGettingXmlName = dataGettingXmlName;
    }
   
	
	
}
