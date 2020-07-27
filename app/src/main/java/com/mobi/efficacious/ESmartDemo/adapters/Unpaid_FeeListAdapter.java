package com.mobi.efficacious.ESmartDemo.adapters;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobi.efficacious.ESmartDemo.R;
import com.mobi.efficacious.ESmartDemo.entity.UnPaidFeeListResponse;

import java.util.List;

/**
 * Created by Rahul on 13,July,2020
 */
public class Unpaid_FeeListAdapter extends RecyclerView.Adapter<Unpaid_FeeListAdapter.ViewHolder> {
    Context context;
    public List<UnPaidFeeListResponse.MonthWiseFee> mStudentDataList;
    LayoutInflater inflter;
    FeeDataClick feeDataClick;
    private int row_index = -1;
    String strRupee="₹";
    public Unpaid_FeeListAdapter(Context context, List<UnPaidFeeListResponse.MonthWiseFee> StudentDataList) {
        this.context = context;
        this.mStudentDataList = StudentDataList;
    }

    public void setOnClickListener(FeeDataClick feeDataClick) {
        this.feeDataClick = feeDataClick;
    }
    public interface FeeDataClick {
        void onFeeClick(int position, String amount, boolean isChecked);
        void onFeeClick(int position, String month);
    }
    @NonNull
    @Override
    public Unpaid_FeeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fee_adapter, parent, false);
        return new Unpaid_FeeListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Unpaid_FeeListAdapter.ViewHolder viewHolde, final int position) {
        Handler mainHandler = new Handler(context.getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {

                final UnPaidFeeListResponse.MonthWiseFee objIncome = mStudentDataList.get(position);

                viewHolde.checkBox.setOnCheckedChangeListener(null);

                //if true, your checkbox will be selected, else unselected
                viewHolde.checkBox.setChecked(objIncome.isSelected());

                viewHolde.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        objIncome.setSelected(isChecked);
                            if(feeDataClick!=null){
                                feeDataClick.onFeeClick(position,objIncome.getSum(),isChecked);
                            }
                    }
                });
                viewHolde.fee_detail_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(feeDataClick!=null){
                            feeDataClick.onFeeClick(position,objIncome.getMonth());
                        }
                    }
                });
                viewHolde.monthtv.setText(mStudentDataList.get(position).getMonth());
                viewHolde.amount.setText(strRupee+mStudentDataList.get(position).getSum());




            }
        };
        mainHandler.post(myRunnable);
    }

    @Override
    public int getItemCount() {
        return mStudentDataList.size();
        //return 200;
    }
    public List<UnPaidFeeListResponse.MonthWiseFee> getFeeList() {
        return mStudentDataList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView monthtv,amount;
        ImageView fee_detail_img;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            monthtv=(TextView)itemView.findViewById(R.id.monthtv);
            amount=(TextView)itemView.findViewById(R.id.amount);
            checkBox  = (CheckBox) itemView.findViewById(R.id.checkBox);
            fee_detail_img  = (ImageView) itemView.findViewById(R.id.fee_detail_img);


        }
    }


}
