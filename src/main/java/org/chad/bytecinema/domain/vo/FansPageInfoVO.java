package org.chad.bytecinema.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FansPageInfoVO {
    private String email;

    private String username;

    private String headImg;

    private Boolean followed;
}
