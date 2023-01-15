package org.symfonycasts.pojo.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
//@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenRequest {

  public String client_id;
  public String client_secret;
  public String grant_type;

}
