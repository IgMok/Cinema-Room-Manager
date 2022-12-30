package cinema

fun main() {
    println("Enter the number of rows:")
    val rows: Int = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats: Int = readln().toInt()
    val numberOfSeats = rows * seats
    var totalIncome = 0
    var selectedRow = 0
    var selectedSeat = 0
    val priceFront = 10
    val priceBack = 8
    var ticketPrice = 0
    var currentIncome = 0
    var purchasedTickets = 0
    val scheme = mutableListOf(
        mutableListOf<String>(" ")
    )
    if (rows > 0 && seats > 0) {
        val row = mutableListOf<String>()
        var countSeat = 1
        repeat(seats) {
            scheme[0].add(countSeat.toString())
            row.add("S")
            countSeat++
        }
        var countRow = 1
        repeat(rows) {
            scheme.add(countRow, mutableListOf(countRow.toString()))
            scheme[countRow].addAll(row)
            countRow++
        }
    }
    while (rows > 0 && seats > 0) {
        if (selectedRow in 1..rows && selectedSeat in 1..seats) {
            scheme[selectedRow.toInt()][selectedSeat.toInt()] = "B"
        }

        println(
            """

            1. Show the seats
            2. Buy a ticket
            3. Statistics
            0. Exit
        """.trimIndent()
        )
        when (readln().toInt()) {
            1 -> {
                println("Cinema:")
                for (i in scheme) {
                    println(i.joinToString(" "))
                }
            }
            2 -> {
                while (true) {
                    println("Enter a row number:")
                    selectedRow = readln().toInt()
                    println("Enter a seat number in that row:")
                    selectedSeat = readln().toInt()
                    try {
                        if (selectedRow in 1..rows && selectedSeat in 1..seats
                            && "B" !in scheme[selectedRow][selectedSeat]) {
                            purchasedTickets++
                            ticketPrice = if (selectedRow > 4) {
                                priceBack
                            } else priceFront
                            println("Ticket price: $$ticketPrice")
                            currentIncome += ticketPrice
                            break
                        } else if ("B" in scheme[selectedRow][selectedSeat]) {
                            println("That ticket has already been purchased!")
                        } else if (selectedRow == 0 || selectedSeat == 0) {
                            println("Wrong input!")
                        }
                    } catch(e: Exception){
                        println("Wrong input!")
                    }
                }
            }
            3 -> {
                var percentage = ((100.0 * purchasedTickets) / numberOfSeats)
                val formatPercentage = "%.2f".format(percentage)
                println("Number of purchased tickets: $purchasedTickets")
                println("Percentage: $formatPercentage%")
                println("Current income: $$currentIncome")
                totalIncome = if (numberOfSeats <= 60) {
                    numberOfSeats * priceFront
                } else if (rows % 2 == 0) {
                    numberOfSeats / 2 * priceFront + numberOfSeats / 2 * priceBack
                } else {
                    (rows / 2 * seats) * priceFront + ((rows - rows / 2) * seats) * priceBack
                }
                println("Total income: $$totalIncome")
            }
            0 -> {
                break
            }
        }
    }
}
