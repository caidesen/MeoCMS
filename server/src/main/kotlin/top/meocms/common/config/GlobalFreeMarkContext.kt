package top.meocms.common.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
@EnableConfigurationProperties(MeoProps::class)
class GlobalFreeMarkContext(
        private val configuration: freemarker.template.Configuration,
        private val meoProps: MeoProps
) {
    @PostConstruct
    fun injection() {
        configuration.setSharedVariable("companyName", meoProps.companyName)
    }
}
