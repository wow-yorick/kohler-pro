/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.bean;

import java.util.ArrayList;
import java.util.List;

public class Field {

	private String id;
	
	private String editorType;
	
	private String name;
	
	private String editoTypeAttributes;
	
	private String dataTypeRef;
	
	private String masterDataCode;
	
	private String affect;

    private String value;
	
	private List<Lang> langs = new ArrayList<Lang>();
	
	private List<Numeration> numeration = new ArrayList<Numeration>();
	
	private List<Check> checks = new ArrayList<Check>();
	
	

	
	public String getAffect() {
        return affect;
    }

    public void setAffect(String affect) {
        this.affect = affect;
    }

    public String getMasterDataCode() {
        return masterDataCode;
    }

    public void setMasterDataCode(String masterDataCode) {
        this.masterDataCode = masterDataCode;
    }

    public List<Check> getChecks() {
        return checks;
    }

    public void setChecks(List<Check> checks) {
        this.checks = checks;
    }

    public String getEditorType() {
		return editorType;
	}

	public void setEditorType(String editorType) {
		this.editorType = editorType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEditoTypeAttributes() {
		return editoTypeAttributes;
	}

	public void setEditoTypeAttributes(String editoTypeAttributes) {
		this.editoTypeAttributes = editoTypeAttributes;
	}

	public List<Numeration> getNumeration() {
		return numeration;
	}

	public void setNumeration(List<Numeration> numeration) {
		this.numeration = numeration;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void addNumeration(Numeration num){
		numeration.add(num);
	}

	public String getDataTypeRef() {
		return dataTypeRef;
	}

	public void setDataTypeRef(String dataTypeRef) {
		this.dataTypeRef = dataTypeRef;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

    /**
     * @return the langs
     */
    public List<Lang> getLangs() {
        return langs;
    }

    /**
     * @param langs the langs to set
     */
    public void setLangs(List<Lang> langs) {
        this.langs = langs;
    }
	

	public void addLang(Lang lang){
	    langs.add(lang);
	}
	
	public void addCheck(Check check){
	    checks.add(check);
	}
}
