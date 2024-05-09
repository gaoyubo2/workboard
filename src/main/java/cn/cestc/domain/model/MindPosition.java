package cn.cestc.domain.model;
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
@TableName(value = "mind_position")
public class MindPosition{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer entityId;
    private Float positionX;
    private Float positionY;
    
    public MindPosition(MindPosition mindPosition) {
        if (Objects.nonNull(mindPosition)) {
            this.id=mindPosition.id;
            this.entityId=mindPosition.entityId;
            this.positionX=mindPosition.positionX;
            this.positionY=mindPosition.positionY;
        }
    }
}
