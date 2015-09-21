/*
 * Copyright 2013 ArcBees Inc.
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

package com.gwtplatform.carstore.client.application.cars;

import java.util.List;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.gwtplatform.carstore.client.application.cars.CarsPresenter.MyView;
import com.gwtplatform.carstore.client.application.cars.renderer.CarCell;
import com.gwtplatform.carstore.client.application.ui.ShowMorePagerPanel;
import com.gwtplatform.carstore.client.resources.MobileDataListStyle;
import com.gwtplatform.carstore.shared.dto.CarDto;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class CarsMobileView extends ViewWithUiHandlers<CarsUiHandlers> implements MyView {
    interface Binder extends UiBinder<Widget, CarsMobileView> {
    }

    private static final int PAGE_SIZE = 20;

    @UiField(provided = true)
    ShowMorePagerPanel pagerPanel;

    private CellList<CarDto> carList;
    private AsyncDataProvider<CarDto> asyncDataProvider;
    private SingleSelectionModel<CarDto> selectionModel;

    @Inject
    CarsMobileView(
            Binder uiBinder,
            CarCell carCell,
            MobileDataListStyle mobileDataListStyle) {
        pagerPanel = new ShowMorePagerPanel(PAGE_SIZE);
        carList = new CellList<>(carCell, mobileDataListStyle);
        selectionModel = new SingleSelectionModel<>();

        initWidget(uiBinder.createAndBindUi(this));

        pagerPanel.setDisplay(carList);
        carList.setSelectionModel(selectionModel);

        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                getUiHandlers().onEdit(selectionModel.getSelectedObject());
            }
        });
    }

    @Override
    public void initDataProvider() {
        asyncDataProvider = new AsyncDataProvider<CarDto>() {
            @Override
            protected void onRangeChanged(HasData<CarDto> display) {
                Range range = display.getVisibleRange();
                getUiHandlers().fetchData(range.getStart(), range.getLength());
            }
        };

        asyncDataProvider.addDataDisplay(carList);
    }

    @Override
    public HasData<CarDto> getCarDisplay() {
        return carList;
    }

    @Override
    public void setCarsCount(Integer result) {
        asyncDataProvider.updateRowCount(result, true);
    }

    @Override
    public void displayCars(int offset, List<CarDto> carDtos) {
        asyncDataProvider.updateRowData(offset, carDtos);
    }
}
