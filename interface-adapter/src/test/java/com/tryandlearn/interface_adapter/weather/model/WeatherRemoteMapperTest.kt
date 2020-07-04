package com.tryandlearn.interface_adapter.weather.model

import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import com.tryandlearn.entity.weather.WeatherKind
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(DataProviderRunner::class)
class WeatherRemoteMapperTest {

    private val mapper = WeatherRemote.Mapper()

    companion object {
        @DataProvider
        @JvmStatic
        @Suppress("unused")
        fun parameters(): Array<Array<Any?>> {
            return arrayOf(
                arrayOf<Any?>("Rain", WeatherKind.RAIN),
                arrayOf<Any?>("Clouds", WeatherKind.CLOUDS),
                arrayOf<Any?>("Snow", WeatherKind.SNOW),
                arrayOf<Any?>("Clear", WeatherKind.CLEAR)
            )
        }
    }

    @Test
    @UseDataProvider("parameters")
    fun map(remoteKind: String?, expectedWeatherKind: WeatherKind) {
        // Given
        val weatherRemote = WeatherRemote(
            dt = 3,
            weather = listOf(
                WeatherRemoteInfo(
                    main = remoteKind,
                    description = "description",
                    icon = "04d"
                )
            )
        )


        // When
        val weather = mapper.toEntity(weatherRemote)

        // Then

        with(weather) {
            org.assertj.core.api.Assertions.assertThat(dateTime).isEqualTo(3)
            org.assertj.core.api.Assertions.assertThat(kind).isEqualTo(expectedWeatherKind)
            org.assertj.core.api.Assertions.assertThat(description).isEqualTo("description")
            org.assertj.core.api.Assertions.assertThat(description).isEqualTo("04d")
        }
    }
}