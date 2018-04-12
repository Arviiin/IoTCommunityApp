package com.archer.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.archer.start.R;
import com.archer.model.MessageBean;

public class ChatMessageAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<MessageBean> mDatas;

    public ChatMessageAdapter(Context context, List<MessageBean> mDatas) {
        inflater = LayoutInflater.from(context);//��ʼ�������ļ�
        this.mDatas = mDatas;//��ʼ�����Դ
    }

    @Override
    public int getCount() {

        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {

        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        //ѡ��ʹ�ú��ֲ���
        MessageBean message = mDatas.get(position);
        ViewHolder viewHolder = null;
        if (view == null) {
            //ͨ��ItemType���ò�ͬ�Ĳ��ָ�ʽ
            if (getItemViewType(position) == 0) {
                view = inflater.inflate(R.layout.item_send, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.mDate = (TextView) view.findViewById(R.id.send_date);
                viewHolder.mMsg = (TextView) view.findViewById(R.id.textView3);
            } else {
                view = inflater.inflate(R.layout.item_receive, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.mDate = (TextView) view.findViewById(R.id.receive_date);
                viewHolder.mMsg = (TextView) view.findViewById(R.id.textView6);
            }
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();//?
        }
        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss ");
        viewHolder.mDate.setText(df.format(message.getDate()));
        viewHolder.mMsg.setText(message.getMsg());
        return view;
    }

    private final class ViewHolder {
        TextView mDate;
        TextView mMsg;
    }

    @Override
    public int getItemViewType(int position) {
        MessageBean message = mDatas.get(position);
        if (message.getType() == MessageBean.Type.INCOMING) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getViewTypeCount() {

        return 2;
    }


}
