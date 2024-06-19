package converter

val distanceUnits = mapOf(
    "m" to listOf("meter", "meters"),
    "km" to listOf("kilometer", "kilometers"),
    "cm" to listOf("centimeter", "centimeters"),
    "mm" to listOf("millimeter", "millimeters"),
    "mi" to listOf("mile", "miles"),
    "yd" to listOf("yard", "yards"),
    "ft" to listOf("foot", "feet"),
    "in" to listOf("inch", "inches")
)

val weightUnits = mapOf(
    "g" to listOf("gram", "grams"),
    "kg" to listOf("kilogram", "kilograms"),
    "mg" to listOf("milligram", "milligrams"),
    "lb" to listOf("pound", "pounds"),
    "oz" to listOf("ounce", "ounces")
)

val temperatureUnits = mapOf(
    "c" to listOf("degree celsius", "degrees celsius", "celsius", "dc"),
    "f" to listOf("degree fahrenheit", "degrees fahrenheit", "fahrenheit", "df"),
    "k" to listOf("kelvin", "kelvins")
)

enum class Unit(val unitMap: Map<String, List<String>>) {
    DISTANCE(distanceUnits), WEIGHT(weightUnits), TEMPERATURE(temperatureUnits);
}

fun main() {
    /*
    println("145 centimeters is 1.45 meters")
    println("2 miles is 3.2187 kilometers")
    println("5.5 inches is 139.7 millimeters")
    println("12 degrees Celsius is 53.6 degrees Fahrenheit")
    println("3 pounds is 1.360776 kilograms")
*/
    print("Enter what you want to convert (or exit): ")
    var str: String
    var arr: List<String>
    var num: Double = -1.00
    var inputUnit: String = ""
    var outputUnit: String = ""

    var tempInputUnit: String
    var tempOutputUnit: String

    var errorExists: Boolean

    var availableDistanceUnits = mutableListOf<String>()
    distanceUnits.values.forEach { a -> run { availableDistanceUnits.add(a[0]); availableDistanceUnits.add(a[1]) } }
    availableDistanceUnits.addAll(distanceUnits.keys)

    var availableWeightUnits = mutableListOf<String>()
    weightUnits.values.forEach { a -> run { availableWeightUnits.add(a[0]); availableWeightUnits.add(a[1]) } }
    availableWeightUnits.addAll(weightUnits.keys)

    var availableTemperatureUnits = mutableListOf<String>()
    temperatureUnits.values.forEach { a ->
        run { a.forEach { a -> run { availableTemperatureUnits.add(a) } } }
    }
    availableTemperatureUnits.addAll(temperatureUnits.keys)

    str = readln()

    while (str.lowercase() != "exit") {
        errorExists = false
        try {
            arr = str.split(" ")
            if (arr.size == 4) {
                num = arr[0].toDouble()
                inputUnit = arr[1]
                outputUnit = arr[3]
            } else if (arr.size == 5) {
                num = arr[0].toDouble()
                if (arr[2].lowercase() == "to" || arr[2].lowercase() == "in") {
                    inputUnit = arr[1]
                    outputUnit = arr[3] + " " + arr[4]
                } else if (arr[3].lowercase() == "to" || arr[3].lowercase() == "in") {
                    inputUnit = arr[1] + " " + arr[2]
                    outputUnit = arr[4]
                } else {
                    errorExists = true
                }

            } else if (arr.size == 6) {
                if (arr[3].lowercase() == "to" || arr[3].lowercase() == "in") {
                    num = arr[0].toDouble()
                    inputUnit = arr[1] + " " + arr[2]
                    outputUnit = arr[4] + " " + arr[5]
                } else {
                    errorExists = true
                }
            } else {
                errorExists = true
            }
        } catch (e: Exception) {
            errorExists = true
        }


        if (errorExists) {
            println("Parse error")
        } else if (!availableDistanceUnits.contains(inputUnit.lowercase()) &&
            !availableWeightUnits.contains(inputUnit.lowercase()) &&
            !availableTemperatureUnits.contains(inputUnit.lowercase()) &&
            !availableDistanceUnits.contains(outputUnit.lowercase()) &&
            !availableWeightUnits.contains(outputUnit.lowercase()) &&
            !availableTemperatureUnits.contains(outputUnit.lowercase())
        ) {
            println("Conversion from ??? to ??? is impossible")
        } else if (!availableDistanceUnits.contains(inputUnit.lowercase()) &&
            !availableWeightUnits.contains(inputUnit.lowercase()) &&
            !availableTemperatureUnits.contains(inputUnit.lowercase())
        ) {
            if (availableDistanceUnits.contains(outputUnit.lowercase())) {
                tempOutputUnit = Unit.DISTANCE.unitMap.get(getUnitKey(Unit.DISTANCE, outputUnit.lowercase()))!![1]
            } else if (availableWeightUnits.contains(outputUnit.lowercase())) {
                tempOutputUnit = Unit.WEIGHT.unitMap.get(getUnitKey(Unit.WEIGHT, outputUnit.lowercase()))!![1]
            } else {
                tempOutputUnit = Unit.TEMPERATURE.unitMap.get(getUnitKey(Unit.TEMPERATURE, outputUnit.lowercase()))!![1]
            }
            println("Conversion from ??? to $tempOutputUnit is impossible")
        } else if (!availableDistanceUnits.contains(outputUnit.lowercase()) &&
            !availableWeightUnits.contains(outputUnit.lowercase()) &&
            !availableTemperatureUnits.contains(outputUnit.lowercase())
        ) {
            if (availableDistanceUnits.contains(inputUnit.lowercase())) {
                tempInputUnit = Unit.DISTANCE.unitMap.get(getUnitKey(Unit.DISTANCE, inputUnit.lowercase()))!![1]
            } else if (availableWeightUnits.contains(inputUnit.lowercase())) {
                tempInputUnit = Unit.WEIGHT.unitMap.get(getUnitKey(Unit.WEIGHT, inputUnit.lowercase()))!![1]
            } else {
                tempInputUnit = Unit.TEMPERATURE.unitMap.get(getUnitKey(Unit.TEMPERATURE, inputUnit.lowercase()))!![1]
            }
            println("Conversion from $tempInputUnit to ??? is impossible")
        } else if ((availableDistanceUnits.contains(inputUnit.lowercase()) &&
                    !availableDistanceUnits.contains(outputUnit.lowercase())) ||
            (availableWeightUnits.contains(inputUnit.lowercase()) &&
                    !availableWeightUnits.contains(outputUnit.lowercase())) ||
            (availableTemperatureUnits.contains(inputUnit.lowercase()) &&
                    !availableTemperatureUnits.contains(outputUnit.lowercase()))
        ) {
            if (availableDistanceUnits.contains(inputUnit.lowercase())) {
                tempInputUnit = Unit.DISTANCE.unitMap.get(getUnitKey(Unit.DISTANCE, inputUnit.lowercase()))!![1]
            } else if (availableWeightUnits.contains(inputUnit.lowercase())) {
                tempInputUnit = Unit.WEIGHT.unitMap.get(getUnitKey(Unit.WEIGHT, inputUnit.lowercase()))!![1]
            } else {
                tempInputUnit = Unit.TEMPERATURE.unitMap.get(getUnitKey(Unit.TEMPERATURE, inputUnit.lowercase()))!![1]
            }

            if (availableDistanceUnits.contains(outputUnit.lowercase())) {
                tempOutputUnit = Unit.DISTANCE.unitMap.get(getUnitKey(Unit.DISTANCE, outputUnit.lowercase()))!![1]
            } else if (availableWeightUnits.contains(outputUnit.lowercase())) {
                tempOutputUnit = Unit.WEIGHT.unitMap.get(getUnitKey(Unit.WEIGHT, outputUnit.lowercase()))!![1]
            } else {
                tempOutputUnit = Unit.TEMPERATURE.unitMap.get(getUnitKey(Unit.TEMPERATURE, outputUnit.lowercase()))!![1]
            }
            println("Conversion from $tempInputUnit to $tempOutputUnit is impossible")
        } else {
            if (availableDistanceUnits.contains(inputUnit.lowercase())) {
                if (num < 0) {
                    println("Length shouldn't be negative")
                } else {
                    convert(Unit.DISTANCE, num, inputUnit.lowercase(), outputUnit.lowercase())
                }
            }
            if (availableWeightUnits.contains(inputUnit.lowercase())) {
                if (num < 0) {
                    println("Weight shouldn't be negative")
                } else {
                    convert(Unit.WEIGHT, num, inputUnit.lowercase(), outputUnit.lowercase())
                }

            }

            if (availableTemperatureUnits.contains(inputUnit.lowercase())) {
                convert(Unit.TEMPERATURE, num, inputUnit.lowercase(), outputUnit.lowercase())
            }

        }
        println()
        print("Enter what you want to convert (or exit): ")

        str = readln()
    }

}

fun convert(unitEnum: Unit, num: Double, inputUnit: String, outputUnit: String) {
    var result: Pair<String, Double> = Pair("", -1.00)
    var resultNum: Double

    val inputUnitString = getUnitString(unitEnum, inputUnit, num)
    if (unitEnum == Unit.DISTANCE) {
        if (inputUnit == "m") {
            result = convertFromMeter(num, outputUnit)
        } else {
            resultNum = convertToMeter(num, inputUnit)
            result = convertFromMeter(resultNum, outputUnit)
        }
    } else if (unitEnum == Unit.WEIGHT) {
        if (inputUnit == "g") {
            result = convertFromGram(num, outputUnit)
        } else {
            resultNum = convertToGram(num, inputUnit)
            result = convertFromGram(resultNum, outputUnit)
        }
    } else if (unitEnum == Unit.TEMPERATURE) {
        if (inputUnit == "c") {
            result = convertFromCelcius(num, outputUnit)
        } else {
            resultNum = convertToCelcius(num, inputUnit)
            result = convertFromCelcius(resultNum, outputUnit)
        }
    }


    val outputUnitString = getUnitString(unitEnum, outputUnit, result.second)

    println("$num $inputUnitString is ${result.second} $outputUnitString")
}

fun getUnitString(unitEnum: Unit, unit: String, num: Double): String {
    val unitStr: String
    if (unitEnum.name != Unit.TEMPERATURE.name && unit.length < 3 || unitEnum.name == Unit.TEMPERATURE.name && unit.length < 2) {
        if (num == 1.0) {
            unitStr = unitEnum.unitMap[unit]!![0]
        } else {
            unitStr = unitEnum.unitMap[unit]!![1]
        }
    } else {
        if (num == 1.0) {
            unitStr = unitEnum.unitMap[getKeyFromValue(unitEnum, unit)]!![0]
        } else {
            unitStr = unitEnum.unitMap[getKeyFromValue(unitEnum, unit)]!![1]
        }
    }

    return unitStr
}

fun getUnitKey(unitEnum: Unit, unit: String): String {
    for (key in unitEnum.unitMap.keys) {
        if (unitEnum.name != Unit.TEMPERATURE.name && unit.length < 3 || unitEnum.name == Unit.TEMPERATURE.name && unit.length < 2) {
            if ((unit.lowercase() == key)) {
                return key
            }
        } else if ((getKeyFromValue(unitEnum, unit.lowercase()) == key)) {
            return key
        }
    }
    return ""
}

fun convertToMeter(num: Double, fromUnit: String): Double {
    val unit = getUnitKey(Unit.DISTANCE, fromUnit)
    val result = when (unit) {
        "km" -> num * 1000.0
        "cm" -> num * 0.01
        "mm" -> num * 0.001
        "mi" -> num * 1609.35
        "yd" -> num * 0.9144
        "ft" -> num * 0.3048
        "in" -> num * 0.0254
        else -> num  //m
    }
    return result
}

fun convertFromMeter(num: Double, toUnit: String): Pair<String, Double> {
    val unit = getUnitKey(Unit.DISTANCE, toUnit)

    val result = when (unit) {
        "km" -> num * 0.001
        "cm" -> num * 100
        "mm" -> num * 1000
        "mi" -> num * (1 / 1609.35)
        "yd" -> num * (1 / 0.9144)
        "ft" -> num * (1 / 0.3048)
        "in" -> num * (1 / 0.0254)
        else -> num  //m
    }


    return unit to result
}

fun convertToGram(num: Double, fromUnit: String): Double {
    val unit = getUnitKey(Unit.WEIGHT, fromUnit)
    val result = when (unit) {
        "kg" -> num * 1000.0
        "mg" -> num * 0.001
        "lb" -> num * 453.592
        "oz" -> num * 28.3495
        else -> num  //g
    }
    return result
}

fun convertFromGram(num: Double, toUnit: String): Pair<String, Double> {
    val unit = getUnitKey(Unit.WEIGHT, toUnit)

    val result = when (unit) {
        "kg" -> num * 0.001
        "mg" -> num * 1000
        "lb" -> num * 1 / 453.592
        "oz" -> num * 1 / 28.3495
        else -> num  //g
    }

    return unit to result
}

fun convertToCelcius(num: Double, fromUnit: String): Double {
    val unit = getUnitKey(Unit.TEMPERATURE, fromUnit)
    val result = when (unit) {
        "f" -> (num - 32) * 5 / 9
        "k" -> num - 273.15
        else -> num  //c
    }
    return result
}

fun convertFromCelcius(num: Double, toUnit: String): Pair<String, Double> {
    val unit = getUnitKey(Unit.TEMPERATURE, toUnit)

    val result = when (unit) {
        "f" -> (num * 9 / 5) + 32
        "k" -> num + 273.15
        else -> num  //c
    }

    return unit to result
}

fun getKeyFromValue(unitEnum: Unit, str: String): String {
    for (key in unitEnum.unitMap.keys) {
        if (unitEnum.unitMap[key]!!.contains(str)) {
            return key
        }
    }
    return ""
}