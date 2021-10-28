package com.calculator.ui

import scalafx.scene.control.Button
import scalafx.scene.layout.GridPane

object PaneComponent {

  val paneCal = new GridPane
  paneCal.hgap = 5
  paneCal.vgap = 5
  paneCal.layoutY = 80
  paneCal.style = "-fx-background-color: #4c3a61"

  def addRow(buttons: List[Button], rowIndex: Int): Unit = {
    paneCal.add(buttons(0), 1, rowIndex)
    paneCal.add(buttons(1), 2, rowIndex)
    paneCal.add(buttons(2), 3, rowIndex)
    paneCal.add(buttons(3), 4, rowIndex)
    paneCal.add(buttons(4), 5, rowIndex)
  }
}
