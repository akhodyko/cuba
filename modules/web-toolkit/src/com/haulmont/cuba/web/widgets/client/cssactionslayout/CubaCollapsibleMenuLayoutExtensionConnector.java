/*
 * Copyright (c) 2008-2020 Haulmont.
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

package com.haulmont.cuba.web.widgets.client.cssactionslayout;

import com.google.gwt.core.client.Scheduler;
import com.haulmont.cuba.web.widgets.CubaCollapsibleMenuLayoutExtension;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.LayoutManager;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

@Connect(CubaCollapsibleMenuLayoutExtension.class)
public class CubaCollapsibleMenuLayoutExtensionConnector extends AbstractExtensionConnector {

    @Override
    protected void extend(ServerConnector target) {
        ComponentConnector connector = ((ComponentConnector) target);
        LayoutManager layoutManager = connector.getLayoutManager();

        Scheduler.get().scheduleFinally(() -> layoutManager.addElementResizeListener(
                connector.getWidget().getElement(),
                e -> layoutManager.setNeedsMeasureRecursively(connector)));
    }
}
