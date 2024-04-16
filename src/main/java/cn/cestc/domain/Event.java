package cn.cestc.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 工作板的案件表 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "event")
public class Event{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键", position = 1)
    private Integer id;
    @ApiModelProperty(value = "名称", position = 2)
    private String name;
    @ApiModelProperty(value = "创建时间", position = 3)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "创建人ID", position = 4)
    private Integer creatorId;
    @ApiModelProperty(value = "更新时间", position = 5)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "修改人ID", position = 6)
    private Integer updaterId;
    @ApiModelProperty(value = "备注信息", position = 7)
    private String remarks;
    @ApiModelProperty(value = "案件类型", position = 8)
    private String eventRole;
    public Event(Event event) {
        if (Objects.nonNull(event)) {
            this.id=event.id;
            this.name=event.name;
            this.createTime=event.createTime;
            this.creatorId=event.creatorId;
            this.updateTime=event.updateTime;
            this.updaterId=event.updaterId;
            this.remarks=event.remarks;
            this.eventRole=event.eventRole;
        }
    }
}
