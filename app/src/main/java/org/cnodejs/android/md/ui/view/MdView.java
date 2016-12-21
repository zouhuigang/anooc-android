package org.cnodejs.android.md.ui.view;

import android.support.annotation.NonNull;
import org.cnodejs.android.md.model.entity.MdList;


public interface MdView {
    void onGetMdDataOk(@NonNull MdList MdList);
    void onGetMdFinish();

}
