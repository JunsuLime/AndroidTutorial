package junsulime.androidtutorial.models

data class HomeResponse(
        val user: User,
        val posts: List<PostSummary>
)