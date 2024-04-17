package cn.cestc.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "oss")
public class Oss implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID", position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户ID", position = 2)
    private Integer uid;
    @ApiModelProperty(value = "工作板", position = 3)
    private String project;
    @ApiModelProperty(value = "文件名", position = 4)
    private String filename;
    @ApiModelProperty(value = "桶名", position = 5)
    private String bucket;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private Integer roleId;
    private String roleName;
    private String extra;
    
    public Oss(Oss oss) {
        if (Objects.nonNull(oss)) {
            this.id=oss.id;
            this.uid=oss.uid;
            this.project=oss.project;
            this.filename=oss.filename;
            this.bucket=oss.bucket;
            this.createTime=oss.createTime;
            this.roleId=oss.roleId;
            this.roleName=oss.roleName;
            this.extra=oss.extra;
        }
    }
}
