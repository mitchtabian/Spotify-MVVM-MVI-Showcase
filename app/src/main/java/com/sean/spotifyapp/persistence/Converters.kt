package com.sean.spotifyapp.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sean.spotifyapp.models.*
import java.lang.reflect.Type

class Converters {

    @TypeConverter
    fun fromStringToCategories(value: String): List<CategoryItem>{
        val categoryListType: Type = object : TypeToken<List<CategoryItem>>() {}.type
        return Gson().fromJson(value, categoryListType)
    }

    @TypeConverter
    fun fromCategoriesToString(list: List<CategoryItem>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToImages(value: String): List<Image>{
        val imageListType: Type = object : TypeToken<List<Image>>() {}.type
        return Gson().fromJson(value, imageListType)
    }

    @TypeConverter
    fun fromImagesToString(list: List<Image>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToOwner(value: String): Owner{
        val ownerType: Type = object : TypeToken<Owner>() {}.type
        return Gson().fromJson(value, ownerType)
    }

    @TypeConverter
    fun fromOwnerToString(owner: Owner): String? {
        val gson = Gson()
        return gson.toJson(owner)
    }

    @TypeConverter
    fun fromStringToTracks(value: String): Tracks {
        val trackType: Type = object : TypeToken<Tracks>() {}.type
        return Gson().fromJson(value, trackType)
    }

    @TypeConverter
    fun fromTracksToString(tracks: Tracks): String? {
        val gson = Gson()
        return gson.toJson(tracks)
    }

    @TypeConverter
    fun fromStringToAlbum(value: String): Album {
        val albumType: Type = object : TypeToken<Album>() {}.type
        return Gson().fromJson(value, albumType)
    }

    @TypeConverter
    fun fromAlbumToString(album: Album): String? {
        val gson = Gson()
        return gson.toJson(album)
    }

    @TypeConverter
    fun fromStringToArtistItem(value: String): Artist {
        val artistType: Type = object : TypeToken<Artist>() {}.type
        return Gson().fromJson(value, artistType)
    }

    @TypeConverter
    fun fromArtistItemToString(artist: Artist): String? {
        val gson = Gson()
        return gson.toJson(artist)
    }

    @TypeConverter
    fun fromStringToFollowers(value: String): Followers {
        val followersType: Type = object : TypeToken<Followers>() {}.type
        return Gson().fromJson(value, followersType)
    }

    @TypeConverter
    fun fromFollowerToString(followers: Followers): String? {
        val gson = Gson()
        return gson.toJson(followers)
    }

    @TypeConverter
    fun fromStringToArtistItems(value: String): List<Artist> {
        val artistType: Type = object : TypeToken<List<Artist>>() {}.type
        return Gson().fromJson(value, artistType)
    }

    @TypeConverter
    fun fromArtistItemsToString(artist: List<Artist>): String? {
        val gson = Gson()
        return gson.toJson(artist)
    }

    @TypeConverter
    fun fromStringToStringList(value: String): List<String>?{
        val listStringType: Type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listStringType)
    }

    @TypeConverter
    fun fromStringListToString(list: List<String>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

}