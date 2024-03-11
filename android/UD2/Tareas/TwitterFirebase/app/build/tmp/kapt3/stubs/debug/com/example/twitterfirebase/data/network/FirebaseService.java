package com.example.twitterfirebase.data.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/twitterfirebase/data/network/FirebaseService;", "", "reference", "Lcom/google/firebase/database/DatabaseReference;", "(Lcom/google/firebase/database/DatabaseReference;)V", "login", "", "username", "", "password", "Companion", "app_debug"})
public final class FirebaseService {
    private final com.google.firebase.database.DatabaseReference reference = null;
    @org.jetbrains.annotations.NotNull
    public static final com.example.twitterfirebase.data.network.FirebaseService.Companion Companion = null;
    private static final java.lang.String PATH = "twitter";
    
    @javax.inject.Inject
    public FirebaseService(@org.jetbrains.annotations.NotNull
    com.google.firebase.database.DatabaseReference reference) {
        super();
    }
    
    public final boolean login(@org.jetbrains.annotations.NotNull
    java.lang.String username, @org.jetbrains.annotations.NotNull
    java.lang.String password) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/twitterfirebase/data/network/FirebaseService$Companion;", "", "()V", "PATH", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}