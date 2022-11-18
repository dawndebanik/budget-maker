package utils

import org.apache.http.client.HttpClient
import org.apache.http.client.methods.RequestBuilder
import org.apache.http.client.utils.URIBuilder
import org.apache.http.util.EntityUtils

private const val AUTHORIZATION_HEADER_KEY = "Authorization"
private const val BEARER_IDENTIFIER = "Bearer"
private val config = ConfigLoader.getApplicationConfig()

class HttpUtils {
    companion object {
        fun performGetCall(httpClient: HttpClient, endpoint: String?, params: Map<String, String>? = emptyMap()): String {
            val uriBuilder = URIBuilder("${config.appConfig?.splitwiseV3BaseUrl}/$endpoint")
            params?.forEach {
                uriBuilder.addParameter(it.key, it.value)
            }
            val request =
                RequestBuilder
                    .get()
                    .setUri(uriBuilder.build())
                    .setHeader(AUTHORIZATION_HEADER_KEY, "$BEARER_IDENTIFIER ${config.apiKey}")
                    .build()

            return EntityUtils.toString(httpClient.execute(request).entity)
        }
    }
}