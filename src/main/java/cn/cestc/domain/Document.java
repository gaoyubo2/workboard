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
@TableName(value = "document")
@Builder
public class Document implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键，自增", position = 1)
    private Integer id;
    @ApiModelProperty(value = "文档名称", position = 2)
    private String name;
    @ApiModelProperty(value = "文档id", position = 3)
    private String padid;
    @ApiModelProperty(value = "作者姓名，多个作者用逗号分隔", position = 4)
    private String author;
    @ApiModelProperty(value = "进度", position = 5)
    private String progress;
    @ApiModelProperty(value = "来源", position = 6)
    private String source;
    
    public Document(Document document) {
        if (Objects.nonNull(document)) {
            this.id=document.id;
            this.name=document.name;
            this.padid=document.padid;
            this.author=document.author;
        }
    }
}
