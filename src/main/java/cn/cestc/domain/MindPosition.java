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
