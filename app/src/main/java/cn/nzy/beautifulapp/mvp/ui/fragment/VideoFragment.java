package cn.nzy.beautifulapp.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import cn.jzvd.JZVideoPlayer;
import cn.nzy.beautifulapp.Bean.VedioBean.VedioBean;
import cn.nzy.beautifulapp.R;
import cn.nzy.beautifulapp.adater.VedioAdapter;
import cn.nzy.beautifulapp.base.BaseFragment;
import cn.nzy.beautifulapp.base.BaseModule;
import cn.nzy.beautifulapp.mvp.contract.VedioContract;
import cn.nzy.beautifulapp.mvp.model.VedioMudle;
import cn.nzy.beautifulapp.mvp.presenter.VedioPresenter;

/**
 * on 2018/5/21. created by nzy
 */

public class VideoFragment extends BaseFragment<VedioContract.IVedioView,VedioPresenter> implements VedioContract.IVedioView, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private VedioAdapter mVedioAdapter;
    private List<VedioBean.ResultBean> mItemBeans;


    @Override
    protected VedioPresenter setPresenter() {
        return new VedioPresenter(this);
    }

    @Override
    protected BaseModule setModule() {
        return new VedioMudle();
    }


    private void setListener() {
    }



    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.layout_rec, null);
    }


    @Override
    protected void initData() {

        mItemBeans = new ArrayList<>();
        mVedioAdapter = new VedioAdapter(R.layout.item_vedio, mItemBeans);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mVedioAdapter);
        mRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                JZVideoPlayer.onChildViewAttachedToWindow(view, R.id.videoplayer);
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                JZVideoPlayer.onChildViewDetachedFromWindow(view);
            }
        });
        mVedioAdapter.setOnItemClickListener(this);
        presenter.getVedio(1);
        setListener();
    }


    @Override
    public void showData(List<VedioBean.ResultBean> vedioItemBeans) {
        mItemBeans.addAll(vedioItemBeans);
        mVedioAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showRecyclerView() {

    }

    @Override
    public void refreshFinish() {

    }

    @Override
    public void refreshFail() {

    }

    @Override
    public void showNetError(Throwable throwable) {

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
