package dominando.android.lifecycleowner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val myLogger by lazy { MyLogger() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myLifeCycleObserver = MyLifeCycleObserver(lifecycle, myLogger)
        lifecycle.addObserver(myLifeCycleObserver)
    }

}