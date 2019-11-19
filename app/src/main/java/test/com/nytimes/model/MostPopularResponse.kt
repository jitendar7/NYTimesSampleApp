package test.com.nytimes.model

data class MostPopularResponse(
        val status: String?,
        val copyright: String?,
        val numOfResults: Long?,
        val resultList: List<MostPopularResult>
)