package cn.cestc.domain.vo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatWithEventVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Integer userId;
    private Integer eventId;
    private LocalDateTime createTime;
    private String jsonData;
    private Integer sessionId;
    private String source;


}
