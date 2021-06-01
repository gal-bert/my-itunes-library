package com.gregorius.mcslecproject;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaListResponse {

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<MediaResponse> mediaResponses = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<MediaResponse> getResults() {
        return mediaResponses;
    }

    public void setResults(List<MediaResponse> mediaResponses) {
        this.mediaResponses = mediaResponses;
    }

}
