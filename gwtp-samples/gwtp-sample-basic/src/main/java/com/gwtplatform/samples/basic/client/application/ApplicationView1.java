/*
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtplatform.samples.basic.client.application;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ApplicationView1 extends ViewWithUiHandlers<ApplicationUiHandlers> implements ApplicationPresenter.MyView {
    @Override
    public void resetAndFocus() {

    }

    @Override
    public void setError(String errorText) {

    }

    interface Binder extends UiBinder<Widget, ApplicationView1> {
    }

//    @UiField
//    TextBox nameField;
//    @UiField
//    Button sendButton;
//    @UiField
//    HTML error;
//
//    @Inject
//    ApplicationView1(
//            Binder uiBinder) {
//        initWidget(uiBinder.createAndBindUi(this));
//    }
//
//    @Override
//    public void resetAndFocus() {
//        nameField.setFocus(true);
//        nameField.selectAll();
//    }
//
//    @Override
//    public void setError(String errorText) {
//        error.setHTML(errorText);
//    }
//
//    @UiHandler("sendButton")
//    void onSend(ClickEvent event) {
//        getUiHandlers().sendName(nameField.getText());


}
