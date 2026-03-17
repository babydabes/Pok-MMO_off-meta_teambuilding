# PokéMMO Team Builder ProGuard Rules

# Keep Room entities
-keep class com.pokemmo.core.database.entity.** { *; }

# Keep Retrofit API interfaces
-keep,allowobfuscation interface com.pokemmo.core.network.api.** { *; }

# Keep kotlinx-serialization DTO classes
-keepclassmembers class com.pokemmo.core.network.dto.** { *; }
-keep class com.pokemmo.core.network.dto.** { *; }

# Keep Hilt generated components
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.lifecycle.HiltViewModel { *; }

# OkHttp
-dontwarn okhttp3.internal.platform.**

# Retrofit
-keepattributes Signature
-keepattributes *Annotation*

# kotlinx.serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** { *** Companion; }

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
