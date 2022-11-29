import org.scalatest.flatspec._
import org.scalatest.matchers.should.Matchers

class ShoppingCartTest extends AnyFlatSpec with Matchers {

  "The checkout system" should "return cart is empty message when no items in cart" in {
    ShoppingCart.calculateTotalCost(Array()) shouldEqual ("Cart is empty.")
  }

  "The checkout system" should "return 60p as the total cost when cart is made up of only 1 apple" in {
    ShoppingCart.calculateTotalCost(Array("Apple")) shouldEqual ("0.60p")
  }

  "The checkout system" should "return 25p as the total cost when cart is made up of only 1 orange" in {
    ShoppingCart.calculateTotalCost(Array("Orange")) shouldEqual ("0.25p")
  }

  "The checkout system" should "return £1.80 as the total cost when cart is made up of 3 apples" in {
    ShoppingCart.calculateTotalCost(Array("Apple", "Apple", "Apple")) shouldEqual ("£1.80")
  }

  "The checkout system" should "return £1.00 as the total cost when cart is made up of 4 oranges" in {
    ShoppingCart.calculateTotalCost(Array("Orange", "Orange", "Orange", "Orange")) shouldEqual ("£1.00")
  }

}
