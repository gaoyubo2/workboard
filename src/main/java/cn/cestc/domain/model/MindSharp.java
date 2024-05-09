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
@TableName(value = "mind_sharp")
public class MindSharp{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    
    public MindSharp(MindSharp mindSharp) {
        if (Objects.nonNull(mindSharp)) {
            this.id=mindSharp.id;
            this.name=mindSharp.name;
        }
    }
}
