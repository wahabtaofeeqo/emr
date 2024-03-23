package com.taoltech.emr.requests;

import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taoltech.emr.models.Vital;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class VitalDTO {

    @NotBlank(message = "Temperature is required")
    private String temperature;
    
    @NotBlank(message = "Blood pressure is required")
    private String blood;
    
    @NotBlank(message = "Respiratory is required")
    private String respiratory;

    @NotBlank(message = "Heart rate is required")
    private String heart;

    @NotBlank(message = "Oxygen is required")
    private String oxygen;

    @NotNull(message = "Patient ID is required")
    private UUID patientId;

    public Vital toVital() {
        Vital vital = new Vital();
        vital.setBlood(blood);
        vital.setHeart(heart);
        vital.setOxygen(oxygen);
        vital.setRespiratory(respiratory);
        vital.setTemperature(temperature);

        //
        return vital;
    }

    /**
     * InnerVitalDTO
     */
    @Setter
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class Update {
        
        @JsonProperty("blood")
        private Optional<String> blood;
    
        @JsonProperty("blood")
        private Optional<String> heart;
    
        @JsonProperty("blood")
        private Optional<String> oxygen;
    
        @JsonProperty("blood")
        private Optional<String> respiratory;

        @JsonProperty("blood")
        private Optional<String> temperature;


    }
}
