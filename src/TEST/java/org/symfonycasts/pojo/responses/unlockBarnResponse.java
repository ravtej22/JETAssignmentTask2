package org.symfonycasts.pojo.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class unlockBarnResponse {

  @JsonProperty("action")
  String action;
  @JsonProperty("success")
  boolean success;

  @JsonProperty("message")
  String message;

  @JsonProperty("data")
  String data;



}
