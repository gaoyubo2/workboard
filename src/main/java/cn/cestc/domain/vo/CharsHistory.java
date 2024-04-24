package cn.cestc.domain.vo;

import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharsHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    private String text;
    private String userId;
    private String time;
    private String userName;
    private Date date;
}
