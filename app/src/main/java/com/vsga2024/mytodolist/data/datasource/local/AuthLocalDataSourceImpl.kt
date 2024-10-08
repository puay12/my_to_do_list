package com.vsga2024.mytodolist.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.vsga2024.mytodolist.data.datasource.interfaces.AuthLocalDataSource
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class AuthLocalDataSourceImpl(
    private val dataStore: DataStore<Preferences>
) : AuthLocalDataSource {
    companion object {
        const val KEY_TOKEN = "token"
        private val DATASTORE_KEY_TOKEN = stringPreferencesKey(KEY_TOKEN)
    }

    override suspend fun saveToken(token: String) {
        setToken(token)
    }

    override suspend fun getToken(): String? {
        return dataStore.data.map { pref ->
            pref[DATASTORE_KEY_TOKEN]
        }.firstOrNull()
    }

    private suspend fun setToken(token: String) {
        dataStore.edit { pref ->
            pref[DATASTORE_KEY_TOKEN] = token
        }
    }
}