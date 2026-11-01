package com.zp.gmall.framework.common.util.image;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 10:32
 * Version : v1.0.0
 * Description:
 */
class ImgMattingUtilTest {

    @Test
    void matting() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        /*
        //如果路径中带有中文会被URLEncoder,因此这里需要解码
        String decode = URLDecoder.decode(resource.getPath(), StandardCharsets.UTF_8);
        */
        File inputFile = new ClassPathResource("matting.png").getFile();
        File outputFile = new File(Paths.get("src/test/resources/matting-out.png").toFile().getAbsolutePath());
        ImgMattingUtil.matting(inputFile,outputFile);
    }


}