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
@TableName(value = "mind_relation")
public class MindRelation{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer entityId;
    private Integer prevId;
    private Integer nextId;
    
    public MindRelation(MindRelation mindRelation) {
        if (Objects.nonNull(mindRelation)) {
            this.id=mindRelation.id;
            this.entityId=mindRelation.entityId;
            this.prevId=mindRelation.prevId;
            this.nextId=mindRelation.nextId;
        }
    }
}
