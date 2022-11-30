import org.scalatest.flatspec._
import org.scalatest.matchers.should.Matchers

class ShoppingCartTest extends AnyFlatSpec with Matchers {

  val apple = "Apple"
  val orange = "Orange"
  val pineapple = "Pineapple"

  "The checkout system" should "return cart is empty message when no items in cart" in {
    ShoppingCart.calculateTotalCost(Array()) shouldEqual ("Cart is empty.")
  }

  "The checkout system" should "return 60p when apples are on buy 1 get 1 free offer and 1 apple in cart" in {
    ShoppingCart.calculateTotalCost(Array(apple)) shouldEqual ("0.60p")
  }

  "The checkout system" should "return 25p when oranges are on buy 2 get 1 free offer and 1 orange is in cart" in {
    ShoppingCart.calculateTotalCost(Array(orange)) shouldEqual ("0.25p")
  }

  "The checkout system" should "return 50p when oranges are on buy 2 get 1 free offer and 2 oranges are in cart" in {
    ShoppingCart.calculateTotalCost(Array.fill(2)(orange)) shouldEqual ("0.50p")
  }

  "The checkout system" should "return 50p when oranges are on buy 2 get 1 free offer and 3 oranges are in cart" in {
    ShoppingCart.calculateTotalCost(Array.fill(3)(orange)) shouldEqual ("0.50p")
  }

  "The checkout system" should "return £1.50 when oranges are on buy 2 get 1 free offer and 9 oranges are in cart" in {
    ShoppingCart.calculateTotalCost(Array.fill(9)(orange)) shouldEqual ("£1.50")
  }

  "The checkout system" should "return £1.20 when apples are on 2 for 1 offer and 3 apples are in cart" in {
    ShoppingCart.calculateTotalCost(Array.fill(3)(apple)) shouldEqual ("£1.20")
  }

  "The checkout system" should "return 0.75p when oranges are on buy 2 get 1 free offer and 4 oranges are in cart" in {
    ShoppingCart.calculateTotalCost(Array.fill(4)(orange)) shouldEqual ("0.75p")
  }

  "The checkout system" should "return £1.70 when on offer. Apples(buy 1 get 1 free), Oranges(3 for 2)" in {
    ShoppingCart.calculateTotalCost(Array(apple, orange, apple, orange, orange, apple, apple)) shouldEqual ("£1.70")
  }

  "The checkout system" should "return £1.10 when on offer. Apples(buy 1 get 1 free), Oranges(3 for 2) " +
    "when cart contains 1 pineapple (invalid item)" in {
    ShoppingCart.calculateTotalCost(Array(apple, orange, apple, orange, pineapple)) shouldEqual (
      "£1.10 (excluding unknown item(s)). Please check unknown item in cart.")
  }

  "The checkout system" should "return 0.85p when on offer. Apples(buy 1 get 1 free), Oranges(3 for 2) " +
    "when cart contains (invalid item in a different order)" in {
    ShoppingCart.calculateTotalCost(Array(apple, pineapple, orange)) shouldEqual (
      "0.85p (excluding unknown item(s)). Please check unknown item in cart.")
  }

}
