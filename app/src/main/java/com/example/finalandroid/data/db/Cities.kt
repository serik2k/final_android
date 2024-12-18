package com.example.finalandroid.data.db

interface City {
    val name: String
    val latitude: Double
    val longitude: Double
}

class Cities {
    companion object {
        private val cities = mutableListOf<City>(
            object : City {
                override val name = "New York"
                override val latitude = 40.7128
                override val longitude = -74.0060
            },
            object : City {
                override val name = "Los Angeles"
                override val latitude = 34.0522
                override val longitude = -118.2437
            },
            object : City {
                override val name = "Chicago"
                override val latitude = 41.8781
                override val longitude = -87.6298
            },
            object : City {
                override val name = "Houston"
                override val latitude = 29.7604
                override val longitude = -95.3698
            },
            object : City {
                override val name = "Phoenix"
                override val latitude = 33.4484
                override val longitude = -112.0740
            },
            object : City {
                override val name = "Paris"
                override val latitude = 48.8566
                override val longitude = 2.3522
            },
            object : City {
                override val name = "London"
                override val latitude = 51.5074
                override val longitude = -0.1278
            },
            object : City {
                override val name = "Berlin"
                override val latitude = 52.5200
                override val longitude = 13.4050
            },
            object : City {
                override val name = "Tokyo"
                override val latitude = 35.6895
                override val longitude = 139.6917
            },
            object : City {
                override val name = "Sydney"
                override val latitude = -33.8688
                override val longitude = 151.2093
            },
            object : City {
                override val name = "Beijing"
                override val latitude = 39.9042
                override val longitude = 116.4074
            },
            object : City {
                override val name = "Moscow"
                override val latitude = 55.7558
                override val longitude = 37.6173
            },
            object : City {
                override val name = "Rio de Janeiro"
                override val latitude = -22.9068
                override val longitude = -43.1729
            },
            object : City {
                override val name = "Cape Town"
                override val latitude = -33.9249
                override val longitude = 18.4241
            },
            object : City {
                override val name = "Dubai"
                override val latitude = 25.276987
                override val longitude = 55.296249
            },
            object : City {
                override val name = "Mumbai"
                override val latitude = 19.0760
                override val longitude = 72.8777
            },
            object : City {
                override val name = "Singapore"
                override val latitude = 1.3521
                override val longitude = 103.8198
            },
            object : City {
                override val name = "Seoul"
                override val latitude = 37.5665
                override val longitude = 126.9780
            },
            object : City {
                override val name = "Bangkok"
                override val latitude = 13.7563
                override val longitude = 100.5018
            },
            object : City {
                override val name = "Buenos Aires"
                override val latitude = -34.6037
                override val longitude = -58.3816
            }
        )

        fun getCities(): List<City> {
            return cities
        }

        fun addCity(city: City) {
            cities.add(city)
        }

        fun removeCity(city: City) {
            cities.remove(city)
        }

        fun isCityInList(city: City): Boolean {
            return cities.contains(city)
        }

        fun clearCities() {
            cities.clear()
        }

        fun getCity(index: Int): City {
            return cities[index]
        }

        fun getCityIndex(city: City): Int {
            return cities.indexOf(city)
        }
    }
}