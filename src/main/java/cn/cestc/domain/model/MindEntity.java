package cn.cestc.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "mind_entity")
public class MindEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer categoryId;
    private String data;
    
    public MindEntity(MindEntity mindEntity) {
        if (Objects.nonNull(mindEntity)) {
            this.id=mindEntity.id;
            this.name=mindEntity.name;
            this.categoryId=mindEntity.categoryId;
            this.data=mindEntity.data;
        }
    }
}
