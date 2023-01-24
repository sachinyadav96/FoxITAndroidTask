package com.foxitImageLock.lockscreen.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.foxitImageLock.lockscreen.security.PFResult;
import com.foxitImageLock.lockscreen.security.PFSecurityManager;
import com.foxitImageLock.lockscreen.security.callbacks.PFPinCodeHelperCallback;
import com.foxitImageLock.lockscreen.security.livedata.PFLiveData;

public class PFPinCodeViewModel extends ViewModel {

    public LiveData<PFResult<String>> encodePin(Context context, String pin) {
        final PFLiveData<PFResult<String>> liveData = new PFLiveData<>();
        PFSecurityManager.getInstance().getPinCodeHelper().encodePin(
                context,
                pin,
                liveData::setData
        );
        return liveData;
    }

    public LiveData<PFResult<Boolean>> checkPin(Context context, String encodedPin, String pin) {
        final PFLiveData<PFResult<Boolean>> liveData = new PFLiveData<>();
        PFSecurityManager.getInstance().getPinCodeHelper().checkPin(
                context,
                encodedPin,
                pin,
                liveData::setData
        );
        return liveData;
    }

    public LiveData<PFResult<Boolean>> delete() {
        final PFLiveData<PFResult<Boolean>> liveData = new PFLiveData<>();
        PFSecurityManager.getInstance().getPinCodeHelper().delete(
                result -> liveData.setData(result)
        );
        return liveData;
    }

    public LiveData<PFResult<Boolean>> isPinCodeEncryptionKeyExist() {
        final PFLiveData<PFResult<Boolean>> liveData = new PFLiveData<>();
        PFSecurityManager.getInstance().getPinCodeHelper().isPinCodeEncryptionKeyExist(
                result -> liveData.setData(result)
        );
        return liveData;
    }

}
