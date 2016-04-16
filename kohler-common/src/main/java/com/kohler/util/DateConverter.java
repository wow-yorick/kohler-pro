/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * spring3 mvc 的日期传递[前台-后台] bug:
 * org.springframework.validation.BindException的解决方式.包括xml的配置
 * 
 * @author shefeng
 * @Date 2014年9月25日
 */
public class DateConverter implements WebBindingInitializer {

    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                if (value == null || "".equals(value)) {
                    setValue(null);
                } else {
                    Pattern ymd = Pattern
                            .compile("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$");
                    Matcher ymdmatcher = ymd.matcher(value);
                    boolean ymdflag = ymdmatcher.matches();

                    if (ymdflag) {
                        try {
                            setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                        } catch (ParseException e) {
                            setValue(null);
                        }
                    }

                    Pattern ymdhms = Pattern
                            .compile("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)\\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$");
                    Matcher ymdhmsmatcher = ymdhms.matcher(value);
                    boolean ymdhmsflag = ymdhmsmatcher.matches();
                    if (ymdhmsflag) {
                        try {
                            setValue(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(value));
                        } catch (ParseException e) {
                            setValue(null);
                        }
                    }
                }
            }
        });
    }
}
