package cn.cestc.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MindEntityVO {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String data;
    // 额外信息
    private Integer sharpId;
    private Float positionX;
    private Float positionY;
    private Integer prevId;

    private List<MindEntityVO> children;
}
