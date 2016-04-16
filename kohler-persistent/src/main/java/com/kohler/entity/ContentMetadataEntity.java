package com.kohler.entity;

public class ContentMetadataEntity extends BaseEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Integer           contentMetadataId;

    private Integer              datatypeId;
    
    private Integer           seqNo;

    /**
     * @return the contentMetadataId
     */
    public Integer getContentMetadataId() {
        return contentMetadataId;
    }

    /**
     * @param contentMetadataId the contentMetadataId to set
     */
    public void setContentMetadataId(Integer contentMetadataId) {
        this.contentMetadataId = contentMetadataId;
    }

    /**
     * @return the datatypeId
     */
    public Integer getDatatypeId() {
        return datatypeId;
    }

    /**
     * @param datatypeId the datatypeId to set
     */
    public void setDatatypeId(Integer datatypeId) {
        this.datatypeId = datatypeId;
    }
    
    /**
     * @return the seqNo
     */
    public Integer getSeqNo() {
        return seqNo;
    }

    /**
     * @param seqNo the seqNo to set
     */
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    
}
