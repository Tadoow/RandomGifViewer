package com.example.myapplication.presentation.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.myapplication.data.api.DevelopersLifeApi;
import com.example.myapplication.data.api.DevelopersLifeApiImpl;
import com.example.myapplication.data.repository.DevelopersLifeRepository;
import com.example.myapplication.data.repository.DevelopersLifeRepositoryImpl;
import com.example.myapplication.data.store.DevelopersLifeStore;
import com.example.myapplication.data.store.DevelopersLifeStoreImpl;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.domain.interactor.DevelopersLifeInteractor;
import com.example.myapplication.domain.model.RandomImageDomain;
import com.example.myapplication.presentation.viewmodel.DevelopersLifeViewModel;
import com.example.myapplication.utils.SchedulersProvider;
import com.example.myapplication.utils.SchedulersProviderImpl;
import com.example.myapplication.utils.parser.JsonParser;
import com.example.myapplication.utils.parser.JsonParserImpl;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity {

    private DevelopersLifeViewModel mViewModel;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        createViewModel();
        observeLiveData();

        if (savedInstanceState == null) {
            mViewModel.loadImage();
        }

        mBinding.buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.loadImage();
            }
        });
    }

    private void createViewModel() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .addNetworkInterceptor(new HttpLoggingInterceptor())
                .build();
        JsonParser parser = new JsonParserImpl();

        DevelopersLifeApi developersLifeApi = new DevelopersLifeApiImpl(httpClient, parser);
        DevelopersLifeStore developersLifeStore = new DevelopersLifeStoreImpl(getSharedPreferences("PREFS", MODE_PRIVATE), parser);
        DevelopersLifeRepository repository = new DevelopersLifeRepositoryImpl(developersLifeApi, developersLifeStore);
        DevelopersLifeInteractor interactor = new DevelopersLifeInteractor(repository);

        SchedulersProvider schedulersProvider = new SchedulersProviderImpl();

        mViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new DevelopersLifeViewModel(interactor, schedulersProvider);
            }
        }).get(DevelopersLifeViewModel.class);
    }

    private void observeLiveData() {
        mViewModel.getProgressLiveData().observe(this, aBoolean -> mBinding.progressbarView.setVisibility(aBoolean ? View.VISIBLE : View.GONE));
        mViewModel.getErrorLiveData().observe(this, throwable -> Snackbar.make(mBinding.getRoot(), throwable.toString(), BaseTransientBottomBar.LENGTH_LONG).show());
        mViewModel.getRandomImageData().observe(this, new Observer<RandomImageDomain>() {
            @Override
            public void onChanged(RandomImageDomain randomImageDomain) {
                Glide.with(getApplicationContext())
                        .load(randomImageDomain.getGifUrl())
                        .into(mBinding.gifView);
            }
        });
    }

}
