package top.meocms.common.error

import org.hibernate.validator.internal.engine.path.PathImpl
import org.slf4j.LoggerFactory
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.NoHandlerFoundException
import top.meocms.common.api.BaseResponse
import top.meocms.common.api.ResultCode
import javax.validation.ConstraintViolationException

@RestControllerAdvice(basePackages = ["top.meocms.controller.rest"])
class GlobalExceptionTranslator {
    private val logger = LoggerFactory.getLogger(GlobalExceptionTranslator::class.java)

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleError(e: MissingServletRequestParameterException): BaseResponse<*> {
        logger.warn(ResultCode.PARAM_MISS.message, e)
        return BaseResponse.fail(ResultCode.PARAM_MISS,
                "${ResultCode.PARAM_MISS.message}:${e.parameterName}")
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleError(e: MethodArgumentTypeMismatchException): BaseResponse<*> {
        logger.warn(ResultCode.PARAM_TYPE_ERROR.message, e)
        return BaseResponse.fail(ResultCode.PARAM_TYPE_ERROR,
                "${ResultCode.PARAM_TYPE_ERROR.message}:${e.name}")
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleError(e: MethodArgumentNotValidException): BaseResponse<*> {
        logger.warn(ResultCode.PARAM_VALID_ERROR.message, e)
        val result = e.bindingResult
        val error = result.fieldError
        return BaseResponse.fail(ResultCode.PARAM_VALID_ERROR, "${error!!.field}:${error.defaultMessage}")
    }

    @ExceptionHandler(BindException::class)
    fun handleError(e: BindException): BaseResponse<*> {
        logger.warn(ResultCode.PARAM_BIND_ERROR.message, e)
        val error = e.fieldError
        return BaseResponse.fail(ResultCode.PARAM_BIND_ERROR, "${error!!.field}:${error.defaultMessage}")
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleError(e: ConstraintViolationException): BaseResponse<*> {
        logger.warn("Constraint Violation", e)
        val violations = e.constraintViolations
        val violation = violations.iterator().next()
        val path = (violation.propertyPath as PathImpl).leafNode.name
        return BaseResponse.fail(ResultCode.PARAM_VALID_ERROR, "${path}:${violation.message}")
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleError(e: NoHandlerFoundException?): BaseResponse<*> {
        logger.error(ResultCode.NOT_FOUND.message, e)
        return BaseResponse.fail(ResultCode.NOT_FOUND)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleError(e: HttpMessageNotReadableException?): BaseResponse<*> {
        logger.error(ResultCode.MSG_NOT_READABLE.message, e)
        return BaseResponse.fail(ResultCode.MSG_NOT_READABLE)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleError(e: HttpRequestMethodNotSupportedException?): BaseResponse<*> {
        logger.error(ResultCode.METHOD_NOT_SUPPORTED.message, e)
        return BaseResponse.fail(ResultCode.METHOD_NOT_SUPPORTED)
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    fun handleError(e: HttpMediaTypeNotSupportedException?): BaseResponse<*> {
        logger.error(ResultCode.MEDIA_TYPE_NOT_SUPPORTED.message, e)
        return BaseResponse.fail(ResultCode.MEDIA_TYPE_NOT_SUPPORTED)
    }

    @ExceptionHandler(ServiceException::class)
    fun handleError(e: ServiceException): BaseResponse<*> {
        logger.error("Service Exception", e)
        return BaseResponse.fail(e.resultCode, e.message)
    }

    @ExceptionHandler(Throwable::class)
    fun handleError(e: Throwable?): BaseResponse<*> {
        logger.error(ResultCode.INTERNAL_SERVER_ERROR.message, e)
        return BaseResponse.fail(ResultCode.INTERNAL_SERVER_ERROR)
    }
}