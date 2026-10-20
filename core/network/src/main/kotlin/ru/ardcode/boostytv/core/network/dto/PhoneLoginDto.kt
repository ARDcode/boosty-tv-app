package ru.ardcode.boostytv.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class PhoneCodeResponseDto(val data: PhoneCodeDataDto = PhoneCodeDataDto())

@Serializable
data class PhoneCodeDataDto(val phoneCode: PhoneCodeDto = PhoneCodeDto())

@Serializable
data class PhoneCodeDto(val code: String = "", val sentTransport: String? = null)
