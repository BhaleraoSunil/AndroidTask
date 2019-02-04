
package com.augdeck.task.pojos;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoryText {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("matchLevel")
    @Expose
    private String matchLevel;
    @SerializedName("matchedWords")
    @Expose
    private List<Object> matchedWords = new ArrayList<Object>();

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMatchLevel() {
        return matchLevel;
    }

    public void setMatchLevel(String matchLevel) {
        this.matchLevel = matchLevel;
    }

    public List<Object> getMatchedWords() {
        return matchedWords;
    }

    public void setMatchedWords(List<Object> matchedWords) {
        this.matchedWords = matchedWords;
    }

}
