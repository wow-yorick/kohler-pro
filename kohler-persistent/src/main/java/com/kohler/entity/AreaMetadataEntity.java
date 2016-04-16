package com.kohler.entity;

/**
 * 
 * Class Function Description
 *
 * @author sana
 * @Date 2014年12月16日
 */
public class AreaMetadataEntity extends BaseEntity {
	/**
	 * 省市区 节点
	 */
	private static final long serialVersionUID = 6314418624406484008L;
	private Integer areaMetadataId;
	private Integer parentId;//父节点
	private Integer areaType;//类型
	private Integer seqNo;//排序
	public Integer getAreaMetadataId() {
		return areaMetadataId;
	}
	public void setAreaMetadataId(Integer areaMetadataId) {
		this.areaMetadataId = areaMetadataId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public Integer getAreaType() {
		return areaType;
	}
	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	
}
