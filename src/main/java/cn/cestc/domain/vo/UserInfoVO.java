package cn.cestc.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer uid;

    private String username;

    private Integer roleId;

    private String roleName;

    private Date createTime;

    private Integer isDelete;
}