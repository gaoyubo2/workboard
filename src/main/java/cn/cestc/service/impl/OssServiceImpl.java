package cn.cestc.service.impl;

import cn.cestc.domain.model.Oss;
import cn.cestc.domain.property.OssProperties;
import cn.cestc.domain.vo.UserInfoVO;
import cn.cestc.mapper.OssMapper;
import cn.cestc.service.IOssService;
import cn.cestc.service.SsoService;
import cn.cestc.template.OssTemplate;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class OssServiceImpl extends ServiceImpl<OssMapper, Oss> implements IOssService {
    @Resource
    private OssProperties ossProperties;

    private final OssTemplate minioTemplate;
    private final SsoService ssoService;

    /**
     * 插入数据库uploadFileDTO
     */
    private boolean insertUploadInfo(String filePath, Integer uid, String fileName, Integer roleId, String roleName, String extra, String username) {
        Oss oss = getOss(filePath, uid, fileName, roleId, roleName,extra,username);
        int cnt = baseMapper.insert(oss);
        return cnt > 0;
    }

    private Oss getOss(String filePath, Integer uid, String fileName, Integer roleId, String roleName, String extra, String username) {
        Oss oss = new Oss();
        oss.setBucket(ossProperties.getBucketName());
        oss.setUid(uid);
        oss.setProject(filePath);
        oss.setFilename(fileName);
        oss.setRoleId(roleId);
        oss.setRoleName(roleName);
        oss.setExtra(extra);
        oss.setUsername(username);
        return oss;
    }

    @Override
    public boolean uploadFile(MultipartFile file, Integer uid, String filePath, String extra) {
        String fileName = file.getOriginalFilename();
        //获取用户角色信息
        UserInfoVO userInfoWithRole = ssoService.getUserInfoById(uid);
        if(userInfoWithRole == null) return false;
        //上传到minio
        minioTemplate.upLoadFile(filePath, fileName, file);
        //同步更新数据库
        return insertUploadInfo(filePath, uid, fileName,userInfoWithRole.getRoleId(),userInfoWithRole.getRoleName(),extra,userInfoWithRole.getUsername());
    }

    @Override
    @Transactional
    public boolean removeFilesByIds(Integer[] ids) {
        //删除数据库文件信息
        boolean removeDataBaseFlag = this.removeByIds(Arrays.asList(ids));
        //删除Minio文件
        boolean removeMinioFlag = minioTemplate.removeFiles(Collections.singletonList(Arrays.toString(ids)));
        return removeDataBaseFlag && removeMinioFlag;
    }
}
