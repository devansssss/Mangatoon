package com.example.mangatoon.repository

import android.util.Log
import com.example.mangatoon.room.database.WebtoonDatabase
import com.example.mangatoon.room.model.Webtoon
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebtoonRepo @Inject constructor(private val webtoonDatabase: WebtoonDatabase){

    suspend fun insertSampleWebtoons() {
        val sampleWebtoons = listOf(
            Webtoon(
                title = "Solo Leveling",
                description = "One of the best action fantasy manhwa and the most talked about adaptation in recent times is undoubtedly Solo Leveling. It is set in a world where humans have discovered supernatural skills, while our protagonist, Sung Jin-Woo, is a nobody with his E-Rank hunting skills. Things will take an interesting turn for him when he becomes the sole survivor of a dungeon raid. Awakened with strange new powers, Sung Jin-Woo will level up from being the weakest Hunter and eventually become the most powerful entity in the universe.",
                imageUrl = "https://4kwallpapers.com/images/walls/thumbs_2t/15859.jpg"
            ),
            Webtoon(
                title = "Tower of God",
                description = "Even if you are not a manhwa fan, you must have heard of Tower of God. This action fantasy manhwa became especially popular after its anime adaptation. Tower of God focuses on Twenty-Fifth Bam, the young protagonist of the manhwa, who is determined to climb a mysterious Tower to find his friend Rachel. It is to be noted that the titular tower has different floors, and each floor has different obstacles. His quest is not going to be an easy one, and whether or not he will be able to meet his friend remains to be seen. Tower of God Season 2 has also been recently released.",
                imageUrl = "https://images6.alphacoders.com/125/1254553.jpg"
            ),
            Webtoon(
                title = "Lore Olympus",
                description = "Lore Olympus is a retelling of Greek myths centered around Hades, god of the underworld, and Persephone, goddess of spring. It's an exploration of heartbreak, love, friendship, trauma, loss, rage, infidelity, secrets, truth, and power dynamics.",
                imageUrl = "https://pbs.twimg.com/media/ET713CJUYAAyASd.png:large"
            )
        )

        val existingWebtoons = webtoonDatabase.webtoonDao().getAllWebtoons()
        val existingTitles = existingWebtoons.map { it.title }

        val newWebtoons = sampleWebtoons.filterNot { existingTitles.contains(it.title) }
        if (newWebtoons.isNotEmpty()) {
            webtoonDatabase.webtoonDao().insertWebtoons(newWebtoons)
        } else {
            Log.d("WebtoonRepo", "No new webtoons to insert; they already exist in the database.")
        }
    }

    suspend fun getFavorites(): List<Webtoon> {
        return webtoonDatabase.webtoonDao().getFavorites()
    }

    suspend fun updateFavoriteStatus(webtoonId: Int, isFavorite: Boolean) {
        webtoonDatabase.webtoonDao().updateFavoriteStatus(webtoonId, isFavorite)
    }

    suspend fun getAllWebtoons(): List<Webtoon> {
        val webtoons = webtoonDatabase.webtoonDao().getAllWebtoons()
        Log.d("WEBTOON", webtoons.toString())
        return webtoons
    }

}