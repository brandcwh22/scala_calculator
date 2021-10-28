package com.calculator.ui

import scalafx.scene.control.TextField
import scalafx.scene.text.Font

object TextFieldComponent {

  def apply(value: String): TextField = {
    val textField: TextField = new TextField {
      style_=(
        """
          |-fx-background-color: #000000;
          |-fx-text-fill: #ffffff;
          |-fx-text-alignment: right;
          |-fx-font-size: 26;
          |""".stripMargin)
    }
    textField.focusTraversable = false
    textField.text.value = value

    textField.prefHeight = 70
    textField.prefWidth = 300

    textField.layoutX = 6
    textField.layoutY = 6

    textField.font = Font.font("Serif", 21)
    textField
  }
}
