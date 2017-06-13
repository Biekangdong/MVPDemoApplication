package com.example.administrator.view.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.example.administrator.base.BasePresenter;
import com.example.administrator.module.entity.EventEntity;
import com.example.administrator.util.RxBus;
import com.example.administrator.util.RxBusUtils;
import com.example.administrator.util.ToastUtils;
import com.example.administrator.view.fragment.Page1Fragment;
import com.example.administrator.view.fragment.Page2Fragment;
import com.example.administrator.view.fragment.Page3Fragment;
import com.example.administrator.view.fragment.Page4Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{
    String TAG = "MainActivity";
    private Observable<String> mainObservable;
    private Subscription rxSubscription;//订阅


    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    @BindView(R.id.rb_tab1)
    RadioButton rb_tab1;
    @BindView(R.id.rb_tab2)
    RadioButton rb_tab2;
    @BindView(R.id.rb_tab3)
    RadioButton rb_tab3;
    @BindView(R.id.rb_tab4)
    RadioButton rb_tab4;
    @BindView(R.id.iv_center)
    ImageView iv_center;
    @BindView(R.id.content_main)
    View content_main;

    Page1Fragment page1Fragment;
    Page2Fragment page2Fragment;
    Page3Fragment page3Fragment;
    Page4Fragment page4Fragment;
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresneter() {
        return null;
    }

    @Override
    protected void init() {
        mainObservable = RxBus.get().register("aa");
        mainObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                ToastUtils.show(MainActivity.this, s);
                Log.e("aa", "+++++++++++++++++++++++++++++++" + s);
                //tv_txt.setText(s);
            }
        });
        rxSubscription = RxBusUtils.getInstance().toObservable(EventEntity.class).subscribe(new Action1<EventEntity>() {
            @Override
            public void call(EventEntity eventEntity) {
                if (eventEntity.getTag().equals("aaa")) {
                    Toast.makeText(MainActivity.this, "aaa", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, "error = " + throwable.getMessage());
            }
        });


        initTab();
    }


    @OnClick({R.id.iv_center})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_center:
                ToastUtils.show(MainActivity.this,"点击了中间按钮");
                break;
        }
    }
    /**
     * 防止fragment 切换时页面堆叠
     *
     * @param fragment
     */
    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment) {
        super.onAttachFragment(fragment);
        if (page1Fragment == null && fragment instanceof Page1Fragment) {
            page1Fragment = (Page1Fragment) fragment;
        } else if (page2Fragment == null && fragment instanceof Page2Fragment) {
            page2Fragment = (Page2Fragment) fragment;
        } else if (page3Fragment == null && fragment instanceof Page3Fragment) {
            page3Fragment = (Page3Fragment) fragment;
        } else if (page4Fragment == null && fragment instanceof Page4Fragment) {
            page4Fragment = (Page4Fragment) fragment;
        }
    }
    private void initTab() {
        page1Fragment=new Page1Fragment();
        page2Fragment=new Page2Fragment();
        page3Fragment=new Page3Fragment();
        page4Fragment=new Page4Fragment();
        fragments.add(page1Fragment);
        fragments.add(page2Fragment);
        fragments.add(page3Fragment);
        fragments.add(page4Fragment);
        radiogroup.setOnCheckedChangeListener(this);
        switchFragments(fragments.get(0));
    }

    private void switchFragments(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //先隐藏已显示的Fragment
        for (int i = 0; i < fragments.size(); i++) {
            if (!fragments.get(i).isHidden()) {
                transaction.hide(fragments.get(i));
            }
        }
        //判断要显示的Fragment是否已经添加，添加过的话直接显示，否则先添加再显示
        if (!fragment.isAdded()) {
            transaction.add(R.id.content_main, fragment).show(fragment).commit();
        } else {
            transaction.show(fragment).commit();
        }


    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            //首页
            case R.id.rb_tab1:
                switchFragments(fragments.get(0));
                break;
            //直播
            case R.id.rb_tab2:
                switchFragments(fragments.get(1));
                break;
            //关注
            case R.id.rb_tab3:
                switchFragments(fragments.get(2));
                break;
            //我的
            case R.id.rb_tab4:
                switchFragments(fragments.get(3));
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister("aa", mainObservable);
        RxBusUtils.getInstance().unRegister(rxSubscription);
    }
}
