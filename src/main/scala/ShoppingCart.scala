import scala.collection.mutable

object ShoppingCart extends App {

  def calculateTotalCost(shoppingCart: Array[String]): String = {
    parseMessage(shoppingCart, applyOffersToItems(countOfEachItemInCart(shoppingCart)).values.sum)
  }

  def parseMessage(shoppingCart: Array[String], totalCostOfItems: Double): String ={
    if(containUnknownItem(shoppingCart))
      formatPrice(totalCostOfItems) + " (excluding unknown item(s)). Please check unknown item in cart."
    else if (shoppingCart.isEmpty)
      "Cart is empty."
    else
      formatPrice(totalCostOfItems)
  }

  def containUnknownItem(shoppingCart: Array[String]) = {
    var containsUnknownItem = false
    val validItems = Array("Apple","Orange")
    for (item <- shoppingCart) {
      if (!validItems.contains(item))
        containsUnknownItem = true
    }
    containsUnknownItem
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

  def applyOffersToItems(countOfSpecificItems: Map[String, Int]): Map[String, Double] = {

    val itemsPrices = Map("Apple" -> 0.60, "Orange" -> 0.25)

    countOfSpecificItems.map(item => {

      val itemName = item._1
      val itemCount = item._2

      itemName match {
        case "Apple" =>
          if (itemCount % 2 == 0)
            (itemName, (itemCount / 2) * itemsPrices(itemName))
          else
            (itemName, (itemCount / 2) * itemsPrices(itemName) + itemsPrices(itemName))
        case "Orange" =>
          if(itemCount < 3)
            (itemName, itemCount  * itemsPrices(itemName))
          else if (itemCount % 3 == 0)
            (itemName, itemCount * itemsPrices(itemName) - (itemsPrices(itemName) * (itemCount / 3)))
          else
            (itemName, itemCount * itemsPrices(itemName) - itemsPrices(itemName))
        case _ => (itemName, 0.0)
      }

    })
  }

}
