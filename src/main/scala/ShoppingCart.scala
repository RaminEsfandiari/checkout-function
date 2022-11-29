object ShoppingCart extends App {

  def calculateTotalCost(shoppingCart: Array[String]): String = {
    if (shoppingCart.isEmpty) {
      "Cart is empty."
    } else {

      val itemsPrices = Map("Apple" -> 0.60, "Orange" -> 0.25)
      var totalCostOfItems = 0.0
      var containsUnknownItem = false
      val unknownItemMessage = " (excluding unknown item(s)). Please check unknown item in cart."

      for(item <- shoppingCart) {
        item match {
          case item if itemsPrices.contains(item) => totalCostOfItems += itemsPrices(item)
          case _ => containsUnknownItem = true
        }
      }

      if (containsUnknownItem)
        formatPrice(totalCostOfItems) + unknownItemMessage
      else
        formatPrice(totalCostOfItems)
    }

  }
  def formatPrice(totalCostOfItems: Double): String = {
    if (totalCostOfItems < 1) f"$totalCostOfItems%1.2fp" else f"£$totalCostOfItems%1.2f"
  }

}