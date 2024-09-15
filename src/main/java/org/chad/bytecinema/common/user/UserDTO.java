package org.chad.bytecinema.common.user;

import lombok.Data;

@Data
public class UserDTO {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 用户头像
     */
    private String icon = "";
}