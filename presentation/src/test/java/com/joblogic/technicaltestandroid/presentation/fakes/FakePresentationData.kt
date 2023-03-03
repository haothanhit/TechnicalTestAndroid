package com.joblogic.technicaltestandroid.presentation.fakes

import com.joblogic.technicaltestandroid.domain.models.MessageRecentModel
import com.joblogic.technicaltestandroid.domain.models.CharacterLocationModel
import com.joblogic.technicaltestandroid.domain.models.SettingType
import com.joblogic.technicaltestandroid.domain.models.Settings
import com.joblogic.technicaltestandroid.presentation.fakes.FakeValueFactory.randomBoolean
import com.joblogic.technicaltestandroid.presentation.fakes.FakeValueFactory.randomInt
import com.joblogic.technicaltestandroid.presentation.fakes.FakeValueFactory.randomString

object FakePresentationData {

    fun getCharacters(
        size: Int,
        isRandomId: Boolean = true,
        isBookmarked: Boolean = false
    ): List<MessageRecentModel> {
        val characters = mutableListOf<MessageRecentModel>()
        repeat(size) {
            characters.add(createCharacter(isRandomId, isBookmarked))
        }
        return characters
    }

    fun getSettings(size: Int): List<Settings> {
        val settings = mutableListOf<Settings>()
        repeat(size) {
            settings.add(createSetting())
        }
        return settings
    }

    private fun createCharacter(isRandomId: Boolean, isBookmarked: Boolean): MessageRecentModel {
        return MessageRecentModel(
            created = randomString(),
            gender = randomString(),
            id = if (isRandomId) randomInt() else 1,
            image = randomString(),
            characterLocationModel = CharacterLocationModel(
                name = randomString(),
                url = randomString()
            ),
            name = randomString(),
            species = randomString(),
            status = randomString(),
            randomString(),
            url = randomString(),
            isBookMarked = if (isBookmarked) true else randomBoolean()
        )
    }

    private fun createSetting(): Settings {
        return Settings(
            id = randomInt(),
            type = SettingType.SWITCH,
            settingLabel = randomString(),
            settingValue = randomString()
        )
    }
}
