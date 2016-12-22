package com.anooc.android.md.ui.view;

import android.support.annotation.NonNull;
import com.anooc.android.md.model.entity.MdList;
import com.anooc.android.md.ui.viewholder.LoadMoreFooter;


public interface MdView {
    void onLoadMoreGetMdDataOk(@NonNull MdList MdList);
    void onLoadMoreGetMdDataFinish(@LoadMoreFooter.State int state);
    void onRefreshMdListOk(@NonNull  MdList MdList);
    void onRefreshMdListFinish();

}
