package com.example.tresenraya.di

import com.example.tresenraya.data.network.FirebaseService
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/*5.6-Creamos el modulo que va a ser el encargado de comunicarse con la UI(viewModel) y proveer de
datos de firebase.

    Creamos el provideDatabaseReference (conexión de la BD)
    Creamos el provideFirebaseService (operaciones disponibles)
*/

@Module
@InstallIn(SingletonComponent::class)
class DataModule_5 {

    @Singleton
    @Provides
    fun provideDatabaseReference() = Firebase.database.reference

    @Singleton
    @Provides
    fun provideFirebaseService(databaseReference: DatabaseReference) = FirebaseService(databaseReference)

}