package org.cnodejs.android.md.ui.view;

import android.support.annotation.NonNull;
import org.cnodejs.android.md.model.entity.Md;
import java.util.List;


public interface MdView {
    void onGetMdDataOk(@NonNull List<Md> MdList);
    void onGetMdFinish();

}
