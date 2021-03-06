/*
 * Copyright (c) 2008-2018 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.cuba.core.sys.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.Servlet;

@Component(ServletRegistrationManager.NAME)
public class ServletRegistrationManagerBean implements ServletRegistrationManager {

    @SuppressWarnings("unchecked")
    @Override
    public Servlet createServlet(ApplicationContext context, String servletClass) {
        Class<? extends Servlet> clazz;

        try {
            ClassLoader classLoader = context.getClassLoader();
            if (classLoader == null) {
                throw new RuntimeException("Context classLoader is null");
            }

            clazz = (Class<? extends Servlet>) classLoader.loadClass(servletClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load servlet class: " + servletClass, e);
        }

        Servlet servlet;
        try {
            servlet = clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Failed to get an instance of a class: " + servletClass, e);
        }

        return servlet;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Filter createFilter(ApplicationContext context, String filterClass) {
        Class<? extends Filter> clazz;

        try {
            ClassLoader classLoader = context.getClassLoader();
            if (classLoader == null) {
                throw new RuntimeException("Context classLoader is null");
            }

            clazz = (Class<? extends Filter>) classLoader.loadClass(filterClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load filter class: " + filterClass, e);
        }

        Filter servlet;
        try {
            servlet = clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Failed to get an instance of a class: " + filterClass, e);
        }

        return servlet;
    }
}
