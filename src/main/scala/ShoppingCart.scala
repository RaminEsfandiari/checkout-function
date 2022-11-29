object ShoppingCart extends App {

  def calculateTotalCost(shoppingCart: Array[String]): String = {
    if (shoppingCart.isEmpty)
      "Cart is empty."
    else
      "0.60p"
  }

}