package com.example.twitterfirebase.di;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/twitterfirebase/di/DataModule;", "", "()V", "provideDatabaseReference", "Lcom/google/firebase/database/DatabaseReference;", "provideFirebaseService", "Lcom/example/twitterfirebase/data/network/FirebaseService;", "databaseReference", "app_debug"})
@dagger.Module
public final class DataModule {
    
    public DataModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @dagger.Provides
    @javax.inject.Singleton
    public final com.google.firebase.database.DatabaseReference provideDatabaseReference() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @dagger.Provides
    @javax.inject.Singleton
    public final com.example.twitterfirebase.data.network.FirebaseService provideFirebaseService(@org.jetbrains.annotations.NotNull
    com.google.firebase.database.DatabaseReference databaseReference) {
        return null;
    }
}