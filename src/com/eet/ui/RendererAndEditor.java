package com.eet.ui;

import com.eet.controllers.EventController;
import com.eet.memory.ActiveUser;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class RendererAndEditor implements TableCellRenderer, TableCellEditor {

    private JButton button;
    private int row;
    private EventController eventController;

    public RendererAndEditor(JTable table) {
        eventController = new EventController();
        button = new JButton("Cancel");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                int eventId = (int) tableModel.getValueAt(row, 8);
                tableModel.removeRow(row);
                eventController.deleteBooking(eventId, ActiveUser.getUser().getId());
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        return button;
    }

    @Override
    public java.awt.Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                                                          int column) {
        this.row = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
      return null;
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
      return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
      return true;
    }

    @Override
    public boolean stopCellEditing() {
      return true;
    }

    @Override
    public void cancelCellEditing() { }

    @Override
    public void addCellEditorListener(CellEditorListener l) { }

    @Override
    public void removeCellEditorListener(CellEditorListener l) { }
}