package com.example.springboot;

import com.example.springboot.service.image.ImageUploadService;
import com.example.springboot.service.image.Qiniu.Qiniu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Lucifer
 * @date 2017-12-24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class QiniuTest {
    @Value("${qiniu.public.domain}")
    private String QiniuDomain;
    @Value("${qiniu.public.bucket}")
    private String Bucket;
    @Autowired
    private ImageUploadService imageUploadService;

    @Test
    public void QiniuTest() {
        String imgKey = "201805071755475l6EjG.png";
        //String imgUrl = Qiniu.downloadHttps(QiniuDomain, imgKey);
        String imgUrl = Qiniu.getPublicImgUrl(QiniuDomain,imgKey,null);
        System.out.println(imgUrl);


    }
}
