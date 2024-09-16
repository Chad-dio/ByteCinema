package org.chad.bytecinema.domain.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.chad.bytecinema.common.database.BaseDO;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_permission")
public class Permission extends BaseDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("p_id")
    private Long pId;
    private String path;
    private String href;
    private String icon;
    private String name;
    @TableField("is_menu")
    private Integer isMenu;
    private String target;
    private Integer sort;
    private Integer state;
}
