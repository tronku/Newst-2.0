package tronku.projects.newst.Networking

sealed class ApiResponse<out T: Any> {
    data class Success<out T: Any>(val output: T?): ApiResponse<T>()
    data class Failure(val apiError: APIError): ApiResponse<Nothing>()
}

data class APIError(
    val code: Int?,
    val msg: String?
)