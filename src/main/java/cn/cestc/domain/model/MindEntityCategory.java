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
@TableName(value = "mind_entity_category")
public class MindEntityCategory{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer sharpId;
    
    public MindEntityCategory(MindEntityCategory mindEntityCategory) {
        if (Objects.nonNull(mindEntityCategory)) {
            this.id=mindEntityCategory.id;
            this.name=mindEntityCategory.name;
            this.sharpId=mindEntityCategory.sharpId;
        }
    }
}
