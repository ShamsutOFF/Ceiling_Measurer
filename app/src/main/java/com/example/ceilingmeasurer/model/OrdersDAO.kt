package com.example.ceilingmeasurer.model

import androidx.room.*
import com.example.ceilingmeasurer.model.Tables.OrderEntity

@Dao
interface OrdersDAO {

    //Получить список заказов по дате замеров от нового к старому
    @Query ("SELECT * FROM OrderEntity ORDER BY date_timestamp DESC" )
    fun all(): List<OrderEntity>

    //Получить список заказов по статусу работы
    @Query ("SELECT * FROM OrderEntity WHERE (status = :by_status) ORDER BY status DESC" )
    fun getOrdersStatusWorked(by_status: String): List<OrderEntity>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: OrderEntity)

    @Update
    fun update(entity: OrderEntity)

    @Delete
    fun delete(entity: OrderEntity)
}