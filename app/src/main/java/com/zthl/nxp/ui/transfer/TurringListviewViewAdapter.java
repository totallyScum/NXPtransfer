package com.zthl.nxp.ui.transfer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.zthl.nxp.MyApplication;
import com.zthl.nxp.ProblemFeedbackFragment;
import com.chen.nxp.R;
import com.zthl.nxp.dummy.DummyContent.DummyItem;
import com.zthl.nxp.model.TurringList;
import com.zthl.nxp.ui.mission.MissionFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
class TurringListviewViewAdapter extends BaseAdapter implements View.OnClickListener{
    private List<TurringList> list = null;
    private Context context = null;
    private int clickPosition = -1;//记录用户点击了的item
    private androidx.fragment.app.FragmentManager fm;
    public Boolean flag = false; //标识下拉view的显示状态


    public TurringListviewViewAdapter(Context context, List<TurringList> list, FragmentManager fm){
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




    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {




            final MyViewHolder vh;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.turring_list_item, null);
                vh = new MyViewHolder(convertView);
                convertView.setTag(vh);
            } else {
                vh = (MyViewHolder) convertView.getTag();
            }

//        if (list.get(position).getTransitSituation().equals("1")) {
//            convertView.setVisibility(View.INVISIBLE);
//        }
            if (list.get(position).getMessageType().equals("INPUT")||list.get(position).getMessageType().equals(""))
            {

           //     vh.listtiem.setBackgroundColor(context.getResources().getColor(R.color.title_green));
                vh.listtiem.setBackground(context.getResources().getDrawable(R.drawable.shape_corner_green));
          //      gdOne.setColor(context.getResources().getColor(R.color.title_green));

            }
          //  vh.backGroundColor.setBackgroundColor(R.color.title_green);


            vh.turringCurrentStatus.setText(list.get(position).getTurningStateName());
            vh.turingQuestionDateTimeMain.setText(list.get(position).getQuestionDateTime());


        vh.turningMachineNumber.setText(list.get(position).getMachineNumber());


            vh.operator.setText(list.get(position).getOperator());
            vh.turningEndDateTime.setText(list.get(position).getTurningEndDateTime());
            vh.machineGroup.setText(list.get(position).getGrouping());
            vh.machineNumber.setText(list.get(position).getMachineNumber());
            vh.transferMan.setText(MyApplication.getPkId());
            vh.transferFinishMan.setText(list.get(position).getFounderRealName());
            vh.currentProgramName.setText(list.get(position).getCurrentName());


            vh.turningStartDateTime.setText(list.get(position).getTurningStartDateTime());
            vh.questionDateTime.setText(list.get(position).getQuestionDateTime());
            vh.founderRealName.setText(list.get(position).getFounderRealName());
            vh.targetProgram.setText(list.get(position).getTargetProgram());



           vh.turingTargeNameMain.setText(list.get(position).getTargetProgram());
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

            if (Integer.parseInt(list.get(position).getTurningState())!=0)
            {
                int i=Integer.parseInt(list.get(position).getTurningState());
                switch (i){
                    case 2:
                        vh.transferStart.setVisibility(View.VISIBLE);
                        vh.transferEnd.setVisibility(View.INVISIBLE);
                        vh.transferError.setVisibility(View.INVISIBLE);
                        vh.transferResume.setVisibility(View.INVISIBLE);
                        vh.transferCancel.setVisibility(View.VISIBLE);
                        break;
                    case 3: {
                        vh.transferStart.setVisibility(View.INVISIBLE);
                        vh.transferEnd.setVisibility(View.VISIBLE);
                        vh.transferError.setVisibility(View.VISIBLE);
                        vh.transferResume.setVisibility(View.INVISIBLE);
                        vh.transferCancel.setVisibility(View.VISIBLE);

                        break;
                    }
                    case 5:{
                        vh.transferStart.setVisibility(View.INVISIBLE);
                        vh.transferEnd.setVisibility(View.INVISIBLE);
                        vh.transferError.setVisibility(View.INVISIBLE);
                        vh.transferResume.setVisibility(View.VISIBLE);
                        vh.transferCancel.setVisibility(View.VISIBLE);
                        break;
                    }

                    }
                }


            vh.transferEnd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyApplication.setTurningState("4");
                    MyApplication.setMachineNumber(list.get(position).getMachineNumber());
                    MyApplication.setTargetProgram(list.get(position).getTargetProgram());
                    MyApplication.setSourceProgram(list.get(position).getCurrentName());
                    FragmentTransaction ft = fm.beginTransaction();
                    MyApplication.setLogId(list.get(position).getpKId());
                    ft.replace(R.id.container, TransferStartFragment.newInstance());
                    ft.addToBackStack("UserTag");
                    ft.commit();
                }
            });

            vh.transferStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyApplication.setTurningState("3");
                    MyApplication.setMachineNumber(list.get(position).getMachineNumber());
                    MyApplication.setTargetProgram(list.get(position).getTargetProgram());
                    MyApplication.setSourceProgram(list.get(position).getCurrentName());
                    FragmentTransaction ft = fm.beginTransaction();
                    MyApplication.setLogId(list.get(position).getpKId());
                    ft.replace(R.id.container, TransferStartFragment.newInstance());
                    ft.addToBackStack("UserTag");
                    ft.commit();
                }
            });
            vh.transferResume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyApplication.setTurningState("6");
                    MyApplication.setMachineNumber(list.get(position).getMachineNumber());
                    MyApplication.setTargetProgram(list.get(position).getTargetProgram());
                    MyApplication.setSourceProgram(list.get(position).getCurrentName());
                    FragmentTransaction ft = fm.beginTransaction();
                    MyApplication.setLogId(list.get(position).getpKId());
                    ft.replace(R.id.container, TransferStartFragment.newInstance());
                    ft.addToBackStack("UserTag");
                    ft.commit();
                }
            });

            vh.transferError.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyApplication.setTurningState("5");
                    MyApplication.setMachineNumber(list.get(position).getMachineNumber());
                    MyApplication.setTargetProgram(list.get(position).getTargetProgram());
                    MyApplication.setSourceProgram(list.get(position).getCurrentName());
                    FragmentTransaction ft = fm.beginTransaction();
                    MyApplication.setLogId(list.get(position).getpKId());
                    ft.replace(R.id.container, TransferStartFragment.newInstance());
                    ft.addToBackStack("UserTag");
                    ft.commit();
                }
            });
            vh.transferCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyApplication.setTurningState("7");
                    MyApplication.setMachineNumber(list.get(position).getMachineNumber());
                    MyApplication.setTargetProgram(list.get(position).getTargetProgram());
                    MyApplication.setSourceProgram(list.get(position).getCurrentName());
                    FragmentTransaction ft = fm.beginTransaction();
                    MyApplication.setLogId(list.get(position).getpKId());
                    ft.replace(R.id.container, TransferStartFragment.newInstance());
                    ft.addToBackStack("UserTag");
                    ft.commit();
                }
            });


//            vh.check_end.setOnClickListener(this);
//            vh.check_error.setOnClickListener(this);


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


    @Override
    public void onClick(View v) {
        FragmentTransaction ft = fm.beginTransaction();
//        switch (v.getId()) {
//            case R.id.check_start:
//                ft.replace(R.id.container, TransferStartFragment.newInstance());
//                ft.addToBackStack("UserTag");
//                ft.commit();
//                break;
//            case R.id.check_end:
//                ft.replace(R.id.container, TransferEndFragment.newInstance());
//                ft.addToBackStack("UserTag");
//                ft.commit();
//                break;
//            case R.id.check_error:
//                ft.replace(R.id.container, ProblemFeedbackFragment.newInstance());
//                ft.addToBackStack("UserTag");
//                ft.commit();
//                break;
        }
    }


    class MyViewHolder {
        View itemView;
        TextView turningMachineNumber, turringCurrentStatus,turingTargeNameMain,turingQuestionDateTimeMain,operator,turningEndDateTime,turningStartDateTime,questionDateTime,targetProgram,machineGroup,machineNumber,transferMan,transferFinishMan,currentProgramName,founderRealName;
        Button transferStart,transferEnd,transferError,transferResume,transferCancel;
        LinearLayout ll_hide,backGroundColor;
        RelativeLayout listtiem;

        public MyViewHolder(View itemView) {
            this.itemView = itemView;
            ll_hide = (LinearLayout) itemView.findViewById(R.id.turning_published_ll_hide);
            listtiem = (RelativeLayout) itemView.findViewById(R.id.turning_list_item);
            backGroundColor=itemView.findViewById(R.id.backGroundColor);



            transferStart=itemView.findViewById(R.id.transfer_start);
            transferEnd=itemView.findViewById(R.id.transfer_end);
            transferError=itemView.findViewById(R.id.transfer_error);
            transferResume=itemView.findViewById(R.id.transfer_resume);
            transferCancel=itemView.findViewById(R.id.transfer_cancel);




            turningMachineNumber=itemView.findViewById(R.id.turing_machine_number);
            turringCurrentStatus=itemView.findViewById(R.id.turing_transfer_status);
            turingQuestionDateTimeMain=itemView.findViewById(R.id.turing_question_dateTime_main);
            turingTargeNameMain=itemView.findViewById(R.id.turing_target_name_main);



            operator=ll_hide.findViewById(R.id.operator);
            turningEndDateTime=ll_hide.findViewById(R.id.turning_end_dateTime);
            machineGroup=ll_hide.findViewById(R.id.machine_group);
            machineNumber=ll_hide.findViewById(R.id.machine_number);
            transferMan=ll_hide.findViewById(R.id.transfer_man);
            transferFinishMan=ll_hide.findViewById(R.id.founder_real_name);
            currentProgramName=ll_hide.findViewById(R.id.current_program_name);
            turningStartDateTime=ll_hide.findViewById(R.id.turning_start_dateTime);
            questionDateTime=ll_hide.findViewById(R.id.question_date_time);
            founderRealName=ll_hide.findViewById(R.id.founder_real_name);
            targetProgram=ll_hide.findViewById(R.id.target_program);

        }
    }


