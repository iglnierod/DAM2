package com.example.twitterfirebase.di;

import com.google.firebase.database.DatabaseReference;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class DataModule_ProvideDatabaseReferenceFactory implements Factory<DatabaseReference> {
  private final DataModule module;

  public DataModule_ProvideDatabaseReferenceFactory(DataModule module) {
    this.module = module;
  }

  @Override
  public DatabaseReference get() {
    return provideDatabaseReference(module);
  }

  public static DataModule_ProvideDatabaseReferenceFactory create(DataModule module) {
    return new DataModule_ProvideDatabaseReferenceFactory(module);
  }

  public static DatabaseReference provideDatabaseReference(DataModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideDatabaseReference());
  }
}
