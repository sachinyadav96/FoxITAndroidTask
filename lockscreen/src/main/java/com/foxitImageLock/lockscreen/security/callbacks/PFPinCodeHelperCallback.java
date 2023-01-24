package com.foxitImageLock.lockscreen.security.callbacks;

import com.foxitImageLock.lockscreen.security.PFResult;

public interface PFPinCodeHelperCallback<T> {
    void onResult(PFResult<T> result);
}
