import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.finalandroid.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_graph)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myCityFragment) as NavHostFragment
        setupActionBarWithNavController(navHostFragment.navController)
    }
}