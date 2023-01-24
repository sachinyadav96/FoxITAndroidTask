package com.mt.android.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mt.android.adapters.HomeImageAdapter.AccountHolder
import com.mt.android.data.model.MainListDataResponse
import com.mt.android.foxit.R

class HomeImageAdapter(
    private val mContext: Context, private val list: ArrayList<MainListDataResponse>
) : RecyclerView.Adapter<AccountHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountHolder {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.row_imagedata, parent, false)
        return AccountHolder(view)
    }

    override fun onBindViewHolder(holder: AccountHolder, position: Int) {
        val data: MainListDataResponse = list[holder.adapterPosition]
        holder.imageLike.text = data.imageLike
        holder.userComment.text = data.imageCommentsCount
        holder.userName.text = data.userName
        holder.imageTags.text = "Tag's: ${data.imageTag}"
        Glide.with(mContext).load(data.previewURL).error(R.drawable.security)
            .placeholder(R.mipmap.ic_launcher).into(holder.imageViewPreview)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class AccountHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageLike: TextView
        var imageViewPreview: ImageView
        var userComment: TextView
        var userName: TextView
        var imageTags: TextView

        init {
            imageLike = itemView.findViewById(R.id.txt_Like)
            userComment = itemView.findViewById(R.id.txt_Comment)
            userName = itemView.findViewById(R.id.txt_userName)
            imageTags = itemView.findViewById(R.id.txt_ImageTags)
            imageViewPreview = itemView.findViewById(R.id.prvImageView)
        }
    }
}