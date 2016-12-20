package org.cnodejs.android.md.ui.activity;
import android.os.Bundle;
import android.support.annotation.NonNull;


import org.cnodejs.android.md.R;
import org.cnodejs.android.md.model.entity.Md;
import org.cnodejs.android.md.presenter.contract.IMdPresenter;
import org.cnodejs.android.md.presenter.implement.MdPresenter;
import org.cnodejs.android.md.ui.base.StatusBarActivity;

import org.cnodejs.android.md.ui.view.MdView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MdActivity extends StatusBarActivity implements MdView  {




    //@BindView(R.id.recycler_view)

    private IMdPresenter imdPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md);
        ButterKnife.bind(this);
        //super.onCreate(savedInstanceState);
         //setContentView(R.layout.activity_md);

        imdPresenter = new MdPresenter(this, this);
        imdPresenter.getMdAsyncTask();
         //setContentView(new MyView(this));
         //ImageView img_avatar= (ImageView) findViewById(R.id.img_avatar);

    }

    @Override
    public void onGetMessagesOk(@NonNull Md md){//会不断的请求网络地址
        System.out.println(md);
        //imdPresenter.getMdAsyncTask();
    }

    @Override
    public void onGetMdFinish() {

    }






}
