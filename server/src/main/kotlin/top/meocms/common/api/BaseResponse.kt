package top.meocms.common.api

class BaseResponse<T>(
        private var code: Int?,
        private var message: String?,
        private var data: T?
) {
    companion object {
        fun <T> success(data: T): BaseResponse<T> {
            return BaseResponse(ResultCode.SUCCESS.code,
                    ResultCode.SUCCESS.message,
                    data)
        }

        fun fail(resultCode: ResultCode): BaseResponse<*> {
            return BaseResponse(resultCode.code,
                    resultCode.message,
                    null)
        }

        fun fail(resultCode: ResultCode, message: String?): BaseResponse<*> {
            return BaseResponse(resultCode.code,
                    message,
                    null)
        }
    }
}