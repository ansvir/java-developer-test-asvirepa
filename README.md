# [ENG] Asteroid Base Implementation v1.0
## [Source task](./Game_Developer_тестовое_задание(1)(1).pdf)
## [Github reference](https://github.com/ansvir/java-developer-test-asvirepa)
## Name Conventions:
Root package: com.example.asteroid
Sub-Packages:
1) "actor": contains custom game actors
2) "screen": all game screens
3) "stage": custom stages to split game input and output
4) "storage": game storage and data structures
5) "util": utilities for assets and initializers for game
## How to launch a game:
Use Gradle (for build and assemble as well):
   - Move to root asteroid project folder
   - Enter in command prompt on Windows `gradlew.bat dist`
   - Enter in terminal on *nix `./gradlew dist`
## Change Log
1) Refactor code: extract code duplication to separate methods, remove extra Actor's classes and make all Actor's inherit common MovableSpriteActor. Add ServiceUtil class to handle search for actors within Stage
2) Additional screens: add menu screen and game over screen. Add according custom stages
3) Remove asteroid spawn colliding with starship: asteroid spawn position calculates based on position and size of a starship
4) Add JavaDoc
# [RUS] Базовая версия игры Asteroid версии 1.0
## [Исходное задание](./Game_Developer_тестовое_задание(1)(1).pdf)
## [Github репозиторий](https://github.com/ansvir/java-developer-test-asvirepa)
## Стандарты наименований
Корневой пакет проекта: com.example.asteroid
Подпакеты:
1) "actor": содержит специфичные имплементации класса Actor
2) "screen": экраны игры
3) "stage": классы, абстрагирующие игровых слушателей событий для разных нужд
4) "storage": реализации хранения данных в игре и манипулиции данных
5) "util": утилиты для ресурсов и запуска игры
## Запуск игры:
Используйте Gradle (в том числе для сборки):
   - Перейдите в корневую папку проекта asteroid
   - Введите на Windows `gradlew.bat dist`
   - Введите на *nix `./gradlew dist`
## Change Log
См. в англоязычной версии