package com.zthl.nxp.ui.history;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chen.nxp.R;
import com.zthl.nxp.MyApplication;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class HistoryItemListViewAdapter extends BaseAdapter   implements View.OnClickListener {
    private List<TurringList> list = null;
    private Context context = null;
    private int clickPosition = -1;//记录用户点击了的item

    public Boolean flag = false; //标识下拉view的显示状态
    public  boolean history;

    public HistoryItemListViewAdapter(Context context, List<TurringList> list, boolean history) {
        this.context = context;
        this.list = list;
        this.history = history;
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
        final MyViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.history_list_item, null);
            vh = new MyViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (MyViewHolder) convertView.getTag();

            if (!history)
                if (list.get(position).getMessageType() != null)
                    if (list.get(position).getMessageType().equals("INPUT")) {

                        //     vh.listtiem.setBackgroundColor(context.getResources().getColor(R.color.title_green));
                        //      gdOne.setColor(context.getResources().getColor(R.color.title_green));
                        vh.listtiem.setBackground(context.getResources().getDrawable(R.drawable.shape_corner_white));

                    }
        }
        //.     Log.d("color23333",list.get(position).getMessageType());

        //     Log.d("8888",list.get(0).getCreatedDateTime());


        vh.historyMachineNumber.setText(list.get(position).getMachineNumber());
        vh.turringCurrentStatus.setText(list.get(position).getTurningStateName());
        vh.turringName.setText(StringUtil.trimString(list.get(position).getTargetProgram()));
        vh.operator.setText(list.get(position).getOperator());
        vh.turningEndDateTime.setText(list.get(position).getTurningEndDateTime());
        vh.machineGroup.setText(list.get(position).getGrouping());
        vh.machineNumber.setText(list.get(position).getMachineNumber());
        vh.transferMan.setText(MyApplication.getPkId());
        vh.transferFinishMan.setText(list.get(position).getFounderRealName());
        vh.currentProgramName.setText(list.get(position).getCurrentName());
        vh.historyQuestionDateTime.setText(list.get(position).getQuestionDateTime());
        vh.historyQuestionDateTimeMain.setText(list.get(position).getQuestionDateTime());
        vh.turningStartDateTime.setText(list.get(position).getTurningStartDateTime());
        vh.questionDateTime.setText(list.get(position).getQuestionDateTime());
        vh.founderRealName.setText(list.get(position).getFounderRealName());
        vh.targetProgram.setText(list.get(position).getTargetProgram());
        vh.historySourceName.setText(StringUtil.trimString(list.get(position).getCurrentName()));
        vh.historyTransferMan.setText(list.get(position).getFounderRealName());


        vh.cancelDateTime.setText(list.get(position).getCancelDateTime());
        vh.errorStartDateTime.setText(list.get(position).getErrorStartDateTime());
        vh.ErrorEndDateTime.setText(list.get(position).getErrorEndDateTime());
        vh.turningEndDateTime.setText(list.get(position).getTurningEndDateTime());
        vh.turningStartDateTime.setText(list.get(position).getTurningStartDateTime());




        if (!history)
                if (list.get(position).getMessageType() == null) {
                    //     vh.listtiem.setBackgroundColor(context.getResources().getColor(R.color.title_green));
                    vh.listtiem.setBackground(context.getResources().getDrawable(R.drawable.shape_corner_green));
                    //      gdOne.setColor(context.getResources().getColor(R.color.title_green));

                } else if (list.get(position).getMessageType().equals("OUTPUT"))
                {
                    vh.listtiem.setBackground(context.getResources().getDrawable(R.drawable.shape_corner_green));
                }


        //   vh.tv_test.setText("2333333");
        //判断用户是不是点击了同一个item
        if (clickPosition == position) {

            //根据flage来处理下拉view是该消失 还是该展开状态
            if (flag) {
                vh.ll_hide.setVisibility(View.GONE);
                flag = false;
            } else {
                vh.ll_hide.setVisibility(View.VISIBLE);
                flag = true;
            }


        } else {
            //当填充的条目position不是刚才点击所标记的position时，让其隐藏，状态图标为false。
            vh.ll_hide.setVisibility(View.GONE);
            Log.e("listview", "状态改变");
        }

        //     vh.hide_5.setOnClickListener(this);
        vh.listtiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "被点了", Toast.LENGTH_SHORT).show();
                clickPosition = position;//记录点击的position
                notifyDataSetChanged();//刷新adapter重新填充条目。在重新填充的过程中，被记录的position会做展开或隐藏的动作，具体的判断看上面代码
                //在此处需要明确的一点是，当adapter执行刷新操作时，整个getview方法会重新执行，也就是条目重新做一次初始化被填充数据。
            }
        });
//        vh.selectorImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (vh.selectorImg.isSelected()) {
//                    vh.selectorImg.setSelected(false);
//                    //取消选中时的业务代码
//                    Log.e("but", "onClick: 没选中状态");
//                } else {
//                    vh.selectorImg.setSelected(true);
//                    //做当被选中时的业务代码
//                    Log.e("but", "onClick: 选中状态");
//                }
//            }
//        });
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

}
    class MyViewHolder {
        View itemView;
        TextView tv_test;
        TextView historyTransferMan,historySourceName,historyMachineNumber,turringName,historyQuestionDateTimeMain,turringCurrentStatus ,historyQuestionDateTime,operator,turningEndDateTime,turningStartDateTime,questionDateTime,targetProgram,machineGroup,machineNumber,transferMan,transferFinishMan,currentProgramName,founderRealName;
        TextView cancelDateTime,errorStartDateTime,ErrorEndDateTime;
        ImageView selectorImg;
        LinearLayout ll_hide;
        RelativeLayout listtiem;

        public MyViewHolder(View itemView) {
            this.itemView = itemView;
            tv_test = (TextView) itemView.findViewById(R.id.tv_test);
            ll_hide = (LinearLayout) itemView.findViewById(R.id.history_ll_hide);
            listtiem = (RelativeLayout) itemView.findViewById(R.id.history_list_item);


            turringName=itemView.findViewById(R.id.history_target_name);
           turringCurrentStatus=itemView.findViewById(R.id.history_transfer_status);
            historySourceName=itemView.findViewById(R.id.history_source_name);
            historyQuestionDateTime=itemView.findViewById(R.id.history_question_date_time);
            operator=ll_hide.findViewById(R.id.history_operator);
       //     turningEndDateTime=ll_hide.findViewById(R.id.history_turning_end_dateTime);
            machineGroup=ll_hide.findViewById(R.id.history_machine_group);
            machineNumber=ll_hide.findViewById(R.id.history_machine_number);
            transferMan=ll_hide.findViewById(R.id.history_founder_name);
            transferFinishMan=ll_hide.findViewById(R.id.history_founder_name);
            currentProgramName=ll_hide.findViewById(R.id.history_current_program_name);
            turningStartDateTime=ll_hide.findViewById(R.id.history_turning_start_dateTime);
            questionDateTime=ll_hide.findViewById(R.id.history_question_date_time);
            founderRealName=ll_hide.findViewById(R.id.history_founder_name);
            targetProgram=ll_hide.findViewById(R.id.history_target_program);
            historyMachineNumber=itemView.findViewById(R.id.history_machine_number);

            historyQuestionDateTimeMain =itemView.findViewById(R.id.history_question_dateTime_main);


            historyTransferMan=itemView.findViewById(R.id.history_transfer_man);





            cancelDateTime=ll_hide.findViewById(R.id.cancel_date_time);
            errorStartDateTime=ll_hide.findViewById(R.id.error_start_date_time);
            ErrorEndDateTime=ll_hide.findViewById(R.id.error_end_date_time);



        }
    }

