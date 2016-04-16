package com.kohler.dao;

import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.record.formula.functions.T;

/**
 * 
 * @author ys
 *
 */
public interface MasterDataCodeDao extends BaseDao<T>{
	public List<Map<String, Object>> getAllTypeByMasterData(List<Object> MasterData);
}
