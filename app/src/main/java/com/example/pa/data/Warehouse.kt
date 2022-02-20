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
            reviewList2.add(Review("Ryan Sun Dwai", "Why is this so dry bruh", 2, LocalDate.of(2012, 2, 19)))
            reviewList2.add(Review("Dryan Sun Wai", "My cheeks are dry", 9, LocalDate.of(2032, 2, 19)))
            products.add(Product("Beverage", 1000.00, "Dehydrated water", "Kitchen Gun", "yes", "China", LocalDate.of(3126,8,2), 0.1, 25, imageList2, reviewList2, 45))
            val imageList3 = ArrayList<Int>()
            val reviewList3 = ArrayList<Review>()
            imageList3.add(R.drawable.nuek2)
            reviewList3.add(Review("Cursive Octagon", "Instructions unclear, curtains on fire", 0, LocalDate.of(2001, 12, 1)))
            products.add(Product("Beverage", 20.00, "Spicy water", "Chernobyl", "tastes like radiation", "RUkraine", LocalDate.of(2090, 1, 1), 1.0, 20, imageList3, reviewList3, 14))
            val imageList4 = ArrayList<Int>()
            val reviewList4 = ArrayList<Review>()
            imageList4.add(R.drawable.lemonteapokka)
            reviewList4.add(Review("Dwain Johnathanson", "This tastes like Hydrogen Bomb!", 7, LocalDate.of(1972, 5, 2)))
            products.add(Product("Beverage", 15.00, "Nuke flavored water","Bottling Company", "water", "Ocean", LocalDate.of(9999, 1, 1), 2.0, 2000, imageList4, reviewList4, 31))
            val imageList5 = ArrayList<Int>()
            val reviewList5 = ArrayList<Review>()
            imageList5.add(R.drawable.benjerryicecream1)
            imageList5.add(R.drawable.benjerryicecream2)
            reviewList5.add(Review("The guy from that thing", "Very nice. Melted very smoothly. How to remove from ears?", 8, LocalDate.of(2022, 2, 20)))
            products.add(Product("Food", 6.90, "Ice cream", "Ice", "Is icy and tastes cold", "Russia", LocalDate.of(2024, 7, 9), 3.0, -5, imageList5, reviewList5, 40))
            val imageList6 = ArrayList<Int>()
            val reviewList6 = ArrayList<Review>()
            imageList6.add(R.drawable.applefujipasar1)
            reviewList6.add(Review("Kim Kom", "Appleappleappleappleappleappleapple yummmmmmmmmmmmmmy", 10, LocalDate.of(2002, 4, 11)))
            products.add(Product("Food", 20.00, "Apple", "Orchard", "you are the apple of my eye", "Kingdoms and Castles", LocalDate.of(2021, 10, 9), 13.0, 5, imageList6, reviewList6, 40))
            val imageList7 = ArrayList<Int>()
            val reviewList7 = ArrayList<Review>()
            imageList7.add(R.drawable.chillifinest1)
            imageList7.add(R.drawable.chillifinest2)
            reviewList7.add(Review("Tree Industry", "Hot ;)", 10, LocalDate.of(2022, 2, 20)))
            reviewList7.add(Review("Sacrificial Environmentalist", "Warm :(", 1, LocalDate.of(2022, 2, 20)))
            products.add(Product("Food", 15.30, "Chili", "Plants", "This thing is spicy", "My house", LocalDate.of(1944, 6, 6), 40.0, 25, imageList7, reviewList7, 19))
            val imageList8 = ArrayList<Int>()
            val reviewList8 = ArrayList<Review>()
            imageList8.add(R.drawable.one1)
            imageList8.add(R.drawable.one2)
            imageList8.add(R.drawable.one3)
            imageList8.add(R.drawable.one4)
            imageList8.add(R.drawable.one5)
            reviewList8.add(Review("Nhoj", "WOO L JOHN", 10, LocalDate.of(1022, 8, 29)))
            reviewList8.add(Review("John", "Brazil Russia India China Kill Your Disease", 4, LocalDate.of(2021, 11, 21)))
            products.add(Product("Food", 10.00, "John got hanged", "washington", "F for him", "Great Soviet United North Peru Brazil China East South Utah Ukraine Switseranld Republic", LocalDate.of(240, 6, 4), 4.0, 20, imageList8, reviewList8, 10))
            val imageList9 = ArrayList<Int>()
            val reviewList9 = ArrayList<Review>()
            imageList9.add(R.drawable.instantnoodlemyojo1)
            reviewList9.add(Review("Unidentified French Object", "Les nouilles sont longues et belles et jaunes comme une longue nouille qui est jaune mais pas orange car les nouilles ne sont pas orange et les nouilles sont fraîches", 9, LocalDate.of(1301,3, 6 )))
            products.add(Product("Food", 20.00, "Noodle", "Noodle Dispenser", "Long and eat it", "Mars", LocalDate.of(2060, 2, 5), 25.0, 15, imageList9, reviewList9, 40))
            val imageListA = ArrayList<Int>()
            val reviewListA = ArrayList<Review>()
            imageListA.add(R.drawable.chickendrumsticksadia1)
            reviewListA.add(Review("La Capana Mamamia", "I love to beat chicken. No chicken No dog? Owooowo", 10, LocalDate.of(2021, 11, 11)))
            products.add(Product("Food", 20.00, "Chicken Stick", "Toy Story", "beat your friends with it", "Farming Simulator", LocalDate.of(2022, 2 , 2), 15.0, -10, imageListA, reviewListA, 40))
            val imageListB = ArrayList<Int>()
            val reviewListB = ArrayList<Review>()
            imageListB.add(R.drawable.deadsoldier1)
            imageListB.add(R.drawable.eastberlin1)
            reviewListB.add(Review("RED", "BLUE IS SUS", 9, LocalDate.of(2018, 6, 5)))
            reviewListB.add(Review("BLUE", "RED IS SUS", 8, LocalDate.of(2018, 6, 5)))
            reviewListB.add(Review("PINK", "prengnta samoungus", 9, LocalDate.of(2018, 6, 5)))
            reviewListB.add(Review("Johnny Stepper", "Sussy sussy sussy amogus XD", 6, LocalDate.of(2018, 6, 5)))
            products.add(Product("Food", -100.00, "Soldier Corpse", "Nazi Germany", "This thing is ancient", "Germany", LocalDate.of(1945, 6, 6), 66.0, -1000, imageListB, reviewListB, 1000))
            val imageListC = ArrayList<Int>()
            val reviewListC = ArrayList<Review>()
            imageListC.add(R.drawable.nuke1)
            reviewListC.add(Review("The Sun", "I am a humongous ball of plasma.", 4, LocalDate.of(2025, 2, 14)))
            products.add(Product("Food", 1000.00, "Taste the sun", "Earth", "now you can eat sunlight", "The World", LocalDate.of(2945, 1, 1), 4670.0, 3000, imageListC, reviewListC, 2))
            val imageListD = ArrayList<Int>()
            val reviewListD = ArrayList<Review>()
            imageListD.add(R.drawable.clorox1)
            reviewListD.add(Review("COVID-19", "NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO jk im still gonna kill 6 million people lol", 4, LocalDate.of(2020, 1, 23)))
            products.add(Product("Pharmacy", 1.00, "Covid-19 Vaccine", "China", "the virus cant survive without a host", "China", LocalDate.of(2022, 1 , 1), 1.0, 25, imageListD, reviewListD, 500))
            val imageListE = ArrayList<Int>()
            val reviewListE = ArrayList<Review>()
            imageListE.add(R.drawable.panadolextra1)
            reviewListE.add(Review("Effective Nuclear Charge", "My name is Zeff", 9, LocalDate.of(1911, 7, 7)))
            products.add(Product("Pharmacy", 10.00, "Cocaine", "Anti-Depressant Movement", "makes you high", "Unknown", LocalDate.of(9999, 1, 1), 0.5, -20, imageListE, reviewListE, 90))
            val imageListF = ArrayList<Int>()
            val reviewListF = ArrayList<Review>()
            imageListF.add(R.drawable.instantnoodlenissin1)
            reviewListF.add(Review("Organ Success", "Not instant. Misleading.", 0, LocalDate.of(1981, 10, 30)))
            products.add(Product("Food", 1.80, "Curry Instant Noodle", "Nissin", "Put in your own description", "Malaysia", LocalDate.of(2022, 1, 17), 500.0, 25, imageListF, reviewListF, 96 ))
            val imageListG = ArrayList<Int>()
            val reviewListG = ArrayList<Review>()
            imageListG.add(R.drawable.milo1)
            imageListG.add(R.drawable.milo2)
            reviewListG.add(Review("Demi Lovato", "I thought you were Emil when you were clued to be hidden in me.", 1, LocalDate.of(1992, 8, 20)))
            products.add(Product("Food", 1.0, "Milo", "Milo", "Yummy", "China", LocalDate.of(2022, 1, 17), 0.3, 25, imageListG, reviewListG, 236))
            val imageListH = ArrayList<Int>()
            val reviewListH = ArrayList<Review>()
            imageListH.add(R.drawable.panadolextra1)
            imageListH.add(R.drawable.panadolextra2)
            imageListH.add(R.drawable.panadolextra3)
            reviewListH.add(Review("Peter Panther", "Did not cure my pregnancy.", 0, LocalDate.of(2008, 9, 6)))
            reviewListH.add(Review("Tinker Belgium", "Cured my hepatocellular carcinoma.", 10, LocalDate.of(2008, 9, 7)))
            products.add(Product("Pharmacy", 10.00, "Panadol Extra", "Panadol", "pain", "China", LocalDate.of(2022, 1, 17), 200.0, 25, imageListH, reviewListH, 66))
            val imageListI = ArrayList<Int>()
            val reviewListI = ArrayList<Review>()
            imageListI.add(R.drawable.watermelonpasar1)
            imageListI.add(R.drawable.watermelonpasar2)
            reviewListI.add(Review("Post Melone", "Hahahahaha\n" +
                    "Tank God\n" +
                    "Ayy, ayy\n" +
                    "I've been nice to my family and poppin' pillies\n" +
                    "Man, I feel just like a rockstar (ayy, ayy)\n" +
                    "All my brothers got that gas\n" +
                    "And they always be smokin' like a Rasta\n" +
                    "Playing with me, call up on a Uzi\n" +
                    "And show up, man, them the shottas\n" +
                    "When my homies pull up on your block\n" +
                    "They make that thing go grrra-ta-ta-ta (ta, pow, pow, pow, ayy, ayy)\n" +
                    "Switch my whip, came back in black\n" +
                    "I'm startin' sayin', \"Rest in peace to Bon Scott\" (Scott, ayy)\n" +
                    "Close that door, we blowin' smoke\n" +
                    "She ask me light a fire like I'm Morrison ('son, ayy)\n" +
                    "Act a fool on stage\n" +
                    "Prolly leave my bad show in a cop car (car, ayy)\n" +
                    "Shit was legendary\n" +
                    "Threw a TV out the window of the Montage\n" +
                    "Cocaine on the table, liquor pourin', don't give a damn\n" +
                    "Dude, your girlfriend is a nice person, she just tryna get in\n" +
                    "Sayin', \"I'm with the band\" (ayy, ayy)\n" +
                    "Now she actin' outta pocket, tryna grab up on my pants\n" +
                    "Hundred friends in my trailer say they ain't got a man\n" +
                    "And they all brought a friend (yeah, ayy, ayy, ayy)\n" +
                    "I've been changing clothes and poppin' pillies\n" +
                    "Man, I feel just like a rockstar (ayy, ayy)\n" +
                    "All my brothers got that gas\n" +
                    "And they always be smokin' like a Rasta\n" +
                    "Dancing with me, call up on a Uzi\n" +
                    "And show up, man, them the shottas\n" +
                    "When my homies pull up on your block\n" +
                    "They make that thing go grrra-ta-ta-ta (ta, pow, pow, pow)\n" +
                    "I've been in the Hills stalking superstars\n" +
                    "Feelin' like a popstar (21, 21, 21)\n" +
                    "Drinkin' Henny, bad bitches jumpin' in the pool\n" +
                    "And they ain't got on no bra (bra)\n" +
                    "Pat her from the back, pullin' on her tracks\n" +
                    "And now she screamin' out, \"¡No más!\" (yeah, yeah, yeah)\n" +
                    "They like, \"Savage, why you got a twelve car garage\n" +
                    "And you only got six cars?\" (21)\n" +
                    "I ain't with the cakin', how you kiss that? (kiss that?)\n" +
                    "You say I'm lookin' like a whole snack (big snack)\n" +
                    "Green hundreds in my safe, I got old racks (old racks)\n" +
                    "L.A. residents always askin', \"Where the coke at?\" (21, 21)\n" +
                    "Livin' like a rockstar, smash out on a cop car\n" +
                    "Sweeter than a Pop-Tart, you know you are not hard\n" +
                    "I done made the hot chart, 'member I used to trap hard\n" +
                    "Livin' like a rockstar, I'm livin' like a rockstar (ayy)\n" +
                    "I've been doing CS and poppin' pillies\n" +
                    "Man, I feel just like a rockstar (ayy, ayy)\n" +
                    "All my brothers got that gas\n" +
                    "And they always be smokin' like a Rasta (yeah, yeah, yeah, yeah)\n" +
                    "Pet my dog with me, call up on a Uzi\n" +
                    "And show up, man, them the shottas\n" +
                    "When my homies pull up on your block\n" +
                    "They make that thing go grrra-ta-ta-ta (ta, grrra-ta-ta-ta-ta)\n" +
                    "Star, star, rockstar, rockstar, star\n" +
                    "Rockstar\n" +
                    "Rockstar, feel just like a-\n" +
                    "Rockstar\n" +
                    "Rockstar\n" +
                    "Rockstar\n" +
                    "Feel just like a...", 10, LocalDate.of(1995, 7, 4)))
            products.add(Product("Food", 2.00, "Watermelon", "Dragon", "juicy", "China", LocalDate.of(2021, 12, 9), 9000.0, 12, imageListI, reviewListI, 53))
            val imageListJ = ArrayList<Int>()
            val reviewListJ = ArrayList<Review>()
            imageListJ.add(R.drawable.alicorn1)
            reviewListJ.add(Review("Marquee Zuckerberg", "HOHOOHOOohooooooh", 10, LocalDate.of(2008, 4, 25)))
            products.add(Product("Military", 1000000.00, "Submersible Aviation Cruiser Alicorn", "Union of Yuktobanian Republics", "This boat has the means to end this hideous war, in a definitive and elegant manner. The world shall be horrified by the number of lives we will take. Only then will they let go of their weapons... Weapons that would have taken the lives of ten million.", "Union of Yuktobanian Republics", LocalDate.of(2019, 9, 14), 800000000.0, 26, imageListJ, reviewListJ, 1))
            val imageListK = ArrayList<Int>()
            val reviewListK = ArrayList<Review>()
            reviewListK.add(Review("Glove", "Sharp.", 3, LocalDate.of(2004, 2, 10)))
            imageListK.add(R.drawable.glaive)

            reviewListK.add(Review("Tewtiy", "The range is actually pretty insane. So they throw the super fast but it doesn't look like all of them bounce. That's still so cool though, look at that. I love that.", 9, LocalDate.of(2021, 7, 30)))
            products.add(Product("Military", 400000.00, "Glaive Dominus", "Boomerang Monkey", "The Bloons will look upon my Glaives, and they will know fear.", "New Zealand", LocalDate.of(9999, 1, 1), 578000.0, 57, imageListK, reviewListK, 1))
            val imageListL = ArrayList<Int>()
            val reviewListL = ArrayList<Review>()
            imageListL.add(R.drawable.goldenarmada1)
            imageListL.add(R.drawable.goldenarmada2)
            reviewListL.add(Review("Amon", "The Golden Armada - a shining symbol of arrogance. Let your pride be your downfall.", 8, LocalDate.of(2506, 5, 8)))
            products.add(Product("Military", 14000000000.0, "The Golden Armada", "Daelaam", "Few can withstand the full might of the firstborn. Where the Golden Armada goes, victory follows.", "Shakuras", LocalDate.of(2506, 5, 8), 35300000000.0, 34, imageListL, reviewListL, 5170))
            val imageListM = ArrayList<Int>()
            val reviewListM = ArrayList<Review>()
            imageListM.add(R.drawable.threebb)
            reviewListM.add(Review("Dennis", "Big and green, tastes like spinach", 10, LocalDate.of(2111, 3, 1)))
            products.add(Product("Military", 3000000.0, "Three Big Balls", "THE BIG DAR", "It's like we're playing tennis", "Ballivia", LocalDate.of(2021, 7, 22), 300000000000.0, 16512, imageListM, reviewListM, 1))
            val imageListN = ArrayList<Int>()
            val reviewListN = ArrayList<Review>()
            imageListN.add(R.drawable.epo)
            reviewListN.add(Review("Norman Johnson", "In geometry, the elongated pentagonal orthocupolarotunda is one of the Johnson solids", 9, LocalDate.of(2022, 2, 20)))
            products.add(Product("Tool", 40.0, "Elongated pentagonal orthocupolarotunda", "Canada", "Good tool for Ecoli", "Canadian Republic of Japan", LocalDate.of(1966, 1, 2), 92.0, 20, imageListN, reviewListN, 1))
            val imageListM1 = ArrayList<Int>()
            val reviewListM1 = ArrayList<Review>()
            imageListM1.add(R.drawable.zyklon1)
            reviewListM1.add(Review("Adolf Hitler", "Great poison. Small amounts of it kill people in minutes. Managed to kill 1.1 million people with it.", 8, LocalDate.of(1945, 4, 11)))
            reviewListM1.add(Review("Sami Vincent", "Ok pesticide, but is banned in some countries.", 6, LocalDate.of(2021, 7, 4)))
            products.add(Product("Pharmacy", 7.40, "Zyklon B", "Degussa", "Hydrogen cyanide is a poisonous gas that interferes with cellular respiration. Cyanide prevents the cell from producing adenosine triphosphate (ATP) by binding to one of the proteins involved in the electron transport chain.", "Nazi Germany", LocalDate.of(2389, 4, 24), 5.0, 5, imageListM1, reviewListM1, 24329))
            val imageListN1 = ArrayList<Int>()
            val reviewListN1 = ArrayList<Review>()
            imageListN1.add(R.drawable.vodka1)
            reviewListN1.add(Review("Gerard DuGalle", "Your vodka can wait, my good Alexei.", 7, LocalDate.of(2500, 9, 29)))
            products.add(Product("Beverage", 23.75, "Vodka", "Brrrrr", "mmm alcohol", "Russia", LocalDate.of(2024, 8, 1), 1.3, 14, imageListN1, reviewListN1, 73))
            val imageListO = ArrayList<Int>()
            val reviewListO = ArrayList<Review>()
            imageListO.add(R.drawable.agentorange1)
            reviewListO.add(Review("Vietnam War Veteran", "Why the friendly fire", 4, LocalDate.of(1975, 4, 30)))
            products.add(Product("Tool", 19.30, "Agent Orange", "U.S. Military", "Kills trees. Also gives humans more fingers", "United States of America", LocalDate.of(1961, 11, 28), 4.8, 22, imageListO, reviewListO, 84738))
            val imageListP = ArrayList<Int>()
            val reviewListP = ArrayList<Review>()
            imageListP.add(R.drawable.odin1)
            imageListP.add(R.drawable.odin2)
            reviewListP.add(Review("Arcturus Mengsk", "Some might baulk at the cost of such a magnificent machine, but I say to you - what price do you put on your loved ones' security?", 10, LocalDate.of(2504, 4, 14)))
            reviewListP.add(Review("Matt Horner", "It's an experimental super-heavy siege walker designed for prolonged front line combat. Very powerful.", 9, LocalDate.of(2504, 4, 12)))
            reviewListP.add(Review("Tychus Findlay", "Brother... it is the single greatest creation in the history of mankind. Stridin' around dispensin' righteous fury just about brought a tear to my eye.", 10, LocalDate.of(2504, 4, 15)))
            products.add(Product("Military", 6000000.00, "Odin", "Terran Dominion", "Behold the Dominion's newest weapon of war - the Odin! Human tenacity and ingenuity have come together to forge the final answer to the zerg threat! This mighty steel giant stands as a testimony to the skill and determination of our Dominion engineers and scientists! Implacable. Relentless. Invincible. The Odin is a stirring symbol of the unyielding strength of the Terran Dominion!", "Korhal", LocalDate.of(2505, 10, 8), 239000000.0, 24, imageListP, reviewListP, 1))
            val imageListQ = ArrayList<Int>()
            val reviewListQ = ArrayList<Review>()
            imageListQ.add(R.drawable.flextape1)
            imageListQ.add(R.drawable.flextape2)
            imageListQ.add(R.drawable.flextape3)
            reviewListQ.add(Review("Human", "Once it's on, it's on!", 10, LocalDate.of(2020, 7, 13)))
            products.add(Product("Tool", 8.50, "Flex Tape", "Flex Seal", "Hi, Phil Swift here for Flex Tape. The super-strong, waterproof tape, that can instantly patch, bond, seal and repair. Flex Tape is no ordinary tape. Its triple-thick adhesive virtually welds itself to the surface, instantly stopping the toughest leaks. Leaky pipes can cause major damage, but Flex Tape grips on tight and bonds instantly. Plus, Flex Tape's powerful adhesive is so strong, it even works underwater. Now you can repair leaks in pools and spas without draining them. Flex tape is perfect for marine, campers and RVs. Flex tape is super strong, and once it's on, it holds on tight. And for emergency auto repair, Flex Tape keeps its grip, even in the toughest conditions. Big storms can cause big damage, but Flex Tape comes super-wide, so you can easily patch large holes. To show you the power of Flex Tape, I sawed this boat in half, and repaired it with only Flex Tape. Not only does Flex Tape's powerful adhesive hold the boat together, but it creates a super-strong, watertight seal, so the inside is completely dry.", "United States of America", LocalDate.of(2024, 2, 13), 3.8, 23, imageListQ, reviewListQ, 74))
            val imageListR = ArrayList<Int>()
            val reviewListR = ArrayList<Review>()
            imageListR.add(R.drawable.kitchengun1)
            imageListR.add(R.drawable.kitchengun2)
            reviewListR.add(Review("Romanos McFarlane", "Cool gun, kitchen is now clean. Made some people spew out blood but cleaned that as well.", 8, LocalDate.of(2014, 5, 7)))
            products.add(Product("Tool", 25.80, "Kitchen Gun", "Kitchen Gun", "Hi I'm Derek Baum. Say goodbye to daily stains and dirty surfaces with new Kitchen Gun. This sink is filthy. But just three shots from kitchen gun, and it sparkles like new. Look how it cleans this greasy hob. Goodbye dirt. Grubby tiles. I love you kitchen gun! Rusty taps. You can even use it on the washing up. There. All clean again.", "Kitchen Gun", LocalDate.of(2027, 1, 19), 2.6, 29, imageListR, reviewListR, 49))
            val imageListS = ArrayList<Int>()
            val reviewListS = ArrayList<Review>()
            imageListS.add(R.drawable.ppt1)
            imageListS.add(R.drawable.ppt2)
            reviewListS.add(Review("Nejla Abbatangelo", "I shot a nuclear bomb at my teammate. Cool and fun device.", 10, LocalDate.of(2021, 12, 4)))
            reviewListS.add(Review("Saraswati Vásquez", "My teammate shot a nuclear bomb at me. Why does this exist", 0, LocalDate.of(2021, 12, 4)))
            reviewListS.add(Review("Hülya Rogerson", "Why can't I shoot a nuclear bomb at my enemies? Or a spider? Or the great wall of china? This device is useless and needs to be improved.", 3, LocalDate.of(2021, 11, 12)))
            products.add(Product("Tool", 750.00, "Payload Propulsion Tower", "Amusement Park", "Long-range payload transport structure. Shoots payloads to other linked payload propulsion towers.", "Brazil", LocalDate.of(9999, 1, 1), 1000.0, 4280, imageListS, reviewListS, 299))
            val imageListT = ArrayList<Int>()
            val reviewListT = ArrayList<Review>()
            imageListT.add(R.drawable.laserdrill1)
            imageListT.add(R.drawable.laserdrill2)
            reviewListT.add(Review("Spitter", "This thing just won't die", 2, LocalDate.of(2020, 6, 19)))
            reviewListT.add(Review("Irradiated Human", "Powerful and resilient. Can even place guns on top of it.", 9, LocalDate.of(2021, 1, 23)))
            products.add(Product("Tool", 149600000.00, "Laser Drill", "Korolev Heavy Industries", "When you go down to Fortuna III, your capsule usually scans the surface for easy to reach deposits of Lethium Gas and Veltecite. What you didn’t know is that Korolev’s orbital satellite network does heavy sub-surface scans at the same time...hope you weren’t planning on starting a family with THOSE levels of radiation. Whenever they find something worthwhile they’ll send one of these metal behemoths down to the surface in an automated flight pattern to the points on the map with the most concentrated deposits of Lethium, Veltecite and all the other good stuff.", "Space", LocalDate.of(4300, 2, 9), 9000000.0, 28, imageListT, reviewListT, 6))
            val imageListU = ArrayList<Int>()
            val reviewListU = ArrayList<Review>()
            imageListU.add(R.drawable.powerarmor1)
            imageListU.add(R.drawable.powerarmor2)
            imageListU.add(R.drawable.powerarmor3)
            imageListU.add(R.drawable.powerarmor4)
            reviewListU.add(Review("Marine", "I still die but at least I run fast", 7, LocalDate.of(2400, 7, 3)))
            products.add(Product("Tool", 5700.00, "Power Armor", "Tesla", "Use electricity to assist you", "Earth", LocalDate.of(2805, 9, 10), 60.0, 24, imageListU, reviewListU, 36))
            val imageListV = ArrayList<Int>()
            val reviewListV = ArrayList<Review>()
            imageListV.add(R.drawable.cm800)
            reviewListV.add(Review("Some brain dead guy", "Somehow, this electricity arcs to wooden crates but not to me. Highly impressive engineering.", 9, LocalDate.of(2090, 9, 17)))
            products.add(Product("Military", 7600.00, "CM 800 Jupiter", "Critical Mass", "Not exactly the most subtle sniper rifle, the Jupiter is designed not for stealthy removal of a key target, but the high profile frying of the target and any adjacent bodyguards. True CM showboat style.", "Europe", LocalDate.of(2870, 6, 11), 17.7, 26, imageListV, reviewListV, 26))
            val imageListW = ArrayList<Int>()
            val reviewListW = ArrayList<Review>()
            imageListW.add(R.drawable.projectthor1)
            reviewListW.add(Review("Gravity", "I get to kill even more people now. Fantastic.", 8, LocalDate.of(2005, 2, 25)))
            reviewListW.add(Review("NASA", "Nice to know that rockets are being used productively.", 6, LocalDate.of(2008, 10, 6)))
            products.add(Product("Military", 28000.00, "Project Thor", "U.S. Military", "Specially delivered to your enemies by God themself. Rocket and Satellite not included.", "Vietnam", LocalDate.of(2760, 2, 20), 5500.0, 25, imageListW, reviewListW, 70))

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