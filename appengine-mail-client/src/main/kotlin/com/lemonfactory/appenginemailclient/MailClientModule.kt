package com.lemonfactory.appenginemailclient

import com.lemonfactory.appenginemailclient.adapter.SendGridAdapter
import com.lemonfactory.crf7505.config.Config
import com.lemonfactory.crf7505.infrastructure.MailService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MailClientModule {

    @Bean
    open fun mailService(config: Config): MailService {
        return MailServiceImpl(mailSender(config), sendGridAdapter())
    }

    @Bean
    open fun mailSender(config: Config) = MailSender(config)

    @Bean
    open fun sendGridAdapter() = SendGridAdapter()

}
