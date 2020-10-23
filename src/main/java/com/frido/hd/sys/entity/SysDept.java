package com.frido.hd.sys.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
public class SysDept extends Model<SysDept> {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 部门编号
     */
    private String deptNo;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父级id
     */
    private String pid;

    /**
     * 状态(1:正常；0:弃用)
     */
    private Boolean status;

    /**
     * 为了维护更深层级关系(规则：父级关系编码+自己的编码)
     */
    private String relationCode;

    /**
     * 部门经理user_id
     */
    private String deptManagerId;

    /**
     * 部门经理名称
     */
    private String managerName;

    /**
     * 部门经理联系电话
     */
    private String phone;

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
