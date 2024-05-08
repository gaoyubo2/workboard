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
@TableName(value = "mind_entity")
public class MindEntity{
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
