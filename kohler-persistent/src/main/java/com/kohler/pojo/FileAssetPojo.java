/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.pojo;

import com.kohler.entity.FileAssetEntity;

/**
 * DataTypePojo
 *
 * @author ys
 * @Date 2014-10-22
 */
public class FileAssetPojo extends FileAssetEntity{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String folderName;
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
}
