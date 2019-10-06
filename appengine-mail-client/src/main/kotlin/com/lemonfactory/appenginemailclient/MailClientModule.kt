package com.lemonfactory.appenginemailclient

import com.lemonfactory.crf7505.infrastructure.MailService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MailClientModule {

    @Bean
    open fun mailService(): MailService {
        return MailServiceImpl()
    }

}