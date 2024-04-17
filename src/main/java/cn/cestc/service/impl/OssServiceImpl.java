package cn.cestc.service.impl;
import cn.cestc.domain.MinioProperties;
import cn.cestc.domain.Oss;
import cn.cestc.domain.dto.UploadFileDTO;
import cn.cestc.domain.vo.UserInfoVO;
import cn.cestc.mapper.OssMapper;
import cn.cestc.service.IOssService;
import cn.cestc.service.SsoService;
import cn.cestc.template.OssTemplate;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@Service
@RequiredArgsConstructor
public class OssServiceImpl extends ServiceImpl<OssMapper, Oss> implements IOssService {
    @Resource
    private MinioProperties ossProperties;

    private final OssTemplate minioTemplate;
    private final SsoService ssoService;

    /**
     * 插入数据库uploadFileDTO
     */
    private boolean insertUploadInfo(String filePath, Integer uid, String fileName,Integer roleId, String roleName) {
        Oss oss = getOss(filePath, uid, fileName, roleId, roleName);
        int cnt = baseMapper.insert(oss);
        return cnt > 0;
    }

    private Oss getOss(String filePath, Integer uid, String fileName, Integer roleId, String roleName) {
        Oss oss = new Oss();
        oss.setBucket(ossProperties.getBucketName());
        oss.setUid(uid);
        oss.setProject(filePath);
        oss.setFilename(fileName);
        oss.setRoleId(roleId);
        oss.setRoleName(roleName);
        return oss;
    }

    @Override
    public boolean uploadFile(MultipartFile file, Integer uid, String filePath) {
        String fileName = file.getOriginalFilename();
        //上传到minio
        minioTemplate.upLoadFile(filePath, fileName, file);
        //获取用户角色信息
        UserInfoVO userInfoWithRole = ssoService.getUserInfoById(uid);
        System.out.println(userInfoWithRole);
        //同步更新数据库
        return insertUploadInfo(filePath, uid, fileName,userInfoWithRole.getRoleId(),userInfoWithRole.getRoleName());
    }
}
