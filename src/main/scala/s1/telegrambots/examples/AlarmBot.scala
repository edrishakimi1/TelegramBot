package s1.telegrambots.examples

import scala.collection.mutable.Buffer
import com.bot4s.telegram.models.User
import s1.telegrambots.BasicBot

object AppleBot extends App {

  class Phone(nimi: String, val hinta: Int, val muisti: Int, val halkaisija: Double, val kamerat: Int) {

    override def toString = nimi

    def getNimi = nimi

    def getMuisti = muisti

    def getHinta = hinta

    def getHalkaisija = halkaisija

    def getKamerat = kamerat
  }


  val bot = new BasicBot() {

    val iPhone13ProMax = new Phone("iPhone 13 Pro Max", 1600, 1024, 6.7, 3)

    val iPhone11 = new Phone("iPhone 11", 400,128, 6.1, 2)

    var phones = Buffer[Phone](iPhone11, iPhone13ProMax)

    def tervehdi(msg: Message) = {
      "Hei " + getUserFirstName(msg) + ". Olet ostamassa puhelinta? Kerrotko vielä budjetin?"
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
    "Mites muisti?"
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
    "Mites näytön halkaisija?"
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
    "Mites kameroiden määrä?"
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
    "Löytyi! Nyt kirjoita /puhelimet"
    }

    def puhelimet(message: Message) = {
      phones.toString
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

