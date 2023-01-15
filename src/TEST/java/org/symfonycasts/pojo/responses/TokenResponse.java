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
public class TokenResponse {


  @JsonProperty("access_token")
  String accessToken;
  @JsonProperty("token_type")
  String tokenType;
  @JsonProperty("scope")
  String scope;
  @JsonProperty("expires_in")
  Long expiresIn;


}
