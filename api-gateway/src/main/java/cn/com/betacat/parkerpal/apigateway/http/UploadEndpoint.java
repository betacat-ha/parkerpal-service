package cn.com.betacat.parkerpal.apigateway.http;

import cn.com.betacat.parkerpal.common.utils.UploadUtils;
import cn.com.betacat.parkerpal.domain.base.ResResult;
import cn.com.betacat.parkerpal.domain.enums.RespEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 * @since 2024-08-14 17:19:48
 */
@Slf4j
@RestController
@Api(tags = "文件上传")
@RequestMapping("upload")
public class UploadEndpoint {
    @Value("${file.upload}")
    private String uploadPath;



    @PostMapping("/files")
    @ApiOperation(value = "文件上传")
    @ApiImplicitParam(name = "files", value = "文件上传", required = true, dataType = "MultipartFile", allowMultiple = true, paramType = "query")
    public ResResult<List<String>> files(@RequestParam("files") MultipartFile[] files) {
        try {
            return ResResult.success(UploadUtils.saveFilesToLocal(files, uploadPath));
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return ResResult.error(RespEnum.FAILURE.getCode(), "文件上传失败");
        }
    }


}