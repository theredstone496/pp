package com.example.pa.data
import com.example.pa.R
import com.example.pa.comparators.ProductCatComparator
import com.example.pa.comparators.ProductNameComparator
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
//class for storing all products and app reviews
class Warehouse {
    companion object {
        //all products
        var products: ArrayList<Product> = ArrayList()
        //all app reviews
        var appRevList: ArrayList<Review> = ArrayList()
        //whether the initialization has happened
        var called: Int = 0
    }
    fun createProducts() {
        if (called == 0) {
            //add all products
            val imageList = ArrayList<Int>()
            val reviewList = ArrayList<Review>()
            imageList.add(R.drawable.meltingface)
            reviewList.add(Review("Human", "Tastes like your mom", 9, LocalDate.of(2022, 2, 19)))
            products.add(Product("Food", 2.00, "Iceceraem", "Snowman", "creamy", "Russia", LocalDate.of(2099, 9, 20), 2.0, 5, imageList, reviewList, 20))
            val imageList2 = ArrayList<Int>()
            val reviewList2 = ArrayList<Review>()
            imageList2.add(R.drawable.water1)
            products.add(Product("Beverage", 1000.00, "Dehydrated water", "Kitchen Gun", "yes", "China", LocalDate.of(3126,8,2), 0.1, 25, imageList2, reviewList2, 45))
            val imageList3 = ArrayList<Int>()
            val reviewList3 = ArrayList<Review>()
            imageList3.add(R.drawable.nuek2)
            products.add(Product("Beverage", 20.00, "Spicy water", "Chernobyl", "tastes like radiation", "RUkraine", LocalDate.of(2090, 1, 1), 1.0, 20, imageList3, reviewList3, 14))
            val imageList4 = ArrayList<Int>()
            val reviewList4 = ArrayList<Review>()
            imageList4.add(R.drawable.lemonteapokka)
            products.add(Product("Beverage", 15.00, "Nuke flavored water","Bottling Company", "water", "Ocean", LocalDate.of(9999, 1, 1), 2.0, 2000, imageList4, reviewList4, 31))
            val imageList5 = ArrayList<Int>()
            val reviewList5 = ArrayList<Review>()
            imageList5.add(R.drawable.benjerryicecream1)
            imageList5.add(R.drawable.benjerryicecream2)
            products.add(Product("Food", 6.90, "Ice cream", "Ice", "Is icy and tastes cold", "Russia", LocalDate.of(2024, 7, 9), 3.0, -5, imageList5, reviewList5, 40))
            val imageList6 = ArrayList<Int>()
            val reviewList6 = ArrayList<Review>()
            imageList6.add(R.drawable.applefujipasar1)
            products.add(Product("Food", 20.00, "Apple", "Orchard", "you are the apple of my eye", "Kingdoms and Castles", LocalDate.of(2021, 10, 9), 13.0, 5, imageList6, reviewList6, 40))
            val imageList7 = ArrayList<Int>()
            val reviewList7 = ArrayList<Review>()
            imageList7.add(R.drawable.chillifinest1)
            imageList7.add(R.drawable.chillifinest2)
            products.add(Product("Food", 15.30, "Chili", "Plants", "This thing is spicy", "My house", LocalDate.of(1944, 6, 6), 40.0, 25, imageList7, reviewList7, 19))
            val imageList8 = ArrayList<Int>()
            val reviewList8 = ArrayList<Review>()
            imageList8.add(R.drawable.one1)
            imageList8.add(R.drawable.one2)
            imageList8.add(R.drawable.one3)
            imageList8.add(R.drawable.one4)
            imageList8.add(R.drawable.one5)
            products.add(Product("Food", 10.00, "John got hanged", "washington", "F for him", "Great Soviet United North Peru Brazil China East South Utah Ukraine Switseranld Republic", LocalDate.of(240, 6, 4), 4.0, 20, imageList8, reviewList8, 10))
            val imageList9 = ArrayList<Int>()
            val reviewList9 = ArrayList<Review>()
            imageList9.add(R.drawable.instantnoodlemyojo1)
            products.add(Product("Food", 20.00, "Noodle", "Noodle Dispenser", "Long and eat it", "Mars", LocalDate.of(2060, 2, 5), 25.0, 15, imageList9, reviewList9, 40))
            val imageListA = ArrayList<Int>()
            val reviewListA = ArrayList<Review>()
            imageListA.add(R.drawable.chickendrumsticksadia1)
            products.add(Product("Food", 20.00, "Chicken Stick", "Toy Story", "beat your friends with it", "Farming Simulator", LocalDate.of(2022, 2 , 2), 15.0, -10, imageListA, reviewListA, 40))
            val imageListB = ArrayList<Int>()
            val reviewListB = ArrayList<Review>()
            imageListB.add(R.drawable.deadsoldier1)
            imageListB.add(R.drawable.eastberlin1)
            products.add(Product("Food", -100.00, "Soldier Corpse", "Nazi Germany", "This thing is ancient", "Germany", LocalDate.of(1945, 6, 6), 66.0, -1000, imageListB, reviewListB, 1000))
            val imageListC = ArrayList<Int>()
            val reviewListC = ArrayList<Review>()
            imageListC.add(R.drawable.nuke1)
            products.add(Product("Food", 1000.00, "Taste the sun", "Earth", "now you can eat sunlight", "The World", LocalDate.of(2945, 1, 1), 4670.0, 3000, imageListC, reviewListC, 2))
            val imageListD = ArrayList<Int>()
            val reviewListD = ArrayList<Review>()
            imageListD.add(R.drawable.clorox1)
            products.add(Product("Pharmacy", 1.00, "Covid-19 Vaccine", "China", "the virus cant survive without a host", "China", LocalDate.of(2022, 1 , 1), 1.0, 25, imageListD, reviewListD, 500))
            val imageListE = ArrayList<Int>()
            val reviewListE = ArrayList<Review>()
            imageListE.add(R.drawable.panadolextra1)
            products.add(Product("Pharmacy", 10.00, "Cocaine", "Anti-Depressant Movement", "makes you high", "Unknown", LocalDate.of(9999, 1, 1), 0.5, -20, imageListE, reviewListE, 90))
            val imageListF = ArrayList<Int>()
            val reviewListF = ArrayList<Review>()
            imageListF.add(R.drawable.instantnoodlenissin1)
            products.add(Product("Food", 1.80, "Curry Instant Noodle", "Nissin", "Put in your own description", "Malaysia", LocalDate.of(2022, 1, 17), 500.0, 25, imageListF, reviewListF, 96 ))
            val imageListG = ArrayList<Int>()
            val reviewListG = ArrayList<Review>()
            imageListG.add(R.drawable.milo1)
            imageListG.add(R.drawable.milo2)
            products.add(Product("Food", 1.0, "Milo", "Milo", "Yummy", "China", LocalDate.of(2022, 1, 17), 0.3, 25, imageListG, reviewListG, 236))
            val imageListH = ArrayList<Int>()
            val reviewListH = ArrayList<Review>()
            imageListH.add(R.drawable.panadolextra1)
            imageListH.add(R.drawable.panadolextra2)
            imageListH.add(R.drawable.panadolextra3)
            products.add(Product("Pharmacy", 10.00, "Panadol Extra", "Panadol", "pain", "China", LocalDate.of(2022, 1, 17), 200.0, 25, imageListH, reviewListH, 66))
            val imageListI = ArrayList<Int>()
            val reviewListI = ArrayList<Review>()
            imageListI.add(R.drawable.watermelonpasar1)
            imageListI.add(R.drawable.watermelonpasar2)
            products.add(Product("Food", 2.00, "Watermelon", "Dragon", "juicy", "China", LocalDate.of(2021, 12, 9), 9000.0, 12, imageListI, reviewListI, 53))
            val imageListJ = ArrayList<Int>()
            val reviewListJ = ArrayList<Review>()
            imageListJ.add(R.drawable.alicorn1)
            products.add(Product("Military", 1000000.00, "Submersible Aviation Cruiser Alicorn", "Union of Yuktobanian Republics", "This boat has the means to end this hideous war, in a definitive and elegant manner. The world shall be horrified by the number of lives we will take. Only then will they let go of their weapons... Weapons that would have taken the lives of ten million.", "Union of Yuktobanian Republics", LocalDate.of(2019, 9, 14), 800000000.0, 26, imageListJ, reviewListJ, 1))
            val imageListK = ArrayList<Int>()
            val reviewListK = ArrayList<Review>()
            imageListK.add(R.drawable.glaive)
            reviewListK.add(Review("Tewtiy", "The range is actually pretty insane. So they throw the super fast but it doesn't look like all of them bounce. That's still so cool though, look at that. I love that.", 9, LocalDate.of(2021, 7, 30)))
            products.add(Product("Military", 400000.00, "Glaive Dominus", "Boomerang Monkey", "The Bloons will look upon my Glaives, and they will know fear.", "New Zealand", LocalDate.of(9999, 1, 1), 578000.0, 57, imageListK, reviewListK, 1))
            val imageListL = ArrayList<Int>()
            val reviewListL = ArrayList<Review>()
            imageListL.add(R.drawable.goldenarmada1)
            imageListL.add(R.drawable.goldenarmada2)
            reviewListL.add(Review("Amon", "The Golden Armada - a shining symbol of arrogance. Let your pride be your downfall.", 8, LocalDate.of(2506, 5, 8)))
            products.add(Product("Military", 14000000000.0, "The Golden Armada", "Daelaam", "Few can withstand the full might of the firstborn. Where the Golden Armada goes, victory follows.", "Shakuras", LocalDate.of(2506, 5, 8), 35300000000.0, 34, imageListL, reviewListL, 5170))

            //initial sorting
            products.sortWith(ProductCatComparator())
            Collections.sort(products, ProductCatComparator())
            //add the app reviews
            appRevList.add(Review("Michelle Obama", "I love spherical balls", 10, LocalDate.of(2022, 2, 16)))
            appRevList.add(Review("Gordon Ramsay", "Where's the lamb sauce?", 1, LocalDate.of(2022, 2, 18)))
            //assigns the variable to 1 so products and reviews are not added twice
            called = 1
        }
    }
}