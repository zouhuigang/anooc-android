package org.cnodejs.android.md.ui.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.cnodejs.android.md.R;
import org.cnodejs.android.md.model.entity.Md;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MdAdapter extends BaseAdapter {

    private final Activity activity;
    private final LayoutInflater inflater;
    private  List<Md> mdList = new ArrayList<>();

    public MdAdapter(@NonNull Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    /*public MdAdapter(@NonNull Activity activity,List<Md> mdList ) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
        this.mdList=mdList;
    }*/

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
            convertView = inflater.inflate(R.layout.item_topic, parent, false);
            holder = new ViewHolder(convertView);
            //holder.md_title = (TextView) convertView.findViewById(R.id.md_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //holder.md_title.setText(mdList.get(position).getTitle());
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

            md_title.setText(md.getTitle());
            Glide.with(activity).load(md.getCover()).placeholder(R.drawable.image_placeholder).dontAnimate().into(md_cover);

            md_uid.setText(md.getUid());
            md_ctime.setText(md.getCtime());
       }

    }


}
