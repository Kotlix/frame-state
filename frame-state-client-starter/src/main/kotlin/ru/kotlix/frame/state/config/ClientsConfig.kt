package ru.kotlix.frame.state.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackageClasses = [ru.kotlix.frame.state.client.UserStateClient::class])
@ConditionalOnMissingBean(ru.kotlix.frame.state.client.UserStateClient::class)
class ClientsConfig
