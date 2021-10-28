package com.calculator

import com.calculator.ui.{ButtonComponent, PaneComponent, TextFieldComponent}
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.{Button}
import scalafx.scene.paint.Color

object Main extends JFXApp {

  val width = 500
  val height = 500
  val red = 0
  val green = 0
  val blue = 0

  stage = new JFXApp.PrimaryStage {
    title.value = "CalcApp"

    resizable = false
    centerOnScreen()

    scene = new Scene {
      fill = Color.rgb(red, green, blue)

      val textField = TextFieldComponent.apply("0")

      val numberColor: String = "fff"
      val buttons = (0 to 9).map(num => ButtonComponent
        .apply(num.toString, numberColor, textField))

      val operationColor: String = "b67890"
      val divideOp: Button = ButtonComponent.apply("/", operationColor, textField)
      val plusOp = ButtonComponent.apply("+", operationColor, textField)
      val minusOp = ButtonComponent.apply("-", operationColor, textField)
      val multiplyOp = ButtonComponent.apply("*", operationColor, textField)
      val startGroupOp = ButtonComponent.apply("(", operationColor, textField)
      val endGroupOp = ButtonComponent.apply(")", operationColor, textField)
      val degreeOp = ButtonComponent.apply("^", operationColor, textField)

      val commandsColor: String = "bfb2a8"

      val floatPointOp = ButtonComponent.apply(".", commandsColor, textField)
      val clearDisplayTextOp = ButtonComponent.apply("C", commandsColor, textField)
      val calculateAns = ButtonComponent.apply("=", commandsColor, textField)

      ButtonComponent.clear(clearDisplayTextOp, textField)
      ButtonComponent.calculate(calculateAns, textField)
      //add buttons to pane and position them in the grid
      //#1 row
      PaneComponent.addRow(List(buttons(7), buttons(8), buttons(9), divideOp, multiplyOp), 0)

      //#2 row
      PaneComponent.addRow(List(buttons(4), buttons(5), buttons(6), plusOp, minusOp), 1)

      //#3 row
      PaneComponent.addRow(List(buttons(1), buttons(2), buttons(3), startGroupOp, endGroupOp), 2)

      //#4 row
      PaneComponent.addRow(List(buttons(0), clearDisplayTextOp, floatPointOp, calculateAns, degreeOp), 3)

      content = List(textField, PaneComponent.paneCal)

    }

  }

}
