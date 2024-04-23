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
@TableName(value = "chat")
public class Chat implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键", position = 1)
    private Integer id;
    @ApiModelProperty(value = "用户id", position = 2)
    private Integer userId;
    @ApiModelProperty(value = "案件板id", position = 3)
    private Integer eventId;
    @ApiModelProperty(value = "创建时间", position = 4)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "会话数据", position = 5)
    private String jsonData;
    
    public Chat(Chat chat) {
        if (Objects.nonNull(chat)) {
            this.id=chat.id;
            this.userId=chat.userId;
            this.eventId=chat.eventId;
            this.createTime=chat.createTime;
            this.jsonData=chat.jsonData;
        }
    }
}
