/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.entity;

/**
 * Class Function Description
 *
 * @author Administrator
 * @Date 2014年11月25日
 */
public class SuiteHotSpotEntity extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 3984366580570971741L;
    private Integer suiteHotSpotId;
    private Integer suiteMetadataId;
    private Integer hotSpotId;
    private Integer seqNo;
    public Integer getSuiteHotSpotId() {
        return suiteHotSpotId;
    }
    public void setSuiteHotSpotId(Integer suiteHotSpotId) {
        this.suiteHotSpotId = suiteHotSpotId;
    }
    public Integer getSuiteMetadataId() {
        return suiteMetadataId;
    }
    public void setSuiteMetadataId(Integer suiteMetadataId) {
        this.suiteMetadataId = suiteMetadataId;
    }
    public Integer getHotSpotId() {
        return hotSpotId;
    }
    public void setHotSpotId(Integer hotSpotId) {
        this.hotSpotId = hotSpotId;
    }
    public Integer getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
}
