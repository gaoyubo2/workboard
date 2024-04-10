package cn.cestc.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "chat")
public class Chat{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer eventId;
    private String jsonData;
    
    public Chat(Chat chat) {
        if (Objects.nonNull(chat)) {
            this.id=chat.id;
            this.userId=chat.userId;
            this.eventId=chat.eventId;
            this.jsonData=chat.jsonData;
        }
    }
}
