package tronku.projects.newst.Networking

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsModel(
    @Expose
    @SerializedName("articles")
    val articles: ArrayList<Article>?
)

@Entity(tableName = "article_list")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @Expose
    @SerializedName("author")
    val author: String?,

    @Expose
    @SerializedName("content")
    val content: String?,

    @Expose
    @SerializedName("description")
    val description: String?,

    @Expose
    @SerializedName("publishedAt")
    val publishedAt: String?,

    @Expose
    @Embedded(prefix = "source_")
    @SerializedName("source")
    val source: Source?,

    @Expose
    @SerializedName("title")
    val title: String?,

    @Expose
    @SerializedName("url")
    val url: String?,

    @Expose
    @SerializedName("urlToImage")
    val urlToImage: String?
)

data class Source(
    @Expose
    @SerializedName("id")
    val id: Any?,

    @Expose
    @SerializedName("name")
    val name: String?
)