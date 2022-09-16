package com.example.samplelist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemObject(val textoItem: String, val detailItem: String) : Parcelable
