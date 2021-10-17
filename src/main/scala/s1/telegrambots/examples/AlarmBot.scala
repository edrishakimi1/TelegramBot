package s1.telegrambots.examples

import scala.collection.mutable.Buffer
import com.bot4s.telegram.models.User
import s1.telegrambots.BasicBot

object AppleBot extends App {

  class Phone(nimi: String, val hinta: Int, val muisti: Int, val halkaisija: Double, val kamerat: Int) {

    //override def toString = nimi

    def getNimi = nimi

    def getMuisti = muisti

    def getHinta = hinta

    def getHalkaisija = halkaisija

    def getKamerat = kamerat

  }

  val bot = new BasicBot() {


    var phones = Buffer[Phone]()

    phones += (new Phone("iPhone 13 Pro Max", 1600, 1024, 6.7, 3))

    phones += (new Phone("iPhone 13 Pro", 1200,512, 6.1, 3))

    phones += (new Phone("iPhone 13 Mini", 900,256, 5.4, 2))

    phones += (new Phone("iPhone 13", 1100, 256, 6.1, 3))

    phones += (new Phone("iPhone 12", 600, 128, 6.1, 2))

    phones += (new Phone("iPhone 12 Mini", 600, 256, 5.4, 2))

    phones += (new Phone("iPhone 12 Pro", 800, 512, 6.1, 3))

    phones += (new Phone("iPhone 12 Pro Max", 1200, 1024, 6.7, 3))

    phones += (new Phone("iPhone 11", 400,128, 6.1, 2))

    phones += (new Phone("iPhone 11 Pro", 600, 128, 5.8, 3))

    phones += (new Phone("iPhone 11 Pro Max", 900, 256, 6.4, 3))

    def tervehdi(msg: Message) = {
      "Hei " + getUserFirstName(msg) + "! Tervetuloa Applen puhelinkauppaan. Mikä on budjettisi puhelinta varten? (Vastaa käskyllä /budjetti)"
    }

    def budjetti(message: Message): String = {

      val summa = util.Try(getString(message).toInt).toOption

      summa match {
        case None => println("Sinun täytyy antaa numeroita!")
        case Some(luku) => {
          for (puhelin <- phones) {
            if (puhelin.getHinta > luku) {
              phones -= puhelin
            }
          }
        }
      }
    "Paljonko muistia haluat puhelimella olevan? 128/256/512/1024 (Vastaa käskyllä /muisti)"
    }

    def muisti(message: Message) = {

      val summa = util.Try(getString(message).toInt).toOption

      summa match {
        case None => println("Sinun täytyy antaa numeroita!")
        case Some(luku) => {
          for (puhelin <- phones) {
            if (puhelin.getMuisti != luku) {
              phones -= puhelin
            }
          }
        }
      }
    "Minkä kokoisen näytön haluat? 5.4/5.8/6.1/6.4/6.7 (Vastaa käskyllä /halkaisija)"
    }


    def halkaisija(message: Message) = {

      val summa = util.Try(getString(message).toDouble).toOption

      summa match {
        case None => println("Sinun täytyy antaa numeroita!")
        case Some(luku) => {
          for (puhelin <- phones) {
            if (puhelin.getHalkaisija != luku) {
              phones -= puhelin
            }
          }
        }
      }
    "Montako kameraa haluat? 1/2/3 (Vastaa käskyllä /kamera)"
    }


    def kamerat(message: Message) = {

      val summa = util.Try(getString(message).toInt).toOption

      summa match {
        case None => println("Sinun täytyy antaa numeroita!")
        case Some(luku) => {
          for (puhelin <- phones) {
            if (puhelin.getKamerat != luku) {
              phones -= puhelin
            }
          }
        }
      }
    "Löytyi! Käskyllä /puhelimet saat hakukriteereihisi sopivat puhelimet esille."
    }

    def puhelimet(message: Message) = {
      var tulos: String = "Hakukriteereihisi sopivat puhelimet ovat:\n"
      for (puhelin <- phones) {
        tulos += s"${puhelin.getNimi}\n"
      }
      tulos
    }


    this.command("terve", tervehdi)
    this.command("budjetti", budjetti)
    this.command("muisti", muisti)
    this.command("halkaisija", halkaisija)
    this.command("kamerat", kamerat)
    this.command("puhelimet", puhelimet)


    // Lopuksi Botti pitää vielä saada käyntiin
    this.run()

    println("Started")
  }
 }

