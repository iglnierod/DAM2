package com.example.twitterfirebase.ui.login;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006\u0013"}, d2 = {"Lcom/example/twitterfirebase/ui/login/LoginViewModel;", "Landroidx/lifecycle/ViewModel;", "firebaseService", "Lcom/example/twitterfirebase/data/network/FirebaseService;", "(Lcom/example/twitterfirebase/data/network/FirebaseService;)V", "_password", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_username", "password", "Lkotlinx/coroutines/flow/StateFlow;", "getPassword", "()Lkotlinx/coroutines/flow/StateFlow;", "username", "getUsername", "checkUser", "", "user", "pwd", "app_debug"})
public final class LoginViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.twitterfirebase.data.network.FirebaseService firebaseService = null;
    private kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _username;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> username = null;
    private kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _password;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> password = null;
    
    @javax.inject.Inject
    public LoginViewModel(@org.jetbrains.annotations.NotNull
    com.example.twitterfirebase.data.network.FirebaseService firebaseService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getUsername() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getPassword() {
        return null;
    }
    
    public final boolean checkUser(@org.jetbrains.annotations.NotNull
    java.lang.String user, @org.jetbrains.annotations.NotNull
    java.lang.String pwd) {
        return false;
    }
}