package com.foxitImageLock.lockscreen.security;

public class PFSecurityManager {
    private static final PFSecurityManager ourInstance = new PFSecurityManager();
    private IPFPinCodeHelper mPinCodeHelper = PFFingerprintPinCodeHelper.getInstance();

    private PFSecurityManager() {
    }

    public static PFSecurityManager getInstance() {
        return ourInstance;
    }

    public IPFPinCodeHelper getPinCodeHelper() {
        return mPinCodeHelper;
    }

    public void setPinCodeHelper(IPFPinCodeHelper pinCodeHelper) {
        mPinCodeHelper = pinCodeHelper;
    }
}
