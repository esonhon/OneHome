package com.dream.onehome.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.dream.onehome.R;
import com.dream.onehome.adapter.SelectDeviceTypeAdapter;
import com.dream.onehome.base.BaseMVVMActivity;
import com.dream.onehome.bean.DeviceTypeBean;
import com.dream.onehome.constract.IResultLisrener;
import com.dream.onehome.databinding.ActivitySelectDevicetypeBinding;
import com.dream.onehome.ui.ViewModel.SelectDeviceTypeModel;
import com.dream.onehome.utils.annotations.ContentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time:2019/12/21
 * Author:TiaoZi
 */
@ContentView(R.layout.activity_select_devicetype)
public class SelectDeviceTypeActivity extends BaseMVVMActivity<SelectDeviceTypeModel, ActivitySelectDevicetypeBinding> {


    private SelectDeviceTypeAdapter mTypeAdapter;

    private List<DeviceTypeBean> dataList = new ArrayList<>();



    @Override
    protected void initIntent() {

        Intent intent = getIntent();

        mTypeAdapter = new SelectDeviceTypeAdapter(SelectDeviceTypeActivity.this,dataList,R.layout.gvitem_devicetype);
        bindingView.typeGv.setAdapter(mTypeAdapter);

    }

    @Override
    protected void onEvent() {

        bindingView.typeGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeviceTypeBean deviceTypeBean = dataList.get(position);
                int device_id = deviceTypeBean.getId();

                Intent intent = new Intent(SelectDeviceTypeActivity.this,BrandActivity.class);
                intent.putExtra("device_id",device_id);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        viewModel.getDeviceTypes(new IResultLisrener<List<DeviceTypeBean>>() {
            @Override
            public void onResults(List<DeviceTypeBean> data) {
                dataList.clear();
                dataList.addAll(data);
                mTypeAdapter.notifyDataSetChanged();
            }
        });
    }
}
