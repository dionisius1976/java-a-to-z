package ru.dionisius.config;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by Dionisius on 26.10.2017.
 */
public class WebDesc
        extends AbstractAnnotationConfigDispatcherServletInitializer {
    public WebDesc() {
        System.out.println("WebDesc instantiated.");
    }

    @Nullable
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SpringRootConfig.class};
    }

    @Nullable
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
