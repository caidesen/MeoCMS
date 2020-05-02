package top.meocms.common.api

/**
 * Unify specified response codes and messages
 */
enum class ResultCode(val code: Int, val message: String) {
    SUCCESS(20000, "Operation is Successful"),
    FAILURE(50001, "Biz Exception"),
    INTERNAL_SERVER_ERROR(50000, "Internal Server Error"),
    MSG_NOT_READABLE(40001, "Message Can't be Read"),
    PARAM_MISS(40002, "Missing Required Parameter"),
    PARAM_BIND_ERROR(40003, "Parameter Binding Error"),
    PARAM_TYPE_ERROR(40004, "Parameter Type Mismatch"),
    PARAM_VALID_ERROR(40004, "Parameter Validation Error"),
    MEDIA_TYPE_NOT_SUPPORTED(41500, "Media Type Not Supported"),
    NOT_FOUND(40400, "404 Not Found"),
    METHOD_NOT_SUPPORTED(40500, "Method Not Supported");
}


