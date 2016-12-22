package com.anooc.android.md.ui.activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.anooc.android.md.R;
import com.anooc.android.md.presenter.contract.IMdPresenter;
import com.anooc.android.md.presenter.implement.MdPresenter;
import com.anooc.android.md.ui.adapter.MdAdapter;
import com.anooc.android.md.ui.base.StatusBarActivity;
import com.anooc.android.md.ui.util.RefreshUtils;
import com.anooc.android.md.ui.view.MdView;
import com.anooc.android.md.model.entity.MdList;
import com.anooc.android.md.ui.viewholder.LoadMoreFooter;
import com.anooc.android.md.ui.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MdActivity extends StatusBarActivity implements MdView,SwipeRefreshLayout.OnRefreshListener,LoadMoreFooter.OnLoadMoreListener  {
    private int recoid = 0;
    //刷新
    @BindView(R.id.refresh_layout)
    protected SwipeRefreshLayout refreshLayout;

    //list数据
    @BindView(R.id.list_view)
    protected ListView listView;

    // 抽屉导航布局
    //@BindView(R.id.drawer_layout)
    //protected DrawerLayout drawerLayout;

    //导航条
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    //数据

    //private MdList list=new MdList();
    private IMdPresenter imdPresenter;
    private LoadMoreFooter loadMoreFooter;
    private MdAdapter adapter;
    private MdView mdView;
    private com.melnykov.fab.FloatingActionButton back_top;// 返回顶部的按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md);
        ButterKnife.bind(this);

        imdPresenter = new MdPresenter(this, this);
        //加载更多
        loadMoreFooter = new LoadMoreFooter(this, listView, this);

        //new视图数据
        adapter = new MdAdapter(this);
        listView.setAdapter(adapter);

        //导航条事件
       // drawerLayout.setDrawerShadow(R.drawable.navigation_drawer_shadow, GravityCompat.START); //设置一个定制的影子覆盖时的主要内容的抽屉里
        //drawerLayout.addDrawerListener(drawerListener);
       // toolbar.setNavigationOnClickListener(new NavigationOpenClickListener(drawerLayout));
        //toolbar.setOnClickListener(new DoubleClickBackToContentTopListener(this));

        back_top= (com.melnykov.fab.FloatingActionButton)findViewById(R.id.back_top);


        RefreshUtils.init(refreshLayout, this);
        RefreshUtils.refresh(refreshLayout, this);
    }

    //请求网络之后,得到数据，然后通过adapter绑定视图数据
    @Override
    public void onLoadMoreGetMdDataOk(@NonNull MdList mdList){//会不断的请求网络地址
        //adapter=new MdAdapter(this);
        //adapter.getMdList().clear();
        //listView.setAdapter(adapter);
        //System.out.println("mdlist size===="+mdList.getList().size());
        //adapter.getMdList().clear();
        adapter.getMdList().addAll(mdList.getList());
        //System.out.println("改变了多少个值"+adapter.getCount());
        adapter.notifyDataSetChanged();
        recoid=mdList.getList().get(mdList.getList().size()-1).getId();
    }


    //下拉刷新
    @Override
    public void onRefreshMdListOk(@NonNull MdList mdList){
        adapter.getMdList().clear();
        adapter.getMdList().addAll(mdList.getList());
        adapter.notifyDataSetChanged();
        if (adapter.getMdList().isEmpty()) {
            loadMoreFooter.setState(LoadMoreFooter.STATE_DISABLE);
            //iconNoData.setVisibility(View.VISIBLE);
        } else {
            loadMoreFooter.setState(LoadMoreFooter.STATE_ENDLESS);
            //iconNoData.setVisibility(View.GONE);
        }

    }

    //刷新方法
    @Override
    public void onRefresh() {
        imdPresenter.OnRefreshGetMdDataAsyncTask(recoid);
    }

    //加载更多
    @Override
    public void onLoadMore() {
        //mainPresenter.loadMoreTopicListAsyncTask(page + 1);
        imdPresenter.onLoadMoreGetMdDataAsyncTask(recoid);
    }


    @Override
    public void onRefreshMdListFinish() {
        refreshLayout.setRefreshing(false);
    }


    @Override
    public void onLoadMoreGetMdDataFinish(@LoadMoreFooter.State int state) {
        loadMoreFooter.setState(state);
        back_top.setVisibility(View.VISIBLE);
    }



    @OnClick(R.id.back_top)
    public void back_top(){
//        setSelection(0);
//        listView.setSelectionAfterHeaderView();
          listView.setSelection(0);
        //listView.smoothScrollToPosition(0);

    }







}
