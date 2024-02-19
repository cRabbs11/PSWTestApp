package com.example.pswtestapp.utils

import androidx.room.ColumnInfo
import com.example.pswtestapp.data.entity.Product

class RoomData {

    fun getProductList(): List<Product> {
        val product1 = getProduct(
            name = "маргарита",
            description = "Неаполитанская пицца, округлой формы с приподнятым краем, украшенная измельченными вручную очищенными помидорами, моцареллой, листьями свежего базилика и оливковым маслом первого холодного отжима.",
            category = "Пицца",
            image = "https://resizer.avtosushi.ru/resize?quality=90&height=500&width=720&url=https%3A%2F%2Fimages.avtosushi.ru%2Fimages%2Fstorage%2Fsource%2F1%2Fv3l5gSWHr6SKX5Jm2FJe72ozIuD_SlLL.png&sign=NmrZdEFPEqAwtT89I996ONFZh_Rlph13Q1OvuE3ES8I")
        val product2 = getProduct(
            name = "пеперони",
            description = "Острая разновидность пиццы салями итало-американского происхождения, а также название пиццы американского происхождения.",
            category = "Пицца",
            image = "https://static.pizzasushiwok.ru/images/menu_new/6-1300.jpg")
        val product3 = getProduct(
            name = "Унаги",
            description = "Для тех, кто хочет прочувствовать вкус настоящей японской кухни, стоит попробовать суши «Унаги». Они готовятся из качественного риса, который дополняется подкопченным угрем, соусом «Терияки» и кунжутными семечками.",
            category = "Суши",
            image = "https://static.pizzasushiwok.ru/images/menu_new/133-1300.jpg")
        val product4 = getProduct(
            name = "Сяке",
            description = "Суши «Сяке» относятся к классическим вариантам, ведь много столетий назад в Японии консервировали соленую рыбу именно с рисом.",
            category = "Суши",
            image = "https://static.pizzasushiwok.ru/images/menu_new/129-1300.jpg")
        val product5 = getProduct(
            name = "Удон с курицей",
            description = "Для приготовления «Удон с курицей» используется пшеничная лапша, которая известна своими питательными свойствами. Насыщенный вкус блюду придает нежнейшие филе куриной грудки, которое нарезается тонкими полосками.",
            category = "Вок",
            image = "https://static.pizzasushiwok.ru/images/menu_new/517-1300.jpg")
        val product6 = getProduct(
            name = "Тяхан с курицей",
            description = "В составе тяхана с курицей собраны только самые полезные ингредиенты, которые способны подарить к тому же настоящее гастрономическое удовольствие.",
            category = "Вок",
            image = "https://static.pizzasushiwok.ru/images/menu_new/550-1300.jpg")
        val list = arrayListOf<Product>(product1, product2, product3, product4, product5, product6)
        return list
    }

    fun getProduct(
        name: String, description: String, category: String, image: String): Product {
        return Product(
            name = name,
            description = description,
            category = category,
            image = image
        )
    }
}