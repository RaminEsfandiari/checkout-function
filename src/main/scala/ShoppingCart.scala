import scala.collection.mutable

object ShoppingCart extends App {

  def calculateTotalCost(shoppingCart: Array[String]): String = {
    if (shoppingCart.isEmpty) {
      "Cart is empty."
    } else {

      val itemsPrices = Map("Apple" -> 0.60, "Orange" -> 0.25)
      var totalCostOfItems = 0.0
      var containsUnknownItem = false
      val unknownItemMessage = " (excluding unknown item(s)). Please check unknown item in cart."
      val countOfSpecificItems = countOfEachItemInCart(shoppingCart)
      val totalCostOfEachItemWithOffersApplied = applyOffersToItems(countOfSpecificItems, itemsPrices)

      totalCostOfItems = totalCostOfEachItemWithOffersApplied.values.sum

      for (item <- shoppingCart) {
        if (!itemsPrices.contains(item)) containsUnknownItem = true
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

  def countOfEachItemInCart(shoppingCart: Array[String]): Map[String, Int] = {
    val itemCount: mutable.Map[String, Int] = mutable.Map()
    for (item <- shoppingCart) {
      val count = shoppingCart.groupBy(identity).mapValues(_.size)(item)
      itemCount += (item -> count)
    }
    itemCount.toMap
  }

  def applyOffersToItems(countOfSpecificItems: Map[String, Int], itemsPrices: Map[String, Double]): Map[String, Double] = {
    countOfSpecificItems.map(item => {
      if (item._2 % 2 == 0) {
        (item._1, (item._2 / 2) * itemsPrices(item._1))
      } else {
        (item._1, (item._2 / 2) * itemsPrices(item._1) + itemsPrices(item._1))
      }
    })
  }

}