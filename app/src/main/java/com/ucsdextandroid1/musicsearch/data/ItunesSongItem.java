package com.ucsdextandroid1.musicsearch.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rjaylward on 2019-05-11
 */
public class ItunesSongItem implements SongItem {

    //TODO add all the members to this class with proper serialized names
    @SerializedName("trackId")
    private long trackId;
    @SerializedName("trackCensoredName")
    private String trackName;
    @SerializedName("artistName")
    private String artistName;
    @SerializedName("artworkUrl60")
    private String artworkUrl;
    @SerializedName("previewUrl")
    private String previewUrl;
    @SerializedName("trackTimeMillis")
    private long trackTime;
    @SerializedName("collectionName")
    private String  collectionName;

    public ItunesSongItem(long trackId, String trackName, String artistName,
                          String artworkUrl, String previewUrl, long trackTime, String collectionName){
        this.trackId = trackId;
        this.trackName = trackName;
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.artworkUrl = artworkUrl;
        this.previewUrl = previewUrl;
        this.trackTime = trackTime;
    }
    @Override
    public long getTrackId() {
        return trackId;
    }

    @Override
    public String getTrackName() {
        return trackName;
    }

    @Override
    public String getArtistName() {
        return artistName;
    }

    @Override
    public String getAlbumName() {
        return collectionName;
    }

    @Override
    public String getArtworkUrl() {
        return artworkUrl;
    }

    @Override
    public String getPreviewUrl() {
        return previewUrl;
    }

    @Override
    public long getTrackTime() {
        return trackTime;
    }

}
