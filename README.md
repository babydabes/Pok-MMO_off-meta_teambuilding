# Pok-MMO_off-meta_teambuilding
Team building for android
PokéMMO Team Builder
An Android application built with Kotlin and Jetpack Compose designed for building "off-meta" competitive teams for the PokéMMO Gen 5 metagame. The project follows a modular, clean architecture approach with a focus on local data persistence and specialized competitive tools.
Features
 * Team Builder: Create and manage 6-slot teams with full support for moves, abilities, natures, and EV/IV spreads.
 * Showdown Integration: Import teams directly from Pokémon Showdown pastes or export your creations for use in other simulators.
 * Taxonomy Browser: A unique "off-meta" discovery tool that allows users to browse Pokémon by biological or thematic categories (e.g., "Raptors," "Serpents," "Canines") rather than just types.
 * Damage Calculator: A specialized Gen 5 damage formula engine for calculating exact damage rolls, featuring weather, status, and item modifiers.
 * Pokédex: Comprehensive listing of Pokémon relevant to the PokéMMO OU, UU, and NU tiers, including base stats and flavor text.
 * Coverage Analysis: Real-time offensive and defensive coverage maps for your active team.
 * Smogon Suggestions: View curated competitive sets for Pokémon directly within the builder.
Tech Stack
 * Language: Kotlin
 * UI Framework: Jetpack Compose
 * Architecture: MVVM with Clean Architecture principles (Domain, Data, and Feature modules)
 * Dependency Injection: Hilt (Dagger)
 * Database: Room Persistence Library with FTS4 full-text search
 * Networking: Retrofit 2 & OkHttp 4
 * Image Loading: Coil 3
 * Concurrency: Kotlin Coroutines & Flow
 * Background Tasks: WorkManager for data synchronization
 * Build System: Gradle Kotlin DSL with Version Catalogs (libs.versions.toml)
Project Structure
The project is split into several modules to ensure separation of concerns:
 * :app: The main entry point, containing the Hilt Application class, MainActivity, and top-level navigation.
 * :core:common: Shared utilities, extension functions, and the Resource wrapper.
 * :core:database: Local Room database, DAOs, entities, and the initial database seeder.
 * :core:domain: Business logic, platform-agnostic models, and Use Cases.
 * :core:network: API service definitions, DTOs, repository implementations, and remote synchronization logic.
 * :core:ui: Shared Compose components, themes (Material 3), and type-specific styling.
 * :feature:*: Specific screens and ViewModels for the Pokedex, Team Builder, Damage Calc, and Taxonomy modules.
Setup & Installation
The project requires Android Studio Ladybug or newer and JDK 17.
 * Clone the repository:
   git clone https://github.com/babydabes/Pok-MMO_off-meta_teambuilding.git

 * Open the project in Android Studio.
 * Sync the project with Gradle files.
 * Run the application on an emulator or physical device (Minimum SDK: 26).
License
This project is licensed under the MIT License.
