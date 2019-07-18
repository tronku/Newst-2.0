package tronku.projects.newst.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tronku.projects.newst.Networking.NewsModel

//@Database(entities = [NewsModel::class], version = 1)
//abstract class NewsDatabase: RoomDatabase() {
//    abstract fun newsDao(): NewsDao
//
//    companion object {
//        @Volatile private var instance: NewsDatabase? = null
//
//        //invoke get called whenever the object of the class is created automatically
//        operator fun invoke (context: Context) = instance ?: synchronized(this) {
//            buildDatabase(context).also { instance = it }
//        }
//
//        operator fun invoke() = "Hey!"
//
//        private fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
//            NewsDatabase::class.java, "news_list.db").build()
//    }
//}