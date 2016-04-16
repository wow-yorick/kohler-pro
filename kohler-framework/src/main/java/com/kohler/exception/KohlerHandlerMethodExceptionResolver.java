/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.exception;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

/**
 * Exception handle 
 *
 * @author Administrator
 * @Date 2014年10月22日
 */
public class KohlerHandlerMethodExceptionResolver extends ExceptionHandlerExceptionResolver {
    private String defaultErrorView;
    
    public String getDefaultErrorView() {
        return defaultErrorView;
    }

    public void setDefaultErrorView(String defaultErrorView) {
        this.defaultErrorView = defaultErrorView;
    }

    /**
     * 
     * {@inheritDoc}
     */
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request,
            HttpServletResponse response,
            HandlerMethod handlerMethod,
            Exception exception) {
        
        if (handlerMethod == null) {
            return null;
        }
        
        Method method = handlerMethod.getMethod();

        if (method == null) {
            return null;
        }
        
        ModelAndView returnValue = super.doResolveHandlerMethodException(request, response, handlerMethod, exception);
        
        ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method, ResponseBody.class);
        if (responseBodyAnn != null) {
            try {
                ResponseStatus responseStatusAnn = AnnotationUtils.findAnnotation(method, ResponseStatus.class);
                if (responseStatusAnn != null) {
                     String reason = responseStatusAnn.reason();
                    //response.sendError(responseStatus.value(), reason);
                    returnValue.addObject("msg", "exception happened, Please try it later." + reason);

                }
            
                return handleResponseBody(returnValue, request, response);

            } catch (Exception e) {
                return null;
            }
        }
        
        if(returnValue.getViewName() == null){
            returnValue.setViewName(defaultErrorView);
        }
        
        return returnValue;
        
    }
    
    /**
     *  error message converted
     * @param returnValue ModelAndView
     * @param request request
     * @param response response
     * @return ModelAndView
     * @throws ServletException ServletException occured
     * @throws IOException IOException occured
     * @author Administrator
     * Date 2014年10月22日
     * @version
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private ModelAndView handleResponseBody(ModelAndView returnValue, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map value = returnValue.getModelMap();
        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
        if (acceptedMediaTypes.isEmpty()) {
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
        }
        MediaType.sortByQualityValue(acceptedMediaTypes);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        Class<?> returnValueType = value.getClass();
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        if (messageConverters != null) {
            for (MediaType acceptedMediaType : acceptedMediaTypes) {
                for (HttpMessageConverter messageConverter : messageConverters) {
                    if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {
                        messageConverter.write(value, acceptedMediaType, outputMessage);
                        return new ModelAndView();
                    }
                }
            }
        }
        if (logger.isWarnEnabled()) {
            logger.warn("Could not find HttpMessageConverter that supports return type [" + returnValueType + "] and " + acceptedMediaTypes);
        }
        return null;
    }


}
