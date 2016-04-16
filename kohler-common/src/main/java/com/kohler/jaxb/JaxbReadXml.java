package com.kohler.jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.kohler.jaxb.entity.Check;
import com.kohler.jaxb.entity.Field;
import com.kohler.jaxb.entity.Template;

public class JaxbReadXml {

    @SuppressWarnings("unchecked")
    public static <T> T readString(Class<T> clazz, String context) throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(new File(context));
        } catch (JAXBException e) {
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T readConfig(Class<T> clazz, String config, Object... arguments) throws IOException,
            JAXBException {
        InputStream is = null;
        try {
            if (arguments.length > 0) {
                config = MessageFormat.format(config, arguments);
            }
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            is = new FileInputStream(config);
            return (T) u.unmarshal(is);
        } catch (IOException e) {
            throw e;
        } catch (JAXBException e) {
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T readConfigFromStream(Class<T> clazz, InputStream dataStream) throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(dataStream);
        } catch (JAXBException e) {
            throw e;
        }
    }

    public static void main(String[] args) throws JAXBException {
    	Template template = JaxbReadXml.readString(Template.class, "src\\main\\resources\\testTemplate.xml");
    	  for (Field filed : template.getFields()) {
    		  System.out.println("===========xml to entity begin===========");
    		  System.out.println("name="+filed.getName());
    		  System.out.println("type="+filed.getType());
    		  System.out.println("area"+filed.getArea());
    		  List<String> values = filed.getValues();
    		  if(values != null){
    			  for (String value : values) {
        			  System.out.println("value="+value);
        		  }
    		  }else{
    			  System.out.println("value=null");
    		  }
    		  Check check = filed.getCheck();
    		  if(check != null){
    			  System.out.println("check begin");
    			  System.out.println("maxsize="+check.getMaxsize());
    			  System.out.println("require="+check.getRequire());
    			  System.out.println("check end");
    		  }else{
    			  System.out.println("check=null");
    		  }
    		  System.out.println("===========xml to entity end===========");
		}
    }
}