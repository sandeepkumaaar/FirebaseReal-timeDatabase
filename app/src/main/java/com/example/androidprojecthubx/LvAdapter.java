package com.example.androidprojecthubx;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class LvAdapter extends ArrayAdapter<DataModel> {

    private Activity context;
    private List<DataModel> dataModelList;

    LvAdapter(Activity context, List<DataModel> dataModelList){
        super(context,R.layout.lv_card,dataModelList);
        this.context = context;
        this.dataModelList = dataModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.lv_card, null, true);

        TextView tv_id = listViewItem.findViewById(R.id.tv_id);
        TextView tv_name = listViewItem.findViewById(R.id.tv_userName);
        TextView tv_email = listViewItem.findViewById(R.id.tv_userEmail);
        TextView tv_mobileNo = listViewItem.findViewById(R.id.tv_userMobileNumber);
        TextView tv_noOfpeople = listViewItem.findViewById(R.id.tv_noOfPeople);
        TextView tv_address = listViewItem.findViewById(R.id.tv_address);
        TextView tv_eventCategory = listViewItem.findViewById(R.id.tv_eventCategory);

        DataModel dataModel = dataModelList.get(position);

        tv_id.setText("ID : " + dataModel.getId());
        tv_name.setText("Name : " + dataModel.getName());
        tv_email.setText("Email id : " + dataModel.getEmail_id());
        tv_mobileNo.setText("Mobile Number : " + dataModel.getMobile_number());
        tv_noOfpeople.setText("No. Of People : " + dataModel.getNumber_of_People());
        tv_address.setText("Address : " + dataModel.getAddress());
        tv_eventCategory.setText("Event Category : " + dataModel.getEvent_Category());

        return listViewItem;
    }
}
