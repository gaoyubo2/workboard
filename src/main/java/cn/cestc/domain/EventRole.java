package cn.cestc.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "event_role")
public class EventRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "案件类型", position = 2)
    private String name;
    
    public EventRole(EventRole eventRole) {
        if (Objects.nonNull(eventRole)) {
            this.id=eventRole.id;
            this.name=eventRole.name;
        }
    }
}
