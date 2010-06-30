
package org.springframework.flex.config.xml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.RequestKey;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.util.StringUtils;

public class SpringSecurity3ConfigHelper implements SpringSecurityConfigHelper {

    private static final String LOGIN_COMMAND_CLASS_NAME = "org.springframework.flex.security3.SpringSecurityLoginCommand";

    private static final String ENDPOINT_INTERCEPTOR_CLASS_NAME = "org.springframework.flex.security3.EndpointInterceptor";

    private static final String ENDPOINT_DEFINITION_SOURCE_CLASS_NAME = "org.springframework.flex.security3.EndpointSecurityMetadataSource";

    private static final String SESSION_FIXATION_POST_PROCESSOR_CLASS_NAME = "org.springframework.flex.security3.SessionFixationProtectionPostProcessor";

    private static final String LOGIN_MESSAGE_INTERCEPTOR_CLASS_NAME = "org.springframework.flex.security3.LoginMessageInterceptor";
    
    private static final String PER_CLIENT_AUTHENTICATION_INTERCEPTOR_CLASS_NAME = "org.springframework.flex.security3.PerClientAuthenticationInterceptor";
    
    private static final String SECURITY_EXCEPTION_TRANSLATOR_CLASS_NAME = "org.springframework.flex.security3.SecurityExceptionTranslator";
    
    public String getAccessManagerId() {
        // In Spring Security 3, the AccessDecisionManager no longer gets assigned a well-known default ID
        return null;
    }

    public String getAuthenticationManagerId() {
        return "org.springframework.security.authenticationManager";
    }

    public String getLoginMessageInterceptorClassName() {
        return LOGIN_MESSAGE_INTERCEPTOR_CLASS_NAME;
    }

    public String getPerClientAuthenticationInterceptorClassName() {
        return PER_CLIENT_AUTHENTICATION_INTERCEPTOR_CLASS_NAME;
    }

    public String getSecurityExceptionTranslatorClassName() {
        return SECURITY_EXCEPTION_TRANSLATOR_CLASS_NAME;
    }

    public Object parseConfigAttributes(String access) {
        if (StringUtils.hasText(access)) {
            String[] attrs = StringUtils.commaDelimitedListToStringArray(access);
            List<ConfigAttribute> config = new ArrayList<ConfigAttribute>();
            for (int i = 0; i < attrs.length; i++) {
                config.add(new SecurityConfig(attrs[i]));
            }
            return config;
        } else {
            return null;
        }
    }

    public Object parseRequestKey(String requestPath) {
        return new RequestKey(requestPath);
    }

    public Object getPathMatcher() {
        return new AntUrlPathMatcher();
    }

    public String getEndpointDefinitionSourceClassName() {
        return ENDPOINT_DEFINITION_SOURCE_CLASS_NAME;
    }

    public String getEndpointInterceptorClassName() {
        return ENDPOINT_INTERCEPTOR_CLASS_NAME;
    }

    public String getLoginCommandClassName() {
        return LOGIN_COMMAND_CLASS_NAME;
    }

    public String getSessionFixationPostProcessorClassName() {
        return SESSION_FIXATION_POST_PROCESSOR_CLASS_NAME;
    }
}