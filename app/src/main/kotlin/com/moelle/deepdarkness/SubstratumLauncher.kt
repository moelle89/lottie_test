@file:Suppress("ConstantConditionIf")

package com.moelle.deepdarkness

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import com.github.javiersantos.piracychecker.*
import com.github.javiersantos.piracychecker.enums.*
import com.github.javiersantos.piracychecker.utils.apkSignature
import com.moelle.deepdarkness.AdvancedConstants.ORGANIZATION_THEME_SYSTEMS
import com.moelle.deepdarkness.AdvancedConstants.OTHER_THEME_SYSTEMS
import com.moelle.deepdarkness.ThemeFunctions.checkApprovedSignature
import com.moelle.deepdarkness.ThemeFunctions.getSelfSignature
import com.moelle.deepdarkness.ThemeFunctions.getSelfVerifiedPirateTools
import com.moelle.deepdarkness.ThemeFunctions.isCallingPackageAllowed
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.fullscreen_dialog.*


/**
 * NOTE TO THEMERS
 *
 * This class is a TEMPLATE of how you should be launching themes. As long as you keep the structure
 * of launching themes the same, you can avoid easy script crackers by changing how
 * functions/methods are coded, as well as boolean variable placement.
 *
 * The more you play with this the harder it would be to decompile and crack!
 */

class SubstratumLauncher : Activity() {

    private val debug = false
    private val tag = "SubstratumThemeReport"
    private val substratumIntentData = "projekt.substratum.THEME"
    private val getKeysIntent = "projekt.substratum.GET_KEYS"
    private val receiveKeysIntent = "projekt.substratum.RECEIVE_KEYS"

    private val themePiracyCheck by lazy {
        if (BuildConfig.ENABLE_APP_BLACKLIST_CHECK) {
            getSelfVerifiedPirateTools(applicationContext)
        } else {
            false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fullscreen_dialog)
        av_from_code.setAnimation("data.json")
        av_from_code.playAnimation()
        av_from_code.loop(true)
        /* STEP 1: Block hijackers */
        val caller = callingActivity!!.packageName
        val organizationsSystem = ORGANIZATION_THEME_SYSTEMS.contains(caller)
        val supportedSystem = organizationsSystem || OTHER_THEME_SYSTEMS.contains(caller)
        if (!BuildConfig.SUPPORTS_THIRD_PARTY_SYSTEMS && !supportedSystem) {
            Log.e(tag, "This theme does not support the launching theme system. [HIJACK] ($caller)")
            Toast.makeText(this,
                    String.format(getString(R.string.unauthorized_theme_client_hijack), caller),
                    Toast.LENGTH_LONG).show()
            finish()
        }
        if (debug) {
            Log.d(tag, "'$caller' has been authorized to launch this theme. (Phase 1)")
        }

        /* STEP 2: Ensure that our support is added where it belongs */
        val action = intent.action
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        var verified = false
        if ((action == substratumIntentData) or (action == getKeysIntent)) {
            // Assume this called from organization's app
            if (organizationsSystem) {
                verified = when {
                    BuildConfig.ALLOW_THIRD_PARTY_SUBSTRATUM_BUILDS -> true
                    else -> checkApprovedSignature(this, caller)
                }
            }
        } else {
            OTHER_THEME_SYSTEMS
                    .filter { action?.startsWith(prefix = it, ignoreCase = true) ?: false }
                    .forEach { verified = true }
        }
        if (!verified) {
            Log.e(tag, "This theme does not support the launching theme system. ($action)")
            Toast.makeText(this, R.string.unauthorized_theme_client, Toast.LENGTH_LONG).show()
            finish()
            return
        }
        if (debug) {
            Log.d(tag, "'$action' has been authorized to launch this theme. (Phase 2)")
        }
        showDialog()
    }

    private fun startAntiPiracyCheck() {
        if (BuildConfig.BASE_64_LICENSE_KEY.isEmpty() && debug && !BuildConfig.DEBUG) {
            Log.e(tag, apkSignature)
        }

        if (!themePiracyCheck) {
            piracyChecker {
                if (BuildConfig.ENFORCE_GOOGLE_PLAY_INSTALL) {
                    enableInstallerId(InstallerID.GOOGLE_PLAY)
                }
                if (BuildConfig.BASE_64_LICENSE_KEY.isNotEmpty()) {
                    enableGooglePlayLicensing(BuildConfig.BASE_64_LICENSE_KEY)
                }
                if (BuildConfig.APK_SIGNATURE_PRODUCTION.isNotEmpty()) {
                    enableSigningCertificate(BuildConfig.APK_SIGNATURE_PRODUCTION)
                }
                callback {
                    allow {
                        val returnIntent = if (intent.action == getKeysIntent) {
                            Intent(receiveKeysIntent)
                        } else {
                            Intent()
                        }

                        val themeName = getString(R.string.ThemeName)
                        val themeAuthor = getString(R.string.ThemeAuthor)
                        val themePid = packageName
                        returnIntent.putExtra("theme_name", themeName)
                        returnIntent.putExtra("theme_author", themeAuthor)
                        returnIntent.putExtra("theme_pid", themePid)
                        returnIntent.putExtra("theme_debug", BuildConfig.DEBUG)
                        returnIntent.putExtra("theme_piracy_check", themePiracyCheck)
                        returnIntent.putExtra("encryption_key", BuildConfig.DECRYPTION_KEY)
                        returnIntent.putExtra("iv_encrypt_key", BuildConfig.IV_KEY)

                        val callingPackage = intent.getStringExtra("calling_package_name")
                        if (!isCallingPackageAllowed(callingPackage)) {
                            finish()
                        } else {
                            returnIntent.`package` = callingPackage
                        }

                        if (intent.action == substratumIntentData) {
                            setResult(getSelfSignature(applicationContext), returnIntent)
                        } else if (intent.action == getKeysIntent) {
                            returnIntent.action = receiveKeysIntent
                            sendBroadcast(returnIntent)
                        }
                        destroy()
                        finish()
                    }
                    doNotAllow { _, _ ->
                        val parse = String.format(
                                getString(R.string.toast_unlicensed),
                                getString(R.string.ThemeName))
                        Toast.makeText(this@SubstratumLauncher, parse, Toast.LENGTH_SHORT).show()
                        destroy()
                        finish()
                    }
                    onError { error ->
                        Toast.makeText(this@SubstratumLauncher, error.toString(), Toast.LENGTH_LONG)
                                .show()
                        destroy()
                        finish()
                    }
                }
            }.start()
        } else {
            Toast.makeText(this, R.string.unauthorized, Toast.LENGTH_LONG).show()
            finish()
        }
    }

    @SuppressLint("InflateParams")
    private fun showDialog() {
    val alertDialog = AlertDialog.Builder(this, R.style.Fullscreendialog)
            .setCancelable(false)

    val view = LayoutInflater.from(this).inflate(R.layout.fullscreen_dialog, null)
    val title = view.findViewById(R.id.title) as TextView
    title.text = getString(R.string.launch_dialog_title)

    /*Buttons*/
    val cont = view.findViewById(R.id.ic_continue) as ImageButton
    cont.setImageResource(R.drawable.ic_continue)
    cont.setOnClickListener { _ ->
        startAntiPiracyCheck()
    }
    val textContinue = view.findViewById(R.id.textContinue) as Button
    textContinue.setOnClickListener { _ ->
        startAntiPiracyCheck()

    }
    val textExit = view.findViewById(R.id.textExit) as Button
        textExit.setOnClickListener { _ ->
        finish()
    }


    alertDialog.setView(view)

    if (getDialogStatus()) {
        startAntiPiracyCheck()
    } else {
        alertDialog.show()
    }

}

    private fun getDialogStatus(): Boolean {
        val mSharedPreferences = getSharedPreferences("dialog", Context.MODE_PRIVATE)
        return mSharedPreferences.getBoolean("show_dialog_" + BuildConfig.VERSION_CODE, false)
}
}