package tronku.projects.newst.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import tronku.projects.newst.Networking.Article
import tronku.projects.newst.Networking.NewsModel

@Dao
interface NewsDao {

    @Query("SELECT * FROM article_list")
    fun getNewsFromLocal(): ArrayList<NewsModel>

    @Query("DELETE FROM article_list")
    fun deleteLocalNews()

    @Insert
    fun insertNewsToLocal(vararg articles: Article)
}