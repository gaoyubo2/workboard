package cn.cestc.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.cestc.domain.MindPosition;
import xin.altitude.cms.common.entity.AjaxResult;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import xin.altitude.cms.common.entity.PageEntity;
import cn.cestc.mapper.MindPositionMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import cn.cestc.service.IMindPositionService;
@RestController
@RequestMapping("/cestc/mind/position")
public class MindPositionController{
    @Autowired
    private IMindPositionService mindPositionService;
    @GetMapping("/page")
    public AjaxResult page(PageEntity pageEntity,MindPosition mindPosition){
        return AjaxResult.success(mindPositionService.page(pageEntity.toPage(), Wrappers.lambdaQuery(mindPosition)));
    }
    @GetMapping("/list")
    public AjaxResult list(MindPosition mindPosition){
        return AjaxResult.success(mindPositionService.list(Wrappers.lambdaQuery(mindPosition)));
    }
    @PostMapping("/push")
    public AjaxResult add(@RequestBody MindPosition mindPosition) {
        return AjaxResult.success(mindPositionService.save(mindPosition));
    }
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody MindPosition mindPosition) {
        return AjaxResult.success(mindPositionService.updateById(mindPosition));
    }
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delete(@PathVariable Integer[] ids) {
        return AjaxResult.success(mindPositionService.removeByIds(Arrays.asList(ids)));
    }
    @GetMapping(value = "/detail/{id}")
    public AjaxResult detail(@PathVariable("id") Integer id) {
        return AjaxResult.success(mindPositionService.getById(id));
    }
}
