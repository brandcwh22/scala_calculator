package com.calculator.domain

import scala.collection.mutable

object Calculator {

  object BinaryOperation {
    val Add = "+"
    val Sub = "-"
    val Mul = "*"
    val Div = "/"
    val Pow = "^"
  }

  object Priority {
    val Opening = "("
    val Closing = ")"
  }

  def stack(operations: mutable.Stack[String], operands: mutable.Stack[Double]): Unit = {
    if (operations.nonEmpty) {
      val next = operations.pop()
      if (!next.equals("(")) {
        next match {
          case BinaryOperation.Add =>
            val op1: Double = operands.pop()
            val op2: Double = operands.pop()

            operands.push(op2 + op1)
          case BinaryOperation.Sub =>
            if (operands.size > 1) {
              val op1: Double = operands.pop()
              val op2: Double = operands.pop()

              operands.push(op2 - op1)
            } else {
              operands.push(-operands.pop())
            }

          case BinaryOperation.Mul =>
            val op1: Double = operands.pop()
            val op2: Double = operands.pop()

            operands.push(op2 * op1)
          case BinaryOperation.Div =>
            val op1: Double = operands.pop()
            val op2: Double = operands.pop()

            operands.push(op2 / op1)
          case BinaryOperation.Pow =>
            val op1: Double = operands.pop()
            val op2: Double = operands.pop()

            operands.push(Math.pow(op2, op1))
        }
        stack(operations, operands)
      } else {
        return
      }
    }
  }

  def performQueuing(expression: String): Array[String] =
    if (expression.nonEmpty) {
      val arr: Array[String] = expression.split(" ")
      arr
    }
    else Array()

  def getPriorities(op: String): Int = {
    op match {
      case BinaryOperation.Add => 1
      case BinaryOperation.Sub => 1
      case BinaryOperation.Mul => 2
      case BinaryOperation.Div => 2
      case BinaryOperation.Pow => 3
      case Priority.Opening => 0
    }
  }

  def MorePriority(oper1: String, oper2: String): Boolean = {
    getPriorities(oper1) < getPriorities(oper2)
  }

  def RightOperation(operation: String): Boolean = {
    operation.equals(BinaryOperation.Add) || operation.equals(BinaryOperation.Sub) ||
      operation.equals(BinaryOperation.Mul) || operation.equals(BinaryOperation.Div) || operation.equals(BinaryOperation.Pow)
  }

  var checker: Boolean = false;

  def iterate(expersion: mutable.Queue[String], operations: mutable.Stack[String], operands: mutable.Stack[Double]): Unit = {
    if (expersion.nonEmpty) {
      val next: String = expersion.front
      if (next.equals("")) {
        iterate(expersion.tail, operations, operands)
      }
      else {
        if (RightOperation(next)) {
          if (operations.nonEmpty && MorePriority(operations.top, next)) {
            operations.push(next)
          } else if (operations.isEmpty) {
            operations.push(next)
          } else {
            stack(operations, operands)
            operations.push(next)
          }
        } else if (next.equals(Priority.Closing)) {
          stack(operations, operands)
        } else if (next.equals(Priority.Opening)) {
          operations.push(next)
        } else {
          operands.push(next.toDouble)
        }
        iterate(expersion.tail, operations, operands)
      }
    }
  }

  def execute(input: String): String = {
    try {
      val queue = new mutable.Queue[String]()
      val operations = new mutable.Stack[String]()
      val operands = new mutable.Stack[Double]()

      queue.addAll(performQueuing(input))
      iterate(queue, operations, operands)
      stack(operations, operands)
      operands.top.toString()
    } catch {
      case e: Exception =>
        Console.err.println("Invalid expression!\n" + "")
        ""
    }
  }
}
