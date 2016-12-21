package org.cnodejs.android.md.ui.activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;


import org.cnodejs.android.md.R;

import org.cnodejs.android.md.model.entity.Topic;
import org.cnodejs.android.md.presenter.contract.IMdPresenter;
import org.cnodejs.android.md.presenter.implement.MdPresenter;
import org.cnodejs.android.md.ui.adapter.MdAdapter;

import org.cnodejs.android.md.ui.adapter.ReplyListAdapter;
import org.cnodejs.android.md.ui.base.StatusBarActivity;

import org.cnodejs.android.md.ui.util.RefreshUtils;
import org.cnodejs.android.md.ui.view.MdView;
import org.cnodejs.android.md.model.entity.MdList;
import org.cnodejs.android.md.model.entity.Md;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MdActivity extends StatusBarActivity implements MdView, SwipeRefreshLayout.OnRefreshListener  {

    //刷新
    @BindView(R.id.refresh_layout)
    protected SwipeRefreshLayout refreshLayout;

    //list数据
    @BindView(R.id.list_view)
    protected ListView listView;

    //数据

    //private MdList list=new MdList();
    private IMdPresenter imdPresenter;
    private MdAdapter adapter;
    private MdView mdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md);
        ButterKnife.bind(this);

        imdPresenter = new MdPresenter(this, this);

        //new视图数据
        adapter = new MdAdapter(this);
        listView.setAdapter(adapter);

        RefreshUtils.init(refreshLayout, this);
        RefreshUtils.refresh(refreshLayout, this);
    }

    //请求网络之后,得到数据，然后通过adapter绑定视图数据
    @Override
    public void onGetMdDataOk(@NonNull MdList mdList){//会不断的请求网络地址
        //adapter=new MdAdapter(this);
        //adapter.getMdList().clear();
        //listView.setAdapter(adapter);
        System.out.println("mdlist size===="+mdList.getList().size());
        adapter.getMdList().clear();
        adapter.getMdList().addAll(mdList.getList());
        System.out.println("改变了多少个值"+adapter.getCount());
        adapter.notifyDataSetChanged();
    }

    //刷新方法
    @Override
    public void onRefresh() {
        imdPresenter.getMdAsyncTask();
    }


    @Override
    public void onGetMdFinish() {
        refreshLayout.setRefreshing(false);
    }






}
