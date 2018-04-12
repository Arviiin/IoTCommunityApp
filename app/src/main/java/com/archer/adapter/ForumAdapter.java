package com.archer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.archer.start.R;
import com.archer.util.ImageLoader;
import com.archer.model.FroumBean;

import java.util.List;

public class ForumAdapter extends BaseAdapter {
    private List<FroumBean> mList;
    private LayoutInflater inflater;
    private ImageLoader mImageLoader;
    private int mStart, mEnd;
    private boolean mfirstIn;
    public static String[] URLS;

    public ForumAdapter(Context context, List<FroumBean> data, ListView listView) {
        mList = data;
        inflater = LayoutInflater.from(context);
        //mImageLoader=new ImageLoader(listView);
        URLS = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            URLS[i] = data.get(i).newsIconUrl;
        }
     /*   mfirstIn=true;
        if(mfirstIn) {
            mImageLoader.loadImages(0, 0 + data.size());
            mfirstIn=false;
        }*/
        //  android.util.Log.v("xys", String.valueOf(data.size()));
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_forum, null);
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.mIcon);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.forum_content);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.forum_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivIcon.setImageResource(R.mipmap.default_user_head_img);
        String url = mList.get(position).newsIconUrl;
        viewHolder.ivIcon.setTag(url);
        //mImageLoader.showImageByThread(viewHolder.ivIcon,mList.get(position).newsIconUrl);
        // mImageLoader.showImageByAsyncTask(viewHolder.ivIcon,url);
        viewHolder.tvTitle.setText(mList.get(position).newsTitle);
        viewHolder.tvContent.setText(mList.get(position).newsContent);
        return convertView;
    }

  /*  @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(scrollState==SCROLL_STATE_IDLE){
            //加载可见项
            mImageLoader.loadImages(mStart,mEnd);

        }else{
            //停止任务
            mImageLoader.cancelAllTask();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mStart=firstVisibleItem;
        mEnd=firstVisibleItem+visibleItemCount;
        //第一次加载时调用
        if(visibleItemCount>0){
            mImageLoader.loadImages(mStart,mEnd);
            mfirstIn=false;
        }
    }*/

    public void onDateChanged(List<FroumBean> froumBeen) {
        this.mList = froumBeen;
        this.notifyDataSetChanged();
    }

    class ViewHolder {
        public TextView tvTitle, tvContent;
        public ImageView ivIcon;
    }
}
