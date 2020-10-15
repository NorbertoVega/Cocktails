package com.example.drinks.data

import com.example.drinks.data.model.Drink
import com.example.drinks.vo.Resource

class DataSource {

    private val drinkList = listOf(
        Drink("https://t1.uc.ltmcdn.com/images/1/3/4/img_como_hacer_un_margarita_20431_600.jpg", "Margarita", "With sugar, vodka and nuts"),
        Drink("https://images.clarin.com/2020/04/07/fernet-con-cola-foto-instagram___N-oobYIWz_340x340__1.jpg", "Fernet", "Fernet with coke and 3 ices"),
        Drink("https://im.vsco.co/aws-us-west-2/325bd9/78936906/5e3f050707c7e429e3000001/vsco5e3f050c9d978.jpg?w=300", "Toro", "Toro with Fanta"),
        Drink("https://d26lpennugtm8s.cloudfront.net/stores/001/170/454/products/ganciasprite-11-39a31e85230089198d15916552714912-640-01-97b7356a812ba0020915994782007585-640-0.jpg", "Gancia", "Gancia with Sprite")
    )

    fun getDrinkList(): Resource<List<Drink>> {
        return Resource.Success(drinkList)
    }
}