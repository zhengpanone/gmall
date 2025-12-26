package com.zp.framework.banner.config;

import com.zp.framework.banner.core.BannerApplicationRunner;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * Author : zhengpanone
 * Date : 2023/11/18 08:34
 * Version : v1.0.0
 * Description: Banner的自动配置类
 */
@AutoConfiguration
public class GmallBannerAutoConfiguration {
    public BannerApplicationRunner bannerApplicationRunner() {
        return new BannerApplicationRunner();
    }
}
