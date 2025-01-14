package com.neuroidreactnativesdk

import android.app.Application
import android.util.Log
import com.facebook.react.bridge.*
import com.neuroid.tracker.NeuroID

class NeuroidReactnativeSdkModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    private var reactApplicationCtx: ReactApplicationContext = reactContext
    private var application: Application? = reactContext.applicationContext as Application

    override fun getName(): String {
        return "NeuroidReactnativeSdk"
    }

    @ReactMethod
    fun configure(key: String) {
        val activityCaller = reactApplicationCtx.currentActivity
        if(NeuroID.getInstance() == null) {
            val neuroID = NeuroID.Builder(application, key).build()
            NeuroID.setNeuroIdInstance(neuroID)
        }
        NeuroID.getInstance()?.configureWithOptions(key, null)
    }

    @ReactMethod
    fun configureWithOptions(key: String, endpoint: String?) {
        val activityCaller = reactApplicationCtx.currentActivity
        if(NeuroID.getInstance() == null) {
            val neuroID = NeuroID.Builder(application, key).build()
            NeuroID.setNeuroIdInstance(neuroID)
        }
        NeuroID.getInstance()?.configureWithOptions(key, endpoint)
    }

    @ReactMethod
    fun start() {
        NeuroID.getInstance()?.start()
    }

    @ReactMethod
    fun stop() {
        NeuroID.getInstance()?.stop()
    }

    @ReactMethod
    fun formSubmit() {
        NeuroID.getInstance()?.formSubmit()
    }

    @ReactMethod
    fun formSubmitSuccess() {
        NeuroID.getInstance()?.formSubmitSuccess()
    }

    @ReactMethod
    fun formSubmitFailure() {
        NeuroID.getInstance()?.formSubmitFailure()
    }

    @ReactMethod
    fun captureEvent(event: String, tags: String) {
        NeuroID.getInstance()?.captureEvent(event, tags)
    }

    @ReactMethod
    fun setUserID(id: String) {
        NeuroID.getInstance()?.setUserID(id)
    }

    @ReactMethod
    fun setScreenName(screen: String) {
        NeuroID.getInstance()?.setScreenName(screen)
    }

    @ReactMethod
    fun getSessionID(promise: Promise) {
        promise.resolve(NeuroID.getInstance()?.getSessionId())
    }

    @ReactMethod
    fun excludeViewByTestID(id: String) {
        NeuroID.getInstance()?.excludeViewByResourceID(id)
    }

    @ReactMethod
    fun setEnvironmentProduction(isProd: Boolean) {
        val environment = if(isProd) {
            "LIVE"
        } else {
            "TEST"
        }

        NeuroID.getInstance()?.setEnvironment(environment)
    }

    @ReactMethod
    fun setSiteId(siteId: String) {
        NeuroID.getInstance()?.setSiteId(siteId)
    }

    @ReactMethod
    fun isStopped(promise: Promise) {
        val instance = NeuroID.getInstance()
        if(instance == null)
            promise.resolve(true)
        else
            promise.resolve(instance.isStopped())
    }
}
