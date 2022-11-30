import org.scalatest.flatspec._
import org.scalatest.matchers.should.Matchers

class ShoppingCartTest extends AnyFlatSpec with Matchers {

  "The checkout system" should "return cart is empty message when no items in cart" in {
    ShoppingCart.calculateTotalCost(Array()) shouldEqual ("Cart is empty.")
  }

  "The checkout system" should "return 60p as the total cost when cart is made up of only 1 apple" in {
    ShoppingCart.calculateTotalCost(Array("Apple")) shouldEqual ("0.60p")
  }

  "The checkout system" should "return 25p when oranges are on buy 2 get 1 free offer and 1 orange is in cart" in {
    ShoppingCart.calculateTotalCost(Array("Orange")) shouldEqual ("0.25p")
  }

  "The checkout system" should "return 50p when oranges are on buy 2 get 1 free offer and 2 oranges are in cart" in {
    ShoppingCart.calculateTotalCost(Array("Orange", "Orange")) shouldEqual ("0.50p")
  }

  "The checkout system" should "return 50p when oranges are on buy 2 get 1 free offer and 3 oranges are in cart" in {
    ShoppingCart.calculateTotalCost(Array("Orange", "Orange", "Orange")) shouldEqual ("0.50p")
  }

  "The checkout system" should "return £1.50 when oranges are on buy 2 get 1 free offer and 9 oranges are in cart" in {
    ShoppingCart.calculateTotalCost(Array("Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange", "Orange")) shouldEqual ("£1.50")
  }

  "The checkout system" should "return £1.20 when apples are on 2 for 1 offer and 3 apples are in cart" in {
    ShoppingCart.calculateTotalCost(Array("Apple", "Apple", "Apple")) shouldEqual ("£1.20")
  }

  "The checkout system" should "return 0.75p when oranges are on buy 2 get 1 free offer and 4 oranges are in cart" in {
    ShoppingCart.calculateTotalCost(Array("Orange", "Orange", "Orange", "Orange")) shouldEqual ("0.75p")
  }

  "The checkout system" should "return £3.15 as the total cost when cart is made up of 3 oranges and 4 apples" in {
    ShoppingCart.calculateTotalCost(Array("Apple", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple")) shouldEqual ("£3.15")
  }

  "The checkout system" should "return total price of £1.70 and alert for unknown item in cart " +
    "when cart is made up of 2 oranges,2 apples and 1 pineapple (invalid item)" in {
    ShoppingCart.calculateTotalCost(Array("Apple", "Orange", "Apple", "Orange", "Pineapple")) shouldEqual (
      "£1.70 (excluding unknown item(s)). Please check unknown item in cart.")
  }

  "The checkout system" should "return total price of 0.85p and alert for unknown item in cart " +
    "when cart is made up of 1 orange, 1 apple and 1 pineapple (invalid item in a different order)" in {
    ShoppingCart.calculateTotalCost(Array("Apple", "Pineapple", "Orange")) shouldEqual (
      "0.85p (excluding unknown item(s)). Please check unknown item in cart.")
  }

}
