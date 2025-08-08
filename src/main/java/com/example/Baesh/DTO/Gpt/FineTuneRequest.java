package com.example.Baesh.DTO.Gpt;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class FineTuneRequest {
    @SerializedName("training_file")
    private String trainingFile;

    private String model;

    public FineTuneRequest(String trainingFile, String model) {
        this.trainingFile = trainingFile;
        this.model = model;
    }
}
