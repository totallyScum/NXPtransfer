package com.zthl.nxp.ui.missionPublished;

import android.content.Context;
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

import com.zthl.nxp.ProblemFeedbackFragment;
import com.chen.nxp.R;
import com.zthl.nxp.TransferEndFragment;
import com.zthl.nxp.TransferStartFragment;
import com.zthl.nxp.dummy.DummyContent.DummyItem;
import com.zthl.nxp.ui.mission.MissionFragment.OnListFragmentInteractionListener;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MissionPublishedListviewViewAdapter extends BaseAdapter implements View.OnClickListener{
    private ArrayList<String> list = null;
    private Context context = null;
    private int clickPosition = -1;//记录用户点击了的item
    private androidx.fragment.app.FragmentManager fm;
    public Boolean flag = false; //标识下拉view的显示状态


    public MissionPublishedListviewViewAdapter(Context context, ArrayList<String> list, FragmentManager fm){
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
        final MyViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.mission_published_item, null);
            vh = new MyViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (MyViewHolder) convertView.getTag();
        }
        vh.tv_test.setText(list.get(position));
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
        vh.hide_1.setOnClickListener(this);
        vh.hide_2.setOnClickListener(this);
        vh.hide_3.setOnClickListener(this);
        vh.check_start.setOnClickListener(this);
        vh.check_end.setOnClickListener(this);
        vh.check_error.setOnClickListener(this);
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
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.check_start:
                ft.replace(R.id.container, TransferStartFragment.newInstance());
                ft.addToBackStack("UserTag");
                ft.commit();
                break;
            case R.id.check_end:
                ft.replace(R.id.container, TransferEndFragment.newInstance());
                ft.addToBackStack("UserTag");
                ft.commit();
                break;
            case R.id.check_error:
                ft.replace(R.id.container, ProblemFeedbackFragment.newInstance());
                ft.addToBackStack("UserTag");
                ft.commit();
                break;
        }
    }


    class MyViewHolder {
        View itemView;
        TextView tv_test;
        TextView hide_1, hide_2, hide_3, hide_4, hide_5;
        Button check_start,check_end,check_error;
        ImageView selectorImg;
        LinearLayout ll_hide;
        RelativeLayout listtiem;

        public MyViewHolder(View itemView) {
            this.itemView = itemView;
            tv_test = (TextView) itemView.findViewById(R.id.tv_test);
 //           selectorImg = (ImageView) itemView.findViewById(R.id.checkbox);
            hide_1 = (TextView) itemView.findViewById(R.id.hide_1);
            hide_2 = (TextView) itemView.findViewById(R.id.hide_2);
            hide_3 = (TextView) itemView.findViewById(R.id.hide_3);
            ll_hide = (LinearLayout) itemView.findViewById(R.id.mission_published_ll_hide);
            listtiem = (RelativeLayout) itemView.findViewById(R.id.mission_list_item);
            check_start=itemView.findViewById(R.id.check_start);
            check_end=itemView.findViewById(R.id.check_end);
            check_error=itemView.findViewById(R.id.check_error);
        }
    }
}
