package com.frido.hd.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser extends Model<SysUser> {

private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
    @TableId("id")
    private String id;

    /**
     * 账户名称
     */
    private String username;

    /**
     * 加密盐值
     */
    private String salt;

    /**
     * 用户密码密文
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 邮箱(唯一)
     */
    private String email;

    /**
     * 账户状态(1.正常 2.锁定 )
     */
    private Boolean status;

    /**
     * 性别(1.男 2.女)
     */
    private Boolean sex;

    /**
     * 是否删除(1未删除；0已删除)
     */
    private Boolean deleted;

    /**
     * 创建人
     */
    private String createId;

    /**
     * 更新人
     */
    private String updateId;

    /**
     * 创建来源(1.web 2.android 3.ios )
     */
    private Boolean createWhere;

    /**
     * 创建时间
     */
    private Date createTime;

    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
