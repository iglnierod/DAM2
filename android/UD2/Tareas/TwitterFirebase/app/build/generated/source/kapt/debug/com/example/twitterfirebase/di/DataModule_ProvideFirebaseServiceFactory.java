package com.example.twitterfirebase.di;

import com.example.twitterfirebase.data.network.FirebaseService;
import com.google.firebase.database.DatabaseReference;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DataModule_ProvideFirebaseServiceFactory implements Factory<FirebaseService> {
  private final DataModule module;

  private final Provider<DatabaseReference> databaseReferenceProvider;

  public DataModule_ProvideFirebaseServiceFactory(DataModule module,
      Provider<DatabaseReference> databaseReferenceProvider) {
    this.module = module;
    this.databaseReferenceProvider = databaseReferenceProvider;
  }

  @Override
  public FirebaseService get() {
    return provideFirebaseService(module, databaseReferenceProvider.get());
  }

  public static DataModule_ProvideFirebaseServiceFactory create(DataModule module,
      Provider<DatabaseReference> databaseReferenceProvider) {
    return new DataModule_ProvideFirebaseServiceFactory(module, databaseReferenceProvider);
  }

  public static FirebaseService provideFirebaseService(DataModule instance,
      DatabaseReference databaseReference) {
    return Preconditions.checkNotNullFromProvides(instance.provideFirebaseService(databaseReference));
  }
}
