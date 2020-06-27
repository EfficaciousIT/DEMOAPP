package com.mobi.efficacious.ESmartDemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobi.efficacious.ESmartDemo.R;
import com.mobi.efficacious.ESmartDemo.entity.StandardDetail;

import java.util.ArrayList;

/**
 * Created by Rahul on 24,May,2020
 */
public class StandardAdapter_Lib extends BaseAdapter {
    private final Context context;
    ArrayList<StandardDetail> menus = new ArrayList<StandardDetail>();
    ImageHolder holder = null;
    OptionDataClick optionDataClick;
    public StandardAdapter_Lib(Context context, ArrayList<StandardDetail> Menus) {
        super();
        this.context = context;
        this.menus = Menus;
    }
    public interface OptionDataClick {
        void onOptionDataClick(int Position);
    }
    public void setOnClickListener(OptionDataClick optionDataClick) {
        this.optionDataClick = optionDataClick;
    }
    static class ImageHolder
    {
        TextView textview1;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        try {
            if(row == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.spinner_item, parent, false);
                holder = new ImageHolder();
                row.setTag(holder);
            }
            else
            {
                holder = (ImageHolder)row.getTag();
            }
            holder.textview1=row.findViewById(R.id.textview1);
            holder.textview1.setText(menus.get(position).getVchStandardName());
            holder.textview1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(optionDataClick!=null)
                    optionDataClick.onOptionDataClick(menus.get(position).getIntStandardId());
                }
            });
        }catch (Exception ex)
        {

        }

        return row;
    }



}
