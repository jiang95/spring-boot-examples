package com.example.SpringBoot.service.image.Qiniu;

import com.example.SpringBoot.constants.Constants;
import com.example.SpringBoot.utils.common.MD5Utils;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lingjun.jlj
 * @data 2018/5/7
 * @Description:
 */
@Slf4j
public class Qiniu {


    /**
     * base64图片上传图片
     */
    public static Response upload(String bucket, String key, byte[] data) throws Exception {
        Auth auth = Auth.create(Constants.ACCESS_KEY, Constants.SECRET_KEY);
        String token = auth.uploadToken(bucket, key);
        UploadManager uploadManager = new UploadManager();
        return uploadManager.put(data, key, token);
    }

    /**
     * @param bucket 空间名
     * @param key    文件名
     * @param file   文件内容
     * @return
     * @throws Exception
     */
    public static Response upload(String bucket, String key, File file) throws Exception {
        Auth auth = Auth.create(Constants.ACCESS_KEY, Constants.SECRET_KEY);
        String token = auth.uploadToken(bucket, key);
        UploadManager uploadManager = new UploadManager();
        return uploadManager.put(file, key, token);
    }

    /**
     * 将MultipartFile类型文件上传至指定bucket,并返回上传文件名
     *
     * @param bucket 空间名
     * @param file   文件
     * @return fileKey 上传文件名
     * @throws Exception
     */
    public static String upload(String bucket, MultipartFile file) throws Exception {

        // 文件后缀名为空,则默认png文件
        String postfix = "";
        String originalFilename = file.getOriginalFilename();
        if (!originalFilename.isEmpty()
                && originalFilename.lastIndexOf(".") != -1) {
            postfix = originalFilename.substring(originalFilename
                    .lastIndexOf(".") + 1);
        }

        if (postfix.isEmpty()) {
            postfix = "png";
        }

        // 根据上传时间和随机6位生成上传文件名
        String fileKey = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date()) + MD5Utils.selectRandom(6) + "." + postfix;

        // 将MultiPartFile转换为File类型
        File tmpFile = File.createTempFile(bucket, fileKey);
        file.transferTo(tmpFile);

        Response uploadResponse = Qiniu.upload(bucket, fileKey, tmpFile);

        if (!uploadResponse.isOK()) {
            throw new Exception("upload attachment failed!"); // 上传失败
        }

        if (!tmpFile.delete()) {
            throw new Exception("delete tmpFile fail!");    // 删除临时文件失败
        }

        return fileKey;
    }

    /**
     * @param domain 域名
     * @param key    文件名
     * @return
     */
    public static String downloadHttps(String domain, String key) {
        String url = "https://" + domain + "/" + key;
        Auth auth = Auth.create(Constants.ACCESS_KEY, Constants.SECRET_KEY);
        return auth.privateDownloadUrl(url);
    }

    /**
     * 拼接获取图片公共URL
     */
    public static String getPublicImgUrl(String imageDomain, String imageKey, String pac) {
        String imageUrl = "https://" + imageDomain + "/" + imageKey;
        if (pac != null && (!pac.equals(""))) {
            imageUrl += "-" + pac;
        }
        return imageUrl;
    }
}
