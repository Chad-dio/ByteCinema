package org.chad.bytecinema.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginVO {
    private String accessToken;
    private String refreshToken;
}
