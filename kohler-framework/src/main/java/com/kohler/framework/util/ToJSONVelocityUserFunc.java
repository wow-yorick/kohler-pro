/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
*/
package com.kohler.framework.util;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

import com.kohler.util.JSonUtil;

/**
 * velocity to json 
 *
 * @author Administrator
 * @Date 2014年12月3日
 */
public class ToJSONVelocityUserFunc extends Directive {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "toJSON";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getType() {
        return LINE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node)
            throws IOException, ResourceNotFoundException, ParseErrorException,
            MethodInvocationException {
        String retStr = JSonUtil.toJSonString(node);
        writer.write(retStr);
        return true;
    }

}
