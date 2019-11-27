package top.easelink.lcg.ui.main.about.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.Calendar;

import javax.inject.Inject;

import top.easelink.framework.base.BaseFragment;
import top.easelink.lcg.BR;
import top.easelink.lcg.R;
import top.easelink.lcg.databinding.FragmentAboutBinding;
import top.easelink.lcg.ui.ViewModelProviderFactory;
import top.easelink.lcg.ui.main.about.viewmodel.AboutViewModel;

public class AboutFragment extends BaseFragment<FragmentAboutBinding, AboutViewModel> implements AboutNavigator {

    @Inject
    ViewModelProviderFactory factory;

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    public AboutViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(AboutViewModel.class);
    }

    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(getTag());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        syncAuthorState();
    }

    private void syncAuthorState() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour < 7) {
            getViewDataBinding().me.setAnimation(R.raw.moon_stars);
        } else if (hour < 12) {
            getViewDataBinding().me.setAnimation(R.raw.personal_mac_daytime);
        } else if (hour == 12) {
            getViewDataBinding().me.setAnimation(R.raw.sun);
        } else if (hour < 18) {
            getViewDataBinding().me.setAnimation(R.raw.personal_phone_daytime);
        } else if (hour < 22) {
            getViewDataBinding().me.setAnimation(R.raw.personal_mac_night);
        } else {
            getViewDataBinding().me.setAnimation(R.raw.personal_phone_night);
        }
    }
}
