package com.calculator.ui

import com.calculator.domain.Calculator
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.shape.Circle

object ButtonComponent {

  private var expression = ""
  val buttonSize: Int = 50

  def apply(value: String, buttonColor: String): Button = {
    val button = new Button(value)
    setStyleToButton(button, buttonColor)
  }

  def apply(value: String, buttonColor: String, textField: TextField): Button = {
    val button = new Button(value)
    setStyleToButton(button, buttonColor)
    addAction(button, textField)
    button
  }

  def addAction(button: Button, textField: TextField): Unit = {
    button.onAction = _ => {
      if (textField.text.value.equals("0")) {
        textField.text.value = ""
      }
      val buttonNumber = button.text.value
      textField.text = textField.text.value + buttonNumber
      val addSpace: Boolean = !buttonNumber.charAt(0).isDigit && !buttonNumber.equals(".")
      if (addSpace) {
        expression = s"$expression $buttonNumber "
      } else {
        expression += buttonNumber
      }
    }
  }

  def calculate(calculateAns: Button, textField: TextField): Unit = {
    calculateAns.onAction = _ => {
      val calculatedValue = Calculator.execute(expression)
      textField.text = calculatedValue
      expression = calculatedValue
    }
  }

  def clear(clearDisplayTextOp: Button, textField: TextField): Unit = {
    clearDisplayTextOp.onAction = _ => {
      textField.text = "0"
      expression = ""
    }
  }


  private def setStyleToButton(button: Button, buttonColor: String): Button = {
    button.style = s"-fx-background-color: #$buttonColor;"
    button.prefWidth = buttonSize
    button.prefHeight = buttonSize

    button
  }

}
