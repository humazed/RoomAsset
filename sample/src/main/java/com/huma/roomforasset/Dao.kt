package com.huma.roomforasset

import android.arch.persistence.room.*

/**
 * User: YourPc
 * Date: 7/20/2017
 */
@Dao
interface ChinookDao {

    @get:Query("SELECT * FROM customers")
    val customers: List<Customers>

    @get:Query("SELECT * FROM employees")
    val employees: List<Employees>

}

@Entity(tableName = "employees",
        foreignKeys = arrayOf(ForeignKey(entity = Employees::class,
                parentColumns = arrayOf("EmployeeId"),
                childColumns = arrayOf("ReportsTo"))))
data class Employees(
        @PrimaryKey @ColumnInfo(name = "EmployeeId") var employeeId: Int = 0,
        @ColumnInfo(name = "LastName") var lastName: String = "",
        @ColumnInfo(name = "FirstName") var firstName: String = "",
        @ColumnInfo(name = "Title") var title: String? = "",
        @ColumnInfo(name = "ReportsTo") var reportsTo: String? = "",
        @ColumnInfo(name = "BirthDate") var birthDate: String? = "",
        @ColumnInfo(name = "HireDate") var hireDate: String? = "",
        @ColumnInfo(name = "Address") var address: String? = "",
        @ColumnInfo(name = "City") var city: String? = "",
        @ColumnInfo(name = "State") var state: String? = "",
        @ColumnInfo(name = "Country") var country: String? = "",
        @ColumnInfo(name = "PostalCode") var postalCode: String? = "",
        @ColumnInfo(name = "Phone") var phone: String? = "",
        @ColumnInfo(name = "Fax") var fax: String? = "",
        @ColumnInfo(name = "Email") var email: String? = ""
)


@Entity(tableName = "customers",
        foreignKeys = arrayOf(ForeignKey(entity = Employees::class,
                parentColumns = arrayOf("EmployeeId"),
                childColumns = arrayOf("SupportRepId"))))
data class Customers(
        @PrimaryKey @ColumnInfo(name = "CustomerId") var id: Int = 0,
        @ColumnInfo(name = "FirstName") var FirstName: String = "",
        @ColumnInfo(name = "LastName") var LastName: String = "",
        @ColumnInfo(name = "Company") var Company: String? = "",
        @ColumnInfo(name = "Address") var Address: String? = "",
        @ColumnInfo(name = "City") var City: String? = "",
        @ColumnInfo(name = "State") var State: String? = "",
        @ColumnInfo(name = "Country") var Country: String? = "",
        @ColumnInfo(name = "PostalCode") var PostalCode: String? = "",
        @ColumnInfo(name = "Phone") var Phone: String? = "",
        @ColumnInfo(name = "Fax") var Fax: String? = "",
        @ColumnInfo(name = "Email") var Email: String = "",
        @ColumnInfo(name = "SupportRepId") var SupportRepId: Int? = 0
)