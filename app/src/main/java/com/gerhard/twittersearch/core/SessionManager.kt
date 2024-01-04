package com.gerhard.twittersearch.core

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.gerhard.twittersearch.BuildConfig
import com.gerhard.twittersearch.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext val context: Context
) {
    private val TWITTER_API_TOKEN = "TWITTER_API_TOKEN"

    private val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private var prefs = EncryptedSharedPreferences.create(
        context.getString(R.string.app_name),
        masterKey,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveAuthToken(token: String): Boolean {
        val editor = prefs.edit()
        editor.putString(TWITTER_API_TOKEN, token)
        return editor.commit()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(TWITTER_API_TOKEN, BuildConfig.TWITTER_API_TOKEN)
    }

}