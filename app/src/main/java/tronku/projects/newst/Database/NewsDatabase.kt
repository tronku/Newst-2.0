package tronku.projects.newst.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import tronku.projects.newst.Networking.Article

@Database(entities = [Article::class], version = 1)
abstract class NewsDatabase: RoomDatabase() {

}