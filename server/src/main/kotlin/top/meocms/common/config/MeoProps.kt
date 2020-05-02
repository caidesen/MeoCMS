package top.meocms.common.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "meo")
class MeoProps(var companyName: String? = null)