package net.iessochoa.grupof.practicafinalandroid.model

import com.google.gson.annotations.SerializedName

data class SongResponse (@SerializedName("tracks") var tracks: Tracks)

data class Tracks (@SerializedName("href") var href: String, @SerializedName("items") var trackItems: List<TrackItem>)

data class TrackItem (@SerializedName("album") var album: Album, @SerializedName("name") var name: String)

data class Album (@SerializedName("images") var images: List<TrackImage>, @SerializedName("release_date") var release_date: String)

data class TrackImage (@SerializedName("url") var url: String)