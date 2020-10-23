package com.frido.hd.sys.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ldhao
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole extends Model<SysRole> {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 角色名称
     */
    private String name;

    private String description;

    /**
     * 状态(1:正常0:弃用)
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除(1未删除；0已删除)
     */
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
