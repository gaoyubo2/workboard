package cn.cestc.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
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
