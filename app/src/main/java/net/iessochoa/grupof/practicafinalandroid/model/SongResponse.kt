package net.iessochoa.grupof.practicafinalandroid.model

import com.google.gson.annotations.SerializedName

data class SongResponse (
    @SerializedName("tracks") var tracks: Song
    )