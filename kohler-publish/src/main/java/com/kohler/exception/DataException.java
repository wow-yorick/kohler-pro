/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.exception;

import java.io.Serializable;

/**
 * custom exception
 *
 * @author Administrator
 * @Date 2014年11月17日
 */
public class DataException  extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public DataException(String msg)  
    {  
        super(msg);  
    }  
    
    public DataException(Exception e)  
    {  
        super(e);  
    }  
}
