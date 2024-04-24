package cn.cestc.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "template")
public class Template implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键，自增", position = 1)
    private Long id;
    @ApiModelProperty(value = "名称，模板名称", position = 2)
    private String name;
    @ApiModelProperty(value = "模板内容", position = 3)
    private String content;
    
    public Template(Template template) {
        if (Objects.nonNull(template)) {
            this.id=template.id;
            this.name=template.name;
            this.content=template.content;
        }
    }
}
