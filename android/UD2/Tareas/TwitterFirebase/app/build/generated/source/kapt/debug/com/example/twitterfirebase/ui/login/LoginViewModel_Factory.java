package com.example.twitterfirebase.ui.login;

import com.example.twitterfirebase.data.network.FirebaseService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
  private final Provider<FirebaseService> firebaseServiceProvider;

  public LoginViewModel_Factory(Provider<FirebaseService> firebaseServiceProvider) {
    this.firebaseServiceProvider = firebaseServiceProvider;
  }

  @Override
  public LoginViewModel get() {
    return newInstance(firebaseServiceProvider.get());
  }

  public static LoginViewModel_Factory create(Provider<FirebaseService> firebaseServiceProvider) {
    return new LoginViewModel_Factory(firebaseServiceProvider);
  }

  public static LoginViewModel newInstance(FirebaseService firebaseService) {
    return new LoginViewModel(firebaseService);
  }
}
