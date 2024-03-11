package com.example.twitterfirebase.data.network;

import com.google.firebase.database.DatabaseReference;
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
public final class FirebaseService_Factory implements Factory<FirebaseService> {
  private final Provider<DatabaseReference> referenceProvider;

  public FirebaseService_Factory(Provider<DatabaseReference> referenceProvider) {
    this.referenceProvider = referenceProvider;
  }

  @Override
  public FirebaseService get() {
    return newInstance(referenceProvider.get());
  }

  public static FirebaseService_Factory create(Provider<DatabaseReference> referenceProvider) {
    return new FirebaseService_Factory(referenceProvider);
  }

  public static FirebaseService newInstance(DatabaseReference reference) {
    return new FirebaseService(reference);
  }
}
