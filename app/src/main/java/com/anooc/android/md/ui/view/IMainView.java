package com.anooc.android.md.ui.view;

import android.support.annotation.NonNull;

import com.anooc.android.md.model.entity.TabType;
import com.anooc.android.md.model.entity.Topic;
import com.anooc.android.md.ui.viewholder.LoadMoreFooter;

import java.util.List;

public interface IMainView {

    void onSwitchTabOk(@NonNull TabType tab);

    void onRefreshTopicListOk(@NonNull List<Topic> topicList);

    void onRefreshTopicListFinish();

    void onLoadMoreTopicListOk(@NonNull List<Topic> topicList);

    void onLoadMoreTopicListFinish(@LoadMoreFooter.State int state);

    void updateUserInfoViews();

    void updateMessageCountViews(int count);

}
