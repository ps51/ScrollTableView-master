package com.loopeer.android.librarys.horizontalverticalscrollview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.loopeer.android.librarys.horizontalverticalscrollview.R;
import com.loopeer.android.librarys.horizontalverticalscrollview.bean.PriceOrderBean;
import com.loopeer.android.librarys.horizontalverticalscrollview.utils.AnimationUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 三星（百位）
 * Created by laiyingtang on 2016/9/1.
 */
public class ThirdStartsAdapter extends BaseAdapter {
    private ArrayList<PriceOrderBean> priceList;
    private Context context;
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;
    public final static int type = 3;//标识是这个adapter

    public ThirdStartsAdapter(ArrayList<PriceOrderBean> priceList, Context context) {
        this.priceList = priceList;
        this.context = context;
        isSelected = new HashMap<Integer, Boolean>();
        initDate();
    }

    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < priceList.size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    @Override
    public int getCount() {
        if (priceList == null || priceList.size() == 0) {
            return 0;
        }
        return priceList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.btn_number = (CheckBox) convertView.findViewById(R.id.btn_number);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.btn_number.setText(priceList.get(position).getNumber());
        viewHolder.btn_number.setChecked(getIsSelected().get(position));

        //item的点击
        viewHolder.btn_number.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    viewHolder.btn_number.setAnimation(AnimationUtils.getAmplificationAnimation(200));
                    viewHolder.btn_number.setBackgroundResource(R.drawable.solid_circular);
                    viewHolder.btn_number.setTextColor(Color.WHITE);
                    viewHolder.btn_number.getPaint().setFakeBoldText(true);
                } else {
                    viewHolder.btn_number.setAnimation(AnimationUtils.getLessenScaleAnimation(200));
                    viewHolder.btn_number.setBackgroundResource(R.drawable.btn_circular);
                    viewHolder.btn_number.setTextColor(context.getResources().getColor(R.color.girdview_item));
                    viewHolder.btn_number.getPaint().setFakeBoldText(false);
                }
            }
        });
        return convertView;
    }


    public class ViewHolder {
        CheckBox btn_number;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        ThirdStartsAdapter.isSelected = isSelected;
    }
}
