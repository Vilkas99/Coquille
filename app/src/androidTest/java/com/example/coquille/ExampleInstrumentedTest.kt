package com.example.coquille

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.*
import androidx.work.testing.TestDriver
import androidx.work.testing.WorkManagerTestInitHelper
import com.example.coquille.workers.PlayNotificationWorker

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    @Throws(Exception::class)
    fun testPlayNotificationWorker() {

        WorkManagerTestInitHelper.initializeTestWorkManager(InstrumentationRegistry.getInstrumentation().targetContext)
        // Create request
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.METERED).build()
        val request = PeriodicWorkRequestBuilder<PlayNotificationWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints) // added this line
            .build()
        val workManager = WorkManager.getInstance(InstrumentationRegistry.getInstrumentation().targetContext)
        val testDriver  = WorkManagerTestInitHelper.getTestDriver(InstrumentationRegistry.getInstrumentation().targetContext)

        // Enqueue and wait for result. This also runs the Worker synchronously
        // because we are using a SynchronousExecutor.
        workManager.enqueue(request).result.get()
        testDriver?.setPeriodDelayMet(request.id)

        // Get WorkInfo and outputData
        val workInfo = workManager.getWorkInfoById(request.id).get()

        val outputData = workInfo.outputData

        // Assert
        assertEquals(workInfo.state, WorkInfo.State.ENQUEUED)

    }

}