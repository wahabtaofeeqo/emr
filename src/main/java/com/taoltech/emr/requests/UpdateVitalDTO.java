package com.taoltech.emr.requests;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateVitalDTO {
    // 20c17a7c-e6b4-49de-8646-efc792917cea
    @JsonProperty("blood")
    private String blood;

    @JsonProperty("heart")
    private String heart;

    @JsonProperty("oxygen")
    private String oxygen;

    @JsonProperty("respiratory")
    private String respiratory;

    @JsonProperty("temperature")
    private String temperature;
}
