package test.com.nytimes.model

data class MostPopularResponse(
        val status: String?,
        val copyright: String?,
        val num_results: Long?,
        val results: List<MostPopularResult>
)