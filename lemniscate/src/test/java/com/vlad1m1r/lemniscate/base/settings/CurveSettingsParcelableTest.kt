package com.vlad1m1r.lemniscate.base.settings

import android.graphics.Paint
import android.os.Parcel
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.mock
import com.vlad1m1r.lemniscate.base.models.LineLength
import com.vlad1m1r.lemniscate.testutils.isEqualTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class CurveSettingsParcelableTest {

    private val paint = mock<Paint>()

    private lateinit var curveSettings: CurveSettings

    @Before
    fun setUp() {
        curveSettings = CurveSettings(paint)
        curveSettings.color = 123
        curveSettings.hasHole = true
        curveSettings.lineLength = LineLength()
        curveSettings.lineLength.lineMaxLength = 0.85f
        curveSettings.lineLength.lineMinLength = 0.26f
        curveSettings.strokeWidth = 23.2f
        curveSettings.precision = 123
    }

    @Test
    fun parcelable() {
        val parcel = Parcel.obtain()
        curveSettings.writeToParcel(parcel, 0)
        parcel.setDataPosition(0)

        val copy = CurveSettings(parcel)
        parcel.recycle()

        assertThat(curveSettings.isEqualTo(copy)).isTrue()
    }
}
