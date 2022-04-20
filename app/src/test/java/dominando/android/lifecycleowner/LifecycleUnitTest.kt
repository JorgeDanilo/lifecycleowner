package dominando.android.lifecycleowner


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class LifecycleUnitTest {

    lateinit var lifeCycleObserver: MyLifeCycleObserver
    lateinit var lifeCycle: LifecycleRegistry
    val logger: MyLogger = mock(MyLogger::class.java)

    @Before
    fun setUp() {
        val lifecycleOwner = mock(LifecycleOwner::class.java)
        lifeCycle = LifecycleRegistry(lifecycleOwner)
        lifeCycleObserver = MyLifeCycleObserver(lifeCycle, logger)
        lifeCycle.addObserver(lifeCycleObserver)

        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    @Test
    fun shouldLogCreate() {
        lifeCycle.markState(Lifecycle.State.CREATED)
        verify(logger).logCreate()
    }

    @Test
    fun shouldLogStart() {
        lifeCycle.markState(Lifecycle.State.STARTED)
        verify(logger).logStart()
    }

    @Test
    fun shouldLogResume() {
        lifeCycle.markState(Lifecycle.State.RESUMED)
        verify(logger).logResume()
    }

    @Test
    fun shouldLogPause() {
        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        verify(logger).logPause()
    }

    @Test
    fun shouldLogStop() {
        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        verify(logger).logStop()
    }

    @Test
    fun shouldLogDestroy() {
        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        verify(logger).logDestroy()
    }
}