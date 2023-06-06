package com.example.plantbook.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Plants(val name: String, val type: String, val heigh: String, val details: String?):
    Parcelable