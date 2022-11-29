object ShoppingCart extends App {

  def calculateTotalCost(shoppingCart: Array[String]): String = {
    if (shoppingCart.isEmpty) {
      "Cart is empty."
    } else {

      var totalCostMessage = ""

      for(item <- shoppingCart) {
        item match {
          case "Apple" => totalCostMessage = "0.60p"
          case "Orange" => totalCostMessage = "0.25p"
        }
      }

      totalCostMessage
    }

  }

}