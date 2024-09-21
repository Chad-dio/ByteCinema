package org.chad.bytecinema.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.chad.bytecinema.common.database.BaseDO;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Data
@TableName("t_favorites")
public class Favorites extends BaseDO implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "得给你的收藏夹取个名字吧?")
    private String name;

    private String description;

    private Long userId;

    // 收藏夹下的视频总数
    @TableField(exist = false)
    private Long videoCount;
}
