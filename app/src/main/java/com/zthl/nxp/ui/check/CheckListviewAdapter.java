package com.zthl.nxp.ui.check;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.chen.nxp.R;
import com.zthl.nxp.MyApplication;
import com.zthl.nxp.ProblemFeedbackFragment;
import com.zthl.nxp.model.InvoiceList;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.ui.transfer.TransferEndFragment;
import com.zthl.nxp.ui.transfer.TransferStartFragment;

import java.util.List;

public class CheckListviewAdapter extends BaseAdapter  {
    private List<InvoiceList> list = null;
    private Context context = null;
    private int clickPosition = -1;//记录用户点击了的item
    private androidx.fragment.app.FragmentManager fm;
    public Boolean flag = false; //标识下拉view的显示状态


    public CheckListviewAdapter(Context context, List<InvoiceList> list, FragmentManager fm){
        this.context = context;
        this.list = list;
        this.fm=fm;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final CheckListviewAdapter.MyViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.invoices_list_item, null);
            vh = new CheckListviewAdapter.MyViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (CheckListviewAdapter.MyViewHolder) convertView.getTag();
        }
//        vh.turringCurrentStatus.setText(list.get(position).getTurningStateName());
//        vh.turringName.setText(list.get(position).getCurrentName());
//        vh.operator.setText(list.get(position).getOperator());
//        vh.bilingTime.setText(list.get(position).getBillingTime());
//        vh.createDateTime.setText(list.get(position).getCreatedDateTime());
//        vh.machineGroup.setText(list.get(position).getGrouping());
//        vh.machineNumber.setText(list.get(position).getMachineNumber());
//        vh.transferMan.setText(MyApplication.getPkId());
//        vh.transferFinishMan.setText(list.get(position).getFounder());
  //      vh.currentProgramName.setText(list.get(position).getCurrentName());
        vh.invoicesNumber.setText(list.get(position).getMachineNumber());
        vh.invoicesName.setText(list.get(position).getCurrentName());
        vh.invoicesStatus.setText(list.get(position).getInvoicesStateName());
        vh.invoiceCurrentName.setText(list.get(position).getCurrentName());
        vh.invoicesOperator.setText(list.get(position).getOperator());
        vh.invoicesTime.setText(list.get(position).getInvoicesTimeStart());
        vh.founderRealName.setText(list.get(position).getFounderRealName());
        vh.invoicesTitleMachineNumber.setText(list.get(position).getMachineNumber());
        vh.invoicesMachineGroup.setText(list.get(position).getGrouping());
        //   vh.tv_test.setText("2333333");
        //判断用户是不是点击了同一个item
        if (clickPosition == position) {

            //根据flage来处理下拉view是该消失 还是该展开状态
            if(flag){
                vh.ll_hide.setVisibility(View.GONE);
                flag = false;
            }else {
                vh.ll_hide.setVisibility(View.VISIBLE);
                flag = true;
            }


        } else {
            //当填充的条目position不是刚才点击所标记的position时，让其隐藏，状态图标为false。
            vh.ll_hide.setVisibility(View.GONE);
            Log.e("listview","状态改变");
        }
//        vh.check_start.setOnClickListener(this);
//        vh.check_end.setOnClickListener(this);
//        vh.check_error.setOnClickListener(this);
        vh.invoicesFinish.setOnClickListener(new View.OnClickListener() {    //开票结束点击事件
            @Override
            public void onClick(View view) {


                FragmentTransaction ft = fm.beginTransaction();
                MyApplication.logId=list.get(position).getPkId();
                MyApplication.setMachineNumber(list.get(position).getMachineNumber());
                MyApplication.setProgram(list.get(position).getCurrentName());
                ft.replace(R.id.container, CheckEndFragment.newInstance());
    //           ft.addToBackStack("UserTag");
                ft.commit();
            }
        });
        vh.invoicesCancel.setOnClickListener(new View.OnClickListener() {//开票取消点击事件
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                MyApplication.logId=list.get(position).getPkId();
                MyApplication.setMachineNumber(list.get(position).getMachineNumber());
                MyApplication.setProgram(list.get(position).getCurrentName());
                ft.replace(R.id.container, CheckCancelFragment.newInstance());
   //             ft.addToBackStack("UserTag");
                ft.commit();
            }
        });


        //
        vh.listtiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "被点了", Toast.LENGTH_SHORT).show();
                clickPosition = position;//记录点击的position
                notifyDataSetChanged();//刷新adapter重新填充条目。在重新填充的过程中，被记录的position会做展开或隐藏的动作，具体的判断看上面代码
                //在此处需要明确的一点是，当adapter执行刷新操作时，整个getview方法会重新执行，也就是条目重新做一次初始化被填充数据。
            }
        });

        return convertView;
    }

//    @Override
//    public void onClick(View v) {
//        FragmentTransaction ft = fm.beginTransaction();
//        switch (v.getId()) {
//            case R.id.invoices_finish:
//                MyApplication.pkId=list.get(clickPosition)
//                ft.replace(R.id.container, CheckEndFragment.newInstance());
//                ft.addToBackStack("UserTag");
//                ft.commit();
//                break;
//            case R.id.invoices_cancel:
//                ft.replace(R.id.container, CheckCancelFragment.newInstance());
//                ft.addToBackStack("UserTag");
//                ft.commit();
//                break;
//
//        }
//    }


    class MyViewHolder {
        View itemView;
        TextView tv_test;
        TextView invoicesNumber, invoicesName,invoiceCurrentName,invoicesOperator,invoicesTime,founderRealName,invoicesTitleMachineNumber,invoicesMachineGroup,turringName, turringCurrentStatus ,operator,bilingTime,createDateTime,machineGroup,machineNumber,transferMan,transferFinishMan,currentProgramName,invoicesStatus;
        Button invoicesCancel,invoicesFinish,check_error;
        LinearLayout ll_hide;
        RelativeLayout listtiem;

        public MyViewHolder(View itemView) {
            this.itemView = itemView;
            tv_test = (TextView) itemView.findViewById(R.id.tv_test);
            //           selectorImg = (ImageView) itemView.findViewById(R.id.checkbox);
            ll_hide = (LinearLayout) itemView.findViewById(R.id.invoices_list_ll_hide);
            listtiem = (RelativeLayout) itemView.findViewById(R.id.invoices_list_item);

            invoicesTitleMachineNumber= itemView.findViewById(R.id.invoices_title_machine_number);



//            check_start=itemView.findViewById(R.id.check_start);
//            check_end=itemView.findViewById(R.id.check_end);
//            check_error=itemView.findViewById(R.id.check_error);
//            turringName=itemView.findViewById(R.id.turring_name);
//            turringCurrentStatus=itemView.findViewById(R.id.turring_status);
//            operator=ll_hide.findViewById(R.id.operator);
//            bilingTime=ll_hide.findViewById(R.id.billing_time);
//            createDateTime=ll_hide.findViewById(R.id.created_date_time);
//            machineGroup=ll_hide.findViewById(R.id.machine_group);
//            machineNumber=ll_hide.findViewById(R.id.machine_number);
//            transferMan=ll_hide.findViewById(R.id.transfer_man);
//            transferFinishMan=ll_hide.findViewById(R.id.transfer_finish_man);
//            currentProgramName=ll_hide.findViewById(R.id.current_program_name);




            invoicesName=itemView.findViewById(R.id.invoices_name);
            invoicesName=itemView.findViewById(R.id.invoices_name);
            invoicesStatus=itemView.findViewById(R.id.invoices_status);
            invoicesCancel=ll_hide.findViewById(R.id.invoices_cancel);
            invoicesFinish=ll_hide.findViewById(R.id.invoices_finish);

            founderRealName=ll_hide.findViewById(R.id.founder_real_name);
            invoicesNumber=ll_hide.findViewById(R.id.invoices_machine_number);
            invoiceCurrentName=ll_hide.findViewById(R.id.invoices_program);
            invoicesOperator=ll_hide.findViewById(R.id.invoices_operator);
            invoicesTime=ll_hide.findViewById(R.id.invoices_time);
            invoicesMachineGroup=ll_hide.findViewById(R.id.invoices_machine_group);
        }
    }
}
