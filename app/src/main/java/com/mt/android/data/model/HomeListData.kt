package com.mt.android.data.model

import com.google.gson.annotations.SerializedName

data class MainList(
    @SerializedName("hits") var data: ArrayList<MainListDataResponse> = arrayListOf()
)

data class MainListDataResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("tags") var imageTag: String? = null,
    @SerializedName("type") var imageType: String? = null,
    @SerializedName("likes") var imageLike: String? = null,
    @SerializedName("previewURL") var previewURL: String? = null,
    @SerializedName("comments") var imageCommentsCount: String? = null,
    @SerializedName("user_id") var userID: String? = null,
    @SerializedName("largeImageURL") var imageUrl: String? = null,
    @SerializedName("user") var userName: String? = null,
    @SerializedName("userImageURL") var userImageURL: String? = null,
)