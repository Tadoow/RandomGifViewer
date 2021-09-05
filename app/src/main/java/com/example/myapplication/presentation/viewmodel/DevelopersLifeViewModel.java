package com.example.myapplication.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.domain.interactor.DevelopersLifeInteractor;
import com.example.myapplication.domain.model.RandomImageDomain;
import com.example.myapplication.utils.SchedulersProvider;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public class DevelopersLifeViewModel extends ViewModel {

    private final DevelopersLifeInteractor mInteractor;
    private final SchedulersProvider mSchedulersProvider;

    private final MutableLiveData<RandomImageDomain> mRandomImageData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mProgressLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> mErrorLiveData = new MutableLiveData<>();

    private Disposable mDisposable;

    public DevelopersLifeViewModel(DevelopersLifeInteractor interactor, SchedulersProvider schedulersProvider) {
        mInteractor = interactor;
        mSchedulersProvider = schedulersProvider;
    }

    public void loadImage() {
        mDisposable = Single.fromCallable(mInteractor::getImage)
                .doOnSubscribe(disposable -> mProgressLiveData.postValue(true))
                .doAfterTerminate(() -> mProgressLiveData.postValue(false))
                .subscribeOn(mSchedulersProvider.io())
                .observeOn(mSchedulersProvider.ui())
                .subscribe(mRandomImageData::setValue, mErrorLiveData::setValue);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

    public MutableLiveData<RandomImageDomain> getRandomImageData() {
        return mRandomImageData;
    }

    public MutableLiveData<Boolean> getProgressLiveData() {
        return mProgressLiveData;
    }

    public MutableLiveData<Throwable> getErrorLiveData() {
        return mErrorLiveData;
    }
}
