package com.example.myapplication.utils;

import io.reactivex.Scheduler;

public interface SchedulersProvider {

    Scheduler io();

    Scheduler ui();
}
