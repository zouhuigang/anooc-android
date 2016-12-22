package com.anooc.android.md.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.anooc.android.md.R;
import com.anooc.android.md.model.entity.Md;

import com.anooc.android.md.ui.activity.MdWebView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MdAdapter extends BaseAdapter {

    private final Activity activity;
    private final LayoutInflater inflater;
    private  List<Md> mdList = new ArrayList<>();
    public MdAdapter(@NonNull Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }


    @NonNull
    public List<Md> getMdList() {
        return mdList;
    }

    @Override
    public Object getItem(int position) {
        return mdList.get(position);
    }

    @Override
    public int getCount() {
        return mdList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            //inflate()的作用就是将一个用xml定义的布局文件查找出来，
            // 注意与findViewById()的区别，inflate是加载一个布局文件，
            // 而findViewById则是从布局文件中查找一个控件。
            //R.layout.item_tpl_0这个xml模板不能在主模板中用include进来
            convertView = inflater.inflate(R.layout.item_tpl_0, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.update(mdList.get(position));
        return convertView;
    }

    protected class ViewHolder {
        @BindView(R.id.md_title)
        protected TextView md_title;

        @BindView(R.id.md_cover)
        protected ImageView md_cover;

        @BindView(R.id.md_uid)
        protected TextView md_uid;

        @BindView(R.id.md_ctime)
        protected TextView md_ctime;
        private Md md;
        protected ViewHolder(@NonNull View itemView) {
            ButterKnife.bind(this, itemView);
        }
        protected void update(@NonNull Md md) {
            this.md=md;
            //System.out.println("title====="+md.getTitle());
            md_title.setText(md.getTitle());
            Glide.with(activity).load(md.getCover()).placeholder(R.drawable.image_placeholder).dontAnimate().into(md_cover);
            md_uid.setText(""+md.getUid()+"");
            md_ctime.setText(md.getCtime());
       }

        //点击文章
        @OnClick(R.id.btn_md_item)
        protected void onBtnItemClick() {
            String url ="http://www.anooc.com/vm/"+md.getVid();
            Intent intent = new Intent(activity, MdWebView.class);
            intent.putExtra("url", url);
            activity.startActivity(intent);
            //System.out.println("点击了这个list");
            //Navigator.TopicWithAutoCompat.start(activity, topic);
            //Intent intent = new Intent(activity, MdActivity.class);

            //activity.startActivity(intent);
        }

    }


}
