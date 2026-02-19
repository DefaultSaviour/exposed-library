package org.example.domain

import org.example.data.database.DatabaseFactory
import org.example.data.repository.BookRepo
import org.example.data.repository.Book_CopiesRepo
import org.example.data.repository.CustomerRepo
import org.example.data.repository.LoanRepo
import org.example.domain.models.Customer



fun main() {
  DatabaseFactory.init()

  val lr = LoanRepo()
  val cr = CustomerRepo()
  val br = BookRepo()
  val bcr = Book_CopiesRepo()

  println("enter first name")
  val firstName= readlnOrNull().toString()
  println("enter last name")
  val  lastName= readlnOrNull().toString()
  println("enter contact no")
  val contactNo = readln().toInt()

  val customer =  Customer(
    firstName = firstName,
    lastName = lastName,
    contactNo = contactNo)

println(customer)

  println(cr.addCustomer(customer))

 // println(lr.getCustomerLoans(CustomerSearch(1)))

//  val loans = lr.getCustomerLoans(CustomerSearch(1))
//
//for (loan in loans) {
//  println(loan.title + " " + loan.dueDate)
//
//}



//println(bookCopyRepo.getAvailableCopies("9781783294403").count())
 // println(bookCopyRepo.getAllCopiesWithBookInfo())

//    val copyRepo = Book_CopiesRepo()
//  println(copyRepo.getAllBook_Copies().first())


//val customerRepo = CustomerRepo()
  //println(customerRepo.getAllCustomers().first().toString())

    //val bookRepo = BookRepo()
    //val books = bookRepo.getAllBooks()

//    for (book in books) {
//        println(
//            """
//
//        ${book.isbn}
//        ${book.title}
//        ${book.author}
//        ${book.genre}
//        ${book.pageCount}
//        ${book.summary}
//        """.trimIndent()
//        )
//    }

    }

