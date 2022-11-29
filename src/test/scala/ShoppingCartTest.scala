import org.scalatest.flatspec._
import org.scalatest.matchers.should.Matchers

class ShoppingCartTest extends AnyFlatSpec with Matchers {

  "The checkout system" should "return cart is empty message when no items in cart" in {
    ShoppingCart.calculateTotalCost(Array()) shouldEqual ("Cart is empty.")
  }

}
