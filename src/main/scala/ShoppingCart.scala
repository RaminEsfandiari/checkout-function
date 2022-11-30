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
      val itemName = item._1
      val itemCount = item._2
      itemName match {
        case "Apple" => {
          if (itemCount % 2 == 0)
            (itemName, (itemCount / 2) * itemsPrices(itemName))
          else
            (itemName, (itemCount / 2) * itemsPrices(itemName) + itemsPrices(itemName))
        }
        case "Orange" => {
          if(itemCount < 3)
            (itemName, itemCount  * itemsPrices(itemName))
          else if (itemCount % 3 == 0)
            (itemName, itemCount * itemsPrices(itemName) - (itemsPrices(itemName) * (itemCount / 3)))
          else
            (itemName, itemCount * itemsPrices(itemName) - itemsPrices(itemName))
        }
        case _ => (itemName, 0.0)
      }

    })
  }

}