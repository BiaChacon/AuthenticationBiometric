package com.biachacon.AuthenticationKeystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {

    private val executor = Executor { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val biometricLoginButton: Button = findViewById(R.id.digital)
            biometricLoginButton.setOnClickListener { showBiometricPrompt() }
        }

        private fun showBiometricPrompt() {

            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Cancel")
                .setConfirmationRequired(false)
                .setDeviceCredentialAllowed(true)
                .build()

            val biometricPrompt = BiometricPrompt(this, executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int,
                                                       errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        Toast.makeText(applicationContext,
                            "Authentication error: $errString", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onAuthenticationSucceeded(
                        result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        val authenticatedCryptoObject: BiometricPrompt.CryptoObject =
                            result.getCryptoObject()!!
                        // User has verified the signature, cipher, or message
                        // authentication code (MAC) associated with the crypto object,
                        // so you can use it in your app's crypto-driven workflows.
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Toast.makeText(applicationContext, "Authentication failed",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                })

            // Displays the "log in" prompt.
            biometricPrompt.authenticate(promptInfo)
        }


}
