# User collection manager app
рабочая версия приложения в ветке `main`
* консольное приложение для работы с масивом данных класса User(name, email, age)
* приложение хранит масив данных и содержит функциональность для работы с ним:  *загрузка, сортировка, сохранение в файл и вывод в консоль загруженных данных, а также поиск вхождений конкретного элемента в загруженном массиве и его очистка*
* приложение готово к расширению для работы с кастомными классами

## Быстрый старт
```bash
git clone https://github.com/nikolenolga/user-collection-manager-app.git
cd user-collection-manager-app

mvn clean install
java -jar user-collection-manager-app-1.0-SNAPSHOT.jar
```
## Внесение изменений

*   **Создание Issues:** Предлагайте новые идеи или сообщайте об ошибках, создавая {Link: Issue docs.github.com}.
*   **Коммиты:**  Следуйте [Conventional Commits](www.conventionalcommits.org) или примерам ниже
    *   `feature: Добавляет новую функциональность`
    *   `add: добавляются новые файлы или новые части в существующие файлы`
    *   `fix: Исправляет ошибку в вашем коде`
    *   `change: Заменяет функциональность на новую реализацию или изменяет логику реализации имеющейся`
    *   `delete: Удаляет пакеты, файлы или части в существующих файлах`
    *   `clean: Рефакторинг кода без изменения функциональности`
*   **Pull Requests (PR):**
    *   Открывайте PR из вашей ветки в `develop`.
    *   Убедитесь, что ваш код покрыт тестами.
    *   Добавьте описание изменений.

## Структура приложения
*   **src** - Исходный код приложения.
*   **test** - Тесты и их ресурсы.
*   **src/main/java/ru/aston/finalproject** - основной пакет приложения
  *   **actions** - пакет содержащий классы исполняемых AppAction команд
  *    **collection** - пакет содержащий кастомные коллекции и связанные компоненты
  *   **entity** - пакет содержащий пакеты с основными кастомными классами с которыми работает приложение
  *   **entity/user** - пакет содержащий класс User с которым работает текущая версия приложения и связанные с ним компоненты
  *   **environment** - пакет содержащий окружение приложение отвечающее за взаимодействие компонентов: *AppData - контекст приложения, AppException - основной тип исключений приложения, AppRequest - класс, инкапсулируйщий в себе всю логику, связанную с созданием и обработкой запроса/команды пользователя*
  *   **parser** - пакет содержащий парсеры для кастомных классов
  *   **service** - пакет с бизнес-логикой приложения
    *   **counters** - пакет для логики подсчета количества элементов в коллекции
    *   **loader** - пакет содержащий загрузчики коллекций
    *   **sorting** - пакет содержащий сортировщики коллекций
    *   **loader** - пакет содержащий загрузчики коллекций
    *   **writer** - пакет содержащий классы для записи коллекций (запись в файл)
    *   **util** - пакет содержащий общие утилитные/вспомогательные классы
  *   **AppRunner** - основной класс отвечающий за выполнение кода сразу после запуска приложения и предоставляющий пользовательский консольный интерфейс\

## Использование приложения
```bash
help    list available commands and required arguments
print 	print current saved list
clear 	clear current saved list
load    load user list
        -size=<size>  -type=file    -path=<path-to-file>    load from file path
        -size=<size>  -type=console                         load from console input
        -size=<size>  -type=random                          load random user list
write    write current list to file
        -file=<file-path>                                   write to file (uses current parser format)
sort    sorting current list to file
        -basic                                               natural order sorting
        -strange                                             sorting only by even age
count    count entries of a given user
        -threads=<tread count>                              thread count for multithread search
                                                            (set at 1 for sequential search)
```

## Установка
```bash
# Ensure Git is installed
# Visit https://git-scm.com to download and install console Git if not already installed

# Clone the repository
git clone https://github.com/nikolenolga/user-collection-manager-app.git

# Navigate to the project directory
cd user-collection-manager-app

# Check if Maven is installed
mvn --version  # Check the installed version of Maven
# Visit https://maven.apache.org to download and install console Maven if not already installed

# build jar with dependencies 
mvn clean install

# Check if JDK is installed - JDK 17 or higher required
java --version  # Check the installed version of JDK
# Visit the official website of OpenJDK or Oracle JDK to install or update it if necessary

# run project jar 
java -jar target/user-collection-manager-app-1.0-SNAPSHOT.jar
```
