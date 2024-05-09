package cn.cestc.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MindEntityDTO {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String data;
    // 额外信息
    private Integer sharpId;
    private Float positionX;
    private Float positionY;
    private Integer prevId;

    private List<MindEntityDTO> children;
}
