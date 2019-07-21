import groovy.lang.Closure
import org.gradle.kotlin.dsl.KotlinClosure2

fun <T, U, V> Any.closureOf(action: (T, U) -> V): Closure<V?> = KotlinClosure2(action, this, this)
