package com.kohler.entity;

public class NewArrialProductEntity extends BaseEntity{

    /**
     * 
     */
    private static final long serialVersionUID = 7353163041836031371L;
    private Integer NEW_ARRIVAL_PRODUCT_ID;
    private Integer NEW_ARRIVAL_METADATA_ID;
    private Integer SKU_METADATA_ID;
    private Integer SEQ_NO;
    
    public Integer getNEW_ARRIVAL_PRODUCT_ID() {
        return NEW_ARRIVAL_PRODUCT_ID;
    }
    public void setNEW_ARRIVAL_PRODUCT_ID(Integer nEW_ARRIVAL_PRODUCT_ID) {
        NEW_ARRIVAL_PRODUCT_ID = nEW_ARRIVAL_PRODUCT_ID;
    }
    public Integer getNEW_ARRIVAL_METADATA_ID() {
        return NEW_ARRIVAL_METADATA_ID;
    }
    public void setNEW_ARRIVAL_METADATA_ID(Integer nEW_ARRIVAL_METADATA_ID) {
        NEW_ARRIVAL_METADATA_ID = nEW_ARRIVAL_METADATA_ID;
    }
    public Integer getSKU_METADATA_ID() {
        return SKU_METADATA_ID;
    }
    public void setSKU_METADATA_ID(Integer sKU_METADATA_ID) {
        SKU_METADATA_ID = sKU_METADATA_ID;
    }
    public Integer getSEQ_NO() {
        return SEQ_NO;
    }
    public void setSEQ_NO(Integer sEQ_NO) {
        SEQ_NO = sEQ_NO;
    }
}
