package com.mt.android.data.repository

import com.mt.android.data.network.MyApi
import com.mt.android.data.network.RetrofitHelper
import com.mt.android.util.Constant


class RetrofitRepository {

    var retrofitClient: MyApi = RetrofitHelper.instance

        suspend fun getData(searchData: String) = retrofitClient.getImageDataList(Constant.tokenKey,searchData,"image","pretty")

}
