package dev.srsouza.foundation.network

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.Response
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.internal.TypeData
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlin.jvm.Transient

public class KtorfitHttpException(
    @Transient val response: HttpResponse,
    val bodyText: String,
) : RuntimeException() {
    override val message: String
        get() = "HTTP " + response.status.value + " " + bodyText
}

internal class UnsuccessResponseConverterFactory : Converter.Factory {

   class UnsuccessResponseSuspendConverter(
       val typeData: TypeData,
       val ktorfit: Ktorfit
    ) : Converter.SuspendResponseConverter<HttpResponse, Any> {
        override suspend fun convert(response: HttpResponse): Any {
            return try {
                if (response.status.isSuccess()) {
                    response.call.body(typeData.typeInfo)
                } else {
                    throw KtorfitHttpException(response, response.bodyAsText())
                }
            } catch (exception: Exception) {
                throw exception
            }
        }
    }

    override fun suspendResponseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit
    ): Converter.SuspendResponseConverter<HttpResponse, *>? {
        if (typeData.typeInfo.type != Response::class) {
            return UnsuccessResponseSuspendConverter(typeData, ktorfit)
        }
        return null
    }
}