package org.chad.bytecinema.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Captcha {
    private String uuid;
    private String image;
}
