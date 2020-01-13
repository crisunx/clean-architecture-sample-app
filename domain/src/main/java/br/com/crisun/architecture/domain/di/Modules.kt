package br.com.crisun.architecture.domain.di

import br.com.crisun.architecture.domain.service.MessageService
import br.com.crisun.architecture.domain.service.MessageServiceImpl
import br.com.crisun.architecture.domain.service.StatisticService
import br.com.crisun.architecture.domain.service.StatisticServiceImpl
import org.koin.dsl.module

val domainModule = module {
    factory<MessageService> { MessageServiceImpl(get()) }
    factory<StatisticService> { StatisticServiceImpl(get()) }
}
