package com.biachacon.AuthenticationKeystore

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import android.content.pm.PackageManager
import android.Manifest.permission
import android.Manifest.permission.USE_FINGERPRINT
import androidx.core.app.ActivityCompat





class BiometricUtil {

    fun isBiometricPromptEnabled():Boolean{
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
    }

    fun isSdkVersionSupported():Boolean{
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
    }

    fun isHardwareSupported(context: Context): Boolean {
        val fingerprintManager = FingerprintManagerCompat.from(context)
        return fingerprintManager.isHardwareDetected
    }

    fun isFingerprintAvailable(context: Context): Boolean {
        val fingerprintManager = FingerprintManagerCompat.from(context)
        return fingerprintManager.hasEnrolledFingerprints()
    }

    fun isPermissionGranted(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) ==
                PackageManager.PERMISSION_GRANTED
    }

}