package br.com.crisun.architecture.domain.di

import br.com.crisun.architecture.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    factory<RequestMessageUseCase> { RequestMessageUseCaseImpl(get()) }
    factory<SaveMessageUseCase> { SaveMessageUseCaseImpl(get()) }
    factory<MessageReportUseCase> { MessageReportUseCaseImpl(get()) }
}
