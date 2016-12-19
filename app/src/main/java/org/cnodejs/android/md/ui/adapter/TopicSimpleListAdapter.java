package org.cnodejs.android.md.ui.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.cnodejs.android.md.R;
import org.cnodejs.android.md.model.entity.TopicSimple;
import org.cnodejs.android.md.ui.activity.UserDetailActivity;
import org.cnodejs.android.md.ui.util.Navigator;
import org.cnodejs.android.md.util.FormatUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopicSimpleListAdapter extends RecyclerView.Adapter<TopicSimpleListAdapter.ViewHolder> {

    private final Activity activity;
    private final LayoutInflater inflater;
    private final List<TopicSimple> topicSimpleList = new ArrayList<>();

    public TopicSimpleListAdapter(@NonNull Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    public List<TopicSimple> getTopicSimpleList() {
        return topicSimpleList;
    }

    @Override
    public int getItemCount() {
        return topicSimpleList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_topic_simple, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.update(topicSimpleList.get(position));
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_avatar)
        protected ImageView imgAvatar;

        @BindView(R.id.tv_title)
        protected TextView tvTitle;

        @BindView(R.id.tv_login_name)
        protected TextView tvLoginName;

        @BindView(R.id.tv_last_reply_time)
        protected TextView tvLastReplyTime;

        private TopicSimple topicSimple;

        protected ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void update(@NonNull TopicSimple topicSimple) {
            this.topicSimple = topicSimple;

            tvTitle.setText(topicSimple.getTitle());
            Glide.with(activity).load(topicSimple.getAuthor().getAvatarUrl()).placeholder(R.drawable.image_placeholder).dontAnimate().into(imgAvatar);
            tvLoginName.setText(topicSimple.getAuthor().getLoginName());
            tvLastReplyTime.setText(FormatUtils.getRelativeTimeSpanString(topicSimple.getLastReplyAt()));
        }

        @OnClick(R.id.img_avatar)
        protected void onBtnAvatarClick() {
            UserDetailActivity.startWithTransitionAnimation(activity, topicSimple.getAuthor().getLoginName(), imgAvatar, topicSimple.getAuthor().getAvatarUrl());
        }

        @OnClick(R.id.btn_item)
        protected void onBtnItemClick() {
            Navigator.TopicWithAutoCompat.start(activity, topicSimple.getId());
        }

    }

}
