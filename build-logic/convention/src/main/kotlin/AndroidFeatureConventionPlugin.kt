import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import com.rafaelroldan.technicaltestmango.libs

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("genapp.android.library")
                apply("genapp.android.hilt")
                apply("androidx.navigation.safeargs.kotlin")
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                add("runtimeOnly", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}