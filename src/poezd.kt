import kotlin.random.Random

// Простой класс вагона
class Wagon(val number: Int) {
    val capacity = Random.nextInt(1, 26) //
    var passengers = 0

    fun canAddMore(): Boolean = passengers < capacity
    fun addPassenger() { passengers++ }
}

// Основной класс программы
class SimpleTrainPlanner {
    private val cities = listOf("Москва", "СПб", "Новосибирск", "Екатеринбург", "Казань",
        "Нижний Новгород", "Челябинск", "Омск", "Самара", "Ростов",
        "Уфа", "Красноярск", "Воронеж", "Пермь", "Волгоград")

    fun start() {
        println("ПРОГРАММА СОСТАВЛЕНИЯ ПОЕЗДОВ")

        while (true) {
            println("\nГлавное меню:")
            print("Введите 1 - составить поезд, EXIT - выход: ")

            when (readLine()?.uppercase()) {
                "1" -> makeTrain()
                "EXIT" -> break
                else -> println("Ошибка! Введите 1 или EXIT")
            }
        }
        println("Программа завершена!")
    }

    private fun makeTrain() {
        println("\n--- ШАГ 1: СОЗДАЕМ НАПРАВЛЕНИЕ ---")
        val route = createRoute()
        println("Маршрут: $route")

        println("\n--- ШАГ 2: ПРОДАЕМ БИЛЕТЫ ---")
        val ticketCount = Random.nextInt(1, 202)
        println("Продано билетов: $ticketCount")

        println("\n--- ШАГ 3: ФОРМИРУЕМ ПОЕЗД ---")
        val wagons = createWagons(ticketCount)
        println("Создано вагонов: ${wagons.size}")

        println("\n--- ШАГ 4: ОТПРАВЛЯЕМ ПОЕЗД ---")
        sendTrain(route, wagons, ticketCount)
    }

    private fun createRoute(): String {
        var from = cities.random()
        var to = cities.random()
        while (from == to) {
            to = cities.random()
        }
        return "$from → $to"
    }

    private fun createWagons(passengers: Int): List<Wagon> {
        val wagons = mutableListOf<Wagon>()
        var remainingPassengers = passengers
        var wagonNumber = 1

        while (remainingPassengers > 0) {
            val wagon = Wagon(wagonNumber)
            val seatsInWagon = minOf(remainingPassengers, wagon.capacity)

            repeat(seatsInWagon) {
                wagon.addPassenger()
            }

            wagons.add(wagon)
            remainingPassengers -= seatsInWagon
            wagonNumber++
        }

        return wagons
    }

    private fun sendTrain(route: String, wagons: List<Wagon>, totalPassengers: Int) {
        println("🚂 Поезд \"$route\" отправлен!")
        println("📊 Статистика:")
        println("   • Всего пассажиров: $totalPassengers")
        println("   • Количество вагонов: ${wagons.size}")
        println("   • Детали по вагонам:")

        wagons.forEach { wagon ->
            println("     Вагон ${wagon.number}: мест ${wagon.capacity}, пассажиров ${wagon.passengers}")
        }
    }
}

// Запуск программы
fun main() {
    SimpleTrainPlanner().start()
}