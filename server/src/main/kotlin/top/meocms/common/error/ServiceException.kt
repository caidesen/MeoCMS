package top.meocms.common.error

import top.meocms.common.api.ResultCode

class ServiceException(val resultCode: ResultCode, message: String, cause: Throwable? = null)
    : RuntimeException(message, cause) {

    constructor(message: String) : this(ResultCode.FAILURE, message)
    constructor(resultCode: ResultCode) : this(resultCode, resultCode.message)
    constructor(resultCode: ResultCode, cause: Throwable) : this(resultCode, resultCode.message, cause)
    constructor(message: String, cause: Throwable) : this(ResultCode.FAILURE, message, cause)

    override fun fillInStackTrace(): Throwable? {
        return this
    }

    fun doFillInStackTrace(): Throwable? {
        return super.fillInStackTrace()
    }
}