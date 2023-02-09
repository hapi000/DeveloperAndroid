

package com.example.android.trackr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.NavHostFragment
import com.example.android.trackr.databinding.MainActivityBinding
import com.example.android.trackr.ui.utils.doOnApplyWindowInsets
import com.example.android.trackr.ui.utils.isRtl
import com.example.android.trackr.ui.utils.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
        }
        setContentView(binding.root)

        // Consume window insets on the side which shows the NavigationRailView. We have to set the
        // listener on the parent because otherwise the parent dispatches the original insets to the
        // other children, so they won't see the consumed insets.
        // NavigationRailView also tries to set its own padding based on the window insets, but it
        // cannot do that properly since the parent receives the insets first and will consume some
        // of them. We'll remove the rail's built-in listener and apply the padding ourselves.
        binding.navigationRail?.let { navRail ->
            binding.activityRoot.doOnApplyWindowInsets { v, insets, _, _ ->
                val isRtl = v.isRtl
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                navRail.updatePadding(
                    left = if (isRtl) 0 else systemBars.left,
                    right = if (isRtl) systemBars.right else 0,
                    top = systemBars.top,
                    bottom = systemBars.bottom
                )

                // Consume either left or right insets, but not both.
                WindowInsetsCompat.Builder(insets).setInsets(
                    WindowInsetsCompat.Type.systemBars(),
                    Insets.of(
                        if (isRtl) systemBars.left else 0,
                        systemBars.top,
                        if (isRtl) 0 else systemBars.right,
                        systemBars.bottom
                    )
                ).build()

            }
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.navigationRail?.apply {
            setupWithNavController(navController)
            setOnItemReselectedListener { } // Prevent navigating to the same item.
            setOnApplyWindowInsetsListener(null) // See above about consuming window insets.

            headerView?.setOnClickListener {
                navController.navigate(R.id.nav_task_edit_graph)
            }
        }
    }
}
