package org.chad.bytecinema.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.chad.bytecinema.common.database.BaseDO;

import java.io.Serializable;

public class User extends BaseDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码，加密存储
     */
    private String password;


    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户喜爱id
     */
    private Long defaultFavoritesId;
}
