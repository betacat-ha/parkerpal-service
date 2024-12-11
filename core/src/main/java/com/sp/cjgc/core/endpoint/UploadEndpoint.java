package com.sp.cjgc.core.endpoint;

import com.sp.cjgc.common.exception.ResponseObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Zoey
 * @Since: 2024-08-14 17:19:48
 * @Description:
 */
@RestController
@Api(tags = "文件上传")
@RequestMapping("upload")
public class UploadEndpoint {

    @PostMapping("/files")
    @ApiOperation(value = "文件上传")
    @ApiImplicitParam(name = "files", value = "文件上传", required = true, dataType = "MultipartFile", allowMultiple = true, paramType = "query")
    public ResponseObject<String> files(@RequestParam("files") MultipartFile[] files) {
        return ResponseObject.success(); // TODO
    }
}
