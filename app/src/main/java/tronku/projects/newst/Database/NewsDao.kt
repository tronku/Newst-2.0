package tronku.projects.newst.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tronku.projects.newst.Networking.Article
import tronku.projects.newst.Networking.NewsModel

@Dao
interface NewsDao {

//    @Query("SELECT * FROM newsmodel_table")
//    suspend fun getNewsFromLocal(): NewsModel?
//
//    @Query("DELETE FROM newsmodel_table")
//    suspend fun deleteLocalNews()
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertNewsToLocal(newsModel: NewsModel?)
}