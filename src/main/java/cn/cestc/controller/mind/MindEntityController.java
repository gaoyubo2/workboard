package cn.cestc.controller.mind;

import cn.cestc.domain.dto.MindEntityDTO;
import cn.cestc.domain.model.MindEntity;
import cn.cestc.service.IMindEntityService;
import cn.cestc.service.IMindRelationService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/mind")
@RequiredArgsConstructor
public class MindEntityController{
    private final IMindRelationService mindRelationService;
    private final IMindEntityService mindEntityService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,MindEntity mindEntity){
        return AjaxResult.success(mindEntityService.page(pageEntity.toPage(), Wrappers.lambdaQuery(mindEntity)));
    }
    @GetMapping("/list")
    public AjaxResult list(MindEntity mindEntity){
        return AjaxResult.success(mindEntityService.list(Wrappers.lambdaQuery(mindEntity)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody MindEntity mindEntity) {
        return AjaxResult.success(mindEntityService.save(mindEntity));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody MindEntity mindEntity) {
        return AjaxResult.success(mindEntityService.updateById(mindEntity));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(mindEntityService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(mindEntityService.getById(id));
    }

    @GetMapping("/nextAll")
    public AjaxResult nextList(@RequestParam("id") Integer id){
        List<MindEntityDTO> nextEntityList =  mindEntityService.getNextList(id);

        return AjaxResult.success(nextEntityList);
    }
    @GetMapping("/nextChild")
    public AjaxResult nextChild(@RequestParam("id") Integer id){
        List<MindEntityDTO> nextEntityList =  mindEntityService.getNextChildList(id);
        return AjaxResult.success(nextEntityList);
    }

    @DeleteMapping("/deleteSelf")
    public AjaxResult deleteSelf(@RequestParam("id") Integer id){
        boolean flag = mindEntityService.deleteSelf(id);
        return flag ? AjaxResult.success("删除节点成功") : AjaxResult.error("删除节点失败-Controller");
    }

    @PutMapping("/changePre")
    public AjaxResult changePre(@RequestParam("id") Integer id,
                                @RequestParam("newPreId") Integer newPreId){
        boolean flag = mindEntityService.changePre(id, newPreId);
        return flag ? AjaxResult.success("修改节点成功") : AjaxResult.error("修改节点失败-Controller");

    }
}
